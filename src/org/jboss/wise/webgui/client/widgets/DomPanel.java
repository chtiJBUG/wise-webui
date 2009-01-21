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
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author fabrizio
 * 
 */
public class DomPanel extends Panel {

    private Element container;

    private List<Widget> children = new ArrayList<Widget>();

    private ToolTip toolTipWidget = null;

    public DomPanel(Element element) {
	this(element, element);
	sinkEvents(Event.MOUSEEVENTS);
    }

    protected DomPanel(Element element, Element container) {
	this.container = container;
	setElement(element);
    }

    @Override
    public void add(Widget child) {
	assert child != null;
	if (child.getParent() == this)
	    return;
	child.removeFromParent();
	addChild(child);
	DOM.appendChild(getContainerElement(), child.getElement());
	adopt(child);
    }

    @Override
    public boolean remove(Widget child) {
	boolean removed = removeChild(child);
	if (removed) {
	    orphan(child);
	    getContainerElement().removeChild(child.getElement());
	}
	return removed;
    }

    @Override
    public void clear() {
	Iterator<Widget> i = iterator();
	while (i.hasNext()) {
	    Widget w = i.next();
	    orphan(w);
	    getContainerElement().removeChild(w.getElement());
	}
	children.clear();
    }

    public Iterator<Widget> iterator() {
	return children.iterator();
    }

    public Element getContainerElement() {
	return container;
    }

    protected void addChild(Widget child) {
	children.add(child);
    }

    protected boolean removeChild(Widget child) {
	return children.remove(child);
    }

    public void setToolTip(Widget html) {
	if (toolTipWidget == null) {
	    toolTipWidget = new ToolTip();
	}
	if (html == null) {
	    toolTipWidget.hide();
	} else {
	    toolTipWidget.setWidget(html);
	}
    }

    @Override
    public void onBrowserEvent(Event event) {
	if (toolTipWidget != null) {
	    switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEMOVE:
		    toolTipWidget.scheduleDisplay(event.getClientX(), event.getClientY());
		    break;
		case Event.ONMOUSEOVER:
		    toolTipWidget.scheduleDisplay(event.getClientX(), event.getClientY());
		    break;
		case Event.ONMOUSEOUT:
		    toolTipWidget.cancelDisplay();
		    break;
	    }
	}
	super.onBrowserEvent(event);
    }
}