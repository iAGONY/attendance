/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.service;

import com.employee.attendance.constant.StatusConstant;
import com.employee.attendance.entity.EmployeeAttendance;
import com.employee.attendance.repository.EmployeeAttendanceRepository;
import com.employee.attendance.utility.DateUtility;
import com.employee.attendance.utility.MathUtil;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author suman
 */
@Service
public class DummyDataInitilization {

    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;

    @Autowired
    EmployeeLoginService employeeLoginService;

    @PostConstruct
    public void init() {
        populateAnnulaReportTillDate();
        System.out.println("Dummy attendace populated ::::::::::");
    }

    private void populateAnnulaReportTillDate() {
        LocalDate fromDate = DateUtility.getLocalDate("yyyy-MM-dd", "2020-01-01");
        LocalDate toDate = LocalDate.now();
        List<LocalDate> finalDates = Stream.iterate(fromDate, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(fromDate, toDate))
                .collect(Collectors.toList());

        for (int index = 0; index < finalDates.size(); index++) {
            EmployeeAttendance attendance = new EmployeeAttendance();
            attendance.setEmployeeLogin(employeeLoginService.getEmployeeLogin());
            attendance.setRequestedDate(java.sql.Date.valueOf(finalDates.get(index)));
            if (MathUtil.isPrime(index)) {
                attendance.setStatus(StatusConstant.ON_LEAVE);
            } else {
                attendance.setStatus(StatusConstant.PRESENT);
            }
            employeeAttendanceRepository.save(attendance);
        }

    }

}
