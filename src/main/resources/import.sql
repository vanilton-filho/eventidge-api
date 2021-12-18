insert into state (id, name) values (1, 'São Paulo');
insert into state (id, name) values (2, 'Rio de Janeiro');
insert into state (id, name) values (3, 'Minas Gerais');
insert into state (id, name) values (4, 'Sergipe');
insert into state (id, name) values (5, 'Pernambuco');

insert into city (id, name, state_id) values (1, 'São Paulo', 1);
insert into city (id, name, state_id) values (2, 'Campinas', 1);
insert into city (id, name, state_id) values (3, 'Rio de Janeiro', 2);
insert into city (id, name, state_id) values (4, 'Paraty', 2);
insert into city (id, name, state_id) values (5, 'Belo Horizonte', 3);
insert into city (id, name, state_id) values (6, 'Uberlândia', 3);
insert into city (id, name, state_id) values (7, 'Itabira', 3);
insert into city (id, name, state_id) values (8, 'Aracaju', 4);
insert into city (id, name, state_id) values (9, 'Boquim', 4);
insert into city (id, name, state_id) values (10, 'Lagarto', 4);
insert into city (id, name, state_id) values (11, 'Recife', 5);

insert into user (id, username, email, phone, person_name, person_gender, person_birth_date, cpf, address_street, address_city_id, created_at, updated_at, status) values (1, 'caleb', 'calebisaacdarosa-93@marcati.com', '5579555555555','Caleb Isaac da Rosa', 'MASCULINO', CONVERT('2001-11-02', DATE), '217.679.974-77', 'rua 0', 5, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true);
insert into user (id, username, email, phone, person_name, person_gender, person_birth_date, cpf, address_street, address_city_id, created_at, updated_at, status) values (2, 'rebeca', 'rrebecamariacardoso@fictor.com.br', '5579333333333','Rebeca Maria Cardoso', 'FEMININO', CONVERT('2001-11-02', DATE), '390.605.515-91', 'rua 0', 5, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true);
insert into user (id, username, email, phone, person_name, person_gender, person_birth_date, cpf, address_street, address_city_id, created_at, updated_at, status) values (3, 'alana', 'aalanaalicemonteiro@a-qualitybrasil.com.br', '5579222222222','Alana Alice Monteiro', 'FEMININO', CONVERT('2001-11-02', DATE), '017.428.544-27', 'rua 0', 5, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true);
insert into user (id, username, email, phone, person_name, person_gender, person_birth_date, cpf, address_street, address_city_id, created_at, updated_at, status) values (4, 'nathan', 'nathanfernandovitorporto-94@holtmail.com', '5579111111111','Nathan Fernando Vitor Porto', 'MASCULINO', CONVERT('2001-11-02', DATE), '034.861.443-83', 'rua 0', 5, UTC_TIMESTAMP(), UTC_TIMESTAMP(), true);

insert into meetup (id, tag, name, description, user_id, created_at, updated_at) values (1, 'first-event', 'First Event', 'This is a description', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP());

insert into meetup_registration (id, entry_time, is_according_terms, meetup_id, user_id) values (1, UTC_TIMESTAMP(), true, 1, 2);
insert into meetup_registration (id, entry_time, is_according_terms, meetup_id, user_id) values (2, UTC_TIMESTAMP(), true, 1, 3);
insert into meetup_registration (id, entry_time, is_according_terms, meetup_id, user_id) values (3, UTC_TIMESTAMP(), true, 1, 4);
