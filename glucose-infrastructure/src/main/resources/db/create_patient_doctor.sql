CREATE TABLE 'patient_doctor' (
  'patient_id' varchar(36) NOT NULL,
  'doctor_id' varchar(36) NOT NULL,
  PRIMARY KEY ('patient_id','doctor_id'),
  KEY 'fk_patient_idx' ('patient_id'),
  KEY 'fk_patient_doctor_1_idx' ('doctor_id'),
  CONSTRAINT 'fk_patient' FOREIGN KEY ('patient_id') REFERENCES 'patient' ('patient_id') ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT 'fk_patient_doctor_1' FOREIGN KEY ('doctor_id') REFERENCES 'doctor' ('doctor_id')
) 