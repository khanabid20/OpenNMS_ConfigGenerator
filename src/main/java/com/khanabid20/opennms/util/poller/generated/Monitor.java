package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Monitor {

	private String service;

    private String class_name;
    

    @XmlAttribute
    public String getService()
    {
        return service;
    }

    public void setService (String service)
    {
        this.service = service;
    }

    @XmlAttribute(name="class-name")
    public String getClass_name()
    {
        return class_name;
    }

    public void setClass_name (String class_name)
    {
        this.class_name = class_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [service = "+service+", class-name = "+class_name+"]";
    }
}
