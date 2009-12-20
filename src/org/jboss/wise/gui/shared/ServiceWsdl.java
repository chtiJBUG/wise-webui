package org.jboss.wise.gui.shared;

import java.io.Serializable;
import java.util.Date;

public class ServiceWsdl implements Serializable {

    private static final long serialVersionUID = -4647111183719034021L;

    private String name;

    private String host;

    private String notes;

    private Date savingDate;

    public ServiceWsdl(String name, String host, String notes, Date savingDate) {
	super();
	this.name = name;
	this.host = host;
	this.notes = notes;
	this.savingDate = savingDate;
    }

    public String getName() {
	return name;
    }

    public String getHost() {
	return host;
    }

    public String getNotes() {
	return notes;
    }

    public Date getSavingDate() {
	return savingDate;
    }

}
