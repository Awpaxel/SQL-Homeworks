package SQLHomeworks9;

public class SQLHomeworks9

-- 1. City ve Country tablolarını INNER JOIN ile birleştirme
SELECT city.city, country.country
FROM city
INNER JOIN country ON city.country_id = country.country_id;

-- 2. Customer ve Payment tablolarını INNER JOIN ile birleştirme
SELECT payment.payment_id, customer.first_name, customer.last_name
FROM customer
INNER JOIN payment ON customer.customer_id = payment.customer_id;

-- 3. Customer ve Rental tablolarını INNER JOIN ile birleştirme
SELECT rental.rental_id, customer.first_name, customer.last_name
FROM customer
INNER JOIN rental ON customer.customer_id = rental.customer_id;

