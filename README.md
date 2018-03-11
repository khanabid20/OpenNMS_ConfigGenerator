# OpenNMS_ConfigGenerator

Hi,
Let's know "what OpenNMS is?" first, It's a software and application monitoring framework. You can have a look on it's official site https://opennms.org/en.

_For Monitoring any application or system you must have configuration files (like collectD, poller & jmx config file[for applications using Java]), so for the sake you have to create the files manually or you can directly generates the files on the OpenNMS itself by providing respective details._

### _Though you can generates the files online but generating the files for many services would be boring & wouldn't be efficient for copying the generated content & paste it again & again...

So, I have created this Utility for this purpose, which will make the task easy.

## GUIDE to use the jar :->
  1. First create an excel workbook with the first sheet named as `config` which will have two columns namely `service name` & `port`.( can give any name but order must be maintained ).
  2. Second sheet would be sheet with name by a service name mentioned in first sheet, now you can guess there would be as many sheets as the services in the `config` sheet.
  3. So, 2nd sheet will have following columns `mbean`, `object name`, `attributse`, `display name` (__keep the order maintained__).
  4. The purpose of 2nd sheet is, it contain mbeans details, so let me write one line for each column here :
     - `mbean` - The name of the mbean in JMX file, this would also be the name of report/graph title on SNMP graph 
     - `object name` - see the sample excel file I've uploaded (link in the NOTE below)
     - `attribute` - this will be the attribute which is collected for monitoring any application, will also be shown on __SNMP graph__
     - `display name` - I mentioned this as `graph title` in my excel sheet, but this actually display name on a graph.
  5. Well you are good to go, you just have to give this workbook/excel file as a parameter to the jar.... wallaaah ! 
  6. The output this generates will have 3 folders as `config`, `graph` & the useless but can be useful for debugging is `Logs`,
     ( config - will have config*.xml files & graph folder will have graph*.properties files )
     
     __NOTE ::__
        * For each service a jmx file gets generated. (poller & collectD files will always be there)
        * [ well you can see the example of complete excel workbook in my repo.. ](https://github.com/khanabid20/OpenNMS_ConfigGenerator/blob/master/xlsx/opennms-configuration.xlsx)
        
        
        
### THANK YOU,
 _GOOD LUCK_
