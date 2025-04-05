package com.mall.service;

import com.mall.dao.ProductDAO;
import com.mall.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // 상품 등록
    public void createProduct(Product product) {
        productDAO.createProduct(product);
    }

    // 판매자별 상품 조회
    public List<Product> getProductsBySeller(int sellerId) {
        return productDAO.findProductsBySeller(sellerId);
    }

    // 상품 업데이트
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    // 상품 삭제
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }
}
