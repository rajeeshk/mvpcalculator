package org.vaadin.mvpcalculator.ui.other.view;

import org.springframework.context.annotation.Scope;
import org.vaadin.mvpcalculator.common.framework.mvp.BaseViewListener;
import org.vaadin.mvpcalculator.ui.CalculatorUI;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
@Scope("prototype")

public class TestOtherViewImpl extends VerticalLayout
        implements TestOtherView, ClickListener {
    private TestOtherViewListener listener = null;
    
    public TestOtherViewImpl() {}
    
    @Override
    public void enter(ViewChangeEvent event) {
    	
    	addComponent(new Label("Click Below button to invoke : Presenter -> Service - > Dao "));
        Button button = new Button("Click Me");
        button.addClickListener(this);
        addComponent(button);
        
        Button backButton = new Button("Go Back To MainView");
        backButton.addClickListener(this);
        addComponent(backButton);
        this.setSpacing(true);
        
    }
    
    public void addListener(BaseViewListener listener) {
        this.listener = (TestOtherViewListener)listener;
    }
    
    @Override
    public void buttonClick(ClickEvent event) {
    	Button button = (Button)event.getSource();
    	if("Click Me".equals(button.getCaption())) {
    		UI.getCurrent().showNotification("Value returned : [" + listener.getValue() + "]");
    	}
    	else if("Go Back To MainView".equals(button.getCaption())) {
    		UI.getCurrent().getNavigator().navigateTo(CalculatorUI.CALCVIEW);
    	}
    }
}