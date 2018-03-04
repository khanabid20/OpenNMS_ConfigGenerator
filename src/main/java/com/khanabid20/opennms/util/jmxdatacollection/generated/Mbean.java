package com.khanabid20.opennms.util.jmxdatacollection.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mbean {
	private String objectname;

    private String name;

    private Attrib[] attrib;

    @XmlAttribute
    public String getObjectname ()
    {
        return objectname;
    }

    public void setObjectname (String objectname)
    {
        this.objectname = objectname;
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

    public Attrib[] getAttrib ()
    {
        return attrib;
    }

    public void setAttrib (Attrib[] attrib)
    {
        this.attrib = attrib;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [objectname = "+objectname+", name = "+name+", attrib = "+attrib+"]";
    }

}
