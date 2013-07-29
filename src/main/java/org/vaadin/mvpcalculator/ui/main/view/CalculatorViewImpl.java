package org.vaadin.mvpcalculator.ui.main.view;

import org.springframework.context.annotation.Scope;
import org.vaadin.mvpcalculator.common.framework.mvp.BaseViewListener;
import org.vaadin.mvpcalculator.ui.CalculatorUI;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@org.springframework.stereotype.Component
@Scope("prototype")

public class CalculatorViewImpl extends CustomComponent
        implements CalculatorView, ClickListener {
    private Label display = new Label("0.0");
    /* Only the presenter registers one listener... */
    private CalculatorViewListener listener = null;
    
    public CalculatorViewImpl() {}
    
    @Override
    public void enter(ViewChangeEvent event) {
        GridLayout layout  = new GridLayout(4, 6);

        // Create a result label that spans over all
        // the 4 columns in the first row
        layout.addComponent(display, 0, 0, 3, 0);
    
        // The operations for the calculator in the order
        // they appear on the screen (left to right, top
        // to bottom)
        String[] operations = new String[] {
            "7", "8", "9", "/", "4", "5", "6",
            "*", "1", "2", "3", "-", "0", "=", "C", "+" };

        // Add buttons and have them send click events
        // to this class
        for (String caption: operations)
            layout.addComponent(new Button(caption, this));
        
        Button button = new Button("Go To OtherView");
        button.addClickListener(this);
        layout.addComponent(button,0,5,3,5);
        setCompositionRoot(layout);
        
    }
    
    public void setDisplay(double value) {
        display.setValue(Double.toString(value));
    }
 
    public void addListener(BaseViewListener listener) {
        this.listener = (CalculatorViewListener)listener;
    }

    /** Relay button clicks to the presenter with an
     *  implementation-independent event */
    @Override
    public void buttonClick(ClickEvent event) {
    	Button button = (Button)event.getSource();
    	if("Go To OtherView".equals(button.getCaption())) {
    		UI.getCurrent().getNavigator().navigateTo(CalculatorUI.OTHERVIEW);
    	}
    	else {
    		listener.buttonClick(event.getButton()
                                 .getCaption().charAt(0));
    	}
    }
}