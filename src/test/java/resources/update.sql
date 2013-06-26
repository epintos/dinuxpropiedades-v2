ALTER TABLE PHOTOS RENAME TO PHOTO;

CREATE TABLE services
(
  publication_id integer NOT NULL,
  element character varying(255),
  CONSTRAINT fk560f747ed782f59b FOREIGN KEY (publication_id)
      REFERENCES property (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE services OWNER TO paw;

INSERT INTO SERVICES (SELECT propertyId, text 'CABLE' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'cable');
INSERT INTO SERVICES (SELECT propertyId, text 'SWIMMINGPOOL' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'pileta');
INSERT INTO SERVICES (SELECT propertyId, text 'SALON' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'salon');
INSERT INTO SERVICES (SELECT propertyId, text 'TELEPHONE' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'telefono');
INSERT INTO SERVICES (SELECT propertyId, text 'PADDLECOURT' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'cancha de paddle');
INSERT INTO SERVICES (SELECT propertyId, text 'QUINCHO' as "element" FROM service_provided, service where serviceId=service.id and serviceName like 'quincho');

ALTER TABLE USERS ADD COLUMN usertype character varying(50);
ALTER TABLE USERS ALTER surname DROP NOT NULL; 
ALTER TABLE USERS DROP CONSTRAINT users_email_key;
UPDATE USERS SET usertype = 'USER';

ALTER TABLE PHOTO DROP CONSTRAINT fk_proper2;
ALTER TABLE PHOTO RENAME propertyId TO publication_id;
ALTER TABLE PHOTO ALTER publication_id DROP NOT NULL; 
ALTER TABLE PHOTO ADD COLUMN user_id integer;
ALTER TABLE PHOTO RENAME photo TO file;
ALTER TABLE PHOTO ADD CONSTRAINT fk4984e127f99b199 FOREIGN KEY (user_id) REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE PHOTO ADD CONSTRAINT fk4984e12d782f59b FOREIGN KEY (publication_id) REFERENCES property (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE PHOTO SET user_id = NULL;

ALTER TABLE PROPERTY RENAME rooms TO roomsqty;
ALTER TABLE PROPERTY ALTER floor TYPE integer;
ALTER TABLE PROPERTY ALTER apartment TYPE character varying(5);
ALTER TABLE PROPERTY ADD COLUMN operationtype character varying(255);
ALTER TABLE PROPERTY ADD COLUMN propertytype character varying(255);
ALTER TABLE PROPERTY ADD COLUMN status character varying(255);
ALTER TABLE PROPERTY ADD COLUMN visitcount integer;
ALTER TABLE PROPERTY ADD COLUMN mainphoto_id integer;
ALTER TABLE PROPERTY ADD COLUMN user_id integer;
ALTER TABLE PROPERTY ADD CONSTRAINT fk23254a0c7f99b199 FOREIGN KEY (user_id)REFERENCES users (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE PROPERTY ADD  CONSTRAINT fk23254a0cd00cc954 FOREIGN KEY (mainphoto_id)REFERENCES photo (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE PROPERTY SET operationtype = aux.operationtype from (SELECT propertyId,text 'SALE' as "operationtype" FROM property_operation, operation,property where propertyId=property.id and operationId=operation.id and operationName like 'sale') aux where property.id = aux.propertyId;
UPDATE PROPERTY SET operationtype = aux.operationtype from (SELECT propertyId,text 'RENT' as "operationtype" FROM property_operation, operation,property where propertyId=property.id and operationId=operation.id and operationName like 'rent') aux where property.id = aux.propertyId;   
UPDATE PROPERTY SET propertytype = 'APARTMENT' where floor is not null;
UPDATE PROPERTY SET propertytype = 'HOUSE' where floor is null;

UPDATE PROPERTY SET status = aux.status from (SELECT propertyId,text 'AVAILABLE' as "status" FROM property_operation, property,operation where propertyId=property.id and operationId=operation.id and property_operation.status like 'available') aux where property.id = aux.propertyId;
UPDATE PROPERTY SET status = aux.status from (SELECT propertyId,text 'FINISHED' as "status" FROM property_operation, property,operation where propertyId=property.id and operationId=operation.id and property_operation.status like 'finished') aux where property.id = aux.propertyId;

UPDATE PROPERTY SET user_id = aux.user_id from (SELECT propertyId, userId as "user_id" FROM property_operation, property where operationId=property.id) aux where property.id = aux.propertyId;

ALTER TABLE PROPERTY RENAME TO PUBLICATION;

CREATE TABLE room
(
  id serial NOT NULL,
  length integer NOT NULL,
  "type" character varying(255) NOT NULL,
  width integer NOT NULL,
  CONSTRAINT room_pkey PRIMARY KEY (id )
);

ALTER TABLE room OWNER TO paw;

CREATE TABLE publication_room
(
  publication_id integer NOT NULL,
  rooms_id integer NOT NULL,
  CONSTRAINT fk6c74cb4e7e16ac5c FOREIGN KEY (rooms_id)
      REFERENCES room (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk6c74cb4ed782f59b FOREIGN KEY (publication_id)
      REFERENCES publication (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT publication_room_rooms_id_key UNIQUE (rooms_id )
);

ALTER TABLE publication_room OWNER TO paw;

DROP TABLE COMMENTS;
DROP TABLE PROPERTY_OPERATION;
DROP TABLE OPERATION;
DROP TABLE SERVICE_PROVIDED;
DROP TABLE SERVICE;