/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.utility;

import com.employee.attendance.constant.MonthConstant;
import com.employee.attendance.dto.ReportFilterDto;
import com.employee.attendance.entity.EmployeeAttendance;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author suman
 */
public class MonthlyLeaveHolder {

    private final Map<MonthConstant, Long> monthlyLeaveMap;

    private MonthlyLeaveHolder(ReportFilterDto reportFilterDto, List<EmployeeAttendance> attendances) {
        this.monthlyLeaveMap = new LinkedHashMap<>();

        int fromDate = DateUtility.getMonth(reportFilterDto.getFromDate()) - 1;
        int toDate = DateUtility.getMonth(reportFilterDto.getToDate());

        MonthConstant[] values = MonthConstant.values();
        for (int index = fromDate; index < toDate; index++) {
            MonthConstant key = values[index];
            this.monthlyLeaveMap.put(key, attendances.stream().filter(e -> DateUtility.getMonthPrefix(e.getRequestedDate()).equalsIgnoreCase(key.name())).collect(Collectors.counting()));
        }
    }

    public static MonthlyLeaveHolder of(ReportFilterDto reportFilterDto,List<EmployeeAttendance> attendances) {
       return new MonthlyLeaveHolder(reportFilterDto,attendances);
    }

    public Map<MonthConstant, Long> getMonthlyLeaveMap() {
        return monthlyLeaveMap;
    }
    
    

}
