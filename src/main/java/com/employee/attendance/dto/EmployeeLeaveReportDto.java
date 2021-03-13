/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author suman
 */
public class EmployeeLeaveReportDto {
    
    private Long totalAllotedLeave;
    
    private List<MonthlyLeaveReportDto> leaveReportDtos;

    public EmployeeLeaveReportDto() {
        this.totalAllotedLeave = 0L;
        this.leaveReportDtos = new ArrayList<>();
    }
    
    public List<MonthlyLeaveReportDto> getLeaveReportDtos() {
        return leaveReportDtos;
    }

    public void setLeaveReportDtos(List<MonthlyLeaveReportDto> leaveReportDtos) {
        this.leaveReportDtos = leaveReportDtos;
    }

    public Long getTotalAllotedLeave() {
        return totalAllotedLeave;
    }

    public void setTotalAllotedLeave(Long totalAllotedLeave) {
        this.totalAllotedLeave = totalAllotedLeave;
    }
    
}
