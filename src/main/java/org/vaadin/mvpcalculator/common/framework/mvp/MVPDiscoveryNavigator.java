package org.vaadin.mvpcalculator.common.framework.mvp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ru.xpoft.vaadin.DiscoveryNavigator;
import ru.xpoft.vaadin.SpringApplicationContext;

import com.vaadin.navigator.View;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;


public class MVPDiscoveryNavigator extends DiscoveryNavigator {
	
	private static final long serialVersionUID = 1L;
	
	protected final Map <String,String> viewPresenterMap = Collections.synchronizedMap(new HashMap<String,String>());
	
	public MVPDiscoveryNavigator(UI ui, SingleComponentContainer container) {
        super(ui, container);
        viewPresenterMap.clear();
        views.clear();
    }
	
	public MVPDiscoveryNavigator(UI ui, ComponentContainer container) {
        super(ui, container);
        viewPresenterMap.clear();
        views.clear();
    }
	
	public void addBeanViewPresenter(String viewName, Class<? extends BaseView> viewClass,
			Class<? extends BasePresenter> presenterClass, boolean cached) {
	    
		if ((viewName == null) || (viewClass == null) || (presenterClass == null)) {
	      throw new IllegalArgumentException("view , viewClass and presenterClass must be non-null");
	    }

	    String[] beanNames = SpringApplicationContext.getApplicationContext().getBeanNamesForType(viewClass);
	    if (beanNames.length != 1) {
	      throw new IllegalArgumentException("cant't resolve Spring View bean name for class :" + viewClass.getName());
	    }
	    
	    String[] presenterNames = SpringApplicationContext.getApplicationContext().getBeanNamesForType(presenterClass);
	    if (presenterNames.length != 1) {
	      throw new IllegalArgumentException("cant't resolve Spring Presenter bean name for class :" + presenterClass.getName());
	    }
	    
	    viewPresenterMap.put(beanNames[0], presenterNames[0]);
	    removeView(viewName);
	    addBeanView(viewName, beanNames[0], viewClass, cached);
	  }
	
	@Override
	public View getView(String name, String beanName, boolean cached) {
		
		BaseView view = (BaseView)SpringApplicationContext.getApplicationContext().getBean(beanName);
	    BasePresenter presenter = (BasePresenter)SpringApplicationContext.getApplicationContext().getBean(viewPresenterMap.get(beanName));
	    if(presenter != null) {
	    	presenter.setView(view);
	    } else {
	    	throw new IllegalArgumentException("cant't resolve Spring Presenter for View bean name [" + beanName + 
	    			"] Presenter bean name [" + viewPresenterMap.get(beanName) +"]");
	    }
	    
	    return view;
	  }

}