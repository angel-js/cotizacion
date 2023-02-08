use cotizacion;
drop table if exists empleado_departamento;
drop table if exists cotizacion;
drop table if exists departamento;
drop table if exists empleado;
drop table if exists comuna;
drop table if exists region;
drop table if exists usuario;

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
    nombre varchar(30) not null,
    direccion varchar(100)
);

create table empleado (
    id int primary key auto_increment,
    nombre varchar(40) not null,
    apellido varchar(50) not null,
    correo varchar(120),
    telefono varchar(15),
    cargo varchar(35)
);

create table cotizacion (
       id int primary key auto_increment,
       motivo varchar(100) not null,
       descripcion varchar(500) not null,
       monto int not null,
       fecha_cotizacion date not null
);

create table usuario (
    id int primary key auto_increment,
    nombre varchar(40) not null,
    apellido varchar(50) not null,
    correo varchar(120) not null,
    contra varchar(50) not null
);


alter table comuna 
    add column region_id int,
    add foreign key (region_id) references region(id);

alter table departamento 
    add column comuna_id int,
    add foreign key (comuna_id) references comuna(id);

alter table cotizacion 
    add column departamento_id int,
    add foreign key (departamento_id) references departamento(id);

CREATE TABLE empleado_departamento (
  empleado_id int NOT NULL,
  departamento_id int NOT NULL,
  PRIMARY KEY (empleado_id, departamento_id),
  FOREIGN KEY (empleado_id) REFERENCES empleado (id),
  FOREIGN KEY (departamento_id) REFERENCES departamento (id)
);

insert into region ( id, nombre ) values
(1, 'Arica y Parinacota'),
(2, 'Tarapacá'),
(3, 'Antofagasta'),
(4, 'Atacama'),
(5, 'Coquimbo'),
(6, 'Valparaíso'),
(7, 'Libertador General Bernardo O\'Higgins'),
(8, 'Maule'),
(9, 'Ñuble'),
(10, 'Bio Bio'),
(11, 'Araucanía'),
(12, 'Los Ríos'),
(13, 'Los Lagos'),
(14, 'Aysén del General Carlos Ibáñez del Campo'),
(15, 'Magallanes y de la Antártica Chilena'),
(16, 'Metropolitana de Santiago');

