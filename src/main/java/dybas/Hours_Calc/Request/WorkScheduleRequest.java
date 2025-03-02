package dybas.Hours_Calc.Request;

import dybas.Hours_Calc.WorkDay;

import java.util.List;

public class WorkScheduleRequest {

    private List<WorkDay> workDays;

    public List<WorkDay> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<WorkDay> workDays) {
        this.workDays = workDays;
    }
}
