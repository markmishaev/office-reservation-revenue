# office-reservation-revenue
Calculates revenue and capacity of offices based on reservation data

Spring Boot based implementation with the following structure:

1. Business Logic Layer
2. DAO
3. Model
4. Utils
5. Tests

The reservation data is loaded from CSV file into list of reservation entities and used for calculation of revenue
and unreserved offices capacity for given month

1. BL layer contains strategy interface and the actual calculations implementation
2. DAO contains access to reservations data, loaded from the CSV file
3. Model contains "Reservation" entity 
4. Utils contains auxiliary methods, mainly for dates manipulations
5. Tests - contains unit tests

In order to run this project you need to have MVN and JDK installed on your computer

Once installed you have two options:
1. Run mvn install from command line which builds, installs and runs unit tests
2. Run mvn test that builds and runs unit tests

You should expect the following output:

**testCapacityCalculationForMoreDates******************

Total capacity for unreserved offices for the month 2013-01 is: 254

Total capacity for unreserved offices for the month 2013-06 is: 241

Total capacity for unreserved offices for the month 2014-03 is: 207

Total capacity for unreserved offices for the month 2014-09 is: 120

Total capacity for unreserved offices for the month 2015-07 is: 135

************testRevenueCalculationForMoreDates******************

Offices revenue for the month 2013-01 is: 8100.0

Offices revenue for the month 2013-06 is: 17650.0

Offices revenue for the month 2014-03 is: 37915.0

Offices revenue for the month 2014-09 is: 86557.0

Offices revenue for the month 2015-07 is: 76177.0


For your convenience you can take a look at this excel file with the main test cases:

https://github.com/markmishaev/office-reservation-revenue/blob/master/src/main/resources/calculation_output_4_specific_dates.xlsx




