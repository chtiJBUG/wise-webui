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
package org.jboss.wise.webgui.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Saved wsdl data
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class WsdlRec implements Serializable {

    private static final long serialVersionUID = 2099759616616369814L;

    private Long id;

    private String url;

    private String descr;

    private Date savingDate;

    private String notes;

    /**
     * 
     */
    public WsdlRec() {
	super();
    }

    /**
     * @param id
     * @param url
     * @param descr
     * @param savingDate
     * @param notes
     */
    public WsdlRec(Long id, String url, String descr, Date savingDate, String notes) {
	super();
	this.id = id;
	this.url = url;
	this.descr = descr;
	this.savingDate = savingDate;
	this.notes = notes;
    }

    /**
     * @return id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            Sets id to the specified value.
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @param url
     *            Sets url to the specified value.
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @return descr
     */
    public String getDescr() {
	return descr;
    }

    /**
     * @param descr
     *            Sets descr to the specified value.
     */
    public void setDescr(String descr) {
	this.descr = descr;
    }

    /**
     * @return savingDate
     */
    public Date getSavingDate() {
	return savingDate;
    }

    /**
     * @param savingDate
     *            Sets savingDate to the specified value.
     */
    public void setSavingDate(Date savingDate) {
	this.savingDate = savingDate;
    }

    /**
     * @return notes
     */
    public String getNotes() {
	return notes;
    }

    /**
     * @param notes
     *            Sets notes to the specified value.
     */
    public void setNotes(String notes) {
	this.notes = notes;
    }
}
