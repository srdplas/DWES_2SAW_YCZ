CREATE database AutoEvaluacion;

CREATE TABLE empleados (
    nombre VARCHAR(10) not null,
    dni int(8),
    sexo CHAR(1),
    categoria int(2),
    anyosTrabajados int(11)
    constraint empleado_pk primary key (dni)
);