-- liquibase formatted sql
--changeset anton:create_user
DROP TABLE IF EXISTS public.user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS comment;
CREATE TABLE public.user
(
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(32)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    firstname VARCHAR(32),
    lastname  VARCHAR(32),
)

--changeset anton:create_task
CREATE TABLE task
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(64) NOT NULL,
    description  VARCHAR(255),
    status       VARCHAR(16) NOT NULL,
    priority     VARCHAR(16) NOT NULL,
    author_id    INTEGER     NOT NULL,
    performer_id INTEGER,
    FOREIGN KEY (author_id) REFERENCES public.user (id),
    FOREIGN KEY (performer_id) REFERENCES public.user (id)
)

--changeset anton:create_comment
CREATE TABLE comment
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(255),
    task_id INTEGER NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (id)
)

