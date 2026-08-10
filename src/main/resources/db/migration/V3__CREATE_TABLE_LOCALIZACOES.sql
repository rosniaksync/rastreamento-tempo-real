create table localizacoes (
    id bigserial primary key,
    latitude numeric(10, 7) not null,
    longitude numeric(10, 7) not null,
    registrada_em timestamp not null default current_timestamp,
    entrega_id bigint not null,
    foreign key (entrega_id) references entregas(id)
);