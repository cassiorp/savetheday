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
    categoria ENUM('INCLUCAO_DE_PESSOAS_COM_DEFICIENCIA','ASSISTENCIA_SOCIAL', 'CULTURA', 'SAUDE', 'MEIO_AMBIENTE', 'DESENVOLVIMENTO_E_DEFESA_DOS_DIREITOS', 'HABITACAO', 'DIREITOS_DOS_ANIMAIS', 'EDUCACAO_E_PESQUISA')
);