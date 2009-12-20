/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
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
package org.jboss.wise.gui.client;

import org.jboss.wise.gui.shared.ServiceEndpoint;
import org.jboss.wise.gui.shared.ServiceWsdl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WsdlRetrieve {

    private static WsdlRetrieve instance = null;

    private static WsdlRetrieveUiBinder uiBinder = GWT.create(WsdlRetrieveUiBinder.class);

    interface WsdlRetrieveUiBinder extends UiBinder<DialogBox, WsdlRetrieve> {
    }

    @UiField
    SpanElement wsdlUrl;

    @UiField
    Button okBtn;

    @UiField
    Button cancelBtn;

    @UiField
    TextBox userName;

    @UiField
    PasswordTextBox password;

    private DialogBox dialog;

    private ServiceWsdl wsdl;

    private WsdlRetrieve() {
	dialog = uiBinder.createAndBindUi(this);
	okBtn.addStyleName("gwt-Button");
    }

    public static void activate(ServiceWsdl wsdl) {
	if (instance == null)
	    instance = new WsdlRetrieve();
	instance.show(wsdl);
    }

    private void show(ServiceWsdl wsdl) {
	this.wsdl = wsdl;
	if (!dialog.isShowing()) {
	    wsdlUrl.setInnerText(wsdl.getUrl());
	    enableButtons();
	    dialog.center();
	    dialog.show();
	    userName.setFocus(true);
	    userName.selectAll();
	}
    }

    private void enableButtons() {
    }

    @UiHandler( { "okBtn", "cancelBtn" })
    void onClick(ClickEvent e) {
	if (e.getSource() == cancelBtn) {
	    dialog.hide();
	} else if (e.getSource() == okBtn) {
	    dialog.hide();
	    EndpointSelection.activate(new ServiceEndpoint(wsdl));
	}
    }

}
