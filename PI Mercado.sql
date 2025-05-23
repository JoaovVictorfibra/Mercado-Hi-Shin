CREATE DATABASE Mercado1;

USE Mercado1;

CREATE TABLE Produto (
	Id int primary key auto_increment,
    nome VARCHAR(100),
    codigo int unique,
    preco decimal(4,2)
    );
    
CREATE TABLE Funcionario (
	Id int primary key auto_increment,
    login VARCHAR(50) unique,
    senha VARCHAR(50),
    nivel VARCHAR(50)
    );
    
    
CREATE TABLE RegistroVenda (
	Id int auto_increment primary key,
    forma_pagamento Varchar(20),
    hora datetime,
    nome VARCHAR(100),
    total decimal(6,2)
    );
    
    
    Insert into Funcionario (login, senha, nivel) values
    ("Joao", "1234", "Gerente"),
    ("Felipe", "1234", "atendente");