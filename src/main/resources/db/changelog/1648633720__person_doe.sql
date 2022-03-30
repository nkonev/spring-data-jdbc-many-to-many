INSERT INTO person (first_name, last_name)
SELECT 'John' || i, 'Doe' FROM generate_series(0, 50) AS i;