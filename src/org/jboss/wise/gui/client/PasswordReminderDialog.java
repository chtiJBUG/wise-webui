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
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;

public class PasswordReminderDialog {

    private static PasswordReminderDialog instance = null;

    private static PasswordReminderDialogUiBinder uiBinder = GWT.create(PasswordReminderDialogUiBinder.class);

    interface PasswordReminderDialogUiBinder extends UiBinder<DialogBox, PasswordReminderDialog> {
    }

    private DialogBox dialog;

    @UiField
    Button sendBtn;

    @UiField
    Button cancelBtn;

    @UiField
    TextBox mail;

    private PasswordReminderDialog() {
	dialog = uiBinder.createAndBindUi(this);
	sendBtn.addStyleName("gwt-Button");
    }

    public static void activate() {
	if (instance == null)
	    instance = new PasswordReminderDialog();
	instance.show();
    }

    private void show() {
	mail.setText("");
	if (!dialog.isShowing()) {
	    enableButtons();
	    dialog.center();
	    dialog.show();
	    mail.setFocus(true);
	    mail.selectAll();
	}
    }

    @UiHandler( { "sendBtn", "cancelBtn" })
    void onClick(ClickEvent e) {
	if (e.getSource() == sendBtn) {
	    dialog.hide();
	    LoginDialog.activate();
	} else if (e.getSource() == cancelBtn) {
	    dialog.hide();
	    LoginDialog.activate();
	}
    }

    @UiHandler("mail")
    void handleBlur(BlurEvent e) {
	enableButtons();
    }

    @UiHandler("mail")
    void handleUpEvent(KeyUpEvent e) {
	enableButtons();
    }

    private void enableButtons() {
	sendBtn.setEnabled(mail.getText().length() > 0);
    }
}
