# 📂 SQL Ödevi 11 - dvdrental Veri Tabanı SUBQUERY (Alt Sorgu) İşlemleri

Bu ödev, **dvdrental** örnek veritabanı üzerinde **SUBQUERY (Alt Sorgu)**, **Agregasyon Fonksiyonları** ve **Karşılaştırma Operatörleri** ile karmaşık veri analizi becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, alt sorgu mantığını anlamak, ortalama/maksimum/minimum değerleri bulmak ve ileri seviye filtreleme pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. Ortalama Film Uzunluğundan Fazla Olan Filmler
**Soru:**  
`film` tablosunda film uzunluğu **length** sütununda gösterilmektedir. Uzunluğu ortalama film uzunluğundan fazla kaç tane film vardır?

**Çözüm:**
```sql
SELECT COUNT(*) 
FROM film
WHERE length > (SELECT AVG(length) FROM film);
```

**Açıklama:**  
- **Alt sorgu (subquery)**: `(SELECT AVG(length) FROM film)` önce çalışır ve ortalama uzunluğu hesaplar.
- **Ana sorgu**: Bu ortalamadan uzun olan filmleri sayar.
- `AVG(length)` tüm filmlerin ortalama uzunluğunu döner (örn: 115.27 dakika).
- `WHERE length >` koşulu ile ortalamadan uzun filmler filtrelenir.
- `COUNT(*)` bu koşula uyan film sayısını verir.

**Alternatif (Ortalamayı da görmek için):**
```sql
SELECT 
    COUNT(*) AS uzun_film_sayisi,
    (SELECT AVG(length) FROM film) AS ortalama_uzunluk
FROM film
WHERE length > (SELECT AVG(length) FROM film);
```

**Detaylı Versiyon (Film isimlerini de görmek için):**
```sql
SELECT title, length
FROM film
WHERE length > (SELECT AVG(length) FROM film)
ORDER BY length DESC;
```

---

### 2. En Yüksek Rental Rate Değerine Sahip Film Sayısı
**Soru:**  
`film` tablosunda en yüksek **rental_rate** değerine sahip kaç tane film vardır?

**Çözüm:**
```sql
SELECT COUNT(*) 
FROM film
WHERE rental_rate = (SELECT MAX(rental_rate) FROM film);
```

**Açıklama:**  
- **Alt sorgu**: `(SELECT MAX(rental_rate) FROM film)` en yüksek rental_rate değerini bulur.
- **Ana sorgu**: Bu değere sahip filmleri sayar.
- `MAX(rental_rate)` en yüksek kiralama ücretini döner (örn: 4.99).
- Birden fazla film aynı maksimum değere sahip olabilir.

**Alternatif (Film detaylarıyla birlikte):**
```sql
SELECT 
    COUNT(*) AS film_sayisi,
    MAX(rental_rate) AS en_yuksek_rental_rate
FROM film
WHERE rental_rate = (SELECT MAX(rental_rate) FROM film);
```

**Film İsimlerini Görmek İçin:**
```sql
SELECT title, rental_rate
FROM film
WHERE rental_rate = (SELECT MAX(rental_rate) FROM film)
ORDER BY title;
```

---

### 3. En Düşük Rental Rate ve En Düşük Replacement Cost'a Sahip Filmler
**Soru:**  
`film` tablosunda en düşük **rental_rate** ve en düşük **replacement_cost** değerlerine sahip filmleri sıralayınız.

**Çözüm:**
```sql
SELECT *
FROM film
WHERE rental_rate = (SELECT MIN(rental_rate) FROM film)
  AND replacement_cost = (SELECT MIN(replacement_cost) FROM film);
```

**Açıklama:**  
- **İki alt sorgu kullanılır**:
  - `(SELECT MIN(rental_rate) FROM film)` en düşük kiralama ücretini bulur.
  - `(SELECT MIN(replacement_cost) FROM film)` en düşük yenileme maliyetini bulur.
- **AND** operatörü ile her iki koşul da sağlanmalı.
- Hem en düşük rental_rate hem de en düşük replacement_cost'a sahip filmler listelenir.

