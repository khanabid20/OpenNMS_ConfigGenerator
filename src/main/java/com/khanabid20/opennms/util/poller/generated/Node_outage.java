package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Node_outage {


	    private String pollAllIfNoCriticalServiceDefined;

	    private Critical_service critical_service;

	    private String status;
	    

	    @XmlAttribute
	    public String getPollAllIfNoCriticalServiceDefined ()
	    {
	        return pollAllIfNoCriticalServiceDefined;
	    }

	    public void setPollAllIfNoCriticalServiceDefined (String pollAllIfNoCriticalServiceDefined)
	    {
	        this.pollAllIfNoCriticalServiceDefined = pollAllIfNoCriticalServiceDefined;
	    }

	   @XmlElement
	    public Critical_service getCritical_service ()
	    {
	        return critical_service;
	    }

	    public void setCritical_service (Critical_service critical_service)
	    {
	        this.critical_service = critical_service;
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

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [pollAllIfNoCriticalServiceDefined = "+pollAllIfNoCriticalServiceDefined+", critical-service = "+critical_service+", status = "+status+"]";
	    }
}
