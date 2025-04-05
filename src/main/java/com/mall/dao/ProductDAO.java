package com.mall.dao;

import com.mall.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 상품 생성
    public void createProduct(Product product) {
        String sql = "INSERT INTO product (seller_id, name, description, price, stock, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getSellerId(), product.getName(), product.getDescription(),
                            product.getPrice(), product.getStock(), product.getCategoryId());
    }

    // 상품 목록 조회 (판매자별로)
    public List<Product> findProductsBySeller(int sellerId) {
        String sql = "SELECT * FROM product WHERE seller_id = ?";
        return jdbcTemplate.query(sql, new Object[]{sellerId}, (rs, rowNum) -> {
            Product product = new Product();
            product.setProductId(rs.getInt("product_id"));
            product.setSellerId(rs.getInt("seller_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setPrice(rs.getDouble("price"));
            product.setStock(rs.getInt("stock"));
            product.setCategoryId(rs.getInt("category_id"));
            return product;
        });
    }

    // 상품 업데이트
    public void updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, stock = ?, category_id = ? WHERE product_id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                            product.getStock(), product.getCategoryId(), product.getProductId());
    }

    // 상품 삭제
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        jdbcTemplate.update(sql, productId);
    }
}
