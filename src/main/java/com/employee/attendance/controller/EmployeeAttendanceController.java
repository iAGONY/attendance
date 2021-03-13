/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.controller;

import com.employee.attendance.dto.AttendanceRequestDto;
import com.employee.attendance.dto.EmployeeLeaveReportDto;
import com.employee.attendance.dto.ReportFilterDto;
import com.employee.attendance.entity.EmployeeAttendance;
import com.employee.attendance.service.EmployeeAttendanceService;
import com.employee.attendance.utility.ApiConstant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author suman
 */
@RestController
@RequestMapping(ApiConstant.EMPLOYEE_ATTENDANCE)
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeAttendanceController {

    @Autowired
    EmployeeAttendanceService employeeAttendanceService;

    @PostMapping
    @RequestMapping(ApiConstant.REQUEST)
    public ResponseEntity<String> attendanceRequest(@RequestBody AttendanceRequestDto attendanceRequestDto) {
        return employeeAttendanceService.attendanceRequest(attendanceRequestDto);
    }

    @RequestMapping(ApiConstant.LEAVE + "/" + ApiConstant.REPORT)
    @PostMapping
    public ResponseEntity<EmployeeLeaveReportDto> getMonthlyLeaveReport(@RequestBody ReportFilterDto reportFilterDto) {
        return ResponseEntity.ok(employeeAttendanceService.getMonthlyLeaveReport(reportFilterDto));
    }

    @RequestMapping(ApiConstant.REPORT)
    @PostMapping
    public ResponseEntity<List<EmployeeAttendance>> getAttendanceReport(@RequestBody ReportFilterDto reportFilterDto) {
        return ResponseEntity.ok(employeeAttendanceService.getAttendanceReport(reportFilterDto));
    }
    
    @GetMapping
    public String check() {
        return "HELOO WORLD";
    }

}
