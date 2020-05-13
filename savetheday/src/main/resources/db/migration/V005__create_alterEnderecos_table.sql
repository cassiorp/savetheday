ALTER TABLE enderecos
ADD id_ong int not null;

ALTER TABLE enderecos
ADD FOREIGN KEY (id_ong) REFERENCES ongs(id);