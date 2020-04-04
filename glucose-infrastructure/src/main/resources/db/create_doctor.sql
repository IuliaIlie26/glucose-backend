CREATE TABLE  doctor  (
   doctor_id  varchar(36) NOT NULL,
   first_name  varchar(50) NOT NULL,
   last_name  varchar(50) NOT NULL,
   speciality  varchar(50) NOT NULL,
   email  varchar(255) DEFAULT NULL,
   phone  varchar(255) DEFAULT NULL,
  PRIMARY KEY ( doctor_id )
) 