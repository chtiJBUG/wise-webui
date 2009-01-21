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

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.PopupPanel;

public class ToolTip extends PopupPanel {
    private Timer timer = null;

    int displayX = 0;

    int displayY = 0;

    public ToolTip() {
	super(false);
	setAnimationEnabled(true);
	addStyleDependentName("tooltip");
    }

    public void scheduleDisplay(int x, int y) {
	if (timer == null) {
	    timer = new Timer() {
		@Override
		public void run() {
		    // coords +1 to avoid mouse-out event in Firefox
		    setPopupPosition(displayX + 1, displayY + 1);
		    show();
		}
	    };
	}
	timer.cancel();
	hide();
	displayX = x;
	displayY = y;
	timer.schedule(2000);
    }

    public void cancelDisplay() {
	if (timer != null)
	    timer.cancel();
	hide();
    }

}
