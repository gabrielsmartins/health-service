DROP TABLE IF EXISTS tbl_measurement;
DROP TABLE IF EXISTS tbl_measurement_type;
DROP TABLE IF EXISTS tbl_person;

CREATE TABLE tbl_person(person_id UUID NOT NULL DEFAULT gen_random_uuid(),
                        person_first_name VARCHAR(256) NOT NULL,
                        person_last_name VARCHAR(256) NOT NULL,
                        person_dob DATE NOT NULL,
                        person_gender CHAR(1) NOT NULL,
                        CONSTRAINT PK_tbl_person
                        PRIMARY KEY (person_id));

CREATE TABLE tbl_measurement_type(measurement_type_id INT NOT NULL,
                                  measurement_type_description VARCHAR(256),
                                  measurement_unit VARCHAR(256),
                                  CONSTRAINT PK_tbl_measurement_type
                                  PRIMARY KEY (measurement_type_id));

CREATE TABLE tbl_measurement(measurement_id SERIAL NOT NULL,
                             measurement_person_id UUID NOT NULL,
                             measurement_type_id INT NOT NULL,
                             measured_at TIMESTAMP NOT NULL,
                             measurement_value DECIMAL(5,5) NOT NULL,
                             analyzed_at TIMESTAMP DEFAULT NULL,
                             measurement_classification CHAR(1) DEFAULT NULL,
                             CONSTRAINT PK_tbl_measurement
                             PRIMARY KEY (measurement_id),
                             CONSTRAINT FK_tbl_measurement_tbl_person
                             FOREIGN KEY (measurement_person_id) REFERENCES tbl_person(person_id),
                             CONSTRAINT FK_tbl_measurement_tbl_measurement_type
                             FOREIGN KEY (measurement_type_id) REFERENCES tbl_measurement_type(measurement_type_id),
                             CONSTRAINT CHK_tbl_measurement CHECK (measurement_classification IN ('N', 'H', 'L')));