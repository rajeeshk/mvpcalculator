package org.vaadin.mvpcalculator.ui.other.view;

import org.vaadin.mvpcalculator.common.framework.mvp.BaseView;
import org.vaadin.mvpcalculator.common.framework.mvp.BaseViewListener;

public interface TestOtherView extends BaseView{

    public interface TestOtherViewListener extends BaseViewListener {
        public double getValue();
    }
    
}