**Alternatif (Sadece önemli sütunları görmek için):**
```sql
SELECT title, rental_rate, replacement_cost, length
FROM film
WHERE rental_rate = (SELECT MIN(rental_rate) FROM film)
  AND replacement_cost = (SELECT MIN(replacement_cost) FROM film)
ORDER BY title;
```

**Alternatif (Değerleri de görmek için):**
```sql
SELECT 
    title, 
    rental_rate, 
    replacement_cost,
    (SELECT MIN(rental_rate) FROM film) AS min_rental_rate,
    (SELECT MIN(replacement_cost) FROM film) AS min_replacement_cost
FROM film
WHERE rental_rate = (SELECT MIN(rental_rate) FROM film)
  AND replacement_cost = (SELECT MIN(replacement_cost) FROM film);
```

---

### 4. En Fazla Alışveriş Yapan Müşteriler
**Soru:**  
`payment` tablosunda en fazla sayıda alışveriş yapan müşterileri **(customer)** sıralayınız.

**Çözüm:**
```sql
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
```

**Açıklama:**  
- **İç içe alt sorgu (nested subquery)** kullanılır:
  1. En içteki sorgu: Her müşterinin alışveriş sayısını hesaplar.
  2. Ortadaki sorgu: Bu sayılardan maksimumu bulur.
  3. Ana sorgu: Maksimum sayıda alışveriş yapan müşterileri filtreler.
- `GROUP BY customer_id` ile müşteri bazında gruplama yapılır.
- `HAVING` ile grup bazlı filtreleme yapılır.

**Daha Basit Alternatif (WITH kullanarak):**
```sql
WITH customer_counts AS (
    SELECT customer_id, COUNT(*) AS alisveris_sayisi
    FROM payment
    GROUP BY customer_id
)
SELECT *
FROM customer_counts
WHERE alisveris_sayisi = (SELECT MAX(alisveris_sayisi) FROM customer_counts)
ORDER BY customer_id;
```

**Müşteri İsimleriyle Birlikte:**
```sql
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
```

**En Fazla Alışveriş Yapan İlk 10 Müşteri:**
```sql
SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    COUNT(p.payment_id) AS alisveris_sayisi,
    SUM(p.amount) AS toplam_harcama
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY alisveris_sayisi DESC, toplam_harcama DESC
LIMIT 10;
```

---

## 🎯 Öğrenilen Konular
- **SUBQUERY (Alt Sorgu)**: Sorgu içinde sorgu kullanma
- **Scalar Subquery**: Tek değer dönen alt sorgu (AVG, MAX, MIN)
- **Nested Subquery**: İç içe alt sorgular
- **WHERE + Subquery**: Filtreleme için alt sorgu
- **HAVING + Subquery**: Grup filtreleme için alt sorgu
- **Karşılaştırma Operatörleri**: >, <, =, >=, <=
- **Agregasyon + Subquery**: COUNT, MAX, MIN, AVG ile birlikte kullanım

---

## 📊 SUBQUERY (Alt Sorgu) Türleri

### 1. Scalar Subquery (Tek Değer Dönen):
```sql
-- Tek bir değer döner (sayı, string, tarih vb.)
SELECT * FROM film
WHERE length > (SELECT AVG(length) FROM film);
--              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
--              Bu alt sorgu tek bir sayı döner
```

### 2. Row Subquery (Satır Dönen):
```sql
-- Bir satır (birden fazla sütun) döner
SELECT * FROM film
WHERE (rental_rate, replacement_cost) = 
    (SELECT MIN(rental_rate), MIN(replacement_cost) FROM film);
```

### 3. Table Subquery (Tablo Dönen):
```sql
-- Birden fazla satır ve sütun döner
SELECT * FROM (
    SELECT customer_id, COUNT(*) AS sayi
    FROM payment
    GROUP BY customer_id
) AS counts
WHERE sayi > 30;
```

