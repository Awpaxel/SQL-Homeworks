# 📂 SQL Ödevi 10 - dvdrental Veri Tabanı LEFT, RIGHT ve FULL JOIN Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **LEFT JOIN**, **RIGHT JOIN**, ve **FULL JOIN** ile tablolar arası ilişkileri kullanarak veri birleştirme becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, farklı JOIN türlerini anlamak, eşleşmeyen kayıtları görmek ve outer join mantığını pratik etmektir.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. City ve Country Tablolarını LEFT JOIN ile Birleştirme
**Soru:**  
`city` tablosu ile `country` tablosunda bulunan şehir **(city)** ve ülke **(country)** isimlerini birlikte görebileceğimiz **LEFT JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT city.city, country.country
FROM city
LEFT JOIN country ON city.country_id = country.country_id;
```

**Açıklama:**  
- `LEFT JOIN` sol tablodaki (`city`) tüm kayıtları getirir.
- Sağ tabloda (`country`) eşleşme varsa, country bilgisi gelir.
- Eşleşme yoksa, country sütunu NULL olur.
- Bu örnekte, her şehrin bir ülkesi olduğundan genellikle NULL görünmez.
- LEFT JOIN, "sol tablodaki tüm verileri göster, sağdaki eşleşenleri ekle" mantığıyla çalışır.

**Alternatif (Alias kullanımı):**
```sql
SELECT c.city, co.country
FROM city c
LEFT JOIN country co ON c.country_id = co.country_id;
```

**NULL Kontrolü ile:**
```sql
SELECT 
    city.city, 
    COALESCE(country.country, 'Ülke Bilinmiyor') AS country
FROM city
LEFT JOIN country ON city.country_id = country.country_id;
```

**LEFT JOIN Mantığı:**
```
city (SOL TABLO)        country (SAĞ TABLO)
┌────────┬────────┐    ┌────────┬─────────┐
│ city   │country_│    │country_│ country │
│        │   id   │    │   id   │         │
├────────┼────────┤    ├────────┼─────────┤
│ London │   1    │◄───│   1    │ UK      │ ✓ Eşleşme
│ Paris  │   2    │◄───│   2    │ France  │ ✓ Eşleşme
│ Rome   │   3    │    │   4    │ Germany │ 
└────────┴────────┘    └────────┴─────────┘
         ▲
         │
    Tüm kayıtlar
    gösterilir!

Sonuç:
┌────────┬─────────┐
│ city   │ country │
├────────┼─────────┤
│ London │ UK      │
│ Paris  │ France  │
│ Rome   │ NULL    │ ← Eşleşme yok ama gösterilir
└────────┴─────────┘
```

---

### 2. Customer ve Payment Tablolarını RIGHT JOIN ile Birleştirme
**Soru:**  
`customer` tablosu ile `payment` tablosunda bulunan **payment_id** ile `customer` tablosundaki **first_name** ve **last_name** isimlerini birlikte görebileceğimiz **RIGHT JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT payment.payment_id, customer.first_name, customer.last_name
FROM customer
RIGHT JOIN payment ON customer.customer_id = payment.customer_id;
```

**Açıklama:**  
- `RIGHT JOIN` sağ tablodaki (`payment`) tüm kayıtları getirir.
- Sol tabloda (`customer`) eşleşme varsa, müşteri bilgileri gelir.
- Eşleşme yoksa, first_name ve last_name NULL olur.
- Bu örnekte, her ödemenin bir müşterisi olduğundan genellikle NULL görünmez.
- RIGHT JOIN, "sağ tablodaki tüm verileri göster, soldaki eşleşenleri ekle" mantığıyla çalışır.

**Alternatif (Alias kullanımı):**
```sql
SELECT p.payment_id, c.first_name, c.last_name
FROM customer c
RIGHT JOIN payment p ON c.customer_id = p.customer_id;
```

**Alternatif (LEFT JOIN ile eşdeğer):**
```sql
-- RIGHT JOIN yerine LEFT JOIN kullanarak aynı sonuç:
SELECT payment.payment_id, customer.first_name, customer.last_name
FROM payment
LEFT JOIN customer ON payment.customer_id = customer.customer_id;
```

