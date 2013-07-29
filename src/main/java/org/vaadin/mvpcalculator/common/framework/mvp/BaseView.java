package org.vaadin.mvpcalculator.common.framework.mvp;

import com.vaadin.navigator.View;

public interface BaseView extends View {
	
	public void addListener(BaseViewListener listener);

}