### 4. Correlated Subquery (Bağımlı Alt Sorgu):
```sql
-- Dış sorguya bağımlı alt sorgu
SELECT f1.title, f1.length
FROM film f1
WHERE f1.length > (
    SELECT AVG(f2.length) 
    FROM film f2 
    WHERE f2.rating = f1.rating
);
-- Her rating için ayrı ortalama hesaplar
```

---

## 💡 SUBQUERY Kullanım Örnekleri

### AVG ile Karşılaştırma:
```sql
-- Ortalamanın altında
SELECT COUNT(*) FROM film
WHERE rental_rate < (SELECT AVG(rental_rate) FROM film);

-- Ortalamanın üstünde
SELECT COUNT(*) FROM film
WHERE rental_rate > (SELECT AVG(rental_rate) FROM film);

-- Ortalamaya eşit
SELECT COUNT(*) FROM film
WHERE rental_rate = (SELECT AVG(rental_rate) FROM film);
```

### MAX/MIN ile Karşılaştırma:
```sql
-- En uzun filmler
SELECT title, length FROM film
WHERE length = (SELECT MAX(length) FROM film);

-- En kısa filmler
SELECT title, length FROM film
WHERE length = (SELECT MIN(length) FROM film);

-- İkinci en uzun filmler
SELECT title, length FROM film
WHERE length = (
    SELECT MAX(length) FROM film
    WHERE length < (SELECT MAX(length) FROM film)
);
```

### IN ile Subquery:
```sql
-- En fazla alışveriş yapan müşterilerin kiralama kayıtları
SELECT * FROM rental
WHERE customer_id IN (
    SELECT customer_id
    FROM payment
    GROUP BY customer_id
    HAVING COUNT(*) > 40
);
```

### EXISTS ile Subquery:
```sql
-- En az bir ödeme yapmış müşteriler
SELECT c.first_name, c.last_name
FROM customer c
WHERE EXISTS (
    SELECT 1 FROM payment p
    WHERE p.customer_id = c.customer_id
);
```

### NOT IN ile Subquery:
```sql
-- Hiç kiralama yapmamış müşteriler
SELECT customer_id, first_name, last_name
FROM customer
WHERE customer_id NOT IN (
    SELECT DISTINCT customer_id FROM rental
);
```

---

## 🔍 WITH Clause (CTE - Common Table Expression)

### WITH Kullanımı (Daha Okunabilir):
```sql
-- Alt sorguyu isimlendirerek kullanma
WITH ortalama_uzunluk AS (
    SELECT AVG(length) AS avg_length FROM film
)
SELECT COUNT(*) AS uzun_film_sayisi
FROM film, ortalama_uzunluk
WHERE film.length > ortalama_uzunluk.avg_length;
```

### Birden Fazla CTE:
```sql
WITH 
film_stats AS (
    SELECT 
        AVG(length) AS avg_length,
        MAX(rental_rate) AS max_rate
    FROM film
),
customer_stats AS (
    SELECT customer_id, COUNT(*) AS alisveris_sayisi
    FROM payment
    GROUP BY customer_id
)
SELECT * FROM customer_stats
WHERE alisveris_sayisi > 30
ORDER BY alisveris_sayisi DESC;
```

---

## 📈 Performans İpuçları

### 1. Subquery vs JOIN:
```sql
-- ❌ Yavaş olabilir (her satır için alt sorgu çalışır)
SELECT f.title,
    (SELECT AVG(length) FROM film) AS avg_length
FROM film f;

-- ✅ Daha hızlı (tek bir CROSS JOIN)
SELECT f.title, s.avg_length
FROM film f
CROSS JOIN (SELECT AVG(length) AS avg_length FROM film) s;
```

### 2. EXISTS vs IN:
```sql
-- ✅ Genellikle daha hızlı (ilk eşleşmede durur)
SELECT * FROM customer c
WHERE EXISTS (
    SELECT 1 FROM payment p WHERE p.customer_id = c.customer_id
);

-- ❌ Tüm listeyi oluşturur
SELECT * FROM customer c
WHERE c.customer_id IN (
    SELECT customer_id FROM payment
);
```

