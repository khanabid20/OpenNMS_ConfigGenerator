package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Critical_service {

	 private String name;

	 @XmlAttribute
	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [name = "+name+"]";
	    }
}
