create table usuarios(
    id bigint not null auto_increment,
    username varchar(100) not null unique,
    clave varchar(100) not null,

    primary key (id)
);