insert into comuna ( id, nombre, region_id ) values
(1, 'Arica', 1),
(2, 'Camarones', 1),
(3, 'General Lagos', 1),
(4, 'Putre', 1),
(5, 'Alto Hospicio', 2),
(6, 'Camiña', 2),
(7, 'Colchane', 2),
(8, 'Huara', 2),
(9, 'Iquique', 2),
(10, 'Pica', 2),
(11, 'Pozo Almonte', 2),
(12, 'Antofagasta', 3),
(13, 'Calama', 3),
(14, 'Maria Elena', 3),
(15, 'Mejillones', 3),
(16, 'Ollagüe', 3),
(17, 'San Pedro de Atacama', 3),
(18, 'Sierra Gorda', 3),
(19, 'Taltal', 3),
(20, 'Tocopilla', 3),
(21, 'Alto del Carmen', 4),
(22, 'Caldera', 4),
(23, 'Chañaral', 4),
(24, 'Copiapó', 4),
(25, 'Diego de Almagro', 4),
(26, 'Freirina', 4),
(27, 'Huasco', 4),
(28, 'Tierra Amarilla', 4),
(29, 'Vallenar', 4),
(30, 'Andacollo', 5),
(31, 'Canela', 5),
(32, 'Combarbalá', 5),
(33, 'Coquimbo', 5),
(34, 'Illapel', 5),
(35, 'La Higuera', 5),
(36, 'La Serena', 5),
(37, 'Los Vilos', 5),
(38, 'Monte Patria', 5),
(39, 'Ovalle', 5),
(40, 'Paihuano', 5),
(41, 'Punitaqui', 5),
(42, 'Rio Hurtado', 5),
(43, 'Salamanca', 5),
(44, 'Vicuña', 5),
(45, 'Algarrobo', 6),
(46, 'Cabildo', 6),
(47, 'Calera', 6),
(48, 'Calle larga', 6),
(49, 'Cartagena', 6),
(50,'Casablanca',6),
(51,'Catemu',6),
(52,'Concón',6),
(53,'El Quisco',6),
(54,'El Tabo',6),
(55,'Hijuelas',6),
(56,'Isla de Pascua',6),
(57,'Juan Fernández',6),
(58,'La Cruz',6),
(59,'La Ligua',6),
(60,'Limache',6),
(61,'Llaillay',6),
(62,'Los Andes',6),
(63,'Nogales',6),
(64,'Olmué',6),
(65,'Panquehue',6),
(66,'Papudo',6),
(67,'Petorca',6),
(68,'Puchuncaví',6),
(69,'Putaendo',6),
(70,'Quillota',6),
(71,'Quilpué',6),
(72,'Quintero',6),
(73,'Rinconada',6),
(74,'San Antonio',6),
(75,'San esteban',6),
(76,'San Felipe',6),
(77,'Santa María',6),
(78,'Santo Domingo',6),
(79,'Valparaíso',6),
(80,'Villa Alemana',6),
(81,'Viña del Mar',6),
(82,'Zapallar',6),
(83,'Chépica',7),
(84,'Chimbarongo',7),
(85,'Codegua',7),
(86,'Coínco',7),
(87,'Coltauco',7),
(88,'Doñihue',7),
(89,'Graneros',7),
(90,'La Estrella',7),
(91,'Las Cabras',7),
(92,'Litueche',7),
(93,'Lolol',7),
(94,'Machalí',7),
(95,'Malloa',7),
(96,'Marchihue',7),
(97,'Mostazal',7),
(98,'Nancagua',7),
(99,'Navidad',7),
(100,'Olivar',7),
(101,'Palmilla',7),
(102,'Paredones',7),
(103,'Peralillo',7),
(104,'Peumo',7),
(105,'Pichidegua',7),
(106,'Pichilemu',7),
(107,'Placilla',7),
(108,'Pumanque',7),
(109,'Quinta de Tilcoco',7),
(110,'Rancagua',7),
(111,'Rengo',7),
(112,'Requínoa',7),
(113,'San Fernando',7),
(114,'Santa Cruz',7),
(115,'San Vicente',7),
(116,'Cauquenes',8),
(117,'Chanco',8),
(118,'Colbún',8),
(119,'Constitución',8),
(120,'Curepto',8),
(121,'Curicó',8),
(122,'Empredado',8),
(123,'Hualañe',8),
(124,'Licatén',8),
(125,'Linares',8),
(126,'Longaví',8),
(127,'Maule',8),
(128,'Molina',8),
(129,'Parral',8),
(130,'Pelarco',8),
(131,'Pelluhue',8),
(132,'Pecanhue',8),
(133,'Rauco',8),
(134,'Retiro',8),
(135,'Río Claro',8),
(136,'Romeral',8),
(137,'Sagrada Familia',8),
(138,'San Clemente',8),
(139,'San Javier',8),
(140,'San Rafael',8),
(141,'Talca',8),
(142,'Teno',8),
(143,'Vichuquén',8),
(144,'Villa Alegre',8),
(145,'Yerbas Buenas',8),
(146,'Bulnes',9),
(147,'Chillán',9),
(148,'Chillán Viejo',9),
(149,'Cobquecura',9),
(150,'Coelemu',9),
(151,'Coihueco',9),
(152,'El Carmen',9),
(153,'Ninhue',9),
(154,'Ñinquen',9),
(155,'Pemuco',9),
(156,'Pinto',9),
(157,'Portezuelo',9),
(158,'Quillón',9),
(159,'Quirihue',9),
(160,'Ránquil',9),
(161,'San Carlos',9),
(162,'San Fabián',9),
(163,'San Ignacio',9),
(164,'San Nicolás',9),
(165,'Treguaco',9),
(166,'Yungay',9),
(167,'Alto Biobío',10),
(168,'Antuco',10),
(169,'Arauco',10),
(170,'Cabrero',10),
(171,'Cañete',10),
(172,'Chiguayante',10),
(173,'Concepción',10),
(174,'Contulmo',10),
(175,'Coronel',10),
(176,'Curanilahue',10),
(177,'Florida',10),
(178,'Hualpén',10),
(179,'Hualqui',10),
(180,'Laja',10),
(181,'Lebu',10),
(182,'Los Alamos',10),
(183,'Los Angeles',10),
(184,'Lota',10),
(185,'Mulchén',10),
(186,'Nacimiento',10),
(187,'Negrete',10),
(188,'Penco',10),
(189,'Quilaco',10),
(190,'Quilleco',10),
(191,'San Pedro de la Paz',10),
(192,'San Rosendo',10),
(193,'Santa Bárbara',10),
(194,'Santa Juana',10),
(195,'Talcahuano',10),
(196,'Tirúa',10),
(197,'Tomé',10),
(198,'Tucapel',10),
(199,'Yumbel',10),
(200,'Angol',11),
(201,'Carahue',11),
(202,'Cholchol',11),
(203,'Collipulli',11),
(204,'Cunco',11),
(205,'Curacautín',11),
(206,'Curarrehue',11),
(207,'Ercilla',11),
(208,'Freire',11),
(209,'Galvarino',11),
(210,'Gorbea',11),
(211,'Lautaro',11),
(212,'Loncoche',11),
(213,'Lonquimay',11),
(214,'Los Sauces',11),
(215,'Lumaco',11),
(216,'Melipeuco',11),
(217,'Nueva Imperial',11),
(218,'Padre Las Casas',11),
(219,'Perquenco',11),
(220,'Pitrufquén',11),
(221,'Pucón',11),
(222,'Purén',11),
(223,'Renaico',11),
(224,'Saavedra',11),
(225,'Temuco',11),
(226,'Teodoro Schmidt',11),
(227,'Toltén',11),
(228,'Traiguén',11),
(229,'Victoria',11),
(230,'Vilcún',11),
(231,'Villarrica',11),
(232,'Corral',12),
(233,'Futrono',12),
(234,'Lago Ranco',12),
(235,'Lanco',12),
(236,'La Unión',12),
(237,'Los Lagos',12),
(238,'Máfil',12),
(239,'Mariquina',12),
(240,'Paillaco',12),
(241,'Panguipulli',12),
(242,'Río Bueno',12),
(243,'Valdivia',12),
(244,'Ancud',13),
(245,'Calbuco',13),
(246,'Castro',13),
(247,'Chaitén',13),
(248,'Chonchi',13),
(249,'Cochamó',13),
(250,'Curaco de Vélez',13),
(251,'Dalcahue',13),
(252,'Fresia',13),
(253,'Frutillar',13),
(254,'Futaleufú',13),
(255,'Hualaihué',13),
(256,'Llanquihue',13),
(257,'Los Muermos',13),
(258,'Maullín',13),
(259,'Osorno',13),
(260,'Palena',13),
(261,'Puerto Montt',13),
(262,'Puerto Octay',13),
(263,'Puerto Varas',13),
(264,'Puqueldón',13),
(265,'Purranque',13),
(266,'Puyehue',13),
(267,'Queilén',13),
(268,'Quellón',13),
(269,'Quemchi',13),
(270,'Quinchao',13),
(271,'Río Negro',13),
(272,'San Juan de la Costa',13),
(273,'San Pablo',13),
(274,'Aysén',14),
(275,'Chile Chico',14),
(276,'Cisnes',14),
(277,'Cochrane',14),
(278,'Coyhaique',14),
(279,'Guaitecas',14),
(280,'Lago Verde',14),
(281,'O\'Higgins',14),
(282,'Río Ibáñez',14),
(283,'Tortel',14),
(284,'Antártica',15),
(285,'Cabo de Hornos',15),
(286,'Laguna Blanca',15),
(287,'Natales',15),
(288,'Porvenir',15),
(289,'Primavera',15),
(290,'Punta Arenas',15),
(291,'Río Verde',15),
(292,'San Gregorio',15),
(293,'Timaukel',15),
(294,'Torres del Paine',15),
(295,'Alhué',16),
(296,'Buin',16),
(297,'Calera de Tango',16),
(298,'Cerrillos',16),
(299,'Cerro Navia',16),
(300,'Colina',16),
(301,'Conchalí',16),
(302,'Curacaví',16),
(303,'El Bosque',16),
(304,'El Monte',16),
(305,'Estación Central',16),
(306,'Huechuraba',16),
(307,'Independencia',16),
(308,'Isla de Maipo',16),
(309,'La Cisterna',16),
(310,'La Florida',16),
(311,'La Granja',16),
(312,'Lampa',16),
(313,'La Pintana',16),
(314,'La Reina',16),
(315,'Las Condes',16),
(316,'Lo Barnechea',16),
(317,'Lo Espejo',16),
(318,'Lo Prado',16),
(319,'Macul',16),
(320,'Maipú',16),
(321,'María Pinto',16),
(322,'Melipilla',16),
(323,'Ñuñoa',16),
(324,'Padre Hurtado',16),
(325,'Paine',16),
(326,'Pedro Aguirre Cerda',16),
(327,'Peñaflor',16),
(328,'Peñalolén',16),
(329,'Pirque',16),
(330,'Providencia',16),
(331,'Pudahuel',16),
(332,'Puente Alto',16),
(333,'Quilicura',16),
(334,'Quinta Normal',16),
(335,'Recoleta',16),
(336,'Renca',16),
(337,'San Bernardo',16),
(338,'San Joaquín',16),
(339,'San José de Maipo',16),
(340,'San Miguel',16),
(341,'San Pedro',16),
(342,'San Ramón',16),
(343,'Santiago',16),
(344,'Talagante',16),
(345,'Tiltil',16),
(346,'Vitacura',16);
