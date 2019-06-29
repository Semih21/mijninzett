package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.user.UserSingleton;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.SubjectPreferenceRepository;
import com.mijninzet.projectteamdrie.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SubjectPreferencesController {
    private final SubjectRepository subjectRepository;
    private final SubjectPreferenceRepository subjectPreferenceRepository;

    public SubjectPreferencesController(SubjectPreferenceRepository subjectPreferenceRepository, SubjectRepository subjectRepository) {
        this.subjectPreferenceRepository = subjectPreferenceRepository;
        this.subjectRepository = subjectRepository;
    }

    @RequestMapping(value = "/showSubjects")
    public String getSubjectList(Model model) {
        Map<Subject, Integer> subjectAndPreferenceMap = new LinkedHashMap<>();
        List<Subject> subjectList = subjectRepository.findAll();
        Collections.sort(subjectList);
        int userId = UserSingleton.getInstance().getId();
        for (Subject subject : subjectList) {
            int subjectId = subject.getSubjectId();
            Integer preference = subjectPreferenceRepository.getPreferenceBySubjectIdAndUserId(subjectId, userId);
            preference = preference != null ? preference : 0;
            subjectAndPreferenceMap.put(subject, preference);
        }
        model.addAttribute("showSubjectsWithPreference", subjectAndPreferenceMap);
        return "teacherSubjectPreferences";
    }

    @RequestMapping(value = "/submitPreferences", method = RequestMethod.POST)
    public String submitPreferences(HttpServletRequest request, Model model) {
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String subjectName = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(subjectName);
            int subjectPreference;
            subjectPreference = Integer.parseInt(paramValues[0]);
            int subjectID = subjectRepository.getSubjectIdByName(subjectName);
            int userId = UserSingleton.getInstance().getId();
            if (subjectPreferenceRepository.getPreferenceBySubjectIdAndUserId(subjectID, userId) == null)
                subjectPreferenceRepository.insertPreference(subjectPreference, subjectID, userId);
            else subjectPreferenceRepository.updatePreference(subjectPreference, subjectID, userId);
        }
        return getSubjectList(model);
    }
}