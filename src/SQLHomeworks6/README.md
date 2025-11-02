# 📂 SQL Ödevi 6 - dvdrental Veri Tabanı Agregasyon Fonksiyonları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **AVG**, **COUNT**, **MAX**, ve **COUNT(DISTINCT)** gibi agregasyon fonksiyonları ile veri analizi becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, ortalama hesaplama, sayma, maksimum değer bulma ve koşullu agregasyon pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. Rental Rate Ortalaması (AVG)
**Soru:**  
`film` tablosunda bulunan **rental_rate** sütunundaki değerlerin ortalaması nedir?

**Çözüm:**
```sql
SELECT AVG(rental_rate) 
FROM film;
```

**Açıklama:**  
- `AVG()` fonksiyonu sayısal bir sütunun aritmetik ortalamasını hesaplar.
- Tüm rental_rate değerlerinin toplamını, kayıt sayısına böler.
- Sonuç ondalıklı bir sayı olarak döner.

**Alternatif (Yuvarlanmış):**
```sql
SELECT ROUND(AVG(rental_rate), 2) AS ortalama_rental_rate
FROM film;
```

---

### 2. 'C' ile Başlayan Film Sayısı (COUNT + LIKE)
**Soru:**  
`film` tablosunda bulunan filmlerden kaç tanesi **'C'** karakteri ile başlar?

**Çözüm:**
```sql
SELECT COUNT(*) 
FROM film
WHERE title LIKE 'C%';
```

**Açıklama:**  
- `WHERE title LIKE 'C%'` ile 'C' karakteriyle başlayan filmleri filtreleriz.
- `COUNT(*)` toplam kayıt sayısını döner.
- Sonuç bir tam sayı olarak döner.

---

### 3. Rental Rate 0.99 Olan En Uzun Film (MAX + WHERE)
**Soru:**  
`film` tablosunda bulunan filmlerden **rental_rate** değeri **0.99**'a eşit olan en uzun **(length)** film kaç dakikadır?

**Çözüm:**
```sql
SELECT MAX(length) 
FROM film
WHERE rental_rate = 0.99;
```

**Açıklama:**  
- `WHERE rental_rate = 0.99` ile rental_rate'i 0.99 olan filmleri filtreleriz.
- `MAX(length)` bu filtreli kayıtlar arasından en büyük length değerini bulur.
- Sonuç dakika cinsinden bir sayı olarak döner.

**Alternatif (Film detaylarıyla birlikte):**
```sql
SELECT title, length, rental_rate
FROM film
WHERE rental_rate = 0.99
ORDER BY length DESC
LIMIT 1;
```

---

### 4. 150 Dakikadan Uzun Filmlerin Farklı Replacement Cost Sayısı
**Soru:**  
`film` tablosunda bulunan filmlerin uzunluğu **150 dakikadan büyük** olanlarına ait kaç farklı **replacement_cost** değeri vardır?

**Çözüm:**
```sql
SELECT COUNT(DISTINCT replacement_cost) 
FROM film
WHERE length > 150;
```

**Açıklama:**  
- `WHERE length > 150` ile 150 dakikadan uzun filmleri filtreleriz.
- `COUNT(DISTINCT replacement_cost)` bu filmler arasında kaç farklı replacement_cost değeri olduğunu sayar.
- `DISTINCT` tekrar eden değerleri tek sayar.
- Sonuç benzersiz replacement_cost değerlerinin sayısıdır.

---

## 🎯 Öğrenilen Konular
- **AVG()**: Ortalama hesaplama
- **COUNT()**: Kayıt sayısı bulma
- **MAX()**: Maksimum değer bulma
- **MIN()**: Minimum değer bulma (bonus)
- **COUNT(DISTINCT ...)**: Benzersiz değer sayısı
- **ROUND()**: Sayıları yuvarlama
- **WHERE** ile agregasyon fonksiyonlarının birlikte kullanımı

---

## 📊 Agregasyon Fonksiyonları Özeti

| Fonksiyon | Açıklama | Örnek |
|-----------|----------|-------|
| `AVG()` | Ortalama | `AVG(rental_rate)` |
| `COUNT()` | Sayma | `COUNT(*)` veya `COUNT(column)` |
| `MAX()` | En büyük değer | `MAX(length)` |
| `MIN()` | En küçük değer | `MIN(rental_rate)` |
| `SUM()` | Toplam | `SUM(amount)` |
| `COUNT(DISTINCT)` | Benzersiz sayma | `COUNT(DISTINCT rating)` |

---

## 💡 İpuçları
- Agregasyon fonksiyonları tek bir değer döner (scalar value).
- `COUNT(*)` tüm satırları sayar, `COUNT(column)` NULL olmayan değerleri sayar.
- `DISTINCT` ile agregasyon fonksiyonlarını birleştirerek benzersiz değerler üzerinde işlem yapabilirsiniz.
- `WHERE` ile önce filtreleme yapın, sonra agregasyon uygulayın.

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

