package org.vaadin.mvpcalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.mvpcalculator.dao.CalculatorDao;


@Service("calculatorService")
public class CalculatorServiceImpl implements CalculatorService  {
    
	@Autowired
    private CalculatorDao calculatorDao;
	
    public void clear() {
    	calculatorDao.clear();
    }

    public void add(double arg) {
    	calculatorDao.add(arg);
    }

    public void multiply(double arg) {
    	calculatorDao.multiply(arg);
    }

    public void divide(double arg) {
    	calculatorDao.divide(arg);
    }
    
    public double getValue() {
    	return calculatorDao.getValue();
    }
    
    public void setValue(double value) {
    	calculatorDao.setValue(value);
    }
}