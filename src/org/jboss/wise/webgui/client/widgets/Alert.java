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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Message dialog window
 * 
 * @todo TODO: move message strings into a resource bundle
 * @todo TODO: allow customization of alert properties like size and location
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class Alert extends DialogBox {

    private static final String ALERT_W = "320px";

    public static final String OK_BUTTON_TEXT = "OK";

    public static final String CANCEL_BUTTON_TEXT = "Cancel";

    public static final String NOTE_DIALOG_TITLE = "Note:";

    public static final String ERROR_DIALOG_TITLE = "Error!";

    public static final String WARNING_DIALOG_TITLE = "Warning...";

    /**
     * Implements this interface to listen alert events
     */
    public interface Listener {
	void onReply(Alert origin, Reply r);
    }

    /**
     * Reply the alert could send back to the caller that depends on the button
     * the user clicked
     */
    public enum Reply {
	OK(OK_BUTTON_TEXT), CANCEL(CANCEL_BUTTON_TEXT);

	private String text;

	Reply(String t) {
	    text = t;
	}

	String getText() {
	    return text;
	}
    }

    /**
     * Alert types
     */
    public enum Type {
	MESSAGE(NOTE_DIALOG_TITLE, Reply.OK), ERROR(ERROR_DIALOG_TITLE, Reply.OK), CAUTION(WARNING_DIALOG_TITLE, Reply.CANCEL, Reply.OK);

	private String message;

	private Reply replySet[];

	Type(String m, Reply... rs) {
	    message = m;
	    replySet = rs;
	}

	String getMessage() {
	    return message;
	}

	Reply[] getReplySet() {
	    return replySet;
	}
    }

    private static class ReplyButtonClickListener implements ClickListener {
	private Reply reply;

	private Alert alert;

	public ReplyButtonClickListener(Reply r, Alert a) {
	    reply = r;
	    alert = a;
	}

	public void onClick(Widget sender) {
	    alert.hide();
	    if (alert.listener != null)
		alert.listener.onReply(alert, reply);
	}

    }

    /**
     * Open an alert of the specified type
     * 
     * @param t
     *            alert type
     * @param m
     *            the text message to be displayed
     * @return the button clicked by the user
     */
    public static Alert open(Type t, String m) {
	final Alert a = new Alert(m, t);
	a.show();
	return a;
    }

    /**
     * Open an alert of the specified type with a custom listener
     * 
     * @param t
     *            the alert type
     * @param m
     *            the text message to be displayed
     * @param l
     *            the listener that handle the user choice
     * @return the button clicked by the user
     */
    public static Alert open(Type t, String m, Listener l) {
	final Alert a = new Alert(m, t);
	a.listener = l;
	a.show();
	return a;
    }

    /**
     * Open a message alert
     * 
     * @param m
     *            the text message to be displayed
     * @return the button clicked by the user
     */
    public static Alert message(String m) {
	return open(Type.MESSAGE, m);
    }

    /**
     * Open an error alert
     * 
     * @param message
     *            the text message to be displayed
     * @return the button clicked by the user
     */
    public static Alert error(String message) {
	return open(Type.ERROR, message);
    }

    /**
     * Open a caution alert
     * 
     * @param m
     *            the text message to be displayed
     * @param l
     *            the listener that handle the user choice
     * @return the button clicked by the user
     */
    public static Alert caution(String m, Listener l) {
	return open(Type.CAUTION, m, l);
    }

    Listener listener = null;

    /**
     * creates a new alert window
     * 
     * @param m
     *            the text message to be displayed
     * @param t
     *            the alert type
     */
    public Alert(String m, Type t) {
	super(false, true);
	setAnimationEnabled(true);

	setText(t.getMessage());

	VerticalPanel content = new VerticalPanel();
	content.setWidth(ALERT_W);
	content.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

	HTML msg = new HTML(m);
	msg.setWidth("100%");
	msg.setWordWrap(true);
	content.add(msg);

	HorizontalPanel buttons = new HorizontalPanel();
	for (Reply r : t.getReplySet()) {
	    Button btn = new Button(r.getText());
	    btn.addClickListener(new ReplyButtonClickListener(r, this));
	    buttons.add(btn);
	}
	content.add(buttons);

	setWidget(content);

	setPopupPositionAndShow(new PopupPanel.PositionCallback() {
	    public void setPosition(int offsetWidth, int offsetHeight) {
		int left = (Window.getClientWidth() - offsetWidth) / 2;
		int top = (Window.getClientHeight() - offsetHeight) / 3;
		setPopupPosition(left, top);
	    }
	});
    }
}
