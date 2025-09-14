# 📂 SQL Ödevi - dvdrental Veri Tabanı Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde temel SQL sorgulama becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, farklı SQL operatörlerini (`BETWEEN`, `IN`, `AND`) kullanarak veri seçme ve filtreleme pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. Replacement Cost Aralığı (BETWEEN)
**Soru:**  
`film` tablosunda bulunan tüm sütunlardaki verileri, **replacement_cost** değeri 12.99’dan büyük/eşit ve 16.99’dan küçük olanları listeleyiniz.

**Çözüm:**
```sql
SELECT * 
FROM film
WHERE replacement_cost BETWEEN 12.99 AND 16.99;
```
### 2. Aktörlerin İsimlerini Filtreleme (IN)
**Soru:**
`actor` tablosunda bulunan **first_name** ve **last_name** sütunlarındaki verileri, **first_name** değeri 'Penelope', 'Nick' veya 'Ed' olanları listeleyiniz.

**Çözüm:**
```sql
SELECT first_name, last_name
FROM actor
WHERE first_name IN ('Penelope', 'Nick', 'Ed');
```
### 3. Film Tablosunda Çoklu Koşul (IN + AND)
**Soru:**
`film` tablosunda bulunan tüm sütunlardaki verileri,

**rental_rate** değeri 0.99, 2.99, 4.99 olanlar,

VE **replacement_cost** değeri 12.99, 15.99, 28.99 olanları listeleyiniz.

**Çözüm:**
```sql
SELECT *
FROM film
WHERE rental_rate IN (0.99, 2.99, 4.99)
  AND replacement_cost IN (12.99, 15.99, 28.99);
```
---
## 🎯 Kazanımlar

- BETWEEN ile aralık bazlı filtreleme yapabilme
- IN ile birden fazla değer üzerinde koşul yazabilme
- AND ile birden fazla koşulu birleştirme
- `dvdrental` veritabanı üzerinde sorgu pratiği kazanma
