# Yayınevi Yönetim Uygulaması – Spring Boot Case Çalışması

Uygulama, Spring Boot kullanılarak katmanlı mimari prensiplerine uygun şekilde tasarlanmış ve geliştirilmiştir.

Case dokümanında belirtilen tüm gereksinimler eksiksiz olarak tamamlanmış, test edilmiş ve doğrulanmıştır.

---

## Kullanılan Teknolojiler

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Feign Client
- Lombok
- JUnit 5
- Mockito
- Swagger (OpenAPI)
- Postman

---

## Mimari ve Tasarım

- Katmanlı mimari (Controller, Service, Repository)
- Exception Handling ile tüm validasyon hataları catch edilmiştir.( NOT: Tüm hatalar belirli bir format dahilinde response edilmektedir.)
- MVC tasarım deseni
- SOLID prensipleri
- Clean Code yaklaşımları
- Service katmanında gerçek unit testler
- Case-insensitive ve normalize edilmiş veri yönetimi
- Service ve Controller package'nın içinde  her servis için metot imzalarının bulunduğu interface ve impl package vardır bu package içinde implementasyonlar bulunur.(Detaylı gösterimi aşağıya ekledim)
 ### Paket Yapısı

```text
service
 ├── IBookService
 ├── IAuthorService
 ├── IPublisherService
 └── impl
     ├── BookServiceImpl
     ├── AuthorServiceImpl
     └── PublisherServiceImpl

controller
 ├── BookController
 ├── AuthorController
 ├── PublisherController
 └── impl
     ├── BookControllerImpl
     ├── AuthorControllerImpl
     └── PublisherControllerImpl
```

### Entityler
- Book
- Author
- Publisher
  


### Veritabanı Relations yapısı
Publisher → Book : OneToMany

Author → Book : OneToMany

Foreign key’ler Book tablosunda tutuluyor

İlişkiler FetchType.LAZY olarak tanımlandı

---

## Gerçekleştirilen Özellikler

### CRUD İşlemleri
- Kitap ekleme, güncelleme, silme ve listeleme
- Kitap kaydı sırasında yazar ve yayınevi varsa foreignkeylerinin book tablosuna eklenmesi , yoksa otomatik oluşturulup sonra eklenmesi

### Listeleme ve Filtreleme
- Tüm kitapların listelenmesi
- Tüm yazarların listelenmesi
- Tüm yayınevlerinin listelenmesi
- Kullanıcıdan baş harf alarak  veya string alarak  kitap filtreleme (Java Stream API)
- Kullanıcının girdiği yıl değişkenine göre girilen yıldan sonra basılan kitapların listelenmesi (Custom JPA Query)

### Veri Normalizasyonu
- Yazar ve yayınevi isimleri kaydedilmeden önce normalize edilir
- Büyük/küçük harf farkından kaynaklanan tekrar kayıtlar engellenir
- Veritabanı ile gelen istekler arasında tutarlılık sağlanır

---

## Google Books API Entegrasyonu

- Feign Client kullanılarak Google Books API entegrasyonu yapılmıştır
- Kitap adına göre arama yapılabilmektedir
 
### API sonucu aşağıdaki formatta dönülmektedir:

```text

{
  "title": "Effective Java",
  "price": 1431.8,
  "isbn13": "9780134686042",
  "publisherName": "Addison-Wesley Professional",
  "authorNameSurname": "Joshua Bloch"
}
```

API Dokümantasyonu

-Swagger  başarıyla entegre edilmiştir 
-Tüm endpoint’ler Swagger üzerinden görüntülenebilir ve test edilebilir
-Servis geliştirilirken tüm testler postman üzerinden yapılmıştır. Ve tüm endpointler aktifdir.



Testler

Unit Testler

Service katmanı JUnit 5 ve Mockito kullanılarak test edilmiştir

Repository katmanı mocklanarak gerçek unit test yaklaşımı uygulanmıştır

İş mantığı veritabanından bağımsız olarak doğrulanmıştır


Manuel Testler

Tüm endpoint’ler Postman üzerinden test edilmiştir

CRUD işlemleri, filtreleme ve harici API entegrasyonu sorunsuz çalışmaktadır

Uygulama çalışma zamanında hata vermemektedir

### Örnek İstek (Request) Yapısı

```text

{
  "title": "kürk mantolu madonna",
  "price": 45.0,
  "isbn13": "9780144350004",
  "publisherName": "işbank",
  "publishYear": 2022,
  "authorNameSurname": "sabahattin ali"
}
```



Proje Durumu

-Case gereksinimlerinin tamamı karşılanmıştır

-Swagger aktif ve çalışır durumdadır

-Unit testler yazılmıştır

-Postman testleri başarıyla tamamlanmıştır

-Proje teknik değerlendirmeye hazırdır




Geliştirici

İbrahim Ayhan
Backend & Spring Boot Geliştirici
