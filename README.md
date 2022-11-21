# Drones management system

## Implemented:
1. Cruds for drones and medication.
2. Drones API for loading, preparing to deliver, send to deliver.
3. Scheduled job for monitoring battery capacity of the drones.

## Needs to be implemented
1. Drones api to receive notification about finishing of delivering.
2. Improve logs.
3. cancelling actions: prepare for deliver and send to deliver
4. Unit tests and integrational tests with testContainers.
5. Blocks in the db to avoid race conditions
6. Rollback drone commands if request to db finished with error