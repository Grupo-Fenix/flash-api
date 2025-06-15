create type tipo_utilizador as enum ('ADMINISTRADOR', 'ALUNO', 'GERENTE', 'PROFESSOR');

create sequence utilizador_id_seq;

create table utilizador
(
    id_utilizador       bigint                 not null default nextval('utilizador_id_seq'),
    nome_utilizador     character varying(15)  not null,
    email_utilizador    character varying(75)  not null,
    password_utilizador character varying(255) not null,
    tipo__utilizador    tipo_utilizador        not null,
    eliminado           boolean                not null default false,
    criado_em           timestamp              not null default current_timestamp,
    atualizado_em       timestamp              not null default current_timestamp,
    constraint utilizador_pk
        primary key (id_utilizador),
    constraint utilizador_username_unique
        unique (nome_utilizador),
    constraint utilizador_email_unique
        unique (email_utilizador)
);


create sequence aluno_id_sequence;

create table aluno
(
    id_aluno              bigint                not null default nextval('aluno_id_sequence'),
    nome_aluno            character varying(21) not null,
    sobrenome_aluno       character varying(21) not null,
    numero_bi_aluno       character(15)         not null,
    data_nascimento_aluno date                  not null,
    utilizador_id         bigint                not null,
    constraint aluno_pk
        primary key (id_aluno, utilizador_id),
    constraint aluno_nif_unique
        unique (numero_bi_aluno)
);
