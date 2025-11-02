# 📂 SQL Ödevi 7 - dvdrental Veri Tabanı GROUP BY ve HAVING Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **GROUP BY**, **HAVING**, ve agregasyon fonksiyonları ile veri gruplama ve analiz becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, verileri gruplama, grup bazlı hesaplamalar yapma ve koşullu filtreleme pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. Filmleri Rating Değerlerine Göre Gruplama (GROUP BY)
**Soru:**  
`film` tablosunda bulunan filmleri **rating** değerlerine göre gruplayınız.

**Çözüm:**
```sql
SELECT rating, COUNT(*) AS film_sayisi
FROM film
GROUP BY rating;
```

**Açıklama:**  
- `GROUP BY rating` ile filmleri rating değerlerine göre gruplarız.
- `COUNT(*)` her gruptaki film sayısını hesaplar.
- Sonuç: Her rating değeri için kaç film olduğunu gösterir (örn: PG-13: 223 film, R: 195 film).

**Alternatif (Sıralı):**
```sql
SELECT rating, COUNT(*) AS film_sayisi
FROM film
GROUP BY rating
ORDER BY film_sayisi DESC;
```

---

### 2. 50'den Fazla Film İçeren Replacement Cost Grupları (GROUP BY + HAVING)
**Soru:**  
`film` tablosunda bulunan filmleri **replacement_cost** sütununa göre grupladığımızda film sayısı **50'den fazla** olan replacement_cost değerini ve karşılık gelen film sayısını sıralayınız.

**Çözüm:**
```sql
SELECT replacement_cost, COUNT(*) AS film_sayisi
FROM film
GROUP BY replacement_cost
HAVING COUNT(*) > 50;
```

**Açıklama:**  
- `GROUP BY replacement_cost` ile filmleri replacement_cost değerlerine göre gruplarız.
- `COUNT(*)` her gruptaki film sayısını hesaplar.
- `HAVING COUNT(*) > 50` ile sadece 50'den fazla film içeren grupları filtreleriz.
- **HAVING** gruplama sonrası filtreleme yapar (WHERE gruplama öncesi filtreleme yapar).

**Sıralı Versiyon:**
```sql
SELECT replacement_cost, COUNT(*) AS film_sayisi
FROM film
GROUP BY replacement_cost
HAVING COUNT(*) > 50
ORDER BY film_sayisi DESC;
```

---

### 3. Store ID'lere Göre Müşteri Sayıları (GROUP BY)
**Soru:**  
`customer` tablosunda bulunan **store_id** değerlerine karşılık gelen müşteri sayılarını nelerdir?

**Çözüm:**
```sql
SELECT store_id, COUNT(*) AS musteri_sayisi
FROM customer
GROUP BY store_id;
```

**Açıklama:**  
- `GROUP BY store_id` ile müşterileri mağaza ID'lerine göre gruplarız.
- `COUNT(*)` her mağazadaki müşteri sayısını hesaplar.
- Sonuç: Her mağaza için kaç müşteri olduğunu gösterir (örn: Store 1: 326 müşteri, Store 2: 273 müşteri).

**Alternatif (Sıralı):**
```sql
SELECT store_id, COUNT(*) AS musteri_sayisi
FROM customer
GROUP BY store_id
ORDER BY store_id;
```

---

### 4. En Fazla Şehir İçeren Ülke (GROUP BY + ORDER BY + LIMIT)
**Soru:**  
`city` tablosunda bulunan şehir verilerini **country_id** sütununa göre gruplandırdıktan sonra en fazla şehir sayısı barındıran **country_id** bilgisini ve şehir sayısını paylaşınız.

**Çözüm:**
```sql
SELECT country_id, COUNT(*) AS sehir_sayisi
FROM city
GROUP BY country_id
ORDER BY sehir_sayisi DESC
LIMIT 1;
```

**Açıklama:**  
- `GROUP BY country_id` ile şehirleri ülke ID'lerine göre gruplarız.
- `COUNT(*)` her ülkedeki şehir sayısını hesaplar.
- `ORDER BY sehir_sayisi DESC` şehir sayısına göre büyükten küçüğe sıralar.
- `LIMIT 1` sadece en üstteki (en fazla şehre sahip) kaydı getirir.

**Alternatif (İlk 5 Ülke):**
```sql
SELECT country_id, COUNT(*) AS sehir_sayisi
FROM city
GROUP BY country_id
ORDER BY sehir_sayisi DESC
LIMIT 5;
```

---

## 🎯 Öğrenilen Konular
- **GROUP BY**: Verileri belirli sütunlara göre gruplama
- **HAVING**: Grup bazlı filtreleme (gruplama sonrası)
- **COUNT() ile GROUP BY**: Her gruptaki kayıt sayısını hesaplama
- **ORDER BY + LIMIT**: En üst/alt grupları bulma
- **WHERE vs HAVING**: WHERE gruplama öncesi, HAVING gruplama sonrası filtreleme yapar

---

## 📊 GROUP BY Kullanım Mantığı

### WHERE vs HAVING Farkı:
```sql
-- WHERE: Gruplama öncesi filtreleme
SELECT rating, COUNT(*) 
FROM film
WHERE length > 100  -- Önce uzun filmleri filtrele
GROUP BY rating;

-- HAVING: Gruplama sonrası filtreleme
SELECT rating, COUNT(*) 
FROM film
GROUP BY rating
HAVING COUNT(*) > 50;  -- Sonra 50'den fazla film içeren grupları filtrele
```

### Tipik GROUP BY Yapısı:
```sql
SELECT 
    gruplama_sutunu,
    COUNT(*) AS sayi,
    AVG(sutun) AS ortalama
FROM tablo
WHERE bireysel_filtreleme  -- Opsiyonel
GROUP BY gruplama_sutunu
HAVING grup_filtresi       -- Opsiyonel
ORDER BY sayi DESC
LIMIT n;                   -- Opsiyonel
```

---

## 💡 İpuçları
- **GROUP BY** kullanırken SELECT'te ya grup sütunu ya da agregasyon fonksiyonu olmalı.
- **HAVING** sadece GROUP BY ile birlikte kullanılır.
- Performans için WHERE ile önce filtreleyin, sonra GROUP BY yapın.
- COUNT(*) tüm satırları, COUNT(sutun) NULL olmayan satırları sayar.

---

## 📈 Agregasyon Fonksiyonları (GROUP BY ile)

| Fonksiyon | Açıklama | Örnek |
|-----------|----------|-------|
| `COUNT(*)` | Grup içindeki kayıt sayısı | `SELECT rating, COUNT(*) FROM film GROUP BY rating` |
| `SUM()` | Grup içindeki toplam | `SELECT store_id, SUM(amount) FROM payment GROUP BY store_id` |
| `AVG()` | Grup içindeki ortalama | `SELECT rating, AVG(length) FROM film GROUP BY rating` |
| `MAX()` | Grup içindeki en büyük değer | `SELECT rating, MAX(length) FROM film GROUP BY rating` |
| `MIN()` | Grup içindeki en küçük değer | `SELECT rating, MIN(rental_rate) FROM film GROUP BY rating` |

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

