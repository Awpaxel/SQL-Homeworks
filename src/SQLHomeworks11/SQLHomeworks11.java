package SQLHomeworks11;

public class SQLHomeworks11

-- 1. Uzunluğu ortalama film uzunluğundan fazla olan film sayısı
SELECT COUNT(*)
FROM film
WHERE length > (SELECT AVG(length) FROM film);

-- 2. En yüksek rental_rate değerine sahip film sayısı
SELECT COUNT(*)
FROM film
WHERE rental_rate = (SELECT MAX(rental_rate) FROM film);

-- 3. En düşük rental_rate ve en düşük replacement_cost değerlerine sahip filmler
SELECT *
FROM film
WHERE rental_rate = (SELECT MIN(rental_rate) FROM film)
  AND replacement_cost = (SELECT MIN(replacement_cost) FROM film);

-- 4. En fazla sayıda alışveriş yapan müşteriler
SELECT customer_id, COUNT(*) AS alisveris_sayisi
FROM payment
GROUP BY customer_id
HAVING COUNT(*) = (
    SELECT MAX(alisveris_sayisi)
    FROM (
        SELECT customer_id, COUNT(*) AS alisveris_sayisi
        FROM payment
        GROUP BY customer_id
    ) AS counts
)
ORDER BY customer_id;

-- 4. (Alternatif - Müşteri isimleriyle birlikte)
SELECT
    c.customer_id,
    c.first_name,
    c.last_name,
    COUNT(p.payment_id) AS alisveris_sayisi
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
HAVING COUNT(p.payment_id) = (
    SELECT MAX(alisveris_sayisi)
    FROM (
        SELECT customer_id, COUNT(*) AS alisveris_sayisi
        FROM payment
        GROUP BY customer_id
    ) AS counts
)
ORDER BY c.last_name, c.first_name;

