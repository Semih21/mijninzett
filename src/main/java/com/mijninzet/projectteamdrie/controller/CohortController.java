package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.Cohort;
import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.repository.CohortRepository;
import com.mijninzet.projectteamdrie.repository.CohortScheduleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping(value = "/cohort")

public class CohortController {
    private final CohortRepository cohortRepository;
    private final CohortScheduleRepository cohortSchedlRepo;

    public CohortController(CohortRepository cohortRepository, CohortScheduleRepository cohortSchedlRepo) {
        this.cohortRepository = cohortRepository;
        this.cohortSchedlRepo = cohortSchedlRepo;
    }

    @RequestMapping(value = "/createCohort", method = RequestMethod.GET)
    public String getAllCohorts(Model model) {
        List<Cohort> cohorts = cohortRepository.findAll();
        Collections.sort(cohorts);
        Cohort cohort = new Cohort();
        model.addAttribute("allCohorts", cohorts);
        model.addAttribute("cohort", cohort);
        model.addAttribute("cohortId", cohort.getCohortId());
        model.addAttribute("beginDate", cohort.getBeginDate());
        model.addAttribute("endDate", cohort.getEndDate());
        return "createCohort";
    }

    @RequestMapping(value = "/createCohort/new/", method = RequestMethod.POST)
    public String createCohort(@ModelAttribute("cohort") Cohort cohort) {
        boolean exists = cohortRepository.existsById(cohort.getCohortId());
        if (exists) return "createCohortError";
        cohort.setBeginDate(cohort.getBeginDate());
        cohort.setEndDate(cohort.getEndDate());
        cohortRepository.save(cohort);
        // maak nieuwe CohortSchedule (default rooster) voor de nieuwe Cohort
        System.out.println("De begindateum= " + cohort.getBeginDate());
        System.out.println("De begindateum= " + cohort.getEndDate());
        System.out.println("De cohort= " + cohort.getCohortId());
        System.out.println("De begindateum in de makeSchedule =  " + cohort.getBeginDate());
        System.out.println("De begindateum in de makeSchedule =  " + cohort.getEndDate());
        makeDefaultCohortSchedule(cohort.getBeginDate(), cohort.getEndDate(), cohort.getCohortId());
        return ("redirect:/cohort/createCohort");
    }

    //hier cohortSchedule (default rooster) aangemaakt voor de nieuwe Cohort
    private void makeDefaultCohortSchedule(LocalDate beginDate, LocalDate endDate, int cohortId) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        for (LocalDate date = beginDate; date.isBefore(endDate); date = date.plusDays(1)) {
            CohortSchedule CsMorning = new CohortSchedule();
            CohortSchedule CsNoon = new CohortSchedule();
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            String dayOfWeekName = dayOfWeek.name();
            switch (dayOfWeekName) {
                case "MONDAY":
                    CsMorning.setDay("maandag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("maandag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "TUESDAY":
                    CsMorning.setDay("dinsdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("dinsdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "WEDNESDAY":
                    CsMorning.setDay("woensdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("woensdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "THURSDAY":
                    CsMorning.setDay("donderdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("donderdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
                case "FRIDAY":
                    CsMorning.setDay("vrijdag");
                    CsMorning.setDaypart("ochtend");
                    CsMorning.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsMorning.setDate(date);
                    CsMorning.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsMorning);
                    CsNoon.setDay("vrijdag");
                    CsNoon.setDaypart("middag");
                    CsNoon.setCohort(cohortRepository.getByCohortId(cohortId));
                    CsNoon.setDate(date);
                    CsNoon.setWeeknr(date.get(weekFields.weekOfWeekBasedYear()));
                    cohortSchedlRepo.save(CsNoon);
                    break;
            }
        }

    }

    @PostMapping("/save")
    public String saveCohort(@ModelAttribute("cohort") Cohort cohort) {
        cohortRepository.save(cohort);
        return ("redirect:/cohort/createCohort");
    }

    @GetMapping("/deleteCohort")
    public String deleteSubject(@RequestParam("cohortId") int cohortId) {
        cohortRepository.deleteById(cohortId);
        return ("redirect:/cohort/createCohort");
    }

    @GetMapping("/updateCohort")
    public String updateCohort(@RequestParam("cohortId") int cohortId, Model model) {
        Cohort cohort = cohortRepository.getByCohortId(cohortId);
        model.addAttribute("cohort", cohort);
        return "cohortForm";
    }
}
