package com.khanabid20.opennms.util.collectd.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="package")
public class CollectDPackage
{
    private String name;

    private Service[] service;

    private String remote;

    private String filter;

    @XmlAttribute
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @XmlElement
    public Service[] getService ()
    {
        return service;
    }

    public void setService (Service[] service)
    {
        this.service = service;
    }
 
    @XmlAttribute
    public String getRemote ()
    {
        return remote;
    }

    public void setRemote (String remote)
    {
        this.remote = remote;
    }

    @XmlElement
    public String getFilter ()
    {
        return filter;
    }

    public void setFilter (String filter)
    {
        this.filter = filter;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", service = "+service+", remote = "+remote+", filter = "+filter+"]";
    }

}