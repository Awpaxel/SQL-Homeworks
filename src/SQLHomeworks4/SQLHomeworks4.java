package SQLHomeworks4;

public class SQLHomeworks4

// 1. replacement_cost sütunundaki farklı değerleri sıralama
SELECT DISTINCT replacement_cost
FROM film
ORDER BY replacement_cost;

// 2. replacement_cost sütunundaki farklı değerlerin sayısı
SELECT COUNT(DISTINCT replacement_cost)
FROM film;

// 3. 'T' ile başlayan ve rating 'G' olan filmlerin sayısı
SELECT COUNT(*)
FROM film
WHERE title LIKE 'T%'
  AND rating = 'G';

// 4. 5 karakterden oluşan ülke isimlerinin sayısı (Çözüm 1: LENGTH)
SELECT COUNT(*)
FROM country
WHERE LENGTH(country) = 5;

// 4. 5 karakterden oluşan ülke isimlerinin sayısı (Çözüm 2: LIKE)
SELECT COUNT(*)
FROM country
WHERE country LIKE '_____';

// 5. 'R' veya 'r' ile biten şehir isimlerinin sayısı
SELECT COUNT(*)
FROM city
WHERE city ILIKE '%r';

