# Full Stack Portfolio

## Description:

This is a full stack portfolio application that allows me to demonstrate my skills in Full stack development. 
I will be using technologies like docker, spring boot, react, aws, and many others.

## Docker-Compose
The application is built using docker-compose, which allows me to easily manage and run multiple containers.
The docker-compose file is located in the root directory of the project.
`Run` the file with `docker-compose up`
`Stop` the file with `docker-compose down`

## Database:

The application will be using MySQL relational database to store information and project details.

### `Running the database`
- `CD` into to the database folder by running this command at the root of the project `cd ./database`
- `Run` the database container by using the command `docker run <image name>`
- `Open` new terminal
- `Interact` with the container by running `docker exec -it <container ID> /bin/bash`
- `Login` to mysql with the command `mysql -p<password> -uroot`
- `View` databases run `show databases;`
- `Use` the database by running `use <database name>;`
- `View` tables run `show tables;`
- `exit` MySQL and bash with the command `exit` on both.
- `Stop` the container with the command `docker stop <container ID>`



## Frontend
The frontend will be built using React.
### `Running the frontend`
- `CD` into to the frontend folder by running this command at the root of the project `cd ./frontend`
- `Run` the frontend application by using the command `npm run dev`

## Docker-Compose
The application is built using docker-compose, which allows me to easily manage and run multiple containers.

### `Running the docker-compose file`
- `CD` into to the root directory of the project
- `Run` the docker-compose file with the command `docker-compose up`
- `Stop` the docker-compose file with the command `docker-compose down`