package SQLHomeworks5;

public class SQLHomeworks5

// 1. 'n' ile biten en uzun 5 film
SELECT *
FROM film
WHERE title LIKE '%n'
ORDER BY length DESC
LIMIT 5;

// 2. 'n' ile biten en kısa ikinci 5 film (6-10. kayıtlar)
SELECT *
FROM film
WHERE title LIKE '%n'
ORDER BY length ASC
LIMIT 5 OFFSET 5;

// 3. store_id = 1 için last_name'e göre azalan ilk 4 müşteri
SELECT *
FROM customer
WHERE store_id = 1
ORDER BY last_name DESC
LIMIT 4;

