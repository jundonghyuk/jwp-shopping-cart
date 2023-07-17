package cart.controller.admin;

import cart.dto.ProductDto;
import cart.service.ProductManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ProductManagementService productManagementService;

    public AdminController(final ProductManagementService productManagementService) {
        System.out.println("hi jenkins");
        this.productManagementService = productManagementService;
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductDto productDto) {
        final Long id = productManagementService.addProduct(productDto);
        return ResponseEntity.created(URI.create("/admin/products/" + id)).build();
    }

    @PutMapping("/products/{product_id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("product_id") final Long id,
                                              @RequestBody @Valid final ProductDto productDto) {
        final ProductDto updatedProductDto
                = new ProductDto(id, productDto.getName(), productDto.getImageUrl(), productDto.getPrice());
        productManagementService.updateProduct(updatedProductDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("product_id") final Long id) {
        productManagementService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
