package com.ibrahimayhan.feign;

import java.util.List;

import lombok.Data;

@Data
public class GoogleBooksResponseDto {

    private List<Item> items;

    @Data
    public static class Item {
        private VolumeInfo volumeInfo;
        private SaleInfo saleInfo;
    }

    @Data
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String publisher;
        private String publishedDate;
        private List<IndustryIdentifier> industryIdentifiers;
    }

    @Data
    public static class IndustryIdentifier {
        private String type;
        private String identifier;
    }

    @Data
    public static class SaleInfo {
        private RetailPrice retailPrice;
    }

    @Data
    public static class RetailPrice {
        private Double amount;
    }
}

	

