package SQLHomeworks7;

public class SQLHomeworks7

// 1. Filmleri rating değerlerine göre gruplama
SELECT rating, COUNT(*) AS film_sayisi
FROM film
GROUP BY rating;

// 2. Film sayısı 50'den fazla olan replacement_cost değerleri
SELECT replacement_cost, COUNT(*) AS film_sayisi
FROM film
GROUP BY replacement_cost
HAVING COUNT(*) > 50;

// 3. Store ID'lere göre müşteri sayıları
SELECT store_id, COUNT(*) AS musteri_sayisi
FROM customer
GROUP BY store_id;

// 4. En fazla şehir barındıran country_id ve şehir sayısı
SELECT country_id, COUNT(*) AS sehir_sayisi
FROM city
GROUP BY country_id
ORDER BY sehir_sayisi DESC
LIMIT 1;

