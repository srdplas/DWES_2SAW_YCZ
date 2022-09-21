CREATE database AutoEvaluacion;
Use AutoEvaluacion;

CREATE TABLE empleados (
    nombre VARCHAR(15) not null,
    dni VARCHAR(10),
    sexo CHAR(1),
    categoria int(2) default 1,
    anyosTrabajados int(11) default 0,
    constraint empleado_pk primary key (dni)
);

CREATE TABLE nominas (
	dni VARCHAR(10),
    sueldo decimal(12),
    

    constraint empleado_pk primary key (dni),
    constraint dni_fk foreign key (dni) references empleados(dni)
);

CREATE USER 'yuri'@'localhost' IDENTIFIED BY 'yuri';
GRANT ALL PRIVILEGES ON * . * TO 'yuri'@'localhost';

insert into empleados values ("James", "32000032G", 'M', 4, 7);
insert into empleados values ("Ada Lovelace", "32000031R", 'F');

insert into nominas values ("32000032G", 245000);
insert into nominas values ("32000031R", 50000);

