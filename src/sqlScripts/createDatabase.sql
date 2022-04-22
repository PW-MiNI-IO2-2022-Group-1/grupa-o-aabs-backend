use dbio;

CREATE TABLE address (
    id int NOT NULL AUTO_INCREMENT,
    city varchar(64) NOT NULL,
    zipCode varchar(8) NOT NULL,
    street varchar(64) NOT NULL,
    houseNumber varchar(8) NOT NULL,
    localNumber varchar(8),
    PRIMARY KEY (id)
);

CREATE TABLE doctor (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(64) NOT NULL,
    lastName varchar(64) NOT NULL,
    email varchar(64) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE patient (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(64) NOT NULL,
    lastName varchar(64) NOT NULL,
    email varchar(64) NOT NULL,
    password varchar(255) NOT NULL,
    pesel varchar(11) NOT NULL,
    addressID int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (addressID) REFERENCES address(id)
);

CREATE TABLE admin (
    id int NOT NULL AUTO_INCREMENT,
    firstName varchar(64) NOT NULL,
    lastName varchar(64) NOT NULL,
    email varchar(64) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE vaccine (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(64) NOT NULL,
    disease varchar(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE vaccinationSlot (
    id int NOT NULL AUTO_INCREMENT,
    date DATE NOT NULL,
    doctorID int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (doctorID) REFERENCES doctor(id)
);

CREATE TABLE vaccination (
    id int NOT NULL AUTO_INCREMENT,
    vaccineId int NOT NULL,
    vaccineSlotId int NOT NULL,
    patientID int NOT NULL,
    doctorID int NOT NULL,
    status varchar(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (vaccineId) REFERENCES vaccine(id),
    FOREIGN KEY (vaccineSlotId) REFERENCES vaccinationSlot(id),
    FOREIGN KEY (patientID) REFERENCES patient(id),
    FOREIGN KEY (doctorID) REFERENCES doctor(id)
)