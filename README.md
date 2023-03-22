# Weather Sensor Data App

This application receives weather data from various sensors that
report metrics such as temperature, humidity, wind speed. 

Using REST APIS, a sensor can be created by providing an ID. Optional metadata can be provided but is not required. 
Once created, data metrics can be added for that sensor.

Metrics can be queried from the application by providing an ID, list of IDs. 
Default behaviour if no ID(s) is provided will be the retrieval of metrics for all IDs. 
The metric data will be returned as an average value. More information on the querying of metric data can be found below.

## Instructions

This is a Java 11 application, built upon Spring Boot, and runs against a local MySQL database.
To run the application, you can simply run the ```main``` method in 
```src/main/java/com/sensor/weather/WeatherSensorApplication.java```

A Swagger UI page is available for easier interaction with the endpoints. ```http://localhost:8080/swagger-ui/```

## Example Usage

#### Create a sensor
```bash
curl -X POST "http://localhost:8080/weather-sensors" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"id\": 10}"
```

#### Query sensor metrics
```bash
curl -X GET "http://localhost:8080/weather-sensors/metrics?fromDate=2023-03-21&metrics=temperature%2Chumidity" -H "accept: */*"
```
Note, query parameters that can be optionally provided are ```fromDate```, ```ids```, ```metrics```. 
- If ```fromDate``` is not provided then today's data will be retrieved. The date must be within the last month. Date format is ```yyyy-MM-dd```.
- If ```ids``` is not provided then all sensors will be retrieved. One, multiple (separated by a comma), or all can be provided.
- If ```metrics``` is not provided then all metrics will be provided. If a valid value(s) is supplied then only that value(s) will be returned.
```temperature```, ```humidity```, and ```windSpeed``` are valid metrics.

## Notes

Due to time:

- Test coverage is incomplete. A WebMvc style test of an endpoint and a unit test of a Service class are available.

With more time:

- Test coverage can be increased.
- Security layers such as basic auth can be introduced.
- Database could be hosted in the cloud.
- Increased input validation can be provided.
- Updates to existing records can be introduced.
- Dates can be included in the input parameters to allow historical data to be added.
- Caching could be implemented if performance needs require it.
- Implement a cleaner way to validate and filter the metrics parameter.
- Extract helper methods from ```SensorMetricService``` into a separate class and make use of generics.