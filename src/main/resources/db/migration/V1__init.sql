create table products
(
id          bigserial primary key,
title       varchar(255) not null,
price       int not null,
created_at  timestamp default current_timestamp,
updated_at  timestamp default current_timestamp
);

insert into products
(title, price) values
('Product_1', 80),('Product_2', 25),('Product_3', 300),
('Product_4', 80),('Product_5', 25),('Product_6', 300),
('Product_7', 80),('Product_8', 25),('Product_9', 300),
('Product_10', 80),('Product_11', 25),('Product_12', 300),
('Product_13', 80),('Product_14', 25),('Product_15', 300),
('Product_16', 80),('Product_17', 25),('Product_18', 300),
('Product_19', 80),('Product_20', 25),('Product_21', 300),
('Product_22', 80),('Product_23', 25),('Product_24', 300),
('Product_25', 80),('Product_26', 25),('Product_27', 300),
('Product_28', 80),('Product_29', 25),('Product_30', 300)
;

create table users
(
id bigserial primary key,
username        varchar(36) not null,
password        varchar(80) not null,
address         varchar(255),
phone           varchar(255),
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

create table roles
(
id              bigserial primary key,
name            varchar(50) not null,
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

create table users_roles
(
user_id         bigint not null references users (id),
role_id         bigint not null references roles (id),
primary key (user_id, role_id),
created_at      timestamp default current_timestamp,
updated_at      timestamp default current_timestamp
);

insert into roles (name) values
('ROLE_USER'),
('ROLE_ADMIN'),
('ROLE_SUPERADMIN');

insert into users
(username, password) values
('User','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6'),
('Admin','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6'),
('SA','$2a$12$WZ/iYUZjSs.gfhJK0U7jEe1aKVjQUYVmRg.Jxy9ciKB6xoxatLxc6')
       ;

insert into users_roles
(user_id, role_id) values
(1,1),
(2,2),
(3,3);

create table orders
(
id                  bigserial primary key,
user_id             bigint not null references users (id),
total_price         int not null,
address             varchar(255),
phone               varchar(255),
created_at          timestamp default current_timestamp,
updated_at          timestamp default current_timestamp
);

create table order_items
(
id                  bigserial primary key,
product_id          bigint not null references products (id),
order_id            bigint not null references orders (id),
quantity            int not null,
price_per_product   int not null,
price               int not null,
created_at          timestamp default current_timestamp,
updated_at          timestamp default current_timestamp
);




















