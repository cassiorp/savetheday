create table ongs(
    id int not null primary key auto_increment,
    nome varchar(120) not null,
    sigla varchar(20) not null,
    fundacao datetime not null,
    cnpj varchar(18),
    foto varchar(255),
    telefone varchar(30) not null,
    email varchar(60) not null,
    senha varchar(20) not null,
    categoria ENUM('Inclusão social de pessoas com deficiência','Assistência social', 'Cultura', 'Saúde', 'Meio ambiente','Desenvolvimento e defesa de direitos','Habitação','Educação e Pesquisa','Direitos dos Animais'),
    id_endereco int not null,

    foreign key ( id_endereco ) references enderecos ( id )
);