package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DownTime {

	 private String interval;

	    private String end;

	    private String begin;
	    
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
	    public String getEnd ()
	    {
	        return end;
	    }

	    public void setEnd (String end)
	    {
	        this.end = end;
	    }

	    @XmlAttribute
	    public String getBegin ()
	    {
	        return begin;
	    }

	    public void setBegin (String begin)
	    {
	        this.begin = begin;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [interval = "+interval+", end = "+end+", begin = "+begin+"]";
	    }
}
