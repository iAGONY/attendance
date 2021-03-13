/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author suman
 */
public class ResponseUtility {
    
    public static ResponseEntity<String> successResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    public static ResponseEntity<String> failedResponse(String message) {
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
