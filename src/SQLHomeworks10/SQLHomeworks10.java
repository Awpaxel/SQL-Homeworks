package SQLHomeworks10;

public class SQLHomeworks10

-- 1. City ve Country tablolarını LEFT JOIN ile birleştirme
SELECT city.city, country.country
FROM city
LEFT JOIN country ON city.country_id = country.country_id;

-- 2. Customer ve Payment tablolarını RIGHT JOIN ile birleştirme
SELECT payment.payment_id, customer.first_name, customer.last_name
FROM customer
RIGHT JOIN payment ON customer.customer_id = payment.customer_id;

-- 3. Customer ve Rental tablolarını FULL JOIN ile birleştirme
SELECT rental.rental_id, customer.first_name, customer.last_name
FROM customer
FULL JOIN rental ON customer.customer_id = rental.customer_id;

