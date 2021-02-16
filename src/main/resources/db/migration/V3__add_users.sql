create table users (
    id int primary key auto_increment,
    username varchar not null,
    password varchar not null
);

alter table users
add foreign key (id) references RECIPE(ID);