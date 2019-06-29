package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.model.entity.user.User;
import com.mijninzet.projectteamdrie.repository.*;
import com.mijninzet.projectteamdrie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherHoursController {

    private final TeacherHoursRepository teacherHoursRepository;
    private final UserService userService;
    private final CohortScheduleRepository cohortScheduleRepository;
    private final CohortRepository cohortRepository;
    private final SubjectRepository subjectRepository;

    public TeacherHoursController(TeacherHoursRepository teacherHoursRepository,
                                  UserService userService, CohortScheduleRepository cohortScheduleRepository,
                                  CohortRepository cohortRepository, SubjectRepository subjectRepository) {
        this.teacherHoursRepository = teacherHoursRepository;
        this.userService = userService;
        this.cohortScheduleRepository = cohortScheduleRepository;
        this.cohortRepository = cohortRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("teacherFTE")
    public String getTeacherFte(User user, Model model) {
        User currentUser = userService.findById(User.getCurrentUserId());
        // double availableHours = userServiceImp.calculateTotalAvailableHours(currentUser.getId());
        double userFTE = user.getFte();
        int availableHours = teacherHoursRepository.getHoursLeft(User.getCurrentUserId());
        List<Cohort> cohorts = cohortRepository.findAllByCohortIdAfter(0);
        List<Integer> weeknrs = cohortScheduleRepository.getDistinctWeeknumbers();
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("voornaam", currentUser.getName());
        model.addAttribute("achternaam", currentUser.getLastName());
        model.addAttribute("email", currentUser.getEmail());
        model.addAttribute("fte", userFTE);
        model.addAttribute("beschikbareUren", availableHours);
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("weeknrs", weeknrs);
        model.addAttribute("vakkenSelectAjax", subjects);
        return "teacherFTE";
    }

    @RequestMapping(value = "teacherSchedule/{cohortId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Integer> getTeacherSchedule(@PathVariable Integer cohortId, User user, Model model) {
        getTeacherFte(user, model);
        System.out.println("teacherschedule/{cohortid} is aangeroepen");
        List<Integer> distinctWeeknrsFromCohort = cohortScheduleRepository.getDistinctWeeknumbersWhereCohortIdIs(cohortId);
//        model.addAttribute("weken", distinctWeeknrsFromCohort);
        System.out.println("Uit methode komt " + distinctWeeknrsFromCohort);
        return distinctWeeknrsFromCohort;
    }

    @RequestMapping(value = "teacherSchedule/week/{weeknr}/{cohortId}", method = RequestMethod.GET)
    public @ResponseBody
    List<CohortSchedule> getTeacherWeekSchedule(@PathVariable int weeknr, @PathVariable int cohortId, User user, Model model) {
        getTeacherFte(user, model);
        System.out.println("Uit mijn requestparam komt : " + weeknr + " en cohortId " + cohortId);
        List<CohortSchedule> cohortScheduleWeek = cohortScheduleRepository.getAllByCohort_CohortIdAndWeeknr(cohortId, weeknr);
        System.out.println("uit mijn cohortscheduleweek komt " + cohortScheduleWeek);

//        model.addAttribute("week",cohortScheduleWeek);

        return cohortScheduleWeek;
    }

    @RequestMapping(value = "/subject/{subjectId}")
    public @ResponseBody
    Subject getSubject(@PathVariable Integer subjectId) {
        System.out.println("MIJN ajax methode is aangeroepen");
        Subject subject = subjectRepository.findSubjectBySubjectId(subjectId);
        System.out.println("IN SUBJECT ZIT  " + subject);
        return subject;

    }
}
