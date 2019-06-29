package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.ConfirmationToken;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.model.entity.user.Role;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.ConfirmationTokenRepository;
import com.mijninzet.projectteamdrie.repository.RoleRepository;
import com.mijninzet.projectteamdrie.repository.TeacherHoursRepository;
import com.mijninzet.projectteamdrie.service.EmailSenderService;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthenticationController {
    private static final int TOTAL_HOURS = 1650;
    private static final int EDUCATIONAL_PERCENTAGE = 80;
    private static final int DEFAULT_HOURS_USED = 0;
    private static final int DEFAULT_FTE = 1;

    private final UserService userService;
    private final TeacherHoursRepository teacherHoursRepo;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    // https://stackabuse.com/password-encoding-with-spring-security/
    // to encode our password
    private final BCryptPasswordEncoder encoder;

    public AuthenticationController(UserService userService, TeacherHoursRepository teacherHoursRepo, RoleRepository roleRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.teacherHoursRepo = teacherHoursRepo;
        this.roleRepository = roleRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
        this.encoder = encoder;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> rolelist = roleRepository.findAll();
        modelAndView.addObject("roles", rolelist);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("newUser"); // resources/template/newUser.html
        System.out.println("! ViewName from ModelandView from registermethod: " + modelAndView.getViewName());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (userService.isUserAlreadyPresent(user))
            modelAndView.addObject("successMessage", "user already exists!");
            // we will save the user if, no binding errors
        else {
            userService.saveUser(user);
//            userServiceImp.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully!");
            int userId = user.getId();
            int totalHours = DEFAULT_FTE * TOTAL_HOURS * EDUCATIONAL_PERCENTAGE / 100;
            TeacherHours newTeacherHours = new TeacherHours(userId, totalHours, DEFAULT_HOURS_USED, totalHours);
            teacherHoursRepo.save(newTeacherHours);
            System.out.println("TEACHERHOURS FILL METHOD IS DONE");
            System.out.println("uderID new user = " + userId);
        }
        List<Role> rolelist = roleRepository.findAll();
        modelAndView.addObject("roles", rolelist);
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("newUser");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("helloTeacher");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newUser"); // resources/template/admin.html
        return modelAndView;
    }

    // Confirm registration
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.saveUser(user);
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error1");
        }
        return modelAndView;
    }

    /**
     * Display the forgot password page and form
     */
    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    public ModelAndView displayResetPassword(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    /**
     * Receive email of the user, create token and send it via email to the user
     */
    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public ModelAndView forgotUserPassword(ModelAndView modelAndView, User user) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            // create token
            ConfirmationToken confirmationToken = new ConfirmationToken(existingUser);
            // save it
            confirmationTokenRepository.save(confirmationToken);
            // create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("nopressure532@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8081/confirm-reset?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
            modelAndView.addObject("message", "Request to reset password received. Check your inbox for the reset link.");
            modelAndView.setViewName("successForgotPassword");
        } else {
            modelAndView.addObject("message", "This email does not exist!");
            modelAndView.setViewName("error1");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null) {
            User user = userService.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userService.saveUser(user);
            modelAndView.addObject("user", user);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error1");
        }

        return modelAndView;
    }

    /**
     * Receive the token from the link sent via email and display form to reset password
     */
    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public ModelAndView resetUserPassword(ModelAndView modelAndView, User user) {
        // ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if (user.getEmail() != null) {
            // use email to find user
            User tokenUser = userService.findByEmail(user.getEmail());
            tokenUser.setEnabled(true);
            tokenUser.setPassword(encoder.encode(user.getPassword()));
            // System.out.println(tokenUser.getPassword());
            userService.updateUser(tokenUser);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error1");
        }
        return modelAndView;
    }
}