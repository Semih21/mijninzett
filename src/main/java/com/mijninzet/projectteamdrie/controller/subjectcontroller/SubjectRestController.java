package com.mijninzet.projectteamdrie.controller.subjectcontroller;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Subject;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import com.mijninzet.projectteamdrie.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vak")
public class SubjectRestController {
    private final SubjectService subjectService;
    private final CohortScheduleRepository cohortRepository;

    public SubjectRestController(SubjectService subjectService, CohortScheduleRepository cohortRepository) {
        this.subjectService = subjectService;
        this.cohortRepository = cohortRepository;
    }

    @RequestMapping("/lijst")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @PostMapping("/addSubject")
    public Subject addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
        return subject;
    }

    @DeleteMapping("/deleteSubject/{id}")
    public void deleteSubject(@PathVariable int id) {
        subjectService.deleteSubjectById(id);
    }

    @RequestMapping("/subjectCohortKopelen/{cohortId}")
    public List<CohortSchedule> findAllByCohortId(@PathVariable int cohortId) {
        List<CohortSchedule> cohortSchedules;
        cohortSchedules = cohortRepository.getAllByCohort_CohortId(cohortId);
        return cohortSchedules;
    }
}
