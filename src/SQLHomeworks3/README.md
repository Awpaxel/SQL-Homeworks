# 📂 SQL Ödevi 3 - dvdrental Veri Tabanı Pattern Matching Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **LIKE** ve **ILIKE** operatörleri ile pattern matching (desen eşleştirme) becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, metin tabanlı filtreleme ve karmaşık koşullarla veri seçme pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. 'A' ile Başlayıp 'a' ile Biten Ülkeler
**Soru:**  
`country` tablosunda bulunan **country** sütunundaki ülke isimlerinden **'A'** karakteri ile başlayıp **'a'** karakteri ile sonlananları sıralayınız.

**Çözüm:**
```sql
SELECT country 
FROM country
WHERE country LIKE 'A%a';
```

---

### 2. En Az 6 Karakterli ve 'n' ile Biten Ülkeler
**Soru:**  
`country` tablosunda bulunan **country** sütunundaki ülke isimlerinden en az **6 karakterden** oluşan ve sonu **'n'** karakteri ile sonlananları sıralayınız.

**Çözüm:**
```sql
SELECT country 
FROM country
WHERE country LIKE '_____%n';
```

---

### 3. En Az 4 Adet 'T' Karakteri İçeren Filmler
**Soru:**  
`film` tablosunda bulunan **title** sütunundaki film isimlerinden en az **4 adet** büyük ya da küçük harf farketmesizin **'T'** karakteri içeren film isimlerini sıralayınız.

**Çözüm:**
```sql
SELECT title 
FROM film
WHERE title ILIKE '%t%t%t%t%';
```

---

### 4. Çoklu Koşullu Film Sorgusu
**Soru:**  
`film` tablosunda bulunan tüm sütunlardaki verilerden:
- **title** 'C' karakteri ile başlayan
- **uzunluğu (length)** 90'dan büyük olan
- **rental_rate** 2.99 olan

verileri sıralayınız.

**Çözüm:**
```sql
SELECT * 
FROM film
WHERE title LIKE 'C%' 
  AND length > 90 
  AND rental_rate = 2.99;
```

---

## 🎯 Öğrenilen Konular
- **LIKE** operatörü ile pattern matching
- **ILIKE** operatörü ile case-insensitive arama
- **%** (herhangi bir karakter dizisi) wildcard kullanımı
- **_** (tek karakter) wildcard kullanımı
- Çoklu koşulların birlikte kullanımı

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

