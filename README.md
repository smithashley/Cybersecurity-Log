# Cybersecurity Log
This Spark application cleans and visualizes sample log data from a web server.
- Data is available here: https://docs.splunk.com/Documentation/Splunk/6.5.0/SearchTutorial/GetthetutorialdataintoSplunk

## Steps
- The data was parsed from a log file
- Columns were renamed
- The data was filtered to show the occurrence of accepted and failed passwords
- Pivoted the data to show counts 
- Repartitioned to a single file
- Loaded the data as a .csv file

## Data Visualization
![](https://github.com/smithashley/cybersecuritylog/blob/main/images/with%20chart.png)
