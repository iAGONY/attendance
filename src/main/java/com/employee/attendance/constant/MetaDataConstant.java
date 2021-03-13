/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employee.attendance.constant;

/**
 *
 * @author suman
 */
public enum MetaDataConstant {

    TOTAL_LEAVE_PER_MONTH("2"),
    TOTAL_LEAVE_PER_YEAR("18");

    private final String fieldValue;

    private MetaDataConstant(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldValue() {
        return fieldValue;
    }

}