**RIGHT JOIN Mantığı:**
```
customer (SOL TABLO)    payment (SAĞ TABLO)
┌────────┬────────┐    ┌────────┬────────┐
│customer│  name  │    │payment_│customer│
│   _id  │        │    │   id   │  _id   │
├────────┼────────┤    ├────────┼────────┤
│   1    │ John   │───►│  101   │   1    │ ✓ Eşleşme
│   2    │ Mary   │───►│  102   │   2    │ ✓ Eşleşme
│   3    │ Bob    │    │  103   │   9    │ ← Müşteri yok
└────────┴────────┘    └────────┴────────┘
                                  ▲
                                  │
                            Tüm kayıtlar
                            gösterilir!

Sonuç:
┌────────┬─────────┐
│payment │  name   │
│  _id   │         │
├────────┼─────────┤
│  101   │ John    │
│  102   │ Mary    │
│  103   │ NULL    │ ← Müşteri bulunamadı
└────────┴─────────┘
```

---

### 3. Customer ve Rental Tablolarını FULL JOIN ile Birleştirme
**Soru:**  
`customer` tablosu ile `rental` tablosunda bulunan **rental_id** ile `customer` tablosundaki **first_name** ve **last_name** isimlerini birlikte görebileceğimiz **FULL JOIN** sorgusunu yazınız.

**Çözüm:**
```sql
SELECT rental.rental_id, customer.first_name, customer.last_name
FROM customer
FULL JOIN rental ON customer.customer_id = rental.customer_id;
```

**Açıklama:**  
- `FULL JOIN` (veya `FULL OUTER JOIN`) her iki tablodaki tüm kayıtları getirir.
- Sol tabloda eşleşme varsa, müşteri bilgileri gelir; yoksa NULL.
- Sağ tabloda eşleşme varsa, rental bilgileri gelir; yoksa NULL.
- Hem kiralama yapmamış müşterileri hem de müşterisi olmayan kiralamaları gösterir.
- FULL JOIN, "her iki tablodaki tüm verileri göster" mantığıyla çalışır.

**Alternatif (Alias kullanımı):**
```sql
SELECT r.rental_id, c.first_name, c.last_name
FROM customer c
FULL JOIN rental r ON c.customer_id = r.customer_id;
```

**Sadece Eşleşmeyenleri Görmek:**
```sql
-- Hiç kiralama yapmamış müşterileri veya müşterisi olmayan kiralamaları bul
SELECT rental.rental_id, customer.first_name, customer.last_name
FROM customer
FULL JOIN rental ON customer.customer_id = rental.customer_id
WHERE customer.customer_id IS NULL OR rental.rental_id IS NULL;
```

**FULL JOIN Mantığı:**
```
customer (SOL TABLO)    rental (SAĞ TABLO)
┌────────┬────────┐    ┌────────┬────────┐
│customer│  name  │    │rental_ │customer│
│   _id  │        │    │   id   │  _id   │
├────────┼────────┤    ├────────┼────────┤
│   1    │ John   │◄──►│  201   │   1    │ ✓ Eşleşme
│   2    │ Mary   │◄──►│  202   │   2    │ ✓ Eşleşme
│   3    │ Bob    │    │  203   │   9    │ ← Müşteri yok
└────────┴────────┘    └────────┴────────┘
    ▲                         ▲
    │                         │
Her iki tablodaki      Her iki tablodaki
tüm kayıtlar!          tüm kayıtlar!

Sonuç:
┌────────┬─────────┐
│rental_ │  name   │
│  id    │         │
├────────┼─────────┤
│  201   │ John    │ ← Eşleşme var
│  202   │ Mary    │ ← Eşleşme var
│  203   │ NULL    │ ← Müşteri bulunamadı (sağ tabloda var)
│  NULL  │ Bob     │ ← Kiralama yok (sol tabloda var)
└────────┴─────────┘
```

---

