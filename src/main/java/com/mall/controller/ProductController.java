package com.mall.controller;

import com.mall.model.Product;
import com.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 상품 등록 폼
    @GetMapping("/create")
    public String createProductForm() {
        return "createProduct";
    }

    // 상품 등록 처리
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/product/list";
    }

    // 판매자별 상품 목록
    @GetMapping("/list")
    public String listProducts(@RequestParam int sellerId, Model model) {
        List<Product> products = productService.getProductsBySeller(sellerId);
        model.addAttribute("products", products);
        return "productList";
    }

    // 상품 수정 폼
    @GetMapping("/edit/{productId}")
    public String editProductForm(@PathVariable int productId, Model model) {
        Product product = productService.getProductsBySeller(productId).get(0);  // 대체 로직 필요
        model.addAttribute("product", product);
        return "editProduct";
    }

    // 상품 수정 처리
    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/product/list?sellerId=" + product.getSellerId();
    }

    // 상품 삭제
    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }
}
