use cotizacion;
drop table if exists region;
drop table if exists comuna;
drop table if exists empleado;
drop table if exists departamento;
drop table if exists cotizacion;

create table region (
    id int primary key auto_increment,
    nombre varchar(30) not null
);

create table comuna (
     id int primary key auto_increment,
     nombre varchar(30) not null
);

create table departamento (
    id int primary key auto_increment,
    nombre varchar(30) not null
);

create table empleado (
    id int primary key auto_increment,
    nombre varchar(40) not null,
    apellido varchar(50) not null,
    telefono varchar(15)
);

create table cotizacion (
       id int primary key auto_increment,
       motivo varchar(100) not null,
       descripcion varchar(500) not null,
       monto int,
       fecha_cotizacion date not null
);

alter table comuna 
    add column region_id int,
    add foreign key (region_id) references region(id);

alter table departamento 
    add column comuna_id int,
    add column empleado_id int,
    add foreign key (comuna_id) references comuna(id),
    add foreign key (empleado_id) references empleado(id);

alter table cotizacion 
    add column departamento_id int,
    add foreign key (departamento_id) references departamento(id);

insert into region ( id, nombre ) values
(1, 'Metropolitana'),
(2, 'Arica y parinacota'),
(3, 'Los lagos');

insert into comuna ( id, nombre, region_id ) values
(1, 'Santiago', 1),
(2, 'La Florida', 1),
(3, 'Maipu', 1);

insert into empleado ( id, nombre, apellido, telefono ) values
(1, 'Mario', 'Kruburgerzt', '967832521'),
(2, 'Jose', 'Aravena', '992452722'),
(3, 'Juan Pablo', 'Gonzalez', '982432423');

insert into departamento ( id, nombre, comuna_id, empleado_id ) values
(1, 'PJD Costanera', 1,1),
(2, 'PJD Maipu', 3,2),
(3, 'PJD La FLorida', 2,1);

insert into cotizacion ( id, motivo, descripcion, monto, fecha_cotizacion, departamento_id ) values
(1, 'Congelador', 'Se debe revisar el motor de congelador', 300000,'2023-01-17',1),
(2, 'Cortina Metalica', 'Instalacion de cortina metalica',600000,'2022-12-26', 2),
(3, 'Servicio Electrico', 'La luz presenta problemas y cortes electricos se debe realizar chequeo general', 150000, '2022-12-30', 3);