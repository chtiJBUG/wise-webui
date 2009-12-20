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

    public WsdlListPage() {
	initWidget(uiBinder.createAndBindUi(this));
	List<ServiceWsdl> data = new ArrayList<ServiceWsdl>();
	for (int i = 0; i < 100; i++) {
	    data.add(new ServiceWsdl("Service " + i, "HOST " + i, "This tool may be...", new Date()));
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

	}
    }

}
