package SQLHomeworks3;

public class SQLHomeworks3

// 1. 'A' ile başlayıp 'a' ile biten ülkeler
SELECT country
FROM country
WHERE country LIKE 'A%a';

// 2. En az 6 karakterli ve 'n' ile biten ülkeler
SELECT country
FROM country
WHERE country LIKE '_____%n';

// 3. En az 4 adet 'T' karakteri içeren filmler (case insensitive)
SELECT title
FROM film
WHERE title ILIKE '%t%t%t%t%';

// 4. 'C' ile başlayan, uzunluğu > 90, rental_rate = 2.99 olan filmler
SELECT *
FROM film
WHERE title LIKE 'C%'
  AND length > 90
  AND rental_rate = 2.99;

