/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.dto;

/**
 *
 * @author suman
 */
public class MonthlyLeaveReportDto {
    
    private String month;
    private Long leaveTaken;
    private Long extraLeaveTaken;
    private Long addedLeave;
    private Long availableLeave;
    private Long unpaidLeave;
    private Double monthlySalary;

    public MonthlyLeaveReportDto() {
        this.leaveTaken = 0L; 
        this.availableLeave = 0L; 
        this.extraLeaveTaken = 0L; 
        this.unpaidLeave = 0L; 
        this.monthlySalary = 0.0D; 
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getLeaveTaken() {
        return leaveTaken;
    }

    public void setLeaveTaken(Long leaveTaken) {
        this.leaveTaken = leaveTaken;
    }

    public Long getAvailableLeave() {
        return availableLeave;
    }

    public void setAvailableLeave(Long availableLeave) {
        this.availableLeave = availableLeave;
    }

    public Long getExtraLeaveTaken() {
        return extraLeaveTaken;
    }

    public void setExtraLeaveTaken(Long extraLeaveTaken) {
        this.extraLeaveTaken = extraLeaveTaken;
    }

    public Long getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(Long unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Long getAddedLeave() {
        return addedLeave;
    }

    public void setAddedLeave(Long addedLeave) {
        this.addedLeave = addedLeave;
    }
    
}
