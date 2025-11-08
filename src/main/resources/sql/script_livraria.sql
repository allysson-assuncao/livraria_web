CREATE SCHEMA IF NOT EXISTS allysson_livraria;

CREATE TABLE allysson_livraria.autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE allysson_livraria.livro (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE,
    ano_publicacao INT,
    id_autor INT NOT NULL,
    CONSTRAINT fk_autor
        FOREIGN KEY(id_autor)
        REFERENCES allysson_livraria.autor(id)
        ON DELETE CASCADE
);