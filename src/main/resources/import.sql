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

insert into user (id, username, email, phone, person_name, person_gender, person_birth_date, cpf, address_street, address_city_id, created_at) values (1, 'caleb', 'calebisaacdarosa-93@marcati.com', '5579555555555','Caleb Isaac da Rosa', 'MASCULINO', CONVERT('2001-11-02', DATE), '57382624765', 'rua 0', 5, UTC_TIMESTAMP());
