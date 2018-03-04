
package com.khanabid20.opennms.util.jmxdatacollection.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="jmx-collection")
@XmlType(propOrder={"rrd","mbeans"})
public class Jmx_collection {

	private Mbeans mbeans;

    private String name;

    private Rrd rrd;
    
    @XmlElement
    public Mbeans getMbeans ()
    {
        return mbeans;
    }

    public void setMbeans (Mbeans mbeans)
    {
        this.mbeans = mbeans;
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

    @Override
    public String toString()
    {
        return "ClassPojo [mbeans = "+mbeans+", name = "+name+", rrd = "+rrd+"]";
    }
}
