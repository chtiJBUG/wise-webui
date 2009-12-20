package org.jboss.wise.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class RegisterDialog {

    private static RegisterDialog instance = null;

    private static RegisterDialogUiBinder uiBinder = GWT.create(RegisterDialogUiBinder.class);

    interface RegisterDialogUiBinder extends UiBinder<DialogBox, RegisterDialog> {
    }

    private DialogBox dialog;

    @UiField
    TextBox mail;

    @UiField
    TextBox confirmMail;

    @UiField
    PasswordTextBox password;

    @UiField
    PasswordTextBox confirmPassword;

    @UiField
    SpanElement msg;

    @UiField
    Button okBtn;

    @UiField
    Button cancelBtn;

    private RegisterDialog() {
	dialog = uiBinder.createAndBindUi(this);
	okBtn.addStyleName("gwt-Button");
    }

    public static void activate() {
	if (instance == null)
	    instance = new RegisterDialog();
	instance.show();
    }

    private void show() {
	if (!dialog.isShowing()) {
	    enableButtons();
	    dialog.center();
	    dialog.show();
	    mail.setFocus(true);
	    mail.selectAll();
	}
    }

    private void enableButtons() {
	boolean fieldsNotEmpty = mail.getText().length() > 0 && confirmMail.getText().length() > 0 && password.getText().length() > 0 && confirmPassword.getText().length() > 0;
	boolean mailConfirmed = mail.getText().equals(confirmMail.getText());
	boolean passwordConfirmed = password.getText().equals(confirmPassword.getText());
	if (mailConfirmed) {
	    if (passwordConfirmed) {
		msg.setInnerText("");
	    } else if (confirmPassword.getText().length() > 0) {
		msg.setInnerText("Password not confirmed");
	    }
	} else if (confirmMail.getText().length() > 0) {
	    msg.setInnerText("Mail not confirmed");
	}
	okBtn.setEnabled(fieldsNotEmpty && mailConfirmed && passwordConfirmed);
    }

    @UiHandler( { "okBtn", "cancelBtn" })
    void handleClick(ClickEvent e) {
	if (e.getSource() == okBtn) {
	    dialog.hide();
	    LoginDialog.activate();
	} else if (e.getSource() == cancelBtn) {
	    dialog.hide();
	    LoginDialog.activate();
	}
    }

    @UiHandler( { "mail", "confirmMail", "password", "confirmPassword" })
    void handleBlur(BlurEvent e) {
	enableButtons();
    }

    @UiHandler( { "mail", "confirmMail", "password", "confirmPassword" })
    void handleUpEvent(KeyUpEvent e) {
	enableButtons();
    }

}
