CREATE TABLE  patient  (
   patient_id  varchar(36) NOT NULL,
   first_name  varchar(50) NOT NULL,
   last_name  varchar(50) NOT NULL,
   birthdate  date NOT NULL,
   email  varchar(45) NOT NULL,
   phone  varchar(10) NOT NULL,
   address_id  varchar(36) DEFAULT NULL,
  PRIMARY KEY ( patient_id ),
  UNIQUE KEY  patient_id_UNIQUE  ( patient_id ),
  KEY  fk_address_idx  ( address_id ),
  CONSTRAINT  fk_address  FOREIGN KEY ( address_id ) REFERENCES  address  ( address_id )
)
