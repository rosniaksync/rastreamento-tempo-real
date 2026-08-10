create table entregadores (
    id bigserial primary key,
    nome varchar(255) not null,
    telefone varchar(20) not null,
    status_entregador varchar(20) not null
);