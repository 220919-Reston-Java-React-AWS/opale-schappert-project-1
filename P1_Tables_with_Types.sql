--Create  tables
drop table if exists reimbursements ;
drop table if exists users ;
drop table if exists roles ;

-- ROLES ----
create table roles(
	id SERIAL primary key,
	role_name VARCHAR(20)
);

insert into roles( role_name)
values
('Employee');

insert into roles( role_name)
values
('Manager');

---ROLES ------


----USERS------

create table users (
	id SERIAL primary key,
	user_name VARCHAR(100) not null unique,
	pass_word VARCHAR(100) not null,
	first_name VARCHAR(50) not null,
	last_name VARCHAR(50) not null,
	role_id INTEGER references roles(id) not null
);

insert into users(user_name, pass_word, first_name, last_name, role_id)
	values
	('Test', '1236', 'Bryan', 'Johnson', 1),
	('Opale_Schapp', 'pass85', 'Opale', 'Schappert', 2),
	('Joe_Scho', 'password123', 'Joe','Schmoe', 1);
	
----USERS------

---REIMBURSEMENTS-----

create table reimbursements(
	id SERIAL primary key,
	amount numeric (7,2) not null , 
	reimbursement_type VARCHAR(50) not null,
	reimbursement_descrip VARCHAR(200) not null,
	employee_id INTEGER references users(id),
	manager_id INTEGER references users(id),
	status VARCHAR(30) default 'pending'
);

insert into reimbursements (amount, employee_id, reimbursement_type, reimbursement_descrip)
	values 
	(1200, 1,'Travel', 'Conference in LA'),
	(50.00, 3,'Food', 'Dinner with Client'),
	(12.55, 1, 'Food', 'Lunch');

---REIMBURSEMENTS------ 






