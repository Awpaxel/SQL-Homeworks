package SQLHomeworks;

-- 1. film tablosunda title ve description sütunlarını listeleme
        SELECT title, description
FROM film;

-- 2. length > 60 AND length < 75
SELECT *
FROM film
WHERE length > 60 AND length < 75;

        -- 3. rental_rate = 0.99 AND replacement_cost IN (12.99, 28.99)
SELECT *
FROM film
WHERE rental_rate = 0.99 AND (replacement_cost = 12.99 OR replacement_cost = 28.99);

-- 4. first_name = 'Mary' olan müşterinin soyadı
SELECT last_name
FROM customer
WHERE first_name = 'Mary';

-- 5. length <= 50 AND rental_rate NOT IN (2.99, 4.99)
SELECT *
FROM film
WHERE length <= 50 AND rental_rate NOT IN (2.99, 4.99);
