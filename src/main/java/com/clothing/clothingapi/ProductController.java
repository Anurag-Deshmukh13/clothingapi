package com.clothing.clothingapi;

import com.clothing.clothingapi.dto.ProductDTO;
import com.clothing.clothingapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public List<ProductDTO> getAll() {
        return service.getAllProducts();
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    // =========================
    // GET BY CATEGORY
    // =========================
    @GetMapping("/category/{category}")
    public List<ProductDTO> getByCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    // =========================
    // SEARCH
    // =========================
    @GetMapping("/search")
    public List<ProductDTO> search(@RequestParam String keyword) {
        return service.searchProducts(keyword);
    }

    // =========================
    // PAGINATION
    // =========================
    @GetMapping("/page")
    public List<ProductDTO> getPage(@RequestParam int page,
                                    @RequestParam int size) {
        return service.getProductsPaginated(page, size);
    }

    // =========================
    // FILTER
    // =========================
    @GetMapping("/filter")
    public List<ProductDTO> filter(
            @RequestParam(required = false) String category,
            @RequestParam double minPrice,
            @RequestParam double maxPrice) {

        return service.filterProducts(category, minPrice, maxPrice);
    }

    // =========================
    // ADD PRODUCT
    // =========================
    @PostMapping
    public ProductDTO add(@RequestBody ProductDTO dto) {
        return service.addProduct(dto);
    }

    // =========================
    // UPDATE PRODUCT
    // =========================
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable int id,
                             @RequestBody ProductDTO dto) {
        return service.updateProduct(id, dto);
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteProduct(id);
    }
}