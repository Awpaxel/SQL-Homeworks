# 📂 SQL Ödevi 5 - dvdrental Veri Tabanı ORDER BY, LIMIT ve OFFSET Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **ORDER BY**, **LIMIT**, ve **OFFSET** komutları ile veri sıralama ve sayfalama becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, verileri sıralama, en üst/alt kayıtları bulma ve sayfalama (pagination) pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. 'n' ile Biten En Uzun 5 Film (ORDER BY + LIMIT)
**Soru:**  
`film` tablosunda bulunan ve film ismi **(title)** **'n'** karakteri ile biten en uzun **(length)** 5 filmi sıralayınız.

**Çözüm:**
```sql
SELECT * 
FROM film
WHERE title LIKE '%n'
ORDER BY length DESC
LIMIT 5;
```

**Açıklama:**  
- `WHERE title LIKE '%n'` ile 'n' karakteriyle biten filmleri filtreleriz.
- `ORDER BY length DESC` ile uzunluğa göre büyükten küçüğe sıralarız (DESC = descending).
- `LIMIT 5` ile sadece ilk 5 kaydı alırız.

---

### 2. 'n' ile Biten En Kısa İkinci 5 Film (ORDER BY + LIMIT + OFFSET)
**Soru:**  
`film` tablosunda bulunan ve film ismi **(title)** **'n'** karakteri ile biten en kısa **(length)** ikinci 5 filmi (6,7,8,9,10) sıralayınız.

**Çözüm:**
```sql
SELECT * 
FROM film
WHERE title LIKE '%n'
ORDER BY length ASC
LIMIT 5 OFFSET 5;
```

**Açıklama:**  
- `WHERE title LIKE '%n'` ile 'n' karakteriyle biten filmleri filtreleriz.
- `ORDER BY length ASC` ile uzunluğa göre küçükten büyüğe sıralarız (ASC = ascending).
- `LIMIT 5` ile 5 kayıt alırız.
- `OFFSET 5` ile ilk 5 kaydı atlar, 6-10. kayıtları getirir (pagination).

**Not:** OFFSET 5 demek ilk 5 kaydı geç, 6. kayıttan başla demektir.

---

### 3. Store ID 1 İçin Last Name'e Göre Azalan İlk 4 Müşteri
**Soru:**  
`customer` tablosunda bulunan **last_name** sütununa göre azalan yapılan sıralamada **store_id 1** olmak koşuluyla ilk 4 veriyi sıralayınız.

**Çözüm:**
```sql
SELECT * 
FROM customer
WHERE store_id = 1
ORDER BY last_name DESC
LIMIT 4;
```

**Açıklama:**  
- `WHERE store_id = 1` ile sadece 1 numaralı mağazanın müşterilerini filtreleriz.
- `ORDER BY last_name DESC` ile soyadına göre Z'den A'ya (azalan) sıralarız.
- `LIMIT 4` ile sadece ilk 4 kaydı alırız.

---

## 🎯 Öğrenilen Konular
- **ORDER BY ASC**: Küçükten büyüğe sıralama (artan)
- **ORDER BY DESC**: Büyükten küçüğe sıralama (azalan)
- **LIMIT**: Döndürülecek kayıt sayısını sınırlar
- **OFFSET**: Belirli sayıda kaydı atlar (pagination için)
- **LIMIT + OFFSET**: Sayfalama (pagination) işlemleri
- **WHERE + ORDER BY + LIMIT**: Filtreleme, sıralama ve sınırlama kombinasyonu

---

## 📊 Kullanım Örnekleri

### Pagination (Sayfalama) Mantığı:
```sql
-- İlk 5 kayıt (1-5)
LIMIT 5 OFFSET 0

-- İkinci 5 kayıt (6-10)
LIMIT 5 OFFSET 5

-- Üçüncü 5 kayıt (11-15)
LIMIT 5 OFFSET 10
```

### Sıralama Yönleri:
- **ASC** (Ascending): A→Z, 0→9, küçük→büyük
- **DESC** (Descending): Z→A, 9→0, büyük→küçük

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

---

## 💡 İpuçları
- OFFSET kullanırken mutlaka ORDER BY ile birlikte kullanın, aksi halde sonuçlar tutarsız olabilir.
- LIMIT olmadan OFFSET kullanılamaz.
- Performans için büyük OFFSET değerlerinden kaçının.

