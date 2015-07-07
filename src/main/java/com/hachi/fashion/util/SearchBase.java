/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.util;

/**
 *
 * @author MinhDT
 */
public class SearchBase {

    public static final String EXPRESSION_EQUALS           = "EQUALS";
    public static final String EXPRESSION_NOT_EQUALS       = "NOTEQUALS";
    public static final String EXPRESSION_LIKE             = "LIKE";
    public static final String EXPRESSION_NOT_LIKE         = "NOTLIKE";
    public static final String EXPRESSION_GREATE           = "GREATE";
    public static final String EXPRESSION_GREATE_OR_EQUALS = "GE";
    public static final String EXPRESSION_LESS             = "LESS";
    public static final String EXPRESSION_LESS_OR_EQUALS   = "LE";
    public static final String EXPRESSION_NULL             = "NULL";
    public static final String EXPRESSION_NOT_NULL         = "NOTNULL";
    public static final String EXPRESSION_IN               = "IN";
    public static final String EXPRESSION_NOT_IN           = "NOTIN";

    protected String colName;

    protected String expression;

    protected Object param;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

}
