CREATE DATABASE CovidManagement;
USE CovidManagement;

 
CREATE TABLE vaccines (
    id                  integer auto_increment primary key,
    name                varchar(255) unique not null,
    doses_required      integer,
    days_between_doses  integer,
    total_doses_received      integer,
    total_doses_left          integer
);

INSERT INTO vaccines (name, doses_required, days_between_doses, total_doses_received, total_doses_left) 
VALUES ('Pfizer/BioNTech', 2, 21, 10000, 1000);

INSERT INTO vaccines (name, doses_required, days_between_doses, total_doses_received, total_doses_left) 
VALUES ('Johnson & Johnson', 1, null, 5000, 5000);

CREATE TABLE patients (
    id                  integer auto_increment primary key,
    first_name          varchar(255) not null,
    last_name           varchar(255) not null,
    vaccine_id          integer not null references vaccines(id),
    first_dose_date     date,
    second_dose_date    date,
    recieved_second_dose boolean 
);

INSERT INTO patients 
VALUES (1, 'John', 'Doe', 1, '2021-02-18', '2021-03-11', true);