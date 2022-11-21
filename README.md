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
3. cancelling actions: prepare for deliver and send to deliver
4. Unit tests and integrational tests with testContainers.
5. Blocks in the db fot data consistency
6. Rollback drone commands if request to db finished with error