package com.khanabid20.opennms.util.collectd.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Service
{
    private Parameter[] parameter;

    private String interval;

    private String status;

    private String name;

    private String user_defined;

    @XmlElement
    public Parameter[] getParameter ()
    {
        return parameter;
    }

    public void setParameter (Parameter[] parameter)
    {
        this.parameter = parameter;
    }

    @XmlAttribute
    public String getInterval ()
    {
        return interval;
    }

    public void setInterval (String interval)
    {
        this.interval = interval;
    }

    @XmlAttribute
    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @XmlAttribute
    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @XmlAttribute(name="user-defined")
    public String getUser_defined ()
    {
        return user_defined;
    }

    public void setUser_defined (String user_defined)
    {
        this.user_defined = user_defined;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [parameter = "+parameter+", interval = "+interval+", status = "+status+", name = "+name+", user-defined = "+user_defined+"]";
    }
}