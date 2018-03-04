package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="poller-configuration")
@XmlType(propOrder={"node_outage", "package", "monitor"})
public class Poller_configuration {


	    private String nextOutageId;

	    private Monitor[] monitor;

	    private String pathOutageEnabled;

	    private String threads;

	    private String serviceUnresponsiveEnabled;

	    private Poller_Package[] poller_package;

//	    private String xmlns;

	    private Node_outage node_outage;
	    
	    
	    
	    @XmlAttribute
	    public String getNextOutageId ()
	    {
	        return nextOutageId;
	    }

	    public void setNextOutageId (String nextOutageId)
	    {
	        this.nextOutageId = nextOutageId;
	    }

	    @XmlElement
	    public Monitor[] getMonitor ()
	    {
	        return monitor;
	    }

	    public void setMonitor (Monitor[] monitor)
	    {
	        this.monitor = monitor;
	    }

	    @XmlAttribute
	    public String getPathOutageEnabled ()
	    {
	        return pathOutageEnabled;
	    }

	    public void setPathOutageEnabled (String pathOutageEnabled)
	    {
	        this.pathOutageEnabled = pathOutageEnabled;
	    }

	   @XmlAttribute
	    public String getThreads ()
	    {
	        return threads;
	    }

	    public void setThreads (String threads)
	    {
	        this.threads = threads;
	    }

	    @XmlAttribute
	    public String getServiceUnresponsiveEnabled ()
	    {
	        return serviceUnresponsiveEnabled;
	    }

	    public void setServiceUnresponsiveEnabled (String serviceUnresponsiveEnabled)
	    {
	        this.serviceUnresponsiveEnabled = serviceUnresponsiveEnabled;
	    }

	    @XmlElement(name="package")
	    public Poller_Package[] getPackage ()
	    {
	        return poller_package;
	    }

	    public void setPackage (Poller_Package[] poller_package)
	    {
	        this.poller_package = poller_package;
	    }

/*	    public String getXmlns ()
	    {
	        return xmlns;
	    }

	    public void setXmlns (String xmlns)
	    {
	        this.xmlns = xmlns;
	    }*/

	    @XmlElement(name="node-outage")
	    public Node_outage getNode_outage ()
	    {
	        return node_outage;
	    }

	    public void setNode_outage (Node_outage node_outage)
	    {
	        this.node_outage = node_outage;
	    }	   
	    

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [nextOutageId = "+nextOutageId+", monitor = "+monitor+", pathOutageEnabled = "+pathOutageEnabled+", threads = "+threads+", serviceUnresponsiveEnabled = "+serviceUnresponsiveEnabled+", package = "+poller_package+", xmlns = +xmlns+, node-outage = "+node_outage+"]";
	    }
}