### 3. Subquery Caching:
PostgreSQL alt sorguları cache'ler, aynı alt sorgu birden fazla kullanılırsa tekrar hesaplanmaz.

---

## 🛠 Best Practices

1. **WITH kullanın** (karmaşık alt sorgular için)
   - Daha okunabilir
   - Tekrar kullanılabilir
   - Debug edilmesi kolay

2. **Alt sorgu sayısını minimize edin**
   - Mümkünse JOIN kullanın
   - Gereksiz alt sorguları birleştirin

3. **Scalar subquery kullanırken dikkatli olun**
   - Tek değer döndüğünden emin olun
   - Birden fazla satır dönerse hata alırsınız

4. **İndeks kullanın**
   - Alt sorgularda kullanılan sütunlarda indeks olmalı

5. **EXPLAIN kullanın**
   - Sorgu performansını analiz edin
   ```sql
   EXPLAIN ANALYZE
   SELECT * FROM film
   WHERE length > (SELECT AVG(length) FROM film);
   ```

---

## 💻 İleri Seviye Örnekler

### 1. Her Rating İçin Ortalama Üstü Filmler:
```sql
SELECT f1.title, f1.rating, f1.length
FROM film f1
WHERE f1.length > (
    SELECT AVG(f2.length)
    FROM film f2
    WHERE f2.rating = f1.rating
)
ORDER BY f1.rating, f1.length DESC;
```

### 2. En Fazla Harcama Yapan Müşteri:
```sql
SELECT 
    c.first_name,
    c.last_name,
    SUM(p.amount) AS toplam_harcama
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
HAVING SUM(p.amount) = (
    SELECT MAX(toplam)
    FROM (
        SELECT customer_id, SUM(amount) AS toplam
        FROM payment
        GROUP BY customer_id
    ) AS customer_totals
);
```

### 3. Ortalamanın Üstünde ve Altında Film Sayıları:
```sql
WITH avg_length AS (
    SELECT AVG(length) AS ortalama FROM film
)
SELECT 
    'Ortalamanın Üstünde' AS kategori,
    COUNT(*) AS film_sayisi
FROM film, avg_length
WHERE film.length > avg_length.ortalama
UNION ALL
SELECT 
    'Ortalamanın Altında' AS kategori,
    COUNT(*) AS film_sayisi
FROM film, avg_length
WHERE film.length < avg_length.ortalama
UNION ALL
SELECT 
    'Ortalama' AS kategori,
    ortalama::INTEGER AS film_sayisi
FROM avg_length;
```

### 4. Her Kategorideki En Pahalı Film:
```sql
SELECT f1.title, f1.rating, f1.rental_rate
FROM film f1
WHERE f1.rental_rate = (
    SELECT MAX(f2.rental_rate)
    FROM film f2
    WHERE f2.rating = f1.rating
)
ORDER BY f1.rating;
```

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

---

## 📚 Özet

| Kavram | Açıklama | Örnek |
|--------|----------|-------|
| **Scalar Subquery** | Tek değer döner | `WHERE x > (SELECT AVG(y) FROM t)` |
| **Table Subquery** | Tablo döner | `FROM (SELECT ... ) AS t` |
| **IN Subquery** | Liste kontrolü | `WHERE x IN (SELECT y FROM t)` |
| **EXISTS Subquery** | Varlık kontrolü | `WHERE EXISTS (SELECT ...)` |
| **Correlated Subquery** | Bağımlı alt sorgu | Dış sorguya referans verir |
| **WITH (CTE)** | İsimlendirilmiş alt sorgu | `WITH t AS (SELECT ...) SELECT * FROM t` |

---

## 🎓 Alıştırmalar

1. Ortalamanın altında rental_rate'e sahip filmleri bulun
2. Her rating kategorisinde en uzun filmi bulun
3. En az alışveriş yapan müşterileri listeleyin
4. Hiç kiralanmamış filmleri bulun
5. Her ülkedeki ortalama müşteri sayısını hesaplayın

