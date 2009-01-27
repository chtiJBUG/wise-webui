/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.wise.webgui.client;

import java.util.List;

import org.jboss.wise.webgui.client.widgets.Alert;
import org.jboss.wise.webgui.client.widgets.CellType;
import org.jboss.wise.webgui.client.widgets.DefaultDataType;
import org.jboss.wise.webgui.client.widgets.TreeGrid;
import org.jboss.wise.webgui.client.widgets.Alert.Reply;
import org.jboss.wise.webgui.shared.WsdlRec;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * List used to add, modify and edit saved wsdl, and access services.
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WsdlList extends Composite implements ClickListener {

    private VerticalPanel vpanel;

    private TreeGrid grid;

    private List<WsdlRec> wsdlList;

    public WsdlList(List<WsdlRec> wsdlList) {
	setup();
	this.wsdlList = wsdlList;
	initWidget(vpanel);
    }

    public void refresh() {
	for (WsdlRec rec : wsdlList) {
	    WsdlRecController ctrl = new WsdlRecController(rec);
	    TreeGrid.Group rows = grid.addRow(rec.getDescr(), rec.getUrl(), rec.getSavingDate(), ctrl.createRowButtons());
	    rows.addRow(rec.getNotes());
	}
	grid.refresh();
    }

    private void setup() {
	vpanel = new VerticalPanel();

	grid = new TreeGrid();

	grid.setTitle(WebGui.messages.wsdlListTitle());

	grid.setHeaderWidths("25%", "55%", "10%", "10%");
	grid.setHeaderAlign(0, "center");
	grid.setHeaderAlign(1, "center");
	grid.setHeaderAlign(2, "center");
	grid.setHeaderData(WebGui.messages.colDescrTitle(), WebGui.messages.colLinkTitle(), WebGui.messages.colSavingDateTitle(), null);

	CellType descrCellType = new CellType(DefaultDataType.STRING, "25%");
	descrCellType.setWordWrapNormal(false);
	CellType wsdlLinkCellType = new CellType(DefaultDataType.STRING, "55%");
	wsdlLinkCellType.setWordWrapNormal(false);
	grid.setBodyTypes(descrCellType, wsdlLinkCellType, new CellType(DefaultDataType.DATE, "10%"), new CellType(DefaultDataType.WIDGET, "10%"));

	grid.setColumnSortable(0, 0, TreeGrid.SortType.TreeState);
	grid.setColumnSortable(1, 1, TreeGrid.SortType.TwoState);
	grid.setColumnSortable(2, 2, TreeGrid.SortType.TwoState);

	grid.setColumnSort(0, TreeGrid.SortState.Default);

	grid.setBodyAlign(2, "center");
	grid.setBodyAlign(3, "right");

	TreeGrid.GroupsLevel level = grid.addLevel();

	level.setBodyTypes(new CellType(DefaultDataType.STRING, "100%"));
	level.addSummaryDetailButtonToFrame(WebGui.messages.btnSummaryDetails());

	grid.setWidth("100%");

	vpanel.add(grid);

	HorizontalPanel p = new HorizontalPanel();
	PushButton add = new PushButton(WebGui.icons.plus().createImage());
	add.addClickListener(this);
	add.setTitle("Add new wsdl");
	p.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	p.add(add);
	vpanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	vpanel.add(p);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
     */
    public void onClick(Widget sender) {
	// TODO a method to implement
    }

    private class WsdlRecController implements ClickListener {
	private WsdlRec rec;

	private PushButton edit;

	private PushButton delete;

	public WsdlRecController(WsdlRec rec) {
	    this.rec = rec;
	}

	public Widget createRowButtons() {
	    HorizontalPanel panel = new HorizontalPanel();
	    edit = new PushButton(WebGui.icons.paint().createImage(), this);
	    edit.setTitle(WebGui.messages.btnEditTitle());
	    panel.add(edit);
	    delete = new PushButton(WebGui.icons.trash().createImage(), this);
	    delete.setTitle(WebGui.messages.btnDeleteTitle());
	    panel.add(delete);
	    return panel;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	@SuppressWarnings("synthetic-access")
	public void onClick(Widget sender) {
	    if (sender == edit) {

	    } else if (sender == delete) {
		assert wsdlList.contains(rec) : "wsdlList does not contains required record";
		Alert.caution(WebGui.messages.msgRemoveWarning(), new Alert.Listener() {
		    public void onReply(Alert origin, Reply r) {
			if (r == Alert.Reply.OK) {
			    // wsdlList.remove(rec);
			}
		    }
		});
	    }
	}
    }

}
