package dybas.Hours_Calc.Controllers;

import dybas.Hours_Calc.Request.WorkScheduleRequest;
import dybas.Hours_Calc.Response.WorkHoursResponse;
import dybas.Hours_Calc.WorkDay;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkHoursController {

    @PostMapping("/calculate-hours")
    public ResponseEntity<WorkHoursResponse> calculateWorkHours(@RequestBody WorkScheduleRequest request) {
        double totalHours = 0.0;

        for (WorkDay workDay : request.getWorkDays()) {
            String day = workDay.getDay();
            String startTime = workDay.getStartTime();
            String endTime = workDay.getEndTime();

            double hoursWorked = calculateHoursForDay(startTime, endTime);
            totalHours += hoursWorked;
        }

        WorkHoursResponse response = new WorkHoursResponse();
        response.setTotalHours(totalHours);
        return ResponseEntity.ok(response);
    }


    private double calculateHoursForDay(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");

        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = startParts.length > 1 ? Integer.parseInt(startParts[1]) : 0;

        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = endParts.length > 1 ? Integer.parseInt(endParts[1]) : 0;


        double startTimeInMinutes = startHour * 60 + startMinute;
        double endTimeInMinutes = endHour * 60 + endMinute;

        return (endTimeInMinutes - startTimeInMinutes) / 60.0;
    }


}
