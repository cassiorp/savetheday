create table if not exists contas(
	id int not null primary key auto_increment,
    nomeBanco varchar(60) not null,
    agencia varchar(20) not null,
    numero varchar(30) not null,
    digito varchar(2),
    id_ong int not null,
    foreign key ( id_ong ) references ongs( id )
);