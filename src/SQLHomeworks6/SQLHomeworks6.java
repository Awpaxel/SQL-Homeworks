package SQLHomeworks6;

public class SQLHomeworks6

// 1. rental_rate sütunundaki değerlerin ortalaması
SELECT AVG(rental_rate)
FROM film;

// 1. (Alternatif - Yuvarlanmış)
SELECT ROUND(AVG(rental_rate), 2) AS ortalama_rental_rate
FROM film;

// 2. 'C' karakteri ile başlayan film sayısı
SELECT COUNT(*)
FROM film
WHERE title LIKE 'C%';

// 3. rental_rate = 0.99 olan en uzun film (dakika)
SELECT MAX(length)
FROM film
WHERE rental_rate = 0.99;

// 4. 150 dakikadan uzun filmlerin farklı replacement_cost sayısı
SELECT COUNT(DISTINCT replacement_cost)
FROM film
WHERE length > 150;

