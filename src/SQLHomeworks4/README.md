# 📂 SQL Ödevi 4 - dvdrental Veri Tabanı DISTINCT ve Agregasyon Sorguları

Bu ödev, **dvdrental** örnek veritabanı üzerinde **DISTINCT**, **COUNT**, ve agregasyon fonksiyonları ile veri analizi becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, benzersiz değerleri bulma, sayma işlemleri ve karmaşık filtreleme pratikleri yapmaktır.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `dvdrental` örnek veritabanı yüklenmiş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.

---

## 📝 Sorgu Senaryoları

### 1. Farklı Replacement Cost Değerlerini Listeleme (DISTINCT)
**Soru:**  
`film` tablosunda bulunan **replacement_cost** sütununda bulunan birbirinden farklı değerleri sıralayınız.

**Çözüm:**
```sql
SELECT DISTINCT replacement_cost 
FROM film
ORDER BY replacement_cost;
```

**Açıklama:**  
- `DISTINCT` anahtar kelimesi tekrar eden değerleri kaldırır ve sadece benzersiz değerleri getirir.
- `ORDER BY` ile sonuçları küçükten büyüğe sıralarız.

---

### 2. Farklı Replacement Cost Değerlerinin Sayısı (COUNT + DISTINCT)
**Soru:**  
`film` tablosunda bulunan **replacement_cost** sütununda birbirinden farklı kaç tane veri vardır?

**Çözüm:**
```sql
SELECT COUNT(DISTINCT replacement_cost) 
FROM film;
```

**Açıklama:**  
- `COUNT(DISTINCT ...)` kombinasyonu benzersiz değerlerin sayısını verir.
- Sonuç tek bir sayı olarak döner.

---

### 3. 'T' ile Başlayan ve Rating 'G' Olan Filmler (LIKE + AND)
**Soru:**  
`film` tablosunda bulunan film isimlerinde **(title)** kaç tanesini **'T'** karakteri ile başlar ve aynı zamanda **rating 'G'** ye eşittir?

**Çözüm:**
```sql
SELECT COUNT(*) 
FROM film
WHERE title LIKE 'T%' 
  AND rating = 'G';
```

**Açıklama:**  
- `LIKE 'T%'` ile 'T' karakteriyle başlayan filmleri filtreleriz.
- `AND rating = 'G'` ile ek koşul ekleriz.
- `COUNT(*)` toplam kayıt sayısını verir.

---

### 4. 5 Karakterden Oluşan Ülke İsimleri (LENGTH veya LIKE)
**Soru:**  
`country` tablosunda bulunan ülke isimlerinden **(country)** kaç tanesi **5 karakterden** oluşmaktadır?

**Çözüm 1 (LENGTH fonksiyonu):**
```sql
SELECT COUNT(*) 
FROM country
WHERE LENGTH(country) = 5;
```

**Çözüm 2 (LIKE ile wildcard):**
```sql
SELECT COUNT(*) 
FROM country
WHERE country LIKE '_____';
```

**Açıklama:**  
- `LENGTH()` fonksiyonu karakter uzunluğunu döner.
- Alternatif olarak `LIKE '_____'` (5 alt çizgi) kullanılabilir.

---

### 5. 'R' veya 'r' ile Biten Şehirler (ILIKE)
**Soru:**  
`city` tablosundaki şehir isimlerinin kaç tanesi **'R' veya 'r'** karakteri ile biter?

**Çözüm:**
```sql
SELECT COUNT(*) 
FROM city
WHERE city ILIKE '%r';
```

**Açıklama:**  
- `ILIKE` operatörü büyük/küçük harf duyarsızdır (case-insensitive).
- `%r` ile 'r' veya 'R' ile biten tüm şehirleri yakalarız.

---

## 🎯 Öğrenilen Konular
- **DISTINCT** ile benzersiz değerleri bulma
- **COUNT()** agregasyon fonksiyonu
- **COUNT(DISTINCT ...)** kombinasyonu
- **LENGTH()** string fonksiyonu
- **ILIKE** ile case-insensitive arama
- Çoklu koşulların (AND) kullanımı
- Agregasyon ve filtrelemenin birlikte kullanımı

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- IntelliJ IDEA (proje organizasyonu için)

---

## 📊 Beklenen Sonuçlar
Bu sorgular çalıştırıldığında:
- Farklı replacement_cost değerleri listelenecek
- Benzersiz değer sayıları döndürülecek
- Belirli koşullara uyan kayıt sayıları bulunacak

