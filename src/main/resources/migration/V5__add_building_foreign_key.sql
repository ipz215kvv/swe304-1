ALTER TABLE person 
ADD COLUMN building_id INT NOT NULL,
ADD CONSTRAINT fk_person_building
FOREIGN KEY (building_id) REFERENCES building(id)
ON DELETE CASCADE;