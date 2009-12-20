package org.jboss.wise.gui.client;

import java.util.Date;

import org.jboss.wise.gui.shared.ServiceWsdl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

public class WsdlListRow {

    private static WsdlListRowUiBinder uiBinder = GWT.create(WsdlListRowUiBinder.class);

    interface WsdlListRowUiBinder extends UiBinder<TableRowElement, WsdlListRow> {
    }

    @UiField
    TableCellElement name;

    @UiField
    TableCellElement description;

    @UiField
    TableCellElement savingDate;

    private TableRowElement row;

    private static DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");

    public WsdlListRow(ServiceWsdl wsdl) {
	row = uiBinder.createAndBindUi(this);
	name.setInnerText(wsdl.getName());
	description.setInnerText(wsdl.getName() + "@" + wsdl.getHost() + ":" + wsdl.getNotes());
	savingDate.setInnerText(format(wsdl.getSavingDate()));
    }

    public TableRowElement getRow() {
	return row;
    }

    private static String format(Date v) {
	if (v == null)
	    return "";
	return dateTimeFormat.format(v);
    }
}
