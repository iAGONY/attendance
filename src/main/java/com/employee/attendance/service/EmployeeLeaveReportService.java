/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.service;

import com.employee.attendance.constant.MetaDataConstant;
import com.employee.attendance.constant.MonthConstant;
import com.employee.attendance.dto.EmployeeLeaveReportDto;
import com.employee.attendance.dto.MonthlyLeaveReportDto;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author suman
 */
@Service
public class EmployeeLeaveReportService {

    @Autowired
    EmployeeLoginService employeeLoginService;
    
    public EmployeeLeaveReportDto getEmployeeLeaveReportDto(Map<MonthConstant, Long> monthlyLeaveMap) {
        EmployeeLeaveReportDto employeeLeaveReportDto = new EmployeeLeaveReportDto();
        Long totalAllotedLeave = 0L;
        Long monthlyAllotedLeave = Long.parseLong(MetaDataConstant.TOTAL_LEAVE_PER_MONTH.getFieldValue());
        Long yearlsAllotedLeave = Long.parseLong(MetaDataConstant.TOTAL_LEAVE_PER_YEAR.getFieldValue());
        for (Map.Entry<MonthConstant, Long> entry : monthlyLeaveMap.entrySet()) {
            MonthConstant month = entry.getKey();
            Long leaveTaken = entry.getValue();
            Long leaveToBeAdded = monthlyAllotedLeave;
            if (totalAllotedLeave < yearlsAllotedLeave) {
                totalAllotedLeave += monthlyAllotedLeave;
                if (totalAllotedLeave > yearlsAllotedLeave) { // checking if exceeds yearlsAllotedLeave
                    leaveToBeAdded = monthlyAllotedLeave - (totalAllotedLeave - yearlsAllotedLeave);
                    totalAllotedLeave = yearlsAllotedLeave;
                }
            } else if (Objects.equals(totalAllotedLeave, yearlsAllotedLeave)) {
                leaveToBeAdded = 0L;
            } 

            MonthlyLeaveReportDto monthlyReport = new MonthlyLeaveReportDto();
            monthlyReport.setMonth(month.name());
            monthlyReport.setLeaveTaken(leaveTaken);
            monthlyReport.setAddedLeave(leaveToBeAdded);
            setExtraLeave(monthlyReport);
            setAvailableLeaveAndUnpaidLeave(monthlyReport, leaveToBeAdded);
            setSalary(monthlyReport);
            employeeLeaveReportDto.getLeaveReportDtos().add(monthlyReport);
        }
        employeeLeaveReportDto.setTotalAllotedLeave(totalAllotedLeave);
        return employeeLeaveReportDto;
    }

    private void setExtraLeave(MonthlyLeaveReportDto monthlyReport) {
        if (monthlyReport.getLeaveTaken() > 0) {
            Long monthlyAllotedLeave = Long.parseLong(MetaDataConstant.TOTAL_LEAVE_PER_MONTH.getFieldValue());
            if (monthlyReport.getLeaveTaken() > monthlyAllotedLeave) {
                monthlyReport.setExtraLeaveTaken(monthlyReport.getLeaveTaken() - monthlyAllotedLeave);
            }
        }
    }

    private void setAvailableLeaveAndUnpaidLeave(MonthlyLeaveReportDto monthlyReport, Long leaveToBeAddedMonthly) {
        if (monthlyReport.getLeaveTaken() > 0) {
            Long availableLeave = monthlyReport.getAvailableLeave() + leaveToBeAddedMonthly - monthlyReport.getLeaveTaken();
            if (availableLeave > 0) {
                monthlyReport.setAvailableLeave(availableLeave);
            } else {
                monthlyReport.setAvailableLeave(0L);
                monthlyReport.setUnpaidLeave(Math.abs(availableLeave));
            }
        } else {
            monthlyReport.setAvailableLeave(monthlyReport.getAvailableLeave() + leaveToBeAddedMonthly);
        }
    }

    private void setSalary(MonthlyLeaveReportDto monthlyReport) {
        monthlyReport.setMonthlySalary(calculateSalary(monthlyReport.getUnpaidLeave()));
    }

    private Double calculateSalary(Long unpaidLeave) {
        Double monthlySalary = employeeLoginService.getEmployeeLogin().getMonthlySalary();
        Double dailySalary = (monthlySalary * 12) / 365; // assuming that total days in year is 365 days
        return monthlySalary - dailySalary * unpaidLeave;
    }

}
