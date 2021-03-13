/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.entity;

import com.employee.attendance.constant.StatusConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author suman
 */
@Entity
@Table(name = "EMPLOYEE_ATTENDANCE")
public class EmployeeAttendance implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REQUESTED_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date requestedDate;

    @JoinColumn(name = "EMPLOYEE_LOGIN", nullable = false, referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private EmployeeLogin employeeLogin;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusConstant status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public EmployeeLogin getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(EmployeeLogin employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public StatusConstant getStatus() {
        return status;
    }

    public void setStatus(StatusConstant status) {
        this.status = status;
    }

}
