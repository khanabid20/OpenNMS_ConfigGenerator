package com.khanabid20.opennms.util.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.log4j.Logger;

import com.khanabid20.opennms.util.methods.CollectDGen;
import com.khanabid20.opennms.util.methods.GraphGen;
import com.khanabid20.opennms.util.methods.JMXGen;
import com.khanabid20.opennms.util.methods.OpenNMS_Utility_Constants;
import com.khanabid20.opennms.util.methods.PollerGen;
import com.khanabid20.opennms.util.methods.ParseExcelFile;

/**
 * This class consists of the main method that runs the entire project as a
 * stand-alone application
 * 
 * @author abid.khan
 * 
 */
public class App {
	static Logger log = Logger.getLogger(App.class);

	/**
	 * This is the entry point of the project
	 * 
	 * @param args
	 *            Input excel(.xlsx) file as command line argument
	 */
	public static void main(String[] args) {

		// Converting xlsx file into csv
		// ParseExcelFile.xlsxToCSV(new
		// File("./xlsx/opennms-configuration.xlsx")); // hardcoded file path

		// Input file as command line argument
		if (args.length == 0) {
			System.out.println("Please give file as parameter (.xslx)");
			log.error("Input file (.xlsx) was not provided.");
			System.exit(0);
		} else {
			log.info("Input File -> "+args[0].trim());
			ParseExcelFile.xlsxToCSV(new File(args[0]));
		}

		log.info("Converted XLSX file into CSV file(s)");

		objectToXML(CollectDGen.genCollectD(), OpenNMS_Utility_Constants.CollectD_FILE_NAME); // takes "Collectd_configuration" object returned by CollectDGen class
		objectToXML(PollerGen.genPoller(), OpenNMS_Utility_Constants.POLLER_FILE_NAME); // takes "Poller_configuration" object returned by PollerGen class

		JMXGen.genJMX(); // generate datacollectd file for each services

		try {
			// Calling method of GraphGen for graph generation of each services
			GraphGen.genGraph();
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		log.info("Successfully generated all xml & graph files");

		System.out.println("Successfully generated all xml & graph files");
	}

	/**
	 * This method binds java object(POJO) into xml and generates xml file.
	 * 
	 * @param <T>
	 *            This method is generic method, operates on object of type T
	 * @param obj
	 *            Type of object passed
	 * @param outputFile
	 *            Output xml file path
	 */
	public static <T> void objectToXML(T obj, String outputFile) {
		JAXBContext contextObj = null;
		try {
			contextObj = JAXBContext.newInstance(obj.getClass());
		} catch (JAXBException e) {
			log.error("Classes use JAXB annotations incorrectly");
		}
		Marshaller marshallerObj = null;
		try {
			marshallerObj = contextObj.createMarshaller();
		} catch (JAXBException e1) {
			log.error("Error while creating marshaller");
		}
		try {
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (PropertyException e) {
			log.info("Error encountered while getting or setting a property");
		}

		try {
			marshallerObj.marshal(obj, new FileOutputStream(outputFile));
		} catch (FileNotFoundException e) {
			log.error("Couldn't create a file");
		} catch (JAXBException e) {
			log.error("Error while marshalling XML element");
		}

	}
}
