INSERT INTO USERS(name ,surname ,password ,phone ,email,username,usertype) values('Juan', 'Gomez','a','2-123-5436','juan@gomez.com','juang','USER');
INSERT INTO USERS(name ,surname ,password ,phone ,email,username,usertype) values('Olivia', 'Perez','a','5-896-7845','olivia@perez.com','olive','USER');
INSERT INTO USERS(name ,password ,phone ,email,username,usertype) values('Granelli', 'a','3-357-4688','info@granelli.com.ar','granelli','AGENCY');
INSERT INTO USERS(name ,password ,phone ,email,username,usertype) values('San Nicolas', 'a','7-489-7896','info@sannicolas.com.ar','sannicolas','AGENCY');
INSERT INTO USERS(name ,password ,phone ,email,username,usertype) values('Alvarez', 'a','5-678-4567','info@alvarez.com.ar','alvarez','AGENCY');
INSERT INTO USERS(name ,password ,phone ,email,username,usertype) values('Castilla', 'a','6-489-4867','info@castilla.com.ar','castilla','AGENCY');


INSERT INTO PUBLICATION(neighbourhood,coveredSurface ,uncoveredSurface ,roomsqty ,description ,age,street ,numbering ,price,operationtype,propertytype,status,user_id) select 'Recoleta', 120, 230,4, 'Moderno duplex bien situado',1,'Av. Callao','3000', 250000,'SALE','HOUSE','AVAILABLE',id from users where usertype like 'AGENCY' fetch first row only;
INSERT INTO PUBLICATION(neighbourhood,coveredSurface ,uncoveredSurface ,roomsqty ,description ,age,street ,numbering ,price,operationtype,propertytype,status,user_id) select 'Escobar', 300, 200,6, 'Casa situada en zona tranquila',10,'9 de Julio','100', 450000,'RENT','HOUSE','FINISHED',id from users where usertype like 'AGENCY' fetch first row only;
INSERT INTO PUBLICATION(neighbourhood,coveredSurface ,uncoveredSurface ,roomsqty ,description ,age,street ,numbering ,price,floor,apartment,operationtype,propertytype,status,user_id) select 'Avellanada', 90, 120,2, 'Moderna casa bien situada',3,'Av. Bartolome Mitre','300', 120000,3,'F','SALE','APARTMENT','AVAILABLE',id from users where usertype like 'AGENCY' fetch first row only;
INSERT INTO PUBLICATION(neighbourhood,coveredSurface ,uncoveredSurface ,roomsqty ,description ,age,street ,numbering ,price,floor,apartment,operationtype,propertytype,status,user_id) select 'Barracas', 120, 100,3, 'Lindo duplex',4,'Vieytes','400', 130000,1,'A','SALE','APARTMENT','RESERVED',id+1 from users where usertype like 'AGENCY' fetch first row only;

INSERT INTO SERVICES(publication_id,element) values(4,'SECURITY');
INSERT INTO SERVICES(publication_id,element) values(10,'LAUNDRY');
INSERT INTO SERVICES(publication_id,element) values(3,'TENNISCOURT');
INSERT INTO SERVICES(publication_id,element) values(7,'SOLARIUM');
INSERT INTO SERVICES(publication_id,element) values(9,'SECURITY');
INSERT INTO SERVICES(publication_id,element) values(10,'SOLARIUM');

SELECT setval(pg_get_serial_sequence('room', 'id'), 1);

INSERT INTO ROOM(length,type,width) values(10,'LIVINGROOM',20);
INSERT INTO ROOM(length,type,width) values(15,'BEDROOM',20);
INSERT INTO ROOM(length,type,width) values(100,'GARDEN',200);
INSERT INTO ROOM(length,type,width) values(56,'GARDEN',50);
INSERT INTO ROOM(length,type,width) values(56,'STUDY',60);
INSERT INTO ROOM(length,type,width) values(10,'LIVINGROOM',12);
INSERT INTO ROOM(length,type,width) values(7,'BEDROOM',15);
INSERT INTO ROOM(length,type,width) values(89,'LIVINGROOM',100);

INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(2,3);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(3,4);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(3,5);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(3,6);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(4,7);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(5,8);
INSERT INTO PUBLICATION_ROOM(publication_id,rooms_id) values(6,9);
