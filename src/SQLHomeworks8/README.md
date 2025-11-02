# 📂 SQL Ödevi 8 - test Veritabanı DDL ve DML İşlemleri

Bu ödev, **test** veritabanı üzerinde **CREATE TABLE**, **INSERT**, **UPDATE**, ve **DELETE** gibi DDL (Data Definition Language) ve DML (Data Manipulation Language) komutları ile tablo oluşturma ve veri manipülasyonu becerilerini geliştirmek amacıyla hazırlanmıştır.  
Amaç, tablo yapısı oluşturma, toplu veri ekleme, güncelleme ve silme işlemlerini pratik etmektir.

---

## 📌 Gereksinimler
- PostgreSQL kurulmuş olmalı.
- `test` veritabanı oluşturulmuş olmalı.
- Sorgular **pgAdmin** veya **psql** üzerinde çalıştırılmalıdır.
- **Mockaroo** servisi ile test verisi üretilmiştir.

---

## 📝 Sorgu Senaryoları

### 1. Employee Tablosunu Oluşturma (CREATE TABLE)
**Soru:**  
`test` veritabanınızda **employee** isimli sütun bilgileri **id(INTEGER)**, **name VARCHAR(50)**, **birthday DATE**, **email VARCHAR(100)** olan bir tablo oluşturalım.

**Çözüm:**
```sql
CREATE TABLE employee (
    id INTEGER PRIMARY KEY,
    name VARCHAR(50),
    birthday DATE,
    email VARCHAR(100)
);
```

**Açıklama:**  
- `CREATE TABLE` ile yeni bir tablo oluşturulur.
- `id INTEGER PRIMARY KEY` ile id sütunu birincil anahtar olarak tanımlanır (benzersiz ve NULL olamaz).
- `VARCHAR(50)` değişken uzunlukta karakter dizisi (maksimum 50 karakter).
- `DATE` tarih formatında veri tutar (YYYY-MM-DD).

---

### 2. Mockaroo ile 50 Adet Veri Ekleme (INSERT)
**Soru:**  
Oluşturduğumuz **employee** tablosuna **'Mockaroo'** servisini kullanarak 50 adet veri ekleyelim.

**Çözüm:**
```sql
INSERT INTO employee (id, name, birthday, email) VALUES
(1, 'Alyse Joddins', '1990-05-15', 'ajoddins0@example.com'),
(2, 'Barnaby Dalgety', '1985-08-22', 'bdalgety1@example.com'),
(3, 'Cordie Blaw', '1992-03-10', 'cblaw2@example.com'),
(4, 'Danya Howieson', '1988-11-30', 'dhowieson3@example.com'),
(5, 'Ernesta Boeter', '1995-07-18', 'eboeter4@example.com'),
-- ... (50 adet veri için tam liste SQLHomeworks8.java dosyasında)
```

**Açıklama:**  
- `INSERT INTO` ile tabloya yeni kayıtlar eklenir.
- Mockaroo servisi (www.mockaroo.com) ile gerçekçi test verileri üretilmiştir.
- Toplu ekleme için virgülle ayrılmış VALUES listesi kullanılır.

**Mockaroo Kullanımı:**
1. www.mockaroo.com adresine gidin
2. Field Name: id, name, birthday, email
3. Field Type: Row Number, Full Name, Date, Email Address
4. Format: SQL seçin
5. Table Name: employee
6. 50 rows oluşturun ve kopyalayın

---

### 3. UPDATE İşlemleri (5 Adet)
**Soru:**  
Sütunların her birine göre diğer sütunları güncelleyecek 5 adet UPDATE işlemi yapalım.

**Çözüm:**

**3.1. ID'ye göre name güncelleme:**
```sql
UPDATE employee
SET name = 'John Doe Updated'
WHERE id = 1;
```

**3.2. Name'e göre email güncelleme:**
```sql
UPDATE employee
SET email = 'newemail@example.com'
WHERE name = 'Barnaby Dalgety';
```

**3.3. Birthday'e göre name güncelleme:**
```sql
UPDATE employee
SET name = 'Birthday Match Updated'
WHERE birthday = '1992-03-10';
```

**3.4. Email'e göre birthday güncelleme:**
```sql
UPDATE employee
SET birthday = '2000-01-01'
WHERE email = 'dhowieson3@example.com';
```

**3.5. ID aralığına göre çoklu sütun güncelleme:**
```sql
UPDATE employee
SET name = 'Bulk Updated',
    email = 'bulkupdate@example.com'
WHERE id BETWEEN 45 AND 50;
```

