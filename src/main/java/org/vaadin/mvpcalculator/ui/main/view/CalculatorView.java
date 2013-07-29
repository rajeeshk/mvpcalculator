package org.vaadin.mvpcalculator.ui.main.view;

import org.vaadin.mvpcalculator.common.framework.mvp.BaseView;
import org.vaadin.mvpcalculator.common.framework.mvp.BaseViewListener;

public interface CalculatorView extends BaseView{
    public void setDisplay(double value);

    public interface CalculatorViewListener extends BaseViewListener {
        void buttonClick(char operation);
    }
    
}