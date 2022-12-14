# Drones management system

## RUN:
gradle build
java -jar ./build/libs/musala-test-app-0.0.1-SNAPSHOT.jar

## Assumptions:
1. It's assumed that interation with drones is executed through some API, that is mocked in the project
2. It's assumed that interaction with the audit service is executed through message broker or API, that is mocked in the project. 

## Implemented:
1. Cruds for drones and medication.
2. Drones API for loading, preparing to deliver, send to deliver.
3. Scheduled job for monitoring battery capacity of the drones.

## Needs to be implemented
1. Drones api to receive notification about finishing of delivering.
2. Improve logs.
3. Cancelling of actions
4. Unit tests and international tests with testContainers.
5. Locks in the db for data consistency
6. Improve consistency between real drone state and drone state in the db 