**Açıklama:**  
- `UPDATE tablo SET sutun = deger WHERE kosul` yapısı kullanılır.
- WHERE koşulu olmadan tüm kayıtlar güncellenir (dikkatli olunmalı!).
- Birden fazla sütun aynı anda güncellenebilir (virgülle ayrılır).

---

### 4. DELETE İşlemleri (5 Adet)
**Soru:**  
Sütunların her birine göre ilgili satırı silecek 5 adet DELETE işlemi yapalım.

**Çözüm:**

**4.1. ID'ye göre silme:**
```sql
DELETE FROM employee
WHERE id = 10;
```

**4.2. Name'e göre silme:**
```sql
DELETE FROM employee
WHERE name = 'Cordie Blaw';
```

**4.3. Birthday'e göre silme:**
```sql
DELETE FROM employee
WHERE birthday = '1995-07-18';
```

**4.4. Email'e göre silme:**
```sql
DELETE FROM employee
WHERE email = 'bulkupdate@example.com';
```

**4.5. ID aralığına göre toplu silme:**
```sql
DELETE FROM employee
WHERE id BETWEEN 46 AND 50;
```

**Açıklama:**  
- `DELETE FROM tablo WHERE kosul` yapısı kullanılır.
- WHERE koşulu olmadan tüm kayıtlar silinir (çok dikkatli olunmalı!).
- Silinen kayıtlar geri alınamaz (transaction kullanılmıyorsa).

---

## 🎯 Öğrenilen Konular
- **CREATE TABLE**: Yeni tablo oluşturma
- **PRIMARY KEY**: Birincil anahtar tanımlama
- **INSERT INTO**: Veri ekleme (tekli ve toplu)
- **UPDATE**: Veri güncelleme
- **DELETE**: Veri silme
- **WHERE**: Koşullu işlemler
- **BETWEEN**: Aralık belirleme
- Mockaroo ile test verisi üretme

---

## 📊 DDL vs DML Komutları

### DDL (Data Definition Language) - Veri Tanımlama Dili:
- `CREATE TABLE`: Tablo oluşturma
- `ALTER TABLE`: Tablo değiştirme
- `DROP TABLE`: Tablo silme
- `TRUNCATE`: Tablo içeriğini temizleme

### DML (Data Manipulation Language) - Veri İşleme Dili:
- `INSERT`: Veri ekleme
- `UPDATE`: Veri güncelleme
- `DELETE`: Veri silme
- `SELECT`: Veri sorgulama

---

## 💡 Önemli Uyarılar

### UPDATE İşlemlerinde Dikkat:
```sql
-- ❌ TEHLİKELİ: WHERE olmadan tüm kayıtları günceller!
UPDATE employee SET name = 'Test';

-- ✅ GÜVENLİ: WHERE ile belirli kayıtları günceller
UPDATE employee SET name = 'Test' WHERE id = 1;
```

### DELETE İşlemlerinde Dikkat:
```sql
-- ❌ TEHLİKELİ: WHERE olmadan tüm kayıtları siler!
DELETE FROM employee;

-- ✅ GÜVENLİ: WHERE ile belirli kayıtları siler
DELETE FROM employee WHERE id = 1;
```

### Best Practices:
1. UPDATE ve DELETE yapmadan önce SELECT ile test edin:
   ```sql
   -- Önce hangi kayıtların etkileneceğini görün
   SELECT * FROM employee WHERE id = 1;
   
   -- Sonra UPDATE/DELETE yapın
   UPDATE employee SET name = 'New Name' WHERE id = 1;
   ```

2. Transaction kullanın:
   ```sql
   BEGIN;
   UPDATE employee SET name = 'Test' WHERE id = 1;
   -- Hata varsa: ROLLBACK;
   -- Sorun yoksa: COMMIT;
   ```

3. Kritik işlemlerde yedek alın:
   ```sql
   -- Tablo yedeği oluşturma
   CREATE TABLE employee_backup AS SELECT * FROM employee;
   ```

---

## 🛠 Teknolojiler
- PostgreSQL
- pgAdmin 4
- Mockaroo (Test verisi üretimi)
- IntelliJ IDEA (proje organizasyonu için)

---

## 📚 Ek Kaynaklar
- Mockaroo: https://www.mockaroo.com/
- PostgreSQL Documentation: https://www.postgresql.org/docs/

