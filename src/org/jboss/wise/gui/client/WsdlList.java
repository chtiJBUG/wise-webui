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

import java.util.Date;
import java.util.List;

import org.jboss.wise.gui.shared.ServiceWsdl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class WsdlList extends Composite {

    private static WsdlListUiBinder uiBinder = GWT.create(WsdlListUiBinder.class);

    interface WsdlListUiBinder extends UiBinder<Widget, WsdlList> {
    }

    @UiField
    TableSectionElement content;

    private List<ServiceWsdl> list;

    private static DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm");

    public WsdlList() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    public void setList(List<ServiceWsdl> list) {
	this.list = list;
	NodeList<TableRowElement> rows = content.getRows();
	if (rows != null) {
	    while (rows.getLength() > 0) {
		content.deleteRow(-1);
	    }
	}
	for (ServiceWsdl wsdl : list) {
	    // WsdlListRow rowCtrl = new WsdlListRow(wsdl);
	    TableRowElement newRow = createRow(wsdl); // rowCtrl.getRow();
	    if (newRow != null) {
		content.appendChild(newRow);
	    }
	}
    }

    private TableRowElement createRow(ServiceWsdl wsdl) {
	TableRowElement row = TableRowElement.as(DOM.createTR());
	TableCellElement cell;

	cell = TableCellElement.as(DOM.createTD());
	cell.setAttribute("width", "20%");
	cell.setInnerText(wsdl.getName());
	row.appendChild(cell);

	cell = TableCellElement.as(DOM.createTD());
	cell.setAttribute("width", "60%");
	cell.setInnerText(wsdl.getName() + "@" + wsdl.getHost() + ":" + wsdl.getNotes());
	row.appendChild(cell);

	cell = TableCellElement.as(DOM.createTD());
	cell.setAttribute("width", "20%");
	cell.setInnerText(format(wsdl.getSavingDate()));
	row.appendChild(cell);

	return row;
    }

    private static String format(Date v) {
	if (v == null)
	    return "";
	else
	    return dateTimeFormat.format(v);
    }
}
