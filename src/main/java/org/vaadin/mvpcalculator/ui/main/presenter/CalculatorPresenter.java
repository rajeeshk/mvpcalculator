package org.vaadin.mvpcalculator.ui.main.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.mvpcalculator.common.framework.mvp.BasePresenter;
import org.vaadin.mvpcalculator.service.CalculatorService;
import org.vaadin.mvpcalculator.ui.main.view.CalculatorView;

@org.springframework.stereotype.Component
@Scope("prototype")
public class CalculatorPresenter extends BasePresenter
        implements CalculatorView.CalculatorViewListener {
	
	@Autowired
    private CalculatorService calculatorService;

    private double current = 0.0;
    private char   lastOperationRequested = 'C';
    
    public CalculatorPresenter() {
    	           
    }
    
    
    @Override
    public void buttonClick(char operation) {
        // Handle digit input
        if ('0' <= operation && operation <= '9') {
            current = current * 10
                    + Double.parseDouble("" + operation);
            ((CalculatorView)view).setDisplay(current);
            return;
        }

        // Execute the previously input operation
        switch (lastOperationRequested) {
        case '+':
        	calculatorService.add(current);
            break;
        case '-':
        	calculatorService.add(-current);
            break;
        case '/':
        	calculatorService.divide(current);
            break;
        case '*':
        	calculatorService.multiply(current);
            break;
        case 'C':
        	calculatorService.setValue(current);
            break;
        } // '=' is implicit

        lastOperationRequested = operation;

        current = 0.0;
        if (operation == 'C')
        	calculatorService.clear();
        ((CalculatorView)view).setDisplay(calculatorService.getValue());
    }
}