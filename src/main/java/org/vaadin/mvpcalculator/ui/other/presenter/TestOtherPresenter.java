package org.vaadin.mvpcalculator.ui.other.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.mvpcalculator.common.framework.mvp.BasePresenter;
import org.vaadin.mvpcalculator.service.CalculatorService;
import org.vaadin.mvpcalculator.ui.other.view.TestOtherView;

@org.springframework.stereotype.Component
@Scope("prototype")
public class TestOtherPresenter extends BasePresenter
        implements TestOtherView.TestOtherViewListener {
	
	@Autowired
    private CalculatorService calculatorService;

    public TestOtherPresenter() {
    }
    
    @Override
    public double getValue() {
        return calculatorService.getValue();
    }
}