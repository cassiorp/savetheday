create table estados(
    id int not null primary key auto_increment,
    nome varchar(60) not null
);

create table cidades(
    id int not null primary key auto_increment,
    nome varchar(60) not null,
    id_estado int not null,
    foreign key ( id_estado ) references estados( id )
);

create table enderecos(
    id int not null primary key auto_increment,
    bairro varchar(60) not null,
    rua varchar(60) not null,
    numero varchar(20) not null,
    CEP varchar(8),
    id_cidade int not null,
    foreign key ( id_cidade ) references cidades( id )
);