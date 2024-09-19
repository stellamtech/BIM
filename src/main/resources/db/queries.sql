INSERT INTO entity_id_repository (id, created, modified, deleted, last_sr_no, module_name, prefix, suffix, description, value_, key_)
			VALUES (0, now(),  now(), 0, 1,'SALE_INVOICE','SIV-',null,null,null,null);
			
INSERT INTO entity_id_repository (id, created, modified, deleted, last_sr_no, module_name, prefix, suffix, description, value_, key_)
			VALUES (0, now(),  now(), 0, 1,'CUSTOMER_NO','CNO-',null,null,null,null);
			
			
INSERT INTO role
(id, created, modified, deleted, role) VALUES (1, now(), now(), 0, 'ROLE_SA');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (2, now(), now(), 0, 'ROLE_TEACHER');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (3, now(), now(), 0, 'ROLE_ADMIN');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (4, now(), now(), 0, 'ROLE_ACCOUNTANT');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (5, now(), now(), 0, 'ROLE_RECEPTIONIST');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (6, now(), now(), 0, 'ROLE_STUDENT');

INSERT INTO role
(id, created, modified, deleted, role) VALUES (7, now(), now(), 0, 'ROLE_EMP');
			