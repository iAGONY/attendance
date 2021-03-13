/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.service;

import com.employee.attendance.AttendanceApplication;
import com.employee.attendance.constant.MonthConstant;
import com.employee.attendance.constant.StatusConstant;
import com.employee.attendance.dto.AttendanceRequestDto;
import com.employee.attendance.dto.EmployeeLeaveReportDto;
import com.employee.attendance.dto.ReportFilterDto;
import com.employee.attendance.entity.EmployeeAttendance;
import com.employee.attendance.repository.EmployeeAttendanceRepository;
import com.employee.attendance.utility.DateUtility;
import com.employee.attendance.utility.MonthlyLeaveHolder;
import com.employee.attendance.utility.ResponseUtility;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author suman
 */
@Service
public class EmployeeAttendanceService {
    
    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;
    
    @Autowired
    EmployeeLoginService employeeLoginService;
    
    @Autowired
    EmployeeLeaveReportService employeeLeaveReportService;
    
    public ResponseEntity<String> attendanceRequest(AttendanceRequestDto attendanceRequestDto) {

//        EmployeeAttendance findByRequestedDate = employeeAttendanceRepository.findByRequestedDate(new Date());
//        if (findByRequestedDate != null) {
//            return ResponseUtility.failedResponse("Today's attendance already requested."); // runtime exception can be thrown
//        }
        EmployeeAttendance employeeAttendance = new EmployeeAttendance();
        employeeAttendance.setEmployeeLogin(employeeLoginService.getProxyLoggedInEmployee());
        employeeAttendance.setRequestedDate(new Date());
        employeeAttendance.setStatus(StatusConstant.valueOf(attendanceRequestDto.getStatus()));
        employeeAttendanceRepository.save(employeeAttendance);
        return ResponseUtility.successResponse("Attendace succesfully reqeusted.");
    }
    
    public EmployeeLeaveReportDto getMonthlyLeaveReport(ReportFilterDto reportFilterDto) {
        List<EmployeeAttendance> attendances = employeeAttendanceRepository.findByStatusAndRequestedDateBetween(StatusConstant.ON_LEAVE, reportFilterDto.getFromDate(), reportFilterDto.getToDate());
        Map<MonthConstant, Long> monthlyLeaveMap = MonthlyLeaveHolder.of(reportFilterDto, attendances).getMonthlyLeaveMap();
        return employeeLeaveReportService.getEmployeeLeaveReportDto(monthlyLeaveMap);
    }
    
    public List<EmployeeAttendance> getAttendanceReport(ReportFilterDto reportFilterDto) {
        return employeeAttendanceRepository.findByRequestedDateBetween(reportFilterDto.getFromDate(), reportFilterDto.getToDate());
    }
    
}
