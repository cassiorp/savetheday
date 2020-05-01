create table casos(
	id int not null primary key auto_increment,
    titulo varchar(25) not null,
    descricao varchar(255) not null,
    total decimal(10,2) not null,
    coletado decimal(10,2),
    status ENUM('ABERTA', 'FECHADA', 'CANCELADA'),
    id_ong int not null,
    foreign key ( id_ong ) references ongs ( id )
);
