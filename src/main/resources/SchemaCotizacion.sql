use cotizacion;
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