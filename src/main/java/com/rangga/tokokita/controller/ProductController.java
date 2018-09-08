package com.rangga.tokokita.controller;

import com.rangga.tokokita.exception.FileStorageException;
import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.model.Product;
import com.rangga.tokokita.model.User;
import com.rangga.tokokita.payload.*;
import com.rangga.tokokita.payload.common.CreatedResponse;
import com.rangga.tokokita.payload.common.PostResponse;
import com.rangga.tokokita.service.FileStorageService;
import com.rangga.tokokita.service.ProductService;
import com.rangga.tokokita.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Api(tags = "product")
@Validated
public class ProductController {


    @Value("${imagepath}")
    private String imagepath;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductPaginationResponse paginationResponse;
    private Page<Product> products;
    private CreatedResponse response;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("")
    @ApiOperation(value = "${ProductController.index}")
    public ProductPaginationResponse index(Pageable pageable,@RequestParam(required = false,name = "q") String q) {
        if(q!=null){
            products = productService.searchByName(q,pageable);
        }else{
            products = productService.index(pageable);
        }
        List<ProductListResponse> response = products.stream()
                .map(product -> modelMapper.map(product, ProductListResponse.class))
                .collect(Collectors.toList());
        paginationResponse.setNumber(products.getNumber());
        paginationResponse.setNumberOfElements(products.getNumberOfElements());
        paginationResponse.setSize(products.getSize());
        paginationResponse.setTotalElement(products.getTotalElements());
        paginationResponse.setTotalPage(products.getTotalPages());
        paginationResponse.setContent(response);
        return paginationResponse;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "${ProductController.show}")
    public ProductResponse show(@PathVariable String id) {
        Product product = productService.findById(id);
        return modelMapper.map(product, ProductResponse.class);
    }


    @PostMapping("")
    @ApiOperation(value = "${ProductController.store}")
    public PostResponse store(HttpServletRequest req,
                              @RequestParam (name = "file",required = false) MultipartFile file,
                              @RequestParam("name")  @NotNull(message = "nama tidak boleh kosong")  String name,
                              @RequestParam("category_id")  String category_id,
                              @RequestParam("category_name")  String category_name,
                              @RequestParam(name = "description",required = false) String description,
                              @RequestParam("price")  @Min(value = 1,message = "price minimal 1")  int price,
                              @RequestParam("stock")  @Min(value = 1,message = "stock minimal 1")  int stock) {

        Product product = new Product();
        String filepath = null;
        if(file != null){
            if(!(file.getContentType().toLowerCase().equals("image/jpg")
                    || file.getContentType().toLowerCase().equals("image/jpeg")
                    || file.getContentType().toLowerCase().equals("image/png"))){
                throw new FileStorageException("Format Gambar harus jpg/png");
            }

            String fileName = fileStorageService.storeFile(file);
            filepath = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(imagepath)
                    .path(fileName)
                    .toUriString();

        }
        User seller = new User();
        seller.set_id(userService.getCurrentUser(req).get_id());
        product.setSeller(seller);
        product.setName(name);
        Category category = new Category();
        category.set_id(category_id);
        category.setName(category_name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setStock(stock);
        product.setPicture(filepath);
        Product newProduct = productService.store(product);
        response = new CreatedResponse(false,"Produk Berhasil dibuat",newProduct.get_id());
        return response;
    }


}
