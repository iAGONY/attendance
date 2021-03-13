/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.service;

import com.employee.attendance.entity.EmployeeLogin;
import com.employee.attendance.repository.EmployeeLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author suman
 */
@Service
public class EmployeeLoginService {
    
    @Autowired
    EmployeeLoginRepository employeeLoginRepository;
    
    private static final Integer LOGGED_IN_EMPLYEE_ID = 1; // this can be replace with user logged in token
    
    public Integer getLoggedInEmployeeId() {
        return LOGGED_IN_EMPLYEE_ID;
    }
    
    public EmployeeLogin getProxyLoggedInEmployee() {
        EmployeeLogin employeeLogin = new EmployeeLogin();
        employeeLogin.setId(LOGGED_IN_EMPLYEE_ID);
        return employeeLogin;
    }
    
    public EmployeeLogin getEmployeeLogin() {
       return employeeLoginRepository.findById(getLoggedInEmployeeId()).orElseThrow(() -> new RuntimeException("User not Found"));
    }
    
    
    
}
