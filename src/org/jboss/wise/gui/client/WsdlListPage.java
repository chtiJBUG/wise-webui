/*
 * JBoss, Home of Professional Open Source Copyright 2009, JBoss Inc., and
 * individual contributors as indicated by the @authors tag. See the
 * copyright.txt in the distribution for a full listing of individual
 * contributors.
 * 
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */

package org.jboss.wise.gui.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.wise.gui.shared.ServiceWsdl;

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
public class WsdlListPage extends Composite {

    private static WsdlListPageUiBinder uiBinder = GWT.create(WsdlListPageUiBinder.class);

    interface WsdlListPageUiBinder extends UiBinder<Widget, WsdlListPage> {
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

    private List<ServiceWsdl> data;

    public WsdlListPage() {

	initWidget(uiBinder.createAndBindUi(this));
	data = new ArrayList<ServiceWsdl>();
	for (int i = 0; i < 100; i++) {
	    data.add(new ServiceWsdl("Service " + i, "http://HOST " + i + ":8080/Service1WS/Service1WSBean?wsdl", "This tool may be...", new Date()));
	}
	list.setList(data);
    }

    @UiHandler( { "deleteBtn", "editBtn", "newBtn", "openBtn" })
    void onClick(ClickEvent e) {
	if (e.getSource() == deleteBtn) {

	} else if (e.getSource() == editBtn) {
	    WsdlEditDialog.activate();
	} else if (e.getSource() == newBtn) {
	    WsdlEditDialog.activate();
	} else if (e.getSource() == openBtn) {
	    WsdlRetrieve.activate(data.get(33));
	}
    }
}
