package com.mall.model;

import lombok.Data;

@Data
public class Product {
    private int productId;
    private int sellerId;  // 판매자 ID
    private String name;
    private String description;
    private double price;
    private int stock;
    private int categoryId;  // 상품 카테고리 ID
}