## 🎯 Öğrenilen Konular
- **LEFT JOIN**: Sol tablodaki tüm kayıtlar + sağdaki eşleşenler
- **RIGHT JOIN**: Sağ tablodaki tüm kayıtlar + soldaki eşleşenler
- **FULL JOIN**: Her iki tablodaki tüm kayıtlar
- **OUTER JOIN**: Eşleşmeyen kayıtları da gösterme
- **NULL Değerleri**: Eşleşmeyen kayıtlarda NULL kontrolü
- **COALESCE**: NULL değerleri için varsayılan değer atama

---

## 📊 JOIN Türleri Karşılaştırması

### Görsel Karşılaştırma:

```
┌─────────────┬─────────────────────────────────────────┐
│  JOIN TÜRÜ  │           NE GETİRİR?                  │
├─────────────┼─────────────────────────────────────────┤
│ INNER JOIN  │ ██████                                  │
│             │ Sadece eşleşenler                       │
├─────────────┼─────────────────────────────────────────┤
│ LEFT JOIN   │ ████████████                            │
│             │ Sol tablo TÜM + sağdaki eşleşenler      │
├─────────────┼─────────────────────────────────────────┤
│ RIGHT JOIN  │         ████████████                    │
│             │ Sağ tablo TÜM + soldaki eşleşenler      │
├─────────────┼─────────────────────────────────────────┤
│ FULL JOIN   │ █████████████████████                   │
│             │ Her iki tablodaki TÜM kayıtlar          │
└─────────────┴─────────────────────────────────────────┘
```

### Tablo Karşılaştırması:

| JOIN Türü | Sol Tablo | Eşleşenler | Sağ Tablo | NULL Değerler |
|-----------|-----------|------------|-----------|---------------|
| **INNER** | Sadece eşleşenler | ✓ | Sadece eşleşenler | Yok |
| **LEFT** | Tümü ✓ | ✓ | Sadece eşleşenler | Sağda olabilir |
| **RIGHT** | Sadece eşleşenler | ✓ | Tümü ✓ | Solda olabilir |
| **FULL** | Tümü ✓ | ✓ | Tümü ✓ | Her iki tarafta olabilir |

---

## 💡 JOIN Türleri - Detaylı Örnekler

### INNER JOIN vs LEFT JOIN:
```sql
-- INNER JOIN: Sadece ödeme yapmış müşteriler
SELECT c.first_name, COUNT(p.payment_id) AS odeme_sayisi
FROM customer c
INNER JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name;
-- Sonuç: Sadece ödeme yapmış müşteriler

-- LEFT JOIN: Tüm müşteriler (ödeme yapmamış olanlar dahil)
SELECT c.first_name, COUNT(p.payment_id) AS odeme_sayisi
FROM customer c
LEFT JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name;
-- Sonuç: Tüm müşteriler, bazıları 0 ödeme ile
```

### RIGHT JOIN vs LEFT JOIN (Eşdeğer):
```sql
-- Bu iki sorgu aynı sonucu verir:

-- RIGHT JOIN
SELECT * FROM customer c
RIGHT JOIN payment p ON c.customer_id = p.customer_id;

-- LEFT JOIN (tabloların yeri değiştirilmiş)
SELECT * FROM payment p
LEFT JOIN customer c ON p.customer_id = c.customer_id;
```

### FULL JOIN Kullanım Senaryosu:
```sql
-- Tüm müşterileri ve tüm kiralamaları göster
SELECT 
    c.first_name,
    c.last_name,
    r.rental_id,
    r.rental_date,
    CASE 
        WHEN c.customer_id IS NULL THEN 'Müşteri Bulunamadı'
        WHEN r.rental_id IS NULL THEN 'Kiralama Yapmamış'
        ELSE 'Normal'
    END AS durum
FROM customer c
FULL JOIN rental r ON c.customer_id = r.customer_id;
```

---

## 🔍 NULL Değerler ile Çalışma

### NULL Kontrolü:
```sql
-- LEFT JOIN sonrası NULL kontrolü
SELECT city.city, country.country
FROM city
LEFT JOIN country ON city.country_id = country.country_id
WHERE country.country IS NULL;
-- Ülkesi olmayan şehirler (varsa)
```

