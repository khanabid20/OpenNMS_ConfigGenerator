package com.khanabid20.opennms.util.methods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.csv.CSVRecord;

/**
 * This class generates graph (.properties) files for each services
 * reads through csv files for mbean attributes(for each service) which
 * is nothing but the columns for a graph
 * 
 * @author abid.khan
 *
 */
public class GraphGen {

	static StringBuilder graphContent;
	static FileWriter graphFile;
	private static StringBuilder reports;

	public static void genGraph() throws IOException {
		// TODO Auto-generated method stub
		List<CSVRecord> servicesList = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_FOLDER + "config.csv");

		for (CSVRecord service : servicesList) {

			graphContent = null;

			if (service.getRecordNumber() != 1) {

				File graphDir = new File(OpenNMS_Utility_Constants.GRAPH_OUTPUT_FOLDER);
				graphDir.mkdir();
				
				// graph file to write into
				graphFile = new FileWriter(graphDir.getPath() + "/" + service.get(0) + "-graph.properties");
				
				// reads all mbean
				List<CSVRecord> mbeansInfo = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_FOLDER + service.get(0) + ".csv");
				
				reports = new StringBuilder();
				for (CSVRecord mbean : mbeansInfo) {
					if (mbean.getRecordNumber() != 1) 
						reports.append(", "+mbean.get(0));
				}
				// top lines
				graphContent = new StringBuilder("#To get " + service.get(0) + " Graph.\n\nreports="+
						reports.toString().replaceFirst(",", ""));

				

				// loops through each mbean for a particular service & generates reports
				for (CSVRecord mbean : mbeansInfo) {
					if (mbean.getRecordNumber() != 1) {
						//System.out.println(mbean.get(0) + "\t\t" + mbean.get(2) + "\t\t" + mbean.get(3));

						// generates report for this mbean(refer as "report" in graph)
						writeForEachReport(mbean.get(0), mbean.get(2), mbean.get(3));
					}
				}
				graphFile.write(graphContent.toString());
				graphFile.close();
			}
		}

	}

	/**
	 * This method generates each report's data 
	 * 
	 * @param report Report name
	 * @param columns Columns in the report
	 * @param graphTitles Graph titles for each column
	 */
	static void writeForEachReport(String report, String columns, String graphTitles) {
		String reportName = report.replace('-', '.');
		graphContent.append("\n\nreport." + reportName + ".name=" + report + "\nreport." + reportName + ".columns="
				+ columns
				+ "\nreport."+reportName+".type=interfaceSnmp\nreport."+reportName+".command=--title=\"" + report + "\" \\");
		writeDefForEachReport(columns.split(","), graphTitles.split(","));
	}
	
	/**
	 * This method generates rrd for each columns
	 * 
	 * @param columns String array of columns
	 * @param graphTitles String array of graph display name for each column
	 */
	static void writeDefForEachReport(String[] columns, String[] graphTitles) {

		for (int i = 0; i < columns.length; i++) {
			graphContent.append("\n  DEF:" + columns[i] + "={rrd" + (i+1) + "}:" + columns[i] + ":AVERAGE \\");
		}

		for (int i = 0; i < columns.length; i++) {
			writeGPrintForEachColumns(graphTitles[i], columns[i]);
		}
	}

	/**
	 * This method generates <i>LINE2:</i> for each column
	 * 
	 * @param graphTitle Graph display name for the column
	 * @param column Column name
	 */
	static void writeGPrintForEachColumns(String graphTitle, String column) {
		graphContent.append("\n  LINE2:" + column + "#"+getRandomHexString(6).toUpperCase()+":\"" + graphTitle + "\" \\" + "\n" + "  GPRINT:" + column
				+ ":AVERAGE:\"Avg \\\\: %8.2lf %s\" \\" + "\n" + "  GPRINT:" + column + ":MIN:\"Min \\\\: %8.2lf %s\" \\"
				+ "\n" + "  GPRINT:" + column + ":MAX:\"Max \\\\: %8.2lf %s \\n\" \\");
				
	}

	/**
	 * This method generates random hex color code
	 * 
	 * @param numchars Number of character(digits - here it is 6) of code
	 * @return Returns hex color code as string
	 */
	public static String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        if(sb.toString().substring(0, numchars).equalsIgnoreCase("ffffff"))
        	getRandomHexString(6);
        return sb.toString().substring(0, numchars);
    }
}
