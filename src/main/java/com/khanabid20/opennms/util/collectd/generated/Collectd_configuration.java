package com.khanabid20.opennms.util.collectd.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="collectd-configuration")
@XmlType(propOrder = { "package", "collector"})
public class Collectd_configuration
{
    private Collector[] collector;

    private String threads;

    private CollectDPackage[] package1;

//    private String xmlns;

    @XmlElement
    public Collector[] getCollector ()
    {
        return collector;
    }

    public void setCollector (Collector[] collector)
    {
        this.collector = collector;
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

    @XmlElement(name="package")
    public CollectDPackage[] getPackage ()
    {
        return package1;
    }

    public void setPackage (CollectDPackage[] package1)
    {
        this.package1 = package1;
    }

   /* public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }*/

    @Override
    public String toString()
    {
//        return "ClassPojo [collector = "+collector+", threads = "+threads+", package = "+package1+", xmlns = "+xmlns+"]";
        return "";
    }
}