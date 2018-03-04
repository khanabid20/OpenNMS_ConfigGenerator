package com.khanabid20.opennms.util.poller.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rrd {

	private String[] rra;

    private String step;

    @XmlElement
    public String[] getRra ()
    {
        return rra;
    }

    public void setRra (String[] rra)
    {
        this.rra = rra;
    }


    @XmlAttribute
    public String getStep ()
    {
        return step;
    }

    public void setStep (String step)
    {
        this.step = step;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rra = "+rra+", step = "+step+"]";
    }
}
