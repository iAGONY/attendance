/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.repository;

import com.employee.attendance.entity.EmployeeLogin;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author suman
 */
public interface EmployeeLoginRepository extends CrudRepository<EmployeeLogin, Integer> {
    
}
