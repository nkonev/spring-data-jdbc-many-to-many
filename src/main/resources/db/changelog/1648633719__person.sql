CREATE TABLE person(id BIGSERIAL PRIMARY KEY, first_name text not null , last_name text not null);

INSERT INTO person (first_name, last_name)
SELECT 'John' || i, 'Doe' || i FROM generate_series(0, 200) AS i;