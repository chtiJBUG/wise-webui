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

import org.jboss.wise.gui.shared.Operation;
import org.jboss.wise.gui.shared.ServiceEndpoint;
import org.jboss.wise.gui.shared.ServiceWsdl;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class Wise_gui implements EntryPoint {

    private static Wise_gui instance = null;

    private LoginDialog loginDialog = null;

    private PasswordReminderDialog passwordReminderDialog = null;

    private RegisterDialog registerDialog = null;

    private Desk desk = null;

    private WsdlList wsdlList = null;

    private WsdlEditDialog wsdlEditDialog = null;

    private List<ServiceWsdl> savedWsdlList = null;

    private WsdlRetrieve wsdlRetrieveDialog = null;

    private EndpointSelection endpointSelectionDialog = null;

    private WsdlBrowser wsdlBrowser = null;

    private ServiceWsdl selectedWsdl = null;

    private ServiceEndpoint serviceEndpoint = null;

    private List<Operation> operations = null;

    public void onModuleLoad() {
	instance = this;
	login();
    }

    public static Wise_gui getInstance() {
	return instance;
    }

    public void login() {
	if (loginDialog == null) {
	    loginDialog = new LoginDialog();
	}
	loginDialog.show();
    }

    public void passwordReminder() {
	if (passwordReminderDialog == null) {
	    passwordReminderDialog = new PasswordReminderDialog();
	}
	passwordReminderDialog.show();
    }

    public void register() {
	if (registerDialog == null) {
	    registerDialog = new RegisterDialog();
	}
	registerDialog.show();
    }

    public boolean verifyLogin(String mail, String password) {
	if (desk == null) {
	    desk = new Desk();
	    RootPanel.get("main").add(desk);
	}
	savedWsdlList = new ArrayList<ServiceWsdl>();
	for (int i = 0; i < 100; i++) {
	    savedWsdlList.add(new ServiceWsdl("Service " + i, "http://HOST " + i + ":8080/Service1WS/Service1WSBean?wsdl", "This tool may be...", new Date()));
	}
	return true;
    }

    public void editWsdl() {
	if (wsdlEditDialog == null) {
	    wsdlEditDialog = new WsdlEditDialog();
	}
	wsdlEditDialog.show();
    }

    public void newWsdl() {
	if (wsdlEditDialog == null) {
	    wsdlEditDialog = new WsdlEditDialog();
	}
	wsdlEditDialog.show();
    }

    public void logout() {
	if (desk != null) {
	    RootPanel.get("main").remove(desk);
	    desk.setContentWidget(null);
	    desk = null;
	    wsdlList = null;
	}
	login();
    }

    public void wsdlList() {
	if (wsdlList == null) {
	    wsdlList = new WsdlList();
	}
	desk.setContentWidget(wsdlList);
    }

    public void retrieveWsdl() {
	if (wsdlRetrieveDialog == null) {
	    wsdlRetrieveDialog = new WsdlRetrieve();
	}
	wsdlRetrieveDialog.show(Wise_gui.getInstance().getSelectedWsdl());
    }

    public void selectEndpoint() {
	if (endpointSelectionDialog == null) {
	    endpointSelectionDialog = new EndpointSelection();
	}
	endpointSelectionDialog.show(serviceEndpoint);
    }

    public void operations() {
	if (wsdlBrowser == null) {
	    wsdlBrowser = new WsdlBrowser();
	}
	desk.setContentWidget(wsdlBrowser);
    }

    /**
     * @return savedWsdlList
     */
    public List<ServiceWsdl> getSavedWsdlList() {
	return savedWsdlList;
    }

    /**
     * @param selectedWsdl
     *            Sets selectedWsdl to the specified value.
     */
    public void setSelectedWsdl(ServiceWsdl selectedWsdl) {
	this.selectedWsdl = selectedWsdl;
	if (selectedWsdl != null) {
	    serviceEndpoint = new ServiceEndpoint(selectedWsdl);
	    operations = new ArrayList<Operation>();
	    for (int op = 0; op < 30; op++) {
		operations.add(new Operation("Operation " + op));
	    }
	} else {
	    serviceEndpoint = null;
	    operations = null;
	}
    }

    /**
     * @return selectedWsdl
     */
    public ServiceWsdl getSelectedWsdl() {
	return selectedWsdl;
    }

    /**
     * @return operations
     */
    public List<Operation> getOperations() {
	return operations;
    }

}
