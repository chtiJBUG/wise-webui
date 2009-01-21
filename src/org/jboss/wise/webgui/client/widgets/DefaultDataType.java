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

import java.util.Comparator;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public enum DefaultDataType implements DataType {
    STRING, INTEGER, LONG, FLOAT, DOUBLE, DATE, BOOLEAN;

    private Comparator<Object> comparator;

    private DefaultDataType() {
	comparator = null;
    }

    public static DefaultDataType typeFor(Object data) {
	if (data instanceof Integer)
	    return INTEGER;
	else if (data instanceof Long)
	    return LONG;
	else if (data instanceof Float)
	    return FLOAT;
	else if (data instanceof Double)
	    return DOUBLE;
	else if (data instanceof Date)
	    return DATE;
	else if (data instanceof Boolean)
	    return BOOLEAN;
	else
	    return STRING;
    }

    public Widget formatAsWidget(Object data, String simpleFormat) {
	HTML widget = null;
	if (data != null) {
	    String widgetConten = "";
	    if (this == STRING) {
		widgetConten = data.toString();
	    } else if (this == INTEGER) {
		if (simpleFormat != null) {
		    NumberFormat nf = NumberFormat.getFormat(simpleFormat);
		    widgetConten = nf.format((Integer) data);
		} else
		    widgetConten = String.valueOf(((Integer) data).intValue());
	    } else if (this == LONG) {
		if (simpleFormat != null) {
		    NumberFormat nf = NumberFormat.getFormat(simpleFormat);
		    widgetConten = nf.format((Long) data);
		} else
		    widgetConten = String.valueOf(((Long) data).intValue());
	    } else if (this == FLOAT) {
		if (simpleFormat != null) {
		    NumberFormat nf = NumberFormat.getFormat(simpleFormat);
		    widgetConten = nf.format((Float) data);
		} else
		    widgetConten = String.valueOf(((Float) data).intValue());
	    } else if (this == DOUBLE) {
		if (simpleFormat != null) {
		    NumberFormat nf = NumberFormat.getFormat(simpleFormat);
		    widgetConten = nf.format((Double) data);
		} else
		    widgetConten = String.valueOf(((Double) data).intValue());
	    } else if (this == DATE) {
		if (simpleFormat == null) {
		    simpleFormat = "dd/MM/yyyy";
		}
		DateTimeFormat dtf = DateTimeFormat.getFormat(simpleFormat);
		widgetConten = dtf.format((Date) data);
	    } else if (this == BOOLEAN) {
		String[] values;
		if (simpleFormat != null) {
		    values = simpleFormat.split(",");
		} else {
		    values = new String[] { "Ã", "" };
		}
		if (((Boolean) data).booleanValue()) {
		    widgetConten = values.length > 0 ? values[0].trim() : "Ã";
		} else {
		    widgetConten = values.length > 1 ? values[1].trim() : null;
		}
	    }

	    widget = new HTML(widgetConten);
	}
	return widget;
    }

    public synchronized Comparator<Object> getComparator() {
	if (comparator == null) {
	    if (this == STRING) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((String) o1).compareTo((String) o2);
		    }
		};
	    } else if (this == INTEGER) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Integer) o1).compareTo((Integer) o2);
		    }
		};
	    } else if (this == LONG) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Long) o1).compareTo((Long) o2);
		    }
		};
	    } else if (this == FLOAT) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Float) o1).compareTo((Float) o2);
		    }
		};
	    } else if (this == DOUBLE) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Double) o1).compareTo((Double) o2);
		    }
		};
	    } else if (this == DATE) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Date) o1).compareTo((Date) o2);
		    }
		};
	    } else if (this == BOOLEAN) {
		comparator = new DataTypeComparator() {
		    @Override
		    protected int doCompare(Object o1, Object o2) {
			return ((Boolean) o1).compareTo((Boolean) o2);
		    }
		};
	    }
	}
	return comparator;
    }
}