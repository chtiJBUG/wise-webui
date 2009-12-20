package org.jboss.wise.gui.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Wise_gui implements EntryPoint {

    public void onModuleLoad() {
	LoginDialog.activate();
    }
}
