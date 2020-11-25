# Cybersecurity Log
This Spark application cleans and visualizes sample Splunk log data from a web server.


## Steps
- The data was parsed from a log file
- Columns were merged and renamed
- The data was filtered to show the occurrence of accepted and failed passwords
- Pivoted the data to show counts 
- Repartitioned to a single file
- Loaded the data as a .csv file

## Data Visualization
![](https://github.com/smithashley/cybersecuritylog/blob/main/images/with%20chart.png)
