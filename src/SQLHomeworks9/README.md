# 📂 SQL Ödevi 9 - dvdrental Veri Tabanı INNER JOIN Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **INNER JOIN** ile tablolar arası ilişkileri kullanarak veri birleştirme becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, birden fazla tablodan veri çekme, foreign key ilişkilerini kullanma ve JOIN mantığını pratik etmektir.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. City ve Country Tablolarını Birleştirme (INNER JOIN)
**Soru:**  
`city` tablosu ile `country` tablosunda bulunan şehir **(city)** ve ülke **(country)** isimlerini birlikte görebileceğimiz **INNER JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT city.city, country.country
FROM city
INNER JOIN country ON city.country_id = country.country_id;
```

**Açıklama:**  
- `city` tablosu ile `country` tablosu `country_id` üzerinden birleştirilir.
- `city.city` şehir ismini, `country.country` ülke ismini getirir.
- `INNER JOIN` sadece her iki tabloda da eşleşen kayıtları getirir.
- Sonuç: Her şehir, hangi ülkede olduğu bilgisiyle birlikte listelenir.

**Alternatif (Alias kullanımı):**
```sql
SELECT c.city, co.country
FROM city c
INNER JOIN country co ON c.country_id = co.country_id;
```

**Alternatif (Sıralı):**
```sql
SELECT city.city, country.country
FROM city
INNER JOIN country ON city.country_id = country.country_id
ORDER BY country.country, city.city;
```

---

### 2. Customer ve Payment Tablolarını Birleştirme (INNER JOIN)
**Soru:**  
`customer` tablosu ile `payment` tablosunda bulunan **payment_id** ile `customer` tablosundaki **first_name** ve **last_name** isimlerini birlikte görebileceğimiz **INNER JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT payment.payment_id, customer.first_name, customer.last_name
FROM customer
INNER JOIN payment ON customer.customer_id = payment.customer_id;
```

**Açıklama:**  
- `customer` tablosu ile `payment` tablosu `customer_id` üzerinden birleştirilir.
- `payment_id` ödeme numarasını, `first_name` ve `last_name` müşteri ismini getirir.
- Her ödeme kaydı, hangi müşteri tarafından yapıldığı bilgisiyle birlikte listelenir.
- Bir müşteri birden fazla ödeme yapmışsa, her ödeme için ayrı satır gösterilir.

**Alternatif (Alias kullanımı):**
```sql
SELECT p.payment_id, c.first_name, c.last_name
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id;
```

**Alternatif (Ek bilgilerle):**
```sql
SELECT 
    payment.payment_id, 
    customer.first_name, 
    customer.last_name,
    payment.amount,
    payment.payment_date
FROM customer
INNER JOIN payment ON customer.customer_id = payment.customer_id
ORDER BY payment.payment_id;
```

---

### 3. Customer ve Rental Tablolarını Birleştirme (INNER JOIN)
**Soru:**  
`customer` tablosu ile `rental` tablosunda bulunan **rental_id** ile `customer` tablosundaki **first_name** ve **last_name** isimlerini birlikte görebileceğimiz **INNER JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT rental.rental_id, customer.first_name, customer.last_name
FROM customer
INNER JOIN rental ON customer.customer_id = rental.customer_id;
```

**Açıklama:**  
- `customer` tablosu ile `rental` tablosu `customer_id` üzerinden birleştirilir.
- `rental_id` kiralama numarasını, `first_name` ve `last_name` müşteri ismini getirir.
- Her kiralama kaydı, hangi müşteri tarafından yapıldığı bilgisiyle birlikte listelenir.
- Bir müşteri birden fazla film kiralamışsa, her kiralama için ayrı satır gösterilir.

**Alternatif (Alias kullanımı):**
```sql
SELECT r.rental_id, c.first_name, c.last_name
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id;
```

**Alternatif (Ek bilgilerle):**
```sql
SELECT 
    rental.rental_id, 
    customer.first_name, 
    customer.last_name,
    rental.rental_date,
    rental.return_date
FROM customer
INNER JOIN rental ON customer.customer_id = rental.customer_id
ORDER BY rental.rental_id;
```

---

## 🎯 Öğrenilen Konular
- **INNER JOIN**: İki tablo arasında eşleşen kayıtları birleştirme
- **Foreign Key İlişkileri**: Tablolar arası bağlantıları kullanma
- **ON Koşulu**: JOIN için eşleştirme kriteri belirleme
- **Tablo Alias**: Sorgu okunabilirliğini artırma (AS veya kısa yazım)
- **Çoklu Tablo Sorguları**: Birden fazla tablodaki verileri birleştirme

---

## 📊 JOIN Türleri ve INNER JOIN

### INNER JOIN Mantığı:
```
Table A          Table B
┌─────┬─────┐    ┌─────┬─────┐
│ ID  │ Val │    │ ID  │ Val │
├─────┼─────┤    ├─────┼─────┤
│ 1   │ A   │    │ 1   │ X   │  → Eşleşme var ✓
│ 2   │ B   │    │ 3   │ Y   │  → Eşleşme var ✓
│ 3   │ C   │    │ 5   │ Z   │
└─────┴─────┘    └─────┴─────┘

