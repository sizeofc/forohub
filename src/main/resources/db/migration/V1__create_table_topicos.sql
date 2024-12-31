create table topicos
(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(500) not null,
    fecha_creacion datetime not null,
    status tinyint,

    primary key(id)

);