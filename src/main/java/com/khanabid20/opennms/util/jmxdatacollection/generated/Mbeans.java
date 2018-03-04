package com.khanabid20.opennms.util.jmxdatacollection.generated;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Mbeans {

	 private Mbean[] mbean;
	 
	 	@XmlElement
	    public Mbean[] getMbean ()
	    {
	        return mbean;
	    }

	    public void setMbean (Mbean[] mbean)
	    {
	        this.mbean = mbean;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [mbean = "+mbean+"]";
	    }
}
