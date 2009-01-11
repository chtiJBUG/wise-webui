/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.wise.webgui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * webgui entry point class
 */
public class WebGui implements EntryPoint {

    /**
     * This is the entry point method.
     * 
     * TODO: the code here is only a place holder and it must be replaced
     */
    public void onModuleLoad() {
	Button button = new Button("Test");

	VerticalPanel vPanel = new VerticalPanel();
	vPanel.setWidth("100%");
	vPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	vPanel.add(button);

	RootPanel.get().add(vPanel);

	final DialogBox dialogBox = new DialogBox();
	dialogBox.setText("Test");
	dialogBox.setAnimationEnabled(true);
	Button closeButton = new Button("close");
	VerticalPanel dialogVPanel = new VerticalPanel();
	dialogVPanel.setWidth("100%");
	dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
	dialogVPanel.add(closeButton);

	closeButton.addClickListener(new ClickListener() {
	    public void onClick(Widget sender) {
		dialogBox.hide();
	    }
	});

	dialogBox.setWidget(dialogVPanel);

	button.addClickListener(new ClickListener() {
	    public void onClick(Widget sender) {
		dialogBox.center();
		dialogBox.show();
	    }
	});
    }
}
