package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="package")
@XmlType(propOrder={"filter","rrd", "service", "downtime"})
public class Poller_Package {

	private DownTime[] downtime;

    private String name;

    private Rrd rrd;

    private Service[] service;

    private String filter;
    
//    private Include_range[] include_range;
    
    @XmlElement
    public DownTime[] getDowntime ()
    {
        return downtime;
    }

    public void setDowntime (DownTime[] downtime)
    {
        this.downtime = downtime;
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

    @XmlElement
    public Rrd getRrd ()
    {
        return rrd;
    }

    public void setRrd (Rrd rrd)
    {
        this.rrd = rrd;
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

    @XmlElement
    public String getFilter ()
    {
        return filter;
    }

    public void setFilter (String filter)
    {
        this.filter = filter;
    }
    
/*    @XmlElement(name="include-range")
    public Include_range[] getInclude_range() {
		return include_range;
	}

	public void setInclude_range(Include_range[] include_range) {
		this.include_range = include_range;
	}*/

	@Override
    public String toString()
    {
        return "ClassPojo [downtime = "+downtime+", name = "+name+", rrd = "+rrd+", service = "+service+", filter = "+filter+"]";
    }
}
