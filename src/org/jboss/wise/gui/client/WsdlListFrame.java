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
package org.jboss.wise.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WsdlListFrame extends Composite {

    private static WsdlListFrameUiBinder uiBinder = GWT.create(WsdlListFrameUiBinder.class);

    interface WsdlListFrameUiBinder extends UiBinder<Widget, WsdlListFrame> {
    }

    @UiField
    WsdlList list;

    @UiField
    TextBox searchBox;

    @UiField
    Button deleteBtn;

    @UiField
    Button editBtn;

    @UiField
    Button newBtn;

    @UiField
    Button openBtn;

    public WsdlListFrame() {
	initWidget(uiBinder.createAndBindUi(this));
	list.setList(Wise_gui.getInstance().getSavedWsdlList());
    }

    @UiHandler( { "deleteBtn", "editBtn", "newBtn", "openBtn" })
    void onClick(ClickEvent e) {
	if (e.getSource() == deleteBtn) {

	} else if (e.getSource() == editBtn) {
	    Wise_gui.getInstance().editWsdl();
	} else if (e.getSource() == newBtn) {
	    Wise_gui.getInstance().newWsdl();
	} else if (e.getSource() == openBtn) {
	    Wise_gui.getInstance().retrieveWsdl();
	}
    }

}
