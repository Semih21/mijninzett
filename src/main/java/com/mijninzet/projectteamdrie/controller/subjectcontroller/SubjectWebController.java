package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import com.mijninzet.projectteamdrie.model.comparator.SubjectNameComparator;
import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.service.CohortService;
import com.mijninzet.projectteamdrie.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class SubjectWebController {
    private final SubjectService subjectService;
    private final CohortService cohortService;
    private final CohortScheduleRepository cohortScheduleRepository;
    private List<Subject> subjectList;

    public SubjectWebController(SubjectService subjectService, CohortService cohortService, CohortScheduleRepository cohortScheduleRepository) {
        this.subjectService = subjectService;
        this.cohortService = cohortService;
        this.cohortScheduleRepository = cohortScheduleRepository;
    }

    @GetMapping("/")
    public String subjectHomePage() {
        return "subject/subject-home";
    }

    @RequestMapping("/subject/list")
    public String listSubject(Model model) {
        subjectList = subjectService.findAll();
        subjectList.sort(new SubjectNameComparator());
        List<Cohort> cohortList = cohortService.findAll();
        model.addAttribute("subjects", subjectList);
        model.addAttribute("AAA", cohortList);
        return "subject/list-subject";
    }

    @GetMapping("/subject/addSubject")
    public String addSubject(Model model) {
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "subject/subject-form";
    }

    @PostMapping("/subject/save")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectService.addSubject(subject);
        return ("redirect:/subject/list");
    }

    @GetMapping("/subject/updateSubject")
    public String updateSubject(@RequestParam("subjectId") int subjectId, Model model) {
        Subject subject = subjectService.findById(subjectId);
        model.addAttribute("subject", subject);
        return "subject/subject-form";
    }

    @GetMapping("subject/deleteSubject")
    public String deleteSubject(@RequestParam("subjectId") int subjectId) {
        cohortScheduleRepository.nullSubjectId(subjectId);
        subjectService.deleteSubjectById(subjectId);
        return ("redirect:/subject/list");
    }

    @GetMapping("/subject/subjectCohortKopelen/{cohortId}")
    public String getAllCohortSchedule(Model model, @PathVariable Integer cohortId) {
        subjectList = subjectService.findAll();
        List<CohortSchedule> cohortSchedules = cohortScheduleRepository.getAllByCohort_CohortId(cohortId);
        model.addAttribute("cohortsSchedules", cohortSchedules);
        model.addAttribute("subjects", subjectList);
        return ("list-cohortSchedule");
    }
}
