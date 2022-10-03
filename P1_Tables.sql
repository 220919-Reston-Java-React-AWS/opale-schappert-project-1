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
	('Bach_tran', 'pass85', 'Bach', 'Tran', 2);
	
----USERS------

---REIMBURSEMENTS-----

create table reimbursements(
	id SERIAL primary key,
	amount MONEY, 
	reimburesement_type VARCHAR(30),
	employee_id INTEGER references users(id),
	manager_id INTEGER references users(id),
	status VARCHAR(30)
);

insert into reimbursements (amount, employee_id)
	values 
	(1200, 1),
	(12.50, 1);

---REIMBURSEMENTS------
