package org.vaadin.mvpcalculator.service;


public interface CalculatorService {
    
    public void clear();
    public void add(double arg);
    public void multiply(double arg);
    public void divide(double arg);
    public double getValue();
    public void setValue(double value);
}