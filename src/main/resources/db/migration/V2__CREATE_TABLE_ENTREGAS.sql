create table entregas (
    id bigserial primary key,
    criada_em timestamp not null default current_timestamp,
    finalizada_em timestamp,
    status_entrega varchar(20) not null,
    entregador_id bigint not null,
    foreign key (entregador_id) references entregadores(id)
);