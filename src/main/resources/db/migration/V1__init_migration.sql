create table recipes
(
    id              int primary key auto_increment,
    name            varchar(40) not null,
    time_to_prepare time        not null,
    created_at      datetime    not null,
    planned         bit         not null
);

create table ingredients
(
    id      int primary key auto_increment,
    name    varchar(50) not null,
    planned bit         not null
);

create table steps
(
    id          int primary key auto_increment,
    description varchar not null
);

alter table recipes
    add foreign key (id) references ingredients (id);
alter table recipes
    add foreign key (id) references steps (id);