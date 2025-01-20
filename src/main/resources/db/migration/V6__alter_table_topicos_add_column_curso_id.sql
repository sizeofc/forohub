alter table topicos
    add column curso_id bigint null,
    ADD CONSTRAINT fk_topico_curso
        FOREIGN KEY (curso_id) REFERENCES cursos (id);
update topicos set curso_id=1 where curso_id is null;