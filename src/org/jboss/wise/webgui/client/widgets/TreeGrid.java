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
package org.jboss.wise.webgui.client.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.TreeImages;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A grid of rows organized in one or more level
 * 
 * @todo TODO: use enumerations for align property
 * @todo TODO: let's configure the default align property for headers
 * @todo TODO: add sanity checks to parameters
 * @todo TODO: widget DOM may be created using a builder object that visits the
 *       TreeGrid data tree
 * @todo TODO: footer?
 * @todo TODO: add a Row superclass for Group so that addRow returnw always a
 *       not null object that may be used to customize the grid component
 *       created
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class TreeGrid extends Composite {

    private SimplePanel outerPanel;

    private boolean titleFrameVisible = false;

    private Widget gridTitle = null;

    TreeGridImpl impl = new TreeGridImpl();

    public static final String DOM_STYLE = "wise-Grid";

    public static final String ROOT_STYLE = "root";

    public static final String HEADER_STYLE_PREFIX = "header";

    public static final String BODY_STYLE_PREFIX = "body";

    public static final String ODD_STYLE_SUFFIX = "odd";

    public static final String EVEN_STYLE_SUFFIX = "even";

    public static final String FIRST_STYLE_SUFFIX = "first";

    public static final String LAST_STYLE_SUFFIX = "last";

    public static final String ACTIVE_STYLE_SUFFIX = "active";

    public static final String COLLAPSED_STYLE_SUFFIX = "collapsed";

    public static final String EMPTY_STYLE_SUFFIX = "empty";

    public static final String EXPANDED_STYLE_SUFFIX = "expanded";

    public static final String LEVEL_STYLE_PREFIX = "level-";

    /**
     * TreeGrid constructor
     */
    public TreeGrid() {
	outerPanel = new SimplePanel();
	initWidget(outerPanel);
    }

    /**
     * Make the grid frame visible and changes it's title
     * 
     * @param gridTitle
     *            widget to be used as title
     */
    public void setGridTitle(Widget gridTitle) {
	setTitle(gridTitle.getTitle());
	this.gridTitle = gridTitle;
	this.titleFrameVisible = true;
    }

    /**
     * Make the grid frame visible and changes it's title
     * 
     * @param gridTitle
     *            an html text to be used as title
     */
    public void setGridTitle(String gridTitle) {
	setTitle(gridTitle);
	this.gridTitle = new HTML(gridTitle);
	this.titleFrameVisible = true;
    }

    /**
     * Define the grid header types
     * 
     * @param headerTypes
     */
    public void setHeaderTypes(CellType... headerTypes) {
	impl.setHeaderTypes(headerTypes);
    }

    /**
     * Define the grid header types
     * 
     * @param headerTypes
     */
    public void setHeaderTypes(List<CellType> headerTypes) {
	impl.setHeaderTypes(headerTypes);
    }

    /**
     * Define the grid header widths All types are set to
     * 
     * @param widths
     */
    public void setHeaderWidths(String... widths) {
	impl.setHeaderWidths(widths);
    }

    /**
     * Sets the header data.
     * 
     * @param headerData
     *            Data whose type corresponds to the types passed as parameters
     *            of the setHeaderTypes method.
     */
    public void setHeaderData(Object... headerData) {
	impl.setHeaderData(headerData);
    }

    /**
     * Sets header align
     * 
     * @param col
     *            column number
     * @param align
     *            align property - "left", "center" or "right"
     */
    public void setHeaderAlign(int col, String align) {
	impl.setHeaderAlign(col, align);
    }

    /**
     * Rebuild grid DOM
     */
    public void refresh() {
	impl.removeFromParent();
	if (titleFrameVisible) {
	    Frame gridFrame = new Frame(impl);
	    outerPanel.setWidget(gridFrame);
	    if (gridTitle != null)
		gridFrame.setTitle(gridTitle);
	    gridFrame.addSummaryDetailButtons();
	    gridFrame.setWidth("100%");
	} else {
	    outerPanel.setWidget(impl);
	}
	impl.setWidth("100%");
	impl.refresh();
    }

    /**
     * Shows waiting animation
     */
    public void startWaiting() {
	impl.startWaiting();
    }

    /**
     * Hides waiting animation
     */
    public void stopWaiting() {
	impl.stopWaiting();
    }

    /**
     * Sets the cell type of each column of the grid body
     * 
     * @param bodyTypes
     */
    public void setBodyTypes(CellType... bodyTypes) {
	impl.setBodyTypes(bodyTypes);
    }

    /**
     * Sets the cell type of each column of the grid body
     * 
     * @param bodyTypes
     */
    public void setBodyTypes(List<CellType> bodyTypes) {
	impl.setBodyTypes(bodyTypes);
    }

    /**
     * Sets the alignment of eachcolumn of the grid body
     * 
     * @param col
     *            column number
     * @param align
     *            align property - "left", "center" or "right"
     */
    public void setBodyAlign(int col, String align) {
	impl.setBodyAlign(col, align);
    }

    /**
     * Add a row to the body of the grid
     * 
     * @param rowData
     *            Data whose type corresponds to the types passed as parameters
     *            of the setBodyTypes method.
     * @return if a nested level is defined, the group of rows that belongs to
     *         the added grid body row; if no nested level is defined, null.
     */
    public Group addRow(Object... rowData) {
	return impl.addRow(rowData);
    }

    public void setBodyData(Object[]... bodyData) {
	impl.setBodyData(bodyData);
    }

    /**
     * Sets the tooltip type of each column of the grid body
     * 
     * @param types
     */
    public void setToolTipsTypes(CellType... types) {
	impl.setToolTipsTypes(types);
    }

    /**
     * @param toolTipsRow
     *            Data whose type corresponds to the types passed as parameters
     *            of the {@link #setToolTipsTypes} method.
     */
    public void addToolTips(Object... toolTipsRow) {
	impl.addToolTips(toolTipsRow);
    }

    /**
     * Add a nested level to the table. Level name is the default level name +
     * "1"
     * 
     * @return the level added
     */
    public GroupsLevel addLevel() {
	return impl.addGroups();
    }

    /**
     * Add a nested level to the table.
     * 
     * @param levelName
     *            the name of the level - null to use the default name
     * @return the level added
     */
    public GroupsLevel addLevel(String levelName) {
	return impl.addGroups(levelName);
    }

    /**
     * Configure the grid to be sorted by a column
     * 
     * @param targetColumn
     *            the column to be used to sort the rows
     * @param buttonColumn
     *            the header column where to place the sort button
     * @param sortType
     *            TwoState (default, ascending or descending) or TreeState
     *            (ascending or descending)
     */
    public void setColumnSortable(int targetColumn, int buttonColumn, SortType sortType) {
	impl.setColumnSortable(targetColumn, buttonColumn, sortType);
    }

    /**
     * Sets current sort of the grid
     * 
     * @param targetColumn
     *            the column whose data determine the row ordering
     * @param state
     *            sorting rule - Default, Ascending and Descending
     */
    public void setColumnSort(int targetColumn, SortState state) {
	impl.setColumnSort(targetColumn, state);
    }

    public boolean isTitleFrameVisible() {
	return titleFrameVisible;
    }

    /**
     * Configure the grid to show or hide the grid frame
     * 
     * @param frameVisible
     */
    public void setTitleFrameVisible(boolean frameVisible) {
	this.titleFrameVisible = frameVisible;
    }

    @Override
    public void setWidth(String width) {
	outerPanel.setWidth("100%");
    }

    public enum SortType {
	TwoState, TreeState;

	public SortState getDefaultState() {
	    if (this == TwoState)
		return SortState.Ascending;
	    return SortState.Default;
	}
    }

    public enum SortState {
	Default, Ascending, Descending
    }

    private class Frame extends Composite {

	private VerticalPanel mainWidget;

	private HorizontalPanel titleBar;

	private SimplePanel titlePanel;

	private HorizontalPanel summaryDetailPanel;

	public Frame(TreeGridImpl content) {
	    mainWidget = new VerticalPanel();
	    mainWidget.setWidth("100%");
	    mainWidget.addStyleName(DOM_STYLE + "-frame");
	    titleBar = new HorizontalPanel();
	    titleBar.setWidth("100%");
	    titleBar.addStyleName(DOM_STYLE + "-frame-titleBar");
	    titlePanel = new SimplePanel();
	    titleBar.add(titlePanel);
	    titleBar.setCellVerticalAlignment(titlePanel, HasVerticalAlignment.ALIGN_MIDDLE);
	    summaryDetailPanel = new HorizontalPanel();
	    titleBar.add(summaryDetailPanel);
	    titleBar.setCellVerticalAlignment(summaryDetailPanel, HasVerticalAlignment.ALIGN_MIDDLE);
	    titleBar.setCellHorizontalAlignment(summaryDetailPanel, HasHorizontalAlignment.ALIGN_RIGHT);
	    mainWidget.add(titleBar);
	    content.setWidth("100%");
	    content.setHeight("100%");
	    mainWidget.add(content);
	    initWidget(mainWidget);
	}

	@Override
	public void setHeight(String height) {
	    mainWidget.setHeight(height);
	}

	@Override
	public void setWidth(String width) {
	    mainWidget.setWidth(width);
	}

	public void setTitle(Widget title) {
	    titlePanel.setWidget(title);
	}

	void addSummaryDetailButtons() {
	    GroupsLevel level = impl.getBodyLevel();
	    while (level != null) {
		if (level.isSummaryDetailButtonsVisibile()) {
		    Button summaryDetailBtn = new Button(level.getSummaryDetailButtonTitle());
		    summaryDetailPanel.add(summaryDetailBtn);
		    final GroupsLevel buttonLevel = level;
		    summaryDetailBtn.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
			    boolean allOpen = buttonLevel.getAllGroupsOpen();
			    buttonLevel.setAllGroupsOpen(!allOpen); // 
			    impl.refresh();
			}
		    });
		}
		level = level.getChildLevel();
	    }
	}
    }

    private class TreeGridImpl extends TableFrame {

	private final Image waitingImg;

	List<CellType> headerTypes = null;

	private Object[] headerData = null;

	private GroupsLevel bodyLevel;

	private Group bodyGroup;

	private HeaderRow headerWidget = null;

	private GroupsLevelWidget levelWidget = null;

	TreeImages treeIcons;

	IconsBundle icons;

	public TreeGridImpl() {
	    super();
	    treeIcons = (TreeImages) GWT.create(TreeImages.class);
	    icons = (IconsBundle) GWT.create(IconsBundle.class);
	    waitingImg = icons.waiting().createImage();
	    bodyLevel = new GroupsLevel(null, ROOT_STYLE);
	    bodyGroup = new Group(bodyLevel, null);
	    setStyleName(DOM_STYLE);
	}

	public GroupsLevel addGroups() {
	    return bodyLevel.addGroups(LEVEL_STYLE_PREFIX + "1");
	}

	public GroupsLevel addGroups(String levelName) {
	    return bodyLevel.addGroups(levelName);
	}

	public void setHeaderTypes(CellType... headerTypes) {
	    List<CellType> typesList = new ArrayList<CellType>();
	    for (CellType type : headerTypes) {
		typesList.add(type);
	    }
	    setHeaderTypes(typesList);
	}

	public void setHeaderTypes(List<CellType> headerTypes) {
	    if (headerTypes == null) {
		this.headerTypes = defaultHeaderTypes();
	    } else {
		this.headerTypes = headerTypes;
	    }
	}

	public void setHeaderWidths(String... widths) {
	    List<CellType> typesList = new ArrayList<CellType>();
	    for (String width : widths) {
		typesList.add(new CellType(DefaultDataType.STRING, width));
	    }
	    setHeaderTypes(typesList);
	}

	public void setHeaderAlign(int col, String align) {
	    assert col >= 0 && col < headerTypes.size();
	    if (headerTypes == null) {
		headerTypes = defaultHeaderTypes();
	    }
	    assert headerTypes != null;
	    assert col >= 0 && col < headerTypes.size();
	    headerTypes.get(col).setAlign(align);
	}

	public void setBodyAlign(int col, String align) {
	    bodyLevel.setBodyAlign(col, align);
	}

	public void setHeaderData(Object... headerData) {
	    this.headerData = headerData;
	}

	public void setColumnSortable(int targetColumn, int buttonColumn, SortType sortType) {
	    bodyLevel.setColumnSortable(targetColumn, buttonColumn, sortType);
	}

	public void setColumnSort(int targetColumn, SortState state) {
	    bodyLevel.setColumnSort(targetColumn, state);
	}

	public GroupsLevel getBodyLevel() {
	    return bodyLevel;
	}

	public Group getBodyGroup() {
	    return bodyGroup;
	}

	public void refresh() {
	    clear();
	    sort();
	    getContainerElement().setClassName(bodyLevel.getLevelName());
	    if (headerTypes == null) {
		headerTypes = defaultHeaderTypes();
	    }
	    if (headerTypes != null) {
		// if (headerWidget == null) {
		headerWidget = new HeaderRow(headerTypes, headerData, bodyGroup);
		add(headerWidget);
		// }
		headerWidget.refresh();
		headerWidget.addSortButtons();
	    }
	    // if (levelWidget == null) {
	    levelWidget = new GroupsLevelWidget(bodyGroup);
	    add(levelWidget);
	    levelWidget.addStyleDependentName(FIRST_STYLE_SUFFIX);
	    levelWidget.addStyleDependentName(LAST_STYLE_SUFFIX);
	    // }
	    levelWidget.refresh();
	}

	public void startWaiting() {
	    TreeGridImpl.this.add(waitingImg);
	}

	public void stopWaiting() {
	    TreeGridImpl.this.remove(waitingImg);
	}

	public void setBodyTypes(CellType... types) {
	    bodyLevel.setBodyTypes(types);
	}

	public void setBodyTypes(List<CellType> types) {
	    bodyLevel.setBodyTypes(types);
	}

	public Group addRow(Object... rowData) {
	    return bodyGroup.addRow(rowData);
	}

	public void setBodyData(Object[]... bodyData) {
	    List<Object[]> dataList = new ArrayList<Object[]>();
	    for (Object[] rowData : bodyData) {
		dataList.add(rowData);
	    }
	    setBodyData(dataList);
	}

	public void setBodyData(List<Object[]> bodyData) {
	    bodyGroup.setBodyData(bodyData);
	}

	public void setToolTipsTypes(CellType... types) {
	    bodyLevel.setToolTipsTypes(types);
	}

	public void addToolTips(Object... toolTipsRow) {
	    bodyGroup.addToolTips(toolTipsRow);
	}

	private void sort() {
	    GroupsLevel level = bodyLevel;
	    while (level != null) {
		level.sort();
		level = level.getChildLevel();
	    }
	}

	private List<CellType> defaultHeaderTypes() {
	    if (headerData == null || headerData.length == 0)
		return null;
	    List<CellType> types = new ArrayList<CellType>();
	    String perc = String.valueOf(100 / headerData.length) + "%";
	    for (Object data : headerData) {
		types.add(new CellType(DefaultDataType.typeFor(data), perc));
	    }
	    return types;
	}

	private class HeaderRow extends SimpleTable implements ClickListener {

	    private Group childGroup;

	    private List<CellType> headerTypes;

	    private Object[] headerData;

	    private Row row;

	    private SimplePanel disclosurePanel;

	    private SimplePanel[] titlePanels;

	    private SimplePanel[] sortPanels;

	    private ToggleButton disclosureButton;

	    public HeaderRow(List<CellType> headerTypes, Object[] headerData, Group childGroup) {
		assert headerTypes.size() == headerData.length;
		this.headerTypes = headerTypes;
		this.headerData = headerData;
		this.childGroup = childGroup;

		setWidth("100%");
		setStyleName(HEADER_STYLE_PREFIX);

		row = this.addRow();
		row.setStylePrimaryName(HEADER_STYLE_PREFIX + "-row");
		row.addStyleDependentName(FIRST_STYLE_SUFFIX);
		row.addStyleDependentName(LAST_STYLE_SUFFIX);

		titlePanels = new SimplePanel[headerData.length];
		sortPanels = new SimplePanel[headerData.length];
		disclosurePanel = new SimplePanel();

		int c = 0;
		for (CellType ctype : headerTypes) {
		    DomPanel cell = row.addHeaderCell();

		    cell.setStylePrimaryName(HEADER_STYLE_PREFIX + "-cell");

		    DomPanel cellContentArea = new DomPanel(DOM.createDiv());
		    cellContentArea.setStylePrimaryName(HEADER_STYLE_PREFIX + "-cell-content-area");
		    if (!ctype.isWordWrapNormal()) {
			cellContentArea.addStyleDependentName("break-words");
		    }
		    cell.add(cellContentArea);

		    String colStyle = ctype.getStyleDependentName();
		    String colAlign = ctype.getAlign();
		    cell.setWidth(ctype.getWidth());
		    if (colStyle != null)
			cell.addStyleDependentName(colStyle);
		    if (colAlign != null)
			cell.getElement().setAttribute("align", colAlign);
		    if (c == 0) {
			cell.addStyleDependentName(FIRST_STYLE_SUFFIX);
		    }
		    if (c == headerTypes.size() - 1) {
			cell.addStyleDependentName(LAST_STYLE_SUFFIX);
		    }

		    SortInfo activeSortInfo = childGroup.getGroupsLevel().getActiveSortInfo();
		    if (activeSortInfo != null && activeSortInfo.getTargetColumn() == c) {
			cell.addStyleDependentName(ACTIVE_STYLE_SUFFIX);
		    }

		    HorizontalPanel cellLayout = new HorizontalPanel();
		    cellContentArea.add(cellLayout);
		    if (c == 0)
			cellLayout.add(disclosurePanel);
		    cellLayout.add(titlePanels[c] = new SimplePanel());
		    cellLayout.add(sortPanels[c] = new SimplePanel());
		    if ("center".equals(colAlign))
			cellLayout.setCellHorizontalAlignment(titlePanels[c], HasHorizontalAlignment.ALIGN_CENTER);
		    else if ("left".equals(colAlign))
			cellLayout.setCellHorizontalAlignment(titlePanels[c], HasHorizontalAlignment.ALIGN_LEFT);
		    else if ("right".equals(colAlign))
			cellLayout.setCellHorizontalAlignment(titlePanels[c], HasHorizontalAlignment.ALIGN_RIGHT);
		    c++;
		}
	    }

	    public void refresh() {
		for (int c = 0; c < headerData.length; c++) {
		    CellType ctype = headerTypes.get(c);
		    Widget cw = ctype.getDataType().formatAsWidget(headerData[c], ctype.getSimpleFormat());
		    titlePanels[c].setWidget(cw);
		}
	    }

	    public void addDisclosureButtons() {
		disclosureButton = new ToggleButton(treeIcons.treeClosed().createImage(), treeIcons.treeOpen().createImage());
		disclosureButton.setDown(childGroup.isOpen());
		disclosureButton.setStyleName("lynx-GroupHeader-ToggleButton");
		disclosurePanel.add(disclosureButton);
		disclosureButton.addClickListener(this);
	    }

	    public void addSortButtons() {
		for (SimplePanel sortPanel : sortPanels) {
		    sortPanel.setWidget(new HorizontalPanel());
		}
		List<SortInfo> sortInfos = childGroup.getGroupsLevel().collectSortInfo();
		for (final SortInfo sortInfo : sortInfos) {
		    int col = sortInfo.getTargetColumn();
		    if (col >= 0 && col < sortPanels.length) {
			HorizontalPanel hPanel = (HorizontalPanel) (sortPanels[col].getWidget());
			Image img = null;
			if (childGroup.getGroupsLevel().getActiveSortInfo() == sortInfo) {
			    if (sortInfo.getCurrentState() == SortState.Ascending) {
				img = icons.downActive().createImage();
			    } else if (sortInfo.getCurrentState() == SortState.Descending) {
				img = icons.upActive().createImage();
			    } else if (sortInfo.getCurrentState() == SortState.Default) {
				img = icons.defaultSortActive().createImage();
			    }
			} else {
			    if (sortInfo.getCurrentState() == SortState.Ascending) {
				img = icons.down().createImage();
			    } else if (sortInfo.getCurrentState() == SortState.Descending) {
				img = icons.up().createImage();
			    } else if (sortInfo.getCurrentState() == SortState.Default) {
				img = icons.defaultSort().createImage();
			    }
			}
			if (img != null) {
			    hPanel.add(img);
			    img.addStyleName(DOM_STYLE + "-ctrl-button");
			    img.addClickListener(new ClickListener() {
				public void onClick(Widget sender) {
				    rotateSort(sortInfo);
				    TreeGridImpl.this.refresh();
				}
			    });
			}
		    }
		}
	    }

	    void rotateSort(SortInfo sortInfo) {
		SortState newState;
		if (sortInfo.getSortType() == SortType.TwoState) {
		    if (sortInfo.getCurrentState() == SortState.Descending)
			newState = SortState.Ascending;
		    else
			newState = SortState.Descending;
		} else if (sortInfo.getSortType() == SortType.TreeState) {
		    if (sortInfo.getCurrentState() == SortState.Default)
			newState = SortState.Ascending;
		    else if (sortInfo.getCurrentState() == SortState.Ascending)
			newState = SortState.Descending;
		    else
			newState = SortState.Default;
		} else {
		    throw new AssertionError("unknown sort type");
		}
		sortInfo.getTargetGroupLevel().setColumnSort(sortInfo.getTargetColumn(), newState);
	    }

	    public void onClick(Widget sender) {
		if (sender == disclosureButton) {
		    if (disclosureButton.isDown()) {
			childGroup.setOpen(true);
		    } else {
			childGroup.setOpen(false);
		    }
		    TreeGridImpl.this.refresh();
		}
	    }
	}

	private class GroupsLevelWidget extends Composite {

	    private Refreshable impl;

	    public GroupsLevelWidget(Group bodyGroup) {
		if (bodyGroup.getGroupsLevel().getChildLevel() != null) {
		    impl = new MidLevelWidget(bodyGroup);
		} else {
		    impl = new LeafLevelWidget(bodyGroup);
		}
		initWidget(impl);
	    }

	    public void refresh() {
		impl.refresh();
	    }

	    private abstract class Refreshable extends Composite {

		protected Group group;

		public Refreshable(Group group) {
		    this.group = group;
		}

		public GroupsLevel getLevel() {
		    return group.getGroupsLevel();
		}

		public Group getGroup() {
		    return group;
		}

		public abstract void refresh();
	    }

	    private class MidLevelWidget extends Refreshable {

		private DomPanel bodyDiv;

		public MidLevelWidget(Group group) {
		    super(group);
		    bodyDiv = new TableFrame();
		    bodyDiv.setWidth("100%");
		    bodyDiv.setStyleName(BODY_STYLE_PREFIX);
		    initWidget(bodyDiv);
		}

		@Override
		public void refresh() {
		    String childLevelName = group.getGroupsLevel().getChildLevel().getLevelName();
		    int rc = group.getRowsCount();
		    for (int r = 0; r < rc; r++) {
			Group childGroup = group.getRowGroup(r);

			DomPanel classDiv = new DomPanel(DOM.createDiv());
			classDiv.setStyleName(childLevelName);
			if (r == 0)
			    classDiv.addStyleDependentName(FIRST_STYLE_SUFFIX);
			if (r == rc - 1)
			    classDiv.addStyleDependentName(LAST_STYLE_SUFFIX);

			HeaderRow headerWidget = new HeaderRow(group.getGroupsLevel().getBodyTypes(), group.getRowData(r), childGroup);
			headerWidget.setWidth("100%");
			headerWidget.refresh();

			if (childGroup.getRowsCount() == 0)
			    classDiv.addStyleDependentName(EMPTY_STYLE_SUFFIX);
			else
			    headerWidget.addDisclosureButtons();

			classDiv.add(headerWidget);

			if (childGroup.isOpen()) {
			    classDiv.addStyleDependentName(EXPANDED_STYLE_SUFFIX);
			    GroupsLevelWidget levelWidget = new GroupsLevelWidget(childGroup);
			    levelWidget.setWidth("100%");
			    levelWidget.refresh();
			    classDiv.add(levelWidget);
			} else {
			    classDiv.addStyleDependentName(COLLAPSED_STYLE_SUFFIX);
			}

			bodyDiv.add(classDiv);
		    }
		}
	    }

	    private class LeafLevelWidget extends Refreshable {

		private SimpleTable table;

		private List<Row> rows = new ArrayList<Row>();

		public LeafLevelWidget(Group group) {
		    super(group);
		    table = new SimpleTable();
		    table.setStyleName(BODY_STYLE_PREFIX);
		    table.setWidth("100%");
		    initWidget(table);
		}

		@Override
		public void refresh() {
		    int rc = group.getRowsCount();
		    for (int r = 0; r < rc; r++) {
			Object[] rowData = group.getRowData(r);
			Object[] toolTipsData = null;
			List<CellType> toolTipsTypes = group.getGroupsLevel().getToolTipsTypes();
			if (toolTipsTypes != null)
			    toolTipsData = group.getToolTipsData(r);
			Row row = table.addRow();
			row.setStylePrimaryName(BODY_STYLE_PREFIX + "-row");
			if (r == 0)
			    row.addStyleDependentName(FIRST_STYLE_SUFFIX);
			if (r == rc - 1)
			    row.addStyleDependentName(LAST_STYLE_SUFFIX);
			if (r % 2 == 0)
			    row.addStyleDependentName(EVEN_STYLE_SUFFIX);
			else
			    row.addStyleDependentName(ODD_STYLE_SUFFIX);
			int c = 0;
			assert group.getGroupsLevel().getBodyTypes().size() == rowData.length : "types.size (" + group.getGroupsLevel().getBodyTypes().size() + ") != data.size (" + rowData.length + ") - " + group.getGroupsLevel().getLevelName();

			for (CellType ctype : group.getGroupsLevel().getBodyTypes()) {

			    DomPanel cell = row.addBodyCell();
			    DomPanel cellContentArea = new DomPanel(DOM.createDiv());
			    cellContentArea.setStylePrimaryName(BODY_STYLE_PREFIX + "-cell-content-area");
			    if (!ctype.isWordWrapNormal()) {
				cellContentArea.addStyleDependentName("break-words");
			    }
			    cell.add(cellContentArea);
			    String colStyle = ctype.getStyleDependentName();
			    String colAlign = ctype.getAlign();
			    cell.setWidth(ctype.getWidth());
			    cell.setStylePrimaryName(BODY_STYLE_PREFIX + "-cell");
			    if (colStyle != null)
				cell.addStyleDependentName(colStyle);
			    if (colAlign != null)
				cell.getElement().setAttribute("align", colAlign);
			    if (c == 0) {
				cell.addStyleDependentName(FIRST_STYLE_SUFFIX);
			    }
			    if (c == headerTypes.size() - 1) {
				cell.addStyleDependentName(LAST_STYLE_SUFFIX);
			    }
			    SortInfo activeSortInfo = group.getGroupsLevel().getActiveSortInfo();
			    if (activeSortInfo != null && activeSortInfo.getTargetGroupLevel() == group.getGroupsLevel() && activeSortInfo.getTargetColumn() == c) {
				cell.addStyleDependentName(ACTIVE_STYLE_SUFFIX);
			    }
			    Widget dataWidget = ctype.getDataType().formatAsWidget(rowData[c], ctype.getSimpleFormat());
			    if (dataWidget != null)
				cellContentArea.add(dataWidget);

			    if (toolTipsData != null && toolTipsTypes != null) {
				CellType toolTipType = toolTipsTypes.get(c);
				if (toolTipType != null) {
				    Widget toolTipWidget = toolTipType.getDataType().formatAsWidget(toolTipsData[c], toolTipType.getSimpleFormat());
				    cell.setToolTip(toolTipWidget);
				}
			    }

			    c++;
			}
			rows.add(row);
		    }
		}
	    }

	}

    }

    private class SortInfo {

	private GroupsLevel targetGroupLevel;

	private int targetColumn;

	private int buttonColumn;

	private SortType sortType;

	private SortState currentState;

	public SortInfo(GroupsLevel targetGroupLevel, int targetColumn, int buttonColumn, SortType sortType) {
	    super();
	    this.targetGroupLevel = targetGroupLevel;
	    this.targetColumn = targetColumn;
	    this.buttonColumn = buttonColumn;
	    this.sortType = sortType;
	    if (this.sortType == SortType.TwoState)
		this.currentState = SortState.Ascending;
	    else
		this.currentState = SortState.Default;
	}

	public GroupsLevel getTargetGroupLevel() {
	    return targetGroupLevel;
	}

	public SortState getCurrentState() {
	    return currentState;
	}

	public void setCurrentState(SortState currentState) {
	    this.currentState = currentState;
	}

	public int getTargetColumn() {
	    return targetColumn;
	}

	public int getButtonColumn() {
	    return buttonColumn;
	}

	public SortType getSortType() {
	    return sortType;
	}
    }

    public class GroupsLevel {

	private GroupsLevel parentLevel;

	private GroupsLevel childLevel;

	private String levelName;

	private List<SortInfo> columnSortInfo = new ArrayList<SortInfo>();

	private List<CellType> bodyTypes = null;

	private List<CellType> toolTipsTypes = null;

	private List<Group> levelGroups = new ArrayList<Group>();

	private SortInfo activeSortInfo = null;

	private boolean summaryDetailButtonsVisibile = false;

	private String buttonTitle;

	public List<SortInfo> collectSortInfo() {
	    List<SortInfo> collected = new ArrayList<SortInfo>(columnSortInfo);
	    GroupsLevel subLevel = childLevel;
	    while (subLevel != null) {
		collected.addAll(subLevel.collectSortInfo());
		subLevel = subLevel.getChildLevel();
	    }
	    return collected;
	}

	GroupsLevel(GroupsLevel parentLevel, String levelName) {
	    this.parentLevel = parentLevel;
	    this.levelName = levelName;
	    this.childLevel = null;
	}

	public GroupsLevel addGroups() {
	    GroupsLevel l = parentLevel;
	    int n = 2;
	    while (l != null) {
		l = l.parentLevel;
		n++;
	    }
	    String levelName = LEVEL_STYLE_PREFIX + String.valueOf(n);
	    childLevel = new GroupsLevel(this, levelName);
	    return childLevel;
	}

	public GroupsLevel addGroups(String levelName) {
	    childLevel = new GroupsLevel(this, levelName);
	    return childLevel;
	}

	public void setBodyTypes(CellType... types) {
	    List<CellType> typesList = new ArrayList<CellType>();
	    for (CellType type : types) {
		typesList.add(type);
	    }
	    setBodyTypes(typesList);
	}

	public void setBodyTypes(List<CellType> bodyTypes) {
	    this.bodyTypes = bodyTypes;
	}

	public void setToolTipsTypes(CellType... types) {
	    List<CellType> typesList = new ArrayList<CellType>();
	    for (CellType type : types) {
		typesList.add(type);
	    }
	    setToolTipsTypes(typesList);
	}

	public void setToolTipsTypes(List<CellType> types) {
	    this.toolTipsTypes = types;
	}

	public void setColumnSortable(int targetColumn, int buttonColumn, SortType sortType) {
	    int pos = findTargetColumnInfo(targetColumn);
	    if (pos >= 0) {
		columnSortInfo.remove(pos);
	    }
	    columnSortInfo.add(new SortInfo(this, targetColumn, buttonColumn, sortType));
	    setColumnSort(targetColumn, sortType.getDefaultState());
	}

	public void setColumnSort(int targetColumn, SortState state) {
	    int infoPos = findTargetColumnInfo(targetColumn);
	    if (infoPos >= 0) {
		if (infoPos == columnSortInfo.size() - 1) {
		    activeSortInfo = columnSortInfo.get(infoPos);
		    activeSortInfo.setCurrentState(state);
		} else {
		    activeSortInfo = columnSortInfo.remove(infoPos);
		    activeSortInfo.setCurrentState(state);
		    columnSortInfo.add(activeSortInfo);
		}
		propagateActiveSortInfo();
	    }
	}

	public boolean isSummaryDetailButtonsVisibile() {
	    return summaryDetailButtonsVisibile;
	}

	String getSummaryDetailButtonTitle() {
	    return buttonTitle;
	}

	public void addSummaryDetailButtonToFrame(String buttonTitle) {
	    TreeGrid.this.setTitleFrameVisible(true);
	    this.summaryDetailButtonsVisibile = true;
	    this.buttonTitle = buttonTitle;
	}

	public void removeSummaryDetailButtonFromFrame() {
	    this.summaryDetailButtonsVisibile = false;
	}

	public List<CellType> getBodyTypes() {
	    return bodyTypes;
	}

	public List<CellType> getToolTipsTypes() {
	    return toolTipsTypes;
	}

	public GroupsLevel findLevel(String levelName) {
	    if (this.levelName.equals(levelName)) {
		return this;
	    } else if (this.childLevel != null) {
		return this.childLevel.findLevel(levelName);
	    } else {
		return null;
	    }
	}

	public GroupsLevel getChildLevel() {
	    return childLevel;
	}

	public String getLevelName() {
	    return this.levelName;
	}

	public List<Group> getLevelGroups() {
	    return levelGroups;
	}

	public void setBodyAlign(int col, String align) {
	    if (bodyTypes == null) {
		bodyTypes = defaultBodyTypes();
	    }
	    assert bodyTypes != null : "body types not defined!";
	    assert col >= 0 && col < bodyTypes.size() : "column number wrong!";
	    bodyTypes.get(col).setAlign(align);

	}

	public SortInfo getActiveSortInfo() {
	    return activeSortInfo;
	}

	public void setAllGroupsOpen(boolean open) {
	    for (Group group : levelGroups) {
		group.setOpen(open);
	    }
	}

	public boolean getAllGroupsOpen() {
	    for (Group group : levelGroups) {
		if (!group.isOpen())
		    return false;
	    }
	    return true;
	}

	public boolean getAllGroupsClosed() {
	    for (Group group : levelGroups) {
		if (group.isOpen())
		    return false;
	    }
	    return true;
	}

	void addGroup(Group newGroup) {
	    levelGroups.add(newGroup);
	}

	void sort() {
	    for (Group group : levelGroups) {
		group.sortBy(columnSortInfo);
	    }
	}

	private int findTargetColumnInfo(int column) {
	    int p = 0;
	    for (SortInfo si : columnSortInfo) {
		if (si.getTargetColumn() == column)
		    return p;
		p++;
	    }
	    return -1;
	}

	private void propagateActiveSortInfo() {
	    GroupsLevel level = parentLevel;
	    while (level != null) {
		level.activeSortInfo = activeSortInfo;
		level = level.parentLevel;
	    }
	    level = childLevel;
	    while (level != null) {
		level.activeSortInfo = activeSortInfo;
		level = level.childLevel;
	    }
	}

	private List<CellType> defaultBodyTypes() {
	    // TODO may be used?
	    return null;
	}

    }

    public class Group {

	GroupsLevel level;

	private Group parentLevel;

	private List<TreeGroupData> bodyData = null;

	private List<Object[]> toolTipsData = null;

	private TreeGroupData sortedBodyData[] = null;

	private GroupComparator sortComparator = null;

	private boolean open = true;

	Group(GroupsLevel groupLevel, Group parentLevel) {
	    this.level = groupLevel;
	    this.parentLevel = parentLevel;
	    groupLevel.addGroup(this);
	}

	public void setOpen(boolean open) {
	    this.open = open;
	}

	public boolean isOpen() {
	    return this.open;
	}

	public void setBodyData(Object[]... bodyData) {
	    for (Object[] rowData : bodyData) {
		addRow(rowData);
	    }
	}

	public void setBodyData(List<Object[]> bodyData) {
	    for (Object[] rowData : bodyData) {
		addRow(rowData);
	    }
	}

	public void setBodyTooltips(Object[]... toolTipsData) {
	    for (Object[] toolTipsRow : toolTipsData) {
		addToolTips(toolTipsRow);
	    }
	}

	public void setBodyTooltips(List<Object[]> toolTipsData) {
	    for (Object[] toolTipsRow : toolTipsData) {
		addToolTips(toolTipsRow);
	    }
	}

	public Group addRow(Object... rowData) {
	    Group childGroup = null;
	    if (this.bodyData == null) {
		this.bodyData = new ArrayList<TreeGroupData>();
	    }
	    if (level.getChildLevel() != null) {
		childGroup = new Group(level.getChildLevel(), this);
	    }
	    bodyData.add(new TreeGroupData(rowData, childGroup, bodyData.size()));
	    sortedBodyData = null;
	    return childGroup;
	}

	public Group getParentGroup() {
	    return parentLevel;
	}

	public GroupsLevel getGroupsLevel() {
	    return level;
	}

	public void addToolTips(Object... toolTipsRow) {
	    if (this.toolTipsData == null) {
		this.toolTipsData = new ArrayList<Object[]>();
	    }
	    this.toolTipsData.add(toolTipsRow);
	}

	void sortBy(List<SortInfo> columnsSort) {
	    if (this.bodyData == null) {
		this.bodyData = new ArrayList<TreeGroupData>();
	    }
	    if (sortComparator == null) {
		sortComparator = new GroupComparator(columnsSort);
	    }
	    if (sortedBodyData == null) {
		sortedBodyData = bodyData.toArray(new TreeGroupData[bodyData.size()]);
	    }
	    Arrays.sort(sortedBodyData, sortComparator);
	}

	int getRowsCount() {
	    if (this.bodyData == null) {
		this.bodyData = new ArrayList<TreeGroupData>();
	    }
	    if (sortedBodyData == null) {
		return bodyData.size();
	    }
	    return sortedBodyData.length;
	}

	Object[] getRowData(int r) {
	    if (sortedBodyData == null) {
		return bodyData.get(r).getData();
	    }
	    return sortedBodyData[r].getData();
	}

	Object[] getToolTipsData(int r) {
	    assert this.toolTipsData.size() == bodyData.size();
	    if (sortedBodyData == null) {
		return toolTipsData.get(r);
	    }
	    return toolTipsData.get(sortedBodyData[r].getOriginalPos());
	}

	Group getRowGroup(int r) {
	    if (sortedBodyData == null) {
		return bodyData.get(r).getGroup();
	    }
	    return sortedBodyData[r].getGroup();
	}

	private class TreeGroupData {

	    private int originalPos;

	    private Object[] data;

	    private Group group;

	    public TreeGroupData(Object[] data, Group group, int originalPos) {
		super();
		this.data = data;
		this.group = group;
		this.originalPos = originalPos;
	    }

	    public Object[] getData() {
		return data;
	    }

	    public void setData(Object[] data) {
		this.data = data;
	    }

	    public Group getGroup() {
		return group;
	    }

	    public void setGroup(Group group) {
		this.group = group;
	    }

	    public int getOriginalPos() {
		return originalPos;
	    }

	}

	private class GroupComparator implements Comparator<TreeGroupData> {

	    private List<SortInfo> columnsSort;

	    public GroupComparator(List<SortInfo> columnsSort) {
		this.columnsSort = columnsSort;
	    }

	    public int compare(TreeGroupData o1, TreeGroupData o2) {
		int cr;
		for (int col = columnsSort.size() - 1; col >= 0; col--) {
		    SortInfo info = columnsSort.get(col);
		    int targetCol = info.getTargetColumn();
		    assert targetCol >= 0;
		    assert targetCol < o1.getData().length;
		    assert targetCol < o2.getData().length;

		    if (info.getCurrentState() == SortState.Default) {
			cr = o1.getOriginalPos() - o2.getOriginalPos();
			if (cr != 0)
			    return cr;
		    } else {
			Object d1 = o1.getData()[targetCol];
			Object d2 = o2.getData()[targetCol];
			CellType ct = level.getBodyTypes().get(targetCol);
			DataType dt = ct.getDataType();
			Comparator<Object> cmp = dt.getComparator();
			if (cmp != null) {
			    cr = cmp.compare(d1, d2);
			    if (cr != 0) {
				if (info.getCurrentState() == SortState.Descending) {
				    cr = -cr;
				}
				return cr;
			    }
			}
		    }
		}
		return 0;
	    }

	}

    }

    private class TableFrame extends DomPanel {

	public TableFrame() {
	    super(DOM.createTable(), DOM.createTD());
	    Element body = DOM.createTBody();
	    Element row = DOM.createTR();
	    DOM.appendChild(row, getContainerElement());
	    DOM.appendChild(body, row);
	    DOM.appendChild(getElement(), body);
	}

    }

    private class SimpleTable extends DomPanel {

	public SimpleTable() {
	    super(DOM.createTable(), DOM.createTBody());
	    DOM.appendChild(getElement(), getContainerElement());
	}

	public Row addRow() {
	    Row r = new Row();
	    add(r);
	    return r;
	}

    }

    private class Row extends DomPanel {
	public Row() {
	    super(DOM.createTR());
	}

	public DomPanel addHeaderCell() {
	    DomPanel c = new DomPanel(DOM.createTH());
	    add(c);
	    return c;
	}

	public DomPanel addBodyCell() {
	    DomPanel c = new DomPanel(DOM.createTD());
	    add(c);
	    return c;
	}
    }
}