INNER JOIN Sonucu:
┌─────┬─────┬─────┐
│ ID  │ ValA│ ValB│
├─────┼─────┼─────┤
│ 1   │ A   │ X   │
│ 3   │ C   │ Y   │
└─────┴─────┴─────┘
```

**INNER JOIN sadece her iki tabloda da eşleşen kayıtları getirir.**

### JOIN Türleri Özeti:

| JOIN Türü | Açıklama | Kullanım |
|-----------|----------|----------|
| **INNER JOIN** | Sadece eşleşen kayıtlar | En yaygın kullanım |
| **LEFT JOIN** | Sol tablodaki tüm kayıtlar + eşleşenler | Eksik verileri görmek için |
| **RIGHT JOIN** | Sağ tablodaki tüm kayıtlar + eşleşenler | Nadir kullanılır |
| **FULL OUTER JOIN** | Her iki tablodaki tüm kayıtlar | Tüm verileri görmek için |

---

## 💡 INNER JOIN Kullanım Örnekleri

### Temel Syntax:
```sql
SELECT tablo1.sutun, tablo2.sutun
FROM tablo1
INNER JOIN tablo2 ON tablo1.ortak_sutun = tablo2.ortak_sutun;
```

### Alias ile Kısa Yazım:
```sql
SELECT t1.sutun, t2.sutun
FROM tablo1 t1
INNER JOIN tablo2 t2 ON t1.ortak_sutun = t2.ortak_sutun;
```

### Çoklu JOIN (3+ Tablo):
```sql
SELECT c.first_name, r.rental_id, f.title
FROM customer c
INNER JOIN rental r ON c.customer_id = r.customer_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film f ON i.film_id = f.film_id;
```

### JOIN + WHERE:
```sql
SELECT city.city, country.country
FROM city
INNER JOIN country ON city.country_id = country.country_id
WHERE country.country = 'Turkey';
```

### JOIN + GROUP BY:
```sql
SELECT 
    customer.first_name, 
    customer.last_name, 
    COUNT(payment.payment_id) AS odeme_sayisi
FROM customer
INNER JOIN payment ON customer.customer_id = payment.customer_id
GROUP BY customer.customer_id, customer.first_name, customer.last_name
ORDER BY odeme_sayisi DESC;
```

---

## 🔍 dvdrental Veritabanı İlişkileri

### Tablolar Arası İlişkiler:
```
customer ──┬── payment (customer_id)
           └── rental (customer_id)

city ────── country (country_id)

rental ──┬── inventory (inventory_id)
         └── customer (customer_id)

inventory ── film (film_id)
```

---

## 📈 Best Practices

1. **Alias Kullanın**: Sorguları daha okunabilir hale getirir
   ```sql
   SELECT c.first_name, p.amount
   FROM customer c
   INNER JOIN payment p ON c.customer_id = p.customer_id;
   ```

2. **Açık JOIN Syntax Kullanın**: Eski stil virgül yerine INNER JOIN kullanın
   ```sql
   -- ✅ Modern (Önerilen)
   FROM customer INNER JOIN payment ON ...
   
   -- ❌ Eski stil (Önerilmez)
   FROM customer, payment WHERE ...
   ```

3. **Gerekli Sütunları Seçin**: SELECT * yerine spesifik sütunlar seçin
   ```sql
   -- ✅ İyi
   SELECT c.first_name, c.last_name, p.payment_id
   
   -- ❌ Kötü
   SELECT *
   ```

4. **ORDER BY Kullanın**: Sonuçları anlamlı şekilde sıralayın

5. **İndeks Kullanın**: JOIN yapılan sütunlarda indeks olmalı (genellikle foreign key'lerde zaten vardır)

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

---

## 📚 Ek Örnekler

### Müşteri - Ödeme - Film İlişkisi:
```sql
SELECT 
    c.first_name || ' ' || c.last_name AS musteri_adi,
    f.title AS film_adi,
    p.amount AS odeme_tutari
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id
INNER JOIN rental r ON p.rental_id = r.rental_id
INNER JOIN inventory i ON r.inventory_id = i.inventory_id
INNER JOIN film f ON i.film_id = f.film_id
LIMIT 10;
```

### Ülke Bazında Müşteri Sayısı:
```sql
SELECT 
    co.country,
    COUNT(DISTINCT c.customer_id) AS musteri_sayisi
FROM customer c
INNER JOIN address a ON c.address_id = a.address_id
INNER JOIN city ci ON a.city_id = ci.city_id
INNER JOIN country co ON ci.country_id = co.country_id
GROUP BY co.country
ORDER BY musteri_sayisi DESC;
```

