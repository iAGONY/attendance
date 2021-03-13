/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.repository;

import com.employee.attendance.constant.StatusConstant;
import com.employee.attendance.entity.EmployeeAttendance;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author suman
 */
public interface EmployeeAttendanceRepository extends CrudRepository<EmployeeAttendance, Integer>{
    
    public EmployeeAttendance findByRequestedDate(Date requestedDate);
    
    public List<EmployeeAttendance> findByRequestedDateBetween(Date fromRequestedDate, Date toRequestDate);
    
    public List<EmployeeAttendance> findByStatusAndRequestedDateBetween(StatusConstant statusConstant, Date fromRequestedDate, Date toRequestDate);
    
}
