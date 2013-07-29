package org.vaadin.mvpcalculator.dao;


public interface CalculatorDao {
    
    public void clear();
    public void add(double arg);
    public void multiply(double arg);
    public void divide(double arg);
    public double getValue();
    public void setValue(double value);
}