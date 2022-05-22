insert into doctor_entity (id, email, first_name, last_name, password)
values (1, 'doctor1@email.com', 'doctorFirstName1', 'doctorLastName1', 'doctorPassword1'),
       (2, 'doctor2@email.com', 'doctorFirstName2', 'doctorLastName2', 'doctorPassword2');

insert into address_entity (id, city, house_number, local_number, street, zip_code)
values (1, 'city1', 'houseNumber1', 'localNumber1', 'street1', '00-001'),
       (2, 'city2', 'houseNumber2', 'localNumber2', 'street2', '00-002');

insert into patient_entity (id, email, first_name, last_name, password, pesel, address_id)
values (1, 'patient1@email.com', 'patientFirstName1', 'patientLastName1', 'patientPassword1', '82110392536', 1),
       (2, 'patient2@email.com', 'patientFirstName2', 'patientLastName2', 'patientPassword2', '00292985753', 2);

insert into vaccine_entity (id, disease, name, required_doses)
values (1, 'COVID-19', 'name1', 1),
       (2, 'COVID-21', 'name2', 2),
       (3, 'Flu', 'name3', 1),
       (4, 'OTHER', 'name4', 1);
