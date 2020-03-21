CREATE TABLE 'patient' (
  'patient_id' varchar(36) NOT NULL,
  'first_name' varchar(50) NOT NULL,
  'last_name' varchar(50) NOT NULL,
  'birthdate' date NOT NULL,
  'email' varchar(45) NOT NULL,
  'phone' varchar(10) NOT NULL,
  PRIMARY KEY ('patient_id')
) 