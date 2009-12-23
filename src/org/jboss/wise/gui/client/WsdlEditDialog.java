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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WsdlEditDialog {

    private static WsdlEditDialogUiBinder uiBinder = GWT.create(WsdlEditDialogUiBinder.class);

    interface WsdlEditDialogUiBinder extends UiBinder<DialogBox, WsdlEditDialog> {
    }

    private DialogBox dialog;

    @UiField
    TextBox name;

    @UiField
    TextArea description;

    @UiField
    TextBox wsdl;

    @UiField
    Button cancelBtn;

    @UiField
    Button okBtn;

    public WsdlEditDialog() {
	dialog = uiBinder.createAndBindUi(this);
	okBtn.addStyleName("gwt-Button");
    }

    @UiHandler( { "cancelBtn", "okBtn" })
    void onClick(ClickEvent e) {
	if (e.getSource() == cancelBtn) {
	    dialog.hide();
	} else if (e.getSource() == okBtn) {
	    dialog.hide();
	}
    }

    public void show() {
	if (!dialog.isShowing()) {
	    enableButtons();
	    dialog.center();
	    dialog.show();
	    name.setFocus(true);
	    name.selectAll();
	}
    }

    private void enableButtons() {

    }
}
