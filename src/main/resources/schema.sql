CREATE TABLE tbl_person(person_id UUID NOT NULL DEFAULT uuid_generate_v4(),
                        person_name VARCHAR(256) NOT NULL,
                        person_last_name VARCHAR(256) NOT NULL,
                        person_dob DATE NOT NULL,
                        person_gender CHAR(1) NOT NULL,
                        ADD CONSTRAINT
                        PK_tbl_person PRIMARY KEY  (person_id));

CREATE TABLE tbl_measurement_type(measurement_type_id INT NOT NULL,
                                  measurement_type_description VARCHAR(256),
                                  measurement_unit VARCHAR(256),
                                  ADD CONSTRAINT PK_tbl_measurement_type
                                  PRIMARY KEY (measurement_type_id));

CREATE TABLE tbl_measurement(measurement_id SERIAL NOT NULL,
                             measurement_person_id UUID NOT NULL,
                             measurement_type_id INT NOT NULL,
                             measured_at DATETIME NOT NULL,
                             measurement_value DECIMAL(5,5) NOT NULL,
                             ADD CONSTRAINT
                             PRIMARY KEY PK_tbl_measurement(measurement_id),
                             ADD CONSTRAINT FK_tbl_measurement_tbl_person
                             FOREIGN KEY  measurement_person_id REFERENCES tbl_person(person_id),
                             ADD CONSTRAINT FK_tbl_measurement_tbl_measurement_type
                             FOREIGN KEY measurement_type_id REFERENCES tbl_measurement_type(measurement_type_id));

