create sequence professor_id_seq;

create table professor
(
    id_prof                     bigint                 not null default nextval('professor_id_seq'),
    nome_prof                   character varying(21)  not null,
    sobrenome_prof              character varying(21)  not null,
    email_prof                  character varying(75)  not null,
    telefone_prof               character varying(15)  not null,
    data_nascimento_prof        date                   not null,
    nacionalidade_prof          character varying(45)  not null,
    grau_academico_prof         character varying(45)  not null,
    area_de_formacao_prof       character varying(75)  not null,
    instituicao_prof            character varying(75)  not null,
    ano_de_formacao_prof        int                    not null,
    ja_ensinou_online_prof      boolean                not null,
    descricao_experiencia_prof  character varying(3000)         default null,
    url_curriculum_prof         character varying(255) not null,
    url_identificacao_prof      character varying(255) not null,
    url_video_apresentacao_prof character varying(255) not null,
    rede_social_prof            character varying(75)  not null,
    data_requisicao             timestamp              not null default current_timestamp,
    constraint professor_pk
        primary key (id_prof),
    constraint professor_email_unique
        unique (email_prof),
    constraint professor_telefone_unique
        unique (telefone_prof),
    constraint professor_bi_unique
        unique (url_identificacao_prof),
    constraint professor_cb_unique
        unique (url_curriculum_prof),
    constraint professor_apresentacao_unique
        unique (url_video_apresentacao_prof),
    constraint professor_rede_social_unique
        unique (rede_social_prof)
);
