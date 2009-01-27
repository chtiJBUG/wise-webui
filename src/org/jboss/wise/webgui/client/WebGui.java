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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.wise.webgui.client.widgets.Alert;
import org.jboss.wise.webgui.shared.WsdlRec;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WebGui implements EntryPoint, ClickListener {
    public static Images icons = GWT.create(Images.class);

    public static Messages messages = GWT.create(Messages.class);

    public WebGui() {
    }

    private HorizontalPanel getMainButtons() {
	HorizontalPanel buttonBar = new HorizontalPanel();
	PushButton standby = new PushButton(icons.standby().createImage());
	standby.setTitle("Stand By");
	buttonBar.add(standby);
	PushButton info = new PushButton(icons.info().createImage());
	info.setTitle("Info");
	buttonBar.add(info);
	return buttonBar;
    }

    /**
     * This is the entry point method.
     * 
     * TODO: the code here is only a place holder and it must be replaced
     */
    public void onModuleLoad() {
	try {
	    List<WsdlRec> recs = new ArrayList<WsdlRec>();
	    recs.add(new WsdlRec(1L, "http://HOST_1:8080/Sample./SampleServiceWs?wsdl", "SampleService@HOST_1", new Date(), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In velit. Praesent posuere eros bibendum ligula."));
	    recs.add(new WsdlRec(2L, "http://HOST_1:8080/Data./DataServiceWs?wsdl", "DataService@HOST_1", new Date(), "Sed consequat ligula sed arcu. Vivamus ut nunc sit amet ante volutpat rhoncus. Nullam orci. Aenean suscipit nulla et sapien. Curabitur sagittis libero a enim."));
	    recs.add(new WsdlRec(1L, "http://ANOTHER_HOST:8080/Bank./BankServiceWs?wsdl", "BankService@ANOTHER_HOST", new Date(), "Sed auctor, dolor sit amet semper sollicitudin, massa purus scelerisque velit, et posuere dolor ante ut urna. Donec molestie, odio non fermentum tristique, felis mi faucibus augue, id placerat sem nisi eget libero. Nullam ante ligula, placerat ut, lacinia a, pretium quis, orci. In nec augue. Fusce dictum varius libero. Donec eu lorem. Sed vestibulum magna vitae arcu."));

	    WsdlList list = new WsdlList(recs);
	    list.setWidth("100%");
	    list.refresh();
	    RootPanel.get("btns").add(getMainButtons());
	    RootPanel.get("main").add(list);
	} catch (Exception err) {
	    err.printStackTrace();
	    Alert.error(err.getMessage());
	}
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
     */
    public void onClick(Widget sender) {
    }
}
