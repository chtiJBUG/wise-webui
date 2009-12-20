package org.jboss.wise.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginDialog {

    private static LoginDialog instance = null;

    private static LoginDialogUiBinder uiBinder = GWT.create(LoginDialogUiBinder.class);

    interface LoginDialogUiBinder extends UiBinder<DialogBox, LoginDialog> {
    }

    private DialogBox dialog;

    @UiField
    TextBox mail;

    @UiField
    PasswordTextBox password;

    @UiField
    CheckBox rememberMe;

    @UiField
    Button loginBtn;

    @UiField
    Button registerBtn;

    @UiField
    Button forgotPasswordBtn;

    private LoginDialog() {
	dialog = uiBinder.createAndBindUi(this);
	loginBtn.addStyleName("gwt-Button");
    }

    public static void activate() {
	if (instance == null)
	    instance = new LoginDialog();
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

    @UiHandler( { "loginBtn", "registerBtn", "forgotPasswordBtn" })
    void handleClick(ClickEvent e) {
	if (e.getSource() == loginBtn) {
	    dialog.hide();
	    RootPanel.get("main").add(new WsdlListPage());
	} else if (e.getSource() == registerBtn) {
	    dialog.hide();
	    RegisterDialog.activate();
	} else if (e.getSource() == forgotPasswordBtn) {
	    dialog.hide();
	    PasswordReminderDialog.activate();
	}
    }

    @UiHandler( { "mail", "password" })
    void handleBlur(BlurEvent e) {
	enableButtons();
    }

    @UiHandler( { "mail", "password" })
    void handleUpEvent(KeyUpEvent e) {
	enableButtons();
    }

    private void enableButtons() {
	boolean enableLogin = mail.getText().length() > 0 && password.getText().length() > 0;
	loginBtn.setEnabled(enableLogin);
    }

}
