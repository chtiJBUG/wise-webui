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

/**
 * A comparator that can compare two instances even if one or both of them are
 * null
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public abstract class DataTypeComparator implements Comparator<Object> {

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object o1, Object o2) {
	if (o1 == null || o2 == null) {
	    if (o1 == o2)
		return 0;
	    return (o1 != null) ? 1 : -1;
	}
	return doCompare(o1, o2);
    }

    /**
     * Override this methods to compare two instances. Don't worry about null
     * parameters.
     * 
     * @param o1
     *            the first object to be compared
     * @param o2
     *            the second object to be compared
     * @return the implementor must return a negative integer, zero, or a
     *         positive integer as the first argument is less than, equal to, or
     *         greater than the second.
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    protected abstract int doCompare(Object o1, Object o2);

}
