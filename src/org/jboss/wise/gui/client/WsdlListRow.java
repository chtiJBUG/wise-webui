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
