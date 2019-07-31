create table employees (
id int unsigned primary key auto_increment,
first_name varchar(45) not null,
last_name varchar(45) not null,
email varchar(45)
);
insert into employees (first_name, last_name, email) values ('Marian', 'Broda', 'prezes@brodacompany.com');
insert into employees (first_name, last_name, email) values ('Aleksandra', 'Pupcia', 'pupcia@brodacompany.com');
insert into employees (first_name, last_name, email) values ('Barbara', 'Wu', 'basia@brodacompany.com');
insert into employees (first_name, last_name, email) values ('Katarzyna', 'Świergot', 'czorna@brodacompany.com');
insert into employees (first_name, last_name, email) values ('Jolanta', 'Kopciarz', 'pokuc@brodacompany.com');
