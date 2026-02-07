package com.ibrahimayhan.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    VALIDATION_ERROR("1002", "Doğrulama hatası.", HttpStatus.BAD_REQUEST),
    ACCESS_DENIED("1004", "Erişim reddedildi.", HttpStatus.FORBIDDEN),
    DATABASE_ERROR("1003", "Veritabanı hatası.", HttpStatus.INTERNAL_SERVER_ERROR),
    GENERAL_EXCEPTION("9999", "Genel bir hata oluştu.", HttpStatus.INTERNAL_SERVER_ERROR),
    DUPLICATE_RECORD("1005", "Aynı kayıt zaten mevcut.", HttpStatus.CONFLICT),

    PUBLISHER_NOT_FOUND("1101", "Yayınevi bulunamadı.", HttpStatus.NOT_FOUND),
    BOOK_NOT_FOUND("1102", "Kitap bulunamadı.", HttpStatus.NOT_FOUND),
    AUTHOR_NOT_FOUND("1103", "Yazar bulunamadı.", HttpStatus.NOT_FOUND),

    ISBN_ALREADY_EXISTS("1201", "Bu ISBN ile kayıtlı kitap zaten mevcut.", HttpStatus.CONFLICT),
    PUBLISHER_ALREADY_EXISTS("1202", "Bu yayınevi zaten mevcut.", HttpStatus.CONFLICT),
    AUTHOR_ALREADY_EXISTS("1203", "Bu yazar zaten mevcut.", HttpStatus.CONFLICT),

    BOOK_NOT_BELONG_TO_PUBLISHER("1301", "Kitap bu yayınevine ait değil.", HttpStatus.BAD_REQUEST),
    PUBLISH_YEAR_INVALID("1302", "Yayın yılı geçersiz.", HttpStatus.BAD_REQUEST),
    PUBLISHER_HAS_BOOKS_CANNOT_DELETE("1303", "Yayınevinin kitabı varken silinemez.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
