package org.vaadin.mvpcalculator.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.mvpcalculator.common.framework.mvp.MVPDiscoveryNavigator;
import org.vaadin.mvpcalculator.ui.main.presenter.CalculatorPresenter;
import org.vaadin.mvpcalculator.ui.main.view.CalculatorViewImpl;
import org.vaadin.mvpcalculator.ui.other.presenter.TestOtherPresenter;
import org.vaadin.mvpcalculator.ui.other.view.TestOtherViewImpl;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Component
@Scope("prototype")
@SuppressWarnings("serial")
@Title("MVP Calculator")
public class CalculatorUI extends UI {

	public static final String CALCVIEW = "calcview";
	public static final String OTHERVIEW = "otherview";
	
	@Override
	protected void init(VaadinRequest request) {
		
		MVPDiscoveryNavigator navigator = new MVPDiscoveryNavigator(this, this);
		
		//Set me to UI so that I can be used even from other views.
		this.setNavigator(navigator);
		
		// Add the views and presenters to the MVPDiscoveryNavigator
		navigator.addBeanViewPresenter(CALCVIEW, CalculatorViewImpl.class, CalculatorPresenter.class, false);
		navigator.addBeanViewPresenter(OTHERVIEW, TestOtherViewImpl.class, TestOtherPresenter.class, false);
		
		//Navigate to the desired View. The presenter also will be tied up with the view
		navigator.navigateTo(CALCVIEW);

	}

}