### COALESCE Kullanımı:
```sql
-- NULL değerleri için varsayılan değer
SELECT 
    city.city,
    COALESCE(country.country, 'Bilinmiyor') AS country,
    COALESCE(country.country_id, 0) AS country_id
FROM city
LEFT JOIN country ON city.country_id = country.country_id;
```

### IS NULL vs IS NOT NULL:
```sql
-- Eşleşmeyenleri bul
SELECT * FROM customer c
LEFT JOIN rental r ON c.customer_id = r.customer_id
WHERE r.rental_id IS NULL;
-- Hiç kiralama yapmamış müşteriler

-- Eşleşenleri bul
SELECT * FROM customer c
LEFT JOIN rental r ON c.customer_id = r.customer_id
WHERE r.rental_id IS NOT NULL;
-- En az bir kiralama yapmış müşteriler
```

---

## 📈 Pratik Kullanım Senaryoları

### 1. Hiç Ödeme Yapmamış Müşterileri Bul:
```sql
SELECT c.customer_id, c.first_name, c.last_name
FROM customer c
LEFT JOIN payment p ON c.customer_id = p.customer_id
WHERE p.payment_id IS NULL;
```

### 2. Tüm Müşterilerin Ödeme Sayısı (0 dahil):
```sql
SELECT 
    c.first_name,
    c.last_name,
    COUNT(p.payment_id) AS odeme_sayisi,
    COALESCE(SUM(p.amount), 0) AS toplam_tutar
FROM customer c
LEFT JOIN payment p ON c.customer_id = p.customer_id
GROUP BY c.customer_id, c.first_name, c.last_name
ORDER BY odeme_sayisi DESC;
```

### 3. Müşterisi Olmayan Ödemeler (Veri Bütünlüğü Kontrolü):
```sql
SELECT payment.*
FROM payment
LEFT JOIN customer ON payment.customer_id = customer.customer_id
WHERE customer.customer_id IS NULL;
-- Normal durumda bu sorgu boş dönmeli (foreign key sayesinde)
```

### 4. Tüm Ülkeler ve Şehir Sayıları (Şehri olmayan ülkeler dahil):
```sql
SELECT 
    co.country,
    COUNT(ci.city_id) AS sehir_sayisi
FROM country co
LEFT JOIN city ci ON co.country_id = ci.country_id
GROUP BY co.country_id, co.country
ORDER BY sehir_sayisi DESC;
```

---

## ⚡ Performans İpuçları

1. **LEFT JOIN genellikle RIGHT JOIN'den daha yaygındır**
   - Daha okunabilir
   - RIGHT JOIN yerine tabloları değiştirip LEFT JOIN kullanın

2. **INNER JOIN daha hızlıdır**
   - Mümkünse INNER JOIN tercih edin
   - OUTER JOIN sadece gerektiğinde kullanın

3. **İndeks Kullanımı**
   - JOIN yapılan sütunlarda indeks olmalı
   - Foreign key'lerde genellikle otomatik indeks vardır

4. **WHERE vs HAVING**
   - WHERE ile önce filtreleyin (daha hızlı)
   - OUTER JOIN'de dikkatli olun: WHERE NULL kontrolü sonuçları değiştirebilir

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

---

## 📚 Best Practices

1. **LEFT JOIN tercih edin** (RIGHT JOIN yerine)
2. **Alias kullanın** (okunabilirlik için)
3. **NULL kontrolü yapın** (beklenmeyen sonuçları önlemek için)
4. **COALESCE kullanın** (NULL değerler için varsayılan değer)
5. **JOIN türünü bilinçli seçin** (ihtiyacınıza göre)

---

## 🔗 İlgili Kavramlar

- **CROSS JOIN**: Kartezyen çarpım (her kayıt her kayıtla eşleşir)
- **SELF JOIN**: Tablonun kendisiyle JOIN
- **NATURAL JOIN**: Ortak sütunlar üzerinden otomatik JOIN
- **USING**: ON yerine ortak sütun ismi ile JOIN

