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

import java.util.Date;

import org.jboss.wise.webgui.client.widgets.Alert;
import org.jboss.wise.webgui.client.widgets.CellType;
import org.jboss.wise.webgui.client.widgets.DefaultDataType;
import org.jboss.wise.webgui.client.widgets.TreeGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * webgui entry point class
 */
public class WebGui implements EntryPoint {

    private TreeGrid getWsdlGrid() {
	TreeGrid grid = new TreeGrid();

	grid.setGridTitle("WSDL list");
	grid.setHeaderWidths("25%", "50%", "25%");
	grid.setHeaderAlign(0, "center");
	grid.setHeaderAlign(1, "center");
	grid.setHeaderAlign(2, "center");
	grid.setHeaderData("Description", "WSDL Link", "Saving Date");

	CellType descrCellType = new CellType(DefaultDataType.STRING, "25%");
	CellType wsdlLinkCellType = new CellType(DefaultDataType.STRING, "50%");

	descrCellType.setWordWrapNormal(false);
	wsdlLinkCellType.setWordWrapNormal(false);

	grid.setBodyTypes(descrCellType, wsdlLinkCellType, new CellType(DefaultDataType.DATE, "25%"));

	grid.setColumnSortable(0, 0, TreeGrid.SortType.TreeState);
	grid.setColumnSortable(1, 1, TreeGrid.SortType.TwoState);
	grid.setColumnSortable(2, 2, TreeGrid.SortType.TwoState);

	grid.setColumnSort(0, TreeGrid.SortState.Ascending);

	grid.setBodyAlign(2, "center");

	TreeGrid.GroupsLevel level = grid.addLevel();

	level.setBodyTypes(new CellType(DefaultDataType.STRING, "100%"));

	TreeGrid.Group rows;

	rows = grid.addRow("SampleService@HOST_1", "http://HOST_1:8080/Sample./SampleServiceWs?wsdl", new Date());
	rows.addRow("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In velit. Praesent posuere eros bibendum ligula.");
	rows = grid.addRow("DataService@HOST_1", "http://HOST_1:8080/Data./DataServiceWs?wsdl", new Date());
	rows.addRow("Sed consequat ligula sed arcu. Vivamus ut nunc sit amet ante volutpat rhoncus. Nullam orci. Aenean suscipit nulla et sapien. Curabitur sagittis libero a enim.");
	rows = grid.addRow("BankService@ANOTHER_HOST", "http://ANOTHER_HOST:8080/Bank./BankServiceWs?wsdl", new Date());
	rows.addRow("Sed auctor, dolor sit amet semper sollicitudin, massa purus scelerisque velit, et posuere dolor ante ut urna. Donec molestie, odio non fermentum tristique, felis mi faucibus augue, id placerat sem nisi eget libero. Nullam ante ligula, placerat ut, lacinia a, pretium quis, orci. In nec augue. Fusce dictum varius libero. Donec eu lorem. Sed vestibulum magna vitae arcu.");

	return grid;
    }

    /**
     * This is the entry point method.
     * 
     * TODO: the code here is only a place holder and it must be replaced
     */
    public void onModuleLoad() {
	try {
	    TreeGrid grid;
	    grid = getWsdlGrid();
	    grid.setWidth("100%");
	    RootPanel.get().add(grid);
	    grid.refresh();
	} catch (Exception err) {
	    err.printStackTrace();
	    Alert.error(err.getMessage());
	}
    }
}
