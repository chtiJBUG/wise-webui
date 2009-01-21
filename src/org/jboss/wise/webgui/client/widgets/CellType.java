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

/**
 * Summarizes the features of a cell in a Grid
 * 
 * There are: cell width; cell style dependent name (see GWT docs for the
 * meaning of this attribute); cell data type; cell data type format string
 * 
 * @todo TODO: evaluate the use a generic attributes mechanism with
 *       getNamedAttribute/setNamedAttribute methods
 * 
 * @author <a href="mailto:fabri.wise@javamac.com">Fabrizio Di Giuseppe</a>
 */
public class CellType {

    private String width;

    private String styleDependentName;

    private String align;

    private DataType dataType;

    private String simpleFormat;

    private boolean wordWrapNormal = true;

    public CellType(DataType type, String width) {
	this.width = width;
	this.dataType = type;
	this.styleDependentName = null;
	this.align = "left";
	this.simpleFormat = null;
    }

    public CellType(DataType type, String width, String simpleFormat) {
	this.width = width;
	this.dataType = type;
	this.styleDependentName = null;
	this.align = "left";
	this.simpleFormat = simpleFormat;
    }

    public CellType(CellType orig) {
	this.width = orig.width;
	this.styleDependentName = orig.styleDependentName;
	this.align = orig.align;
	this.dataType = orig.dataType;
	this.simpleFormat = orig.simpleFormat;
    }

    public String getWidth() {
	return width;
    }

    public void setWidth(String width) {
	this.width = width;
    }

    public String getStyleDependentName() {
	return styleDependentName;
    }

    public void setStyleDependentName(String styleDependentName) {
	this.styleDependentName = styleDependentName;
    }

    public DataType getDataType() {
	return dataType;
    }

    public void setDataType(DataType type) {
	this.dataType = type;
    }

    public String getSimpleFormat() {
	return simpleFormat;
    }

    public void setSimpleFormat(String simpleFormat) {
	this.simpleFormat = simpleFormat;
    }

    public String getAlign() {
	return align;
    }

    public void setAlign(String align) {
	this.align = align;
    }

    public boolean isWordWrapNormal() {
	return wordWrapNormal;
    }

    public void setWordWrapNormal(boolean wordWrapNormal) {
	this.wordWrapNormal = wordWrapNormal;
    }

}