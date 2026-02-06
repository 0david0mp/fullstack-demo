-- auto-generated definition
create table if not exists workcolumn
(
    id   serial  primary key,
    name varchar not null
);

-- auto-generated definition
create table if not exists workitem
(
    id          serial primary key,
    title       varchar,
    description varchar,
    ordering    integer unique,
    workcolumn  integer default 1 not null
        constraint fk_workitem_workcolumn
            references workcolumn (id)
);

INSERT INTO workcolumn (name) VALUES ('Ideas'), ('Prepared');
INSERT INTO workitem (title, description, ordering) VALUES
('Titulo 1', 'Descripción larga larga', 0),
('Titulo 2', 'Descripción larga larga', 1);
