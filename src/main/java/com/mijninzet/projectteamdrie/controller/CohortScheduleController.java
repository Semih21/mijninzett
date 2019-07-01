package com.mijninzet.projectteamdrie.controller;

import com.mijninzet.projectteamdrie.model.entity.CohortSchedule;
import com.mijninzet.projectteamdrie.model.entity.Exception;
import com.mijninzet.projectteamdrie.model.entity.TeacherHours;
import com.mijninzet.projectteamdrie.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class CohortScheduleController {
    @Autowired
    CohortScheduleRepository cohortScheduleRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SubjectRepository subjectRepo;
    @Autowired
    TeacherHoursRepository teacherHoursRepository;
    @Autowired
    StaffAvailibilityRepository staffAvailrepo;
    @Autowired
    ExceptionRepository exceptionRepo;

    private LocalDate getDateFromStringArray(String[] arrOfDate) {
        int year = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int day = Integer.parseInt(arrOfDate[2]);
        return LocalDate.of(year, month, day);
    }

    private String totalCheck(int cohortId, int teacherId, String dayPart, String weekDay, int subjectId, int weekNumber) {
        String result = "default";
        String avail = checkGeneralAvail(teacherId, weekDay, dayPart, cohortId);
        String overlap = checkCohortOverlap(cohortId, teacherId, weekNumber, dayPart, weekDay);
        String incident = checkTeacherException(teacherId, weekNumber, weekDay);
        String dayAvail;
        String hours;

        String subjectPref = checkSubjectPreference(teacherId, subjectId);
        if (subjectPref.equals("NON")) subjectPref = "OK";
        String checkHours = checkTeacherHours(teacherId, subjectId, cohortId);
        if (checkHours.equals("OK") || checkHours.equals("NON")) hours = "OK";
        else hours = "NOK";
        //check if availability is OK or NOK
        if ((avail.equals("NOK") && incident.equals("OK") && overlap.equals("OK")) ||
                (avail.equals("OK") && ((incident.equals("OK") ||
                        incident.equals("NON")) && overlap.equals("OK")))) dayAvail = "OK";
        else dayAvail = "NOK";
        String total = hours + "-" + dayAvail + "-" + subjectPref;
        switch (total) {
            case "OK-OK-OK":
                result = "OK";
                break;
            case "NOK-NOK-NOK":
                result = "NOK";
                break;
            case "NOK-OK-OK":
                result = "hourNOK_restOK";
                break;
            case "OK-NOK-OK":
                result = "availNOK_restOK";
                break;
            case "OK-OK-NOK":
                result = "subjectNOK_restOK";
                break;
            case "NOK-NOK-OK":
                result = "hoursNOK_availNOK_OK";
                break;
            case "OK-NOK-NOK":
                result = "OK_availNOK_prefNOK";
                break;
            case "NOK-OK-NOK":
                result = "hoursNOK_OK_prefNOK";
                break;
        }
        return result;
    }

    private String checkGeneralAvail(int teacherId, String day, String dayPart, int cohortId) {
        String dayDayPart = day + "_" + dayPart, result = "default";
        switch (dayDayPart) {
            case "maandag_ochtend":
                result = staffAvailrepo.getMondayMorning(cohortId, teacherId);
                break;
            case "dinsdag_ochtend":
                result = staffAvailrepo.getTuesdayMorning(cohortId, teacherId);
                break;
            case "woensdag_ochtend":
                result = staffAvailrepo.getWednesdayMorning(cohortId, teacherId);
                break;
            case "donderdag_ochtend":
                result = staffAvailrepo.getThursdayMorning(cohortId, teacherId);
                break;
            case "vrijdag_ochtend":
                result = staffAvailrepo.getFridayMorning(cohortId, teacherId);
                break;
            case "maandag_middag":
                result = staffAvailrepo.getMondayNoon(cohortId, teacherId);
                break;
            case "dinsdag_middag":
                result = staffAvailrepo.getTuesdayNoon(cohortId, teacherId);
                break;
            case "woensdag_middag":
                result = staffAvailrepo.getWednesdayNoon(cohortId, teacherId);
                break;
            case "donderdag_middag":
                result = staffAvailrepo.getThursdayNoon(cohortId, teacherId);
                break;
            case "vrijdag_middag":
                result = staffAvailrepo.getFridayNoon(cohortId, teacherId);
                break;
        }
        if (result.equals("JA")) result = "OK";
        else if (result.equals("NEE")) result = "NOK";
        else result = "NON";
        return result;
    }

    private String checkCohortOverlap(int cohortId, int teacherId, int weeknr, String dayPart, String weekDay) {
        String result;
        int scheduleId;
        String tempId = cohortScheduleRepo.getWeekNrDaypartOverlap(cohortId, weeknr, dayPart, teacherId, weekDay);
        if (tempId == null) scheduleId = 0;
        else scheduleId = Integer.parseInt(tempId);
        if (scheduleId > 0) result = "NOK";
        else result = "OK";
        return result;
    }

    private String checkSubjectPreference(int teacherId, int subjectId) {
        String result = "default";
        String preference = subjectRepo.getSingleTeacherSubjectPref(teacherId, subjectId);
        if (preference == null) result = "NON";
        else if (preference.equals("1")) result = "NOK";
        else if (preference.equals("2")) result = "OK";
        else if (preference.equals("3")) result = "OK";
        return result;
    }

    private String checkTeacherException(int teacherId, int weekNumber, String weekDay) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        String result = "NON";
        String incidentText = "Default";
        List<Exception> teacherIncidents = exceptionRepo.findByUser_Id(teacherId);
        if (teacherIncidents == null || teacherIncidents.size() == 0) {
            result = "NON";
        } else {
            for (Exception exception : teacherIncidents) {
                String[] arrOfStartDate = exception.getStartDate().split("-", 0);
                String[] arrOfEndDate = exception.getStartDate().split("-", 0);
                LocalDate startdayDate = getDateFromStringArray(arrOfStartDate);
                LocalDate enddayDate = getDateFromStringArray(arrOfEndDate);
                int exceptStartWeeknr = startdayDate.get(weekFields.weekOfWeekBasedYear());
                DayOfWeek exceptStartDay = startdayDate.getDayOfWeek();
                if (exception.getStartDate().equals(exception.getEndDate())) {
                    if (exceptStartWeeknr == weekNumber && exceptStartDay.equals(weekDay)) {
                        result = exception.getColorOption();
                    } else {
                        for (LocalDate date = startdayDate; date.isBefore(enddayDate); date = date.plusDays(1)) {
                            int tempWeeknr = date.get(weekFields.weekOfWeekBasedYear());
                            DayOfWeek tempDay = date.getDayOfWeek();

                            if (tempWeeknr == weekNumber && tempDay.equals(weekDay)) {
                                if (exception.getColorOption().equals("JA")) {
                                    result = "OK";
                                } else {
                                    result = "NOK";
                                }
                                break;
                            }
                        }
                    }

                }

            }
        }

        return result;
    }

    private String checkTeacherHours(int teacherId, int subjectId, int cohortId) {
        TeacherHours teacherHours = teacherHoursRepository.findByUserId(teacherId);
        int realTeacherHours = 4;
        if (teacherHours != null) {
            if (!doesTeacherHaveExperienceWithSubject(teacherId, subjectId, cohortId)) {

                if (teacherHours.getTeachingHoursLeft() < 6) return "NOK";
                else return "OK";
            } else {
                int yearsOfExperience = howManyYearsExperienceDoesTeacherHave(teacherId, subjectId, cohortId);
                switch (yearsOfExperience) {
                    case 1:
                        realTeacherHours = 8;
                        break;
                    case 2:
                        realTeacherHours = 6;
                        break;
                }
                if (teacherHours.getTeachingHoursLeft() < realTeacherHours) return "NOK";
            }
            return "OK";
        } else return "NON";
    }


    private boolean doesTeacherHaveExperienceWithSubject(int teacherId, int subjectId, int cohortId) {
        boolean experience;
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectIdAndCohort_CohortIdIsNot(teacherId, subjectId, cohortId);
        experience = cohortScheduleList.size() > 0;
        return experience;
    }

    private int howManyYearsExperienceDoesTeacherHave(int teacherId, int subjectId, int cohortId) {
        int numberOfYearsExperience;
        List<CohortSchedule> cohortScheduleList = cohortScheduleRepo.getAllByUserIdAndSubject_SubjectIdAndCohort_CohortIdIsNot(teacherId, subjectId, cohortId);
        if (cohortScheduleList.size() == 1) numberOfYearsExperience = 1;
        else if (cohortScheduleList.size() == 2) numberOfYearsExperience = 2;
        else numberOfYearsExperience = 3;
        return numberOfYearsExperience;
    }

    @PostMapping(value = "/generateCohortSchedule/check")
    public @ResponseBody
    String checkSchedule(HttpServletRequest request) {

        String[] arrOfDate = request.getParameter("dateDay").split("-", 0);
        String buttonClicked = request.getParameter("button");
        String schedId = request.getParameter("scheduleId");
        String weekDay = request.getParameter("day");
        String dayPart = request.getParameter("daypart");

        int scheduledId = Integer.parseInt(schedId);
        int cohortId = Integer.parseInt(request.getParameter("cohortnr"));
        int subjectId = Integer.parseInt(request.getParameter("subjectnr"));
        int teacherId = Integer.parseInt(request.getParameter("teachernr"));

        LocalDate dayDate = getDateFromStringArray(arrOfDate);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = dayDate.get(weekFields.weekOfWeekBasedYear());


        String result = "";
        if (buttonClicked.equals("check")) {
            int previousTeacher;

            Integer tempPreviousTeacher = cohortScheduleRepo.getTeacherAtRowId(scheduledId);
            if (tempPreviousTeacher == null) {

                previousTeacher = 0;
            } else previousTeacher = tempPreviousTeacher;

            if (previousTeacher == teacherId) {

                result = "sameTeacher";
            } else {
                result = totalCheck(cohortId, teacherId, dayPart, weekDay, subjectId, weekNumber);

            }
            return result;
        }
        if (buttonClicked.equals("save")) {
            int previousTeacher;
            result = totalCheck(cohortId, teacherId, dayPart, weekDay, subjectId, weekNumber);
            Integer tempPreviousTeacher = cohortScheduleRepo.getTeacherAtRowId(scheduledId);
            if (tempPreviousTeacher == null) {

                previousTeacher = 0;
            } else {
                previousTeacher = tempPreviousTeacher;  //previousTeacher = Integer.parseInt(tempPreviousTeacher);

            }
            if (previousTeacher == teacherId) {

                result = "sameTeacher";
            } else if (result.equals("NOK") || result.equals("hourNOK_restOK") ||
                    result.equals("hoursNOK_availNOK_OK") || result.equals("hoursNOK_OK_prefNOK")) {
                result = "hoursNOK";
            } else if (previousTeacher != 0) {
                // 1) check of previoususer geen 0 is indien niet 0 dan
                // 2) zet uren terug voor de previous teacher.
                int hoursToSubract,
                        oldYearsOfExperience = howManyYearsExperienceDoesTeacherHave(previousTeacher, subjectId, cohortId);
                if (oldYearsOfExperience <= 1) hoursToSubract = 8;
                else hoursToSubract = 6;
                int newHoursLeft = teacherHoursRepository.getHoursLeft(previousTeacher) + hoursToSubract,
                        newHoursUsed = teacherHoursRepository.getHoursUsed(previousTeacher) - hoursToSubract;
                teacherHoursRepository.updateTeacherHours(newHoursLeft, newHoursUsed, previousTeacher);

                // 3) verreken de uren voor de huidige teacher
                int newHoursToSubract,
                        newYearsOfExperience = howManyYearsExperienceDoesTeacherHave(teacherId, subjectId, cohortId);
                if (newYearsOfExperience <= 1) newHoursToSubract = 8;
                else newHoursToSubract = 6;

                newHoursLeft = teacherHoursRepository.getHoursLeft(teacherId) - newHoursToSubract;
                newHoursUsed = teacherHoursRepository.getHoursUsed(teacherId) + newHoursToSubract;
                teacherHoursRepository.updateTeacherHours(newHoursLeft, newHoursUsed, teacherId);
                cohortScheduleRepo.assignTeacherToSubject(teacherId, scheduledId);

            } else {
                int newHoursToSubract,
                        newYearsOfExperience = howManyYearsExperienceDoesTeacherHave(teacherId, subjectId, cohortId);
                if (newYearsOfExperience <= 1) newHoursToSubract = 8;
                else newHoursToSubract = 6;
                int newHoursLeft = teacherHoursRepository.getHoursLeft(teacherId) - newHoursToSubract,
                        newHoursUsed = teacherHoursRepository.getHoursUsed(teacherId) + newHoursToSubract;
                teacherHoursRepository.updateTeacherHours(newHoursLeft, newHoursUsed, teacherId);
                cohortScheduleRepo.assignTeacherToSubject(teacherId, scheduledId);
            }
            return result;
        }
        return result;
    }

    @GetMapping(value = "/generateCohortSchedule")
    public String generateCohortSchedule(Model model) {
        model.addAttribute("subjects", subjectRepo.getSubjects());
        model.addAttribute("teachers", userRepo.getTeachers());
        model.addAttribute("preferences", subjectRepo.getPreferences());
        model.addAttribute("cohorts", cohortScheduleRepo.getCohorts());

        if (cohortScheduleRepo.getScheduleLastCohort() != null)
            model.addAttribute("cohortschedule", cohortScheduleRepo.getScheduleLastCohort());
        else {
            CohortSchedule tempCS = new CohortSchedule();
            model.addAttribute("cohortschedule", tempCS);
        }
        return "generateCohortSchedule";
    }

    @PostMapping(value = "/subjectCohortCoupeling")
    public @ResponseBody
    String subjectCohortCoupeling(HttpServletRequest request) {
        String tempId = request.getParameter("scheduleId");
        int id = Integer.parseInt(tempId);
        int subjectId = Integer.parseInt(request.getParameter("subjectnr"));
        cohortScheduleRepo.assignSubjectToCohort(subjectId, id);
        return "OK";
    }
}
