CREATE TABLE workers(
    id INT PRIMARY KEY,
    name VARCHAR NOT NULL,
    birthday DATE CHECK (birthday >= '1900-01-01'),
    levelTechSkill VARCHAR NOT NULL CHECK (levelTechSkill in ('Trainee', 'Junior', 'Middle', 'Senior')),
    salary INT CHECK (salary > 100  AND  salary<100000)
);

CREATE TABLE clients  (
    id int PRIMARY KEY,
    name VARCHAR   NOT NULL CHECK (LENGTH(name) >2 AND  LENGTH(name) <1000)
 );

CREATE TABLE projects  (
    id int PRIMARY KEY,
    client_id  INT,
    start_date   DATE,
    finish_date  DATE
    );

CREATE TABLE project_worker  (
    project_id  INT ,
    worker_id  INT
    );
alter table project_worker   add constraint project_worker_workers_fk
FOREIGN key (worker_id) references workers(id) on delete cascade;

alter table project_worker   add constraint project_worker_projects_fk
FOREIGN key (project_id) references projects(id) on delete cascade;