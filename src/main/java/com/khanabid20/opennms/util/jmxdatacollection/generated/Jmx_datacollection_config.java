package com.khanabid20.opennms.util.jmxdatacollection.generated;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="jmx-datacollection-config")
public class Jmx_datacollection_config {

	private Jmx_collection jmx_collection;
	private String rrdRepository;

	@XmlElement(name="jmx-collection")
	public Jmx_collection getJmx_collection() {
		return jmx_collection;
	}

	public void setJmx_collection(Jmx_collection jmx_collection) {
		this.jmx_collection = jmx_collection;
	}

	@XmlAttribute
	public String getRrdRepository() {
		return rrdRepository;
	}

	public void setRrdRepository(String rrdRepository) {
		this.rrdRepository = rrdRepository;
	}

	@Override
	public String toString() {
		return "ClassPojo [jmx_collection = " + jmx_collection + "]";
	}

}
