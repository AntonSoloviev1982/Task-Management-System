-- liquibase formatted sql

--changeset anton:create_users
DROP TABLE IF EXISTS public.user;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS comment;
CREATE TABLE public.user
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(32)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(32),
    last_name  VARCHAR(32),
    role       VARCHAR(16)  NOT NULL
);

--changeset anton:create_tasks
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
);

--changeset anton:create_comments
CREATE TABLE comment
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(255),
    task_id INTEGER NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task (id)
);

