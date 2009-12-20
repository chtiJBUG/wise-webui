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

public class WsdlEditDialog {

    private static WsdlEditDialog instance = null;

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

    private WsdlEditDialog() {
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

    public static void activate() {
	if (instance == null)
	    instance = new WsdlEditDialog();
	instance.show();
    }

    private void show() {
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
