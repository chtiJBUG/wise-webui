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
