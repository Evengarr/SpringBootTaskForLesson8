CREATE TABLE simple_products
(
    id         bigserial PRIMARY KEY,
    title      VARCHAR(255),
    cost       double precision,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
INSERT INTO simple_products (title, cost)
VALUES ('aaa', 1.1),
       ('bbb', 2.15),
       ('ccc', 10.13),
       ('ddd', 5.5),
       ('eee', 50.15),
       ('fff', 32.3),
       ('ggg', 16.5),
       ('hhh', 100.5),
       ('iii', 95.9),
       ('jjj', 999.9),
       ('kkk', 235.35),
       ('lll', 361.01),
       ('mmm', 509.9),
       ('nnn', 55.9),
       ('ooo', 94.31),
       ('ppp', 888.9);