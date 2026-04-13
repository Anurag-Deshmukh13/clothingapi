package com.clothing.clothingapi.service;

import com.clothing.clothingapi.Product;
import com.clothing.clothingapi.dto.ProductDTO;
import com.clothing.clothingapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // =========================
    // GET ALL
    // =========================
    public List<ProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // GET BY ID
    // =========================
    public ProductDTO getById(int id) {
        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return toDTO(product);
    }

    // =========================
    // GET BY CATEGORY
    // =========================
    public List<ProductDTO> getByCategory(String category) {
        return repository.findByCategory(category)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // SEARCH
    // =========================
    public List<ProductDTO> searchProducts(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // PAGINATION
    // =========================
    public List<ProductDTO> getProductsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = repository.findAll(pageable);

        return productPage
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // FILTER (category + price range)
    // =========================
    public List<ProductDTO> filterProducts(String category, double minPrice, double maxPrice) {

        return repository.findAll()
                .stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // ADD PRODUCT
    // =========================
    public ProductDTO addProduct(ProductDTO dto) {
        Product product = toEntity(dto);
        return toDTO(repository.save(product));
    }

    // =========================
    // UPDATE PRODUCT
    // =========================
    public ProductDTO updateProduct(int id, ProductDTO dto) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setRate(dto.getRate());
        product.setCount(dto.getCount());

        return toDTO(repository.save(product));
    }

    // =========================
    // DELETE PRODUCT
    // =========================
    public void deleteProduct(int id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        repository.deleteById(id);
    }

    // =========================
    // MAPPER: ENTITY → DTO
    // =========================
    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getImage(),
                product.getRate(),
                product.getCount()
        );
    }

    // =========================
    // MAPPER: DTO → ENTITY
    // =========================
    public Product toEntity(ProductDTO dto) {
        Product product = new Product();

        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setRate(dto.getRate());
        product.setCount(dto.getCount());

        return product;
    }
}