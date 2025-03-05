package org.example.ecommercebackend.service;

import org.example.ecommercebackend.entities.Category;
import org.example.ecommercebackend.entities.Product;
import org.example.ecommercebackend.exceptions.APIException;
import org.example.ecommercebackend.exceptions.ResourceNotFoundException;
import org.example.ecommercebackend.payload.ProductDTO;
import org.example.ecommercebackend.payload.ProductResponse;
import org.example.ecommercebackend.repositories.CategoryRepository;
import org.example.ecommercebackend.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productdto) {
        // check if product is already present or not


        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        boolean isProductPresent= false;
        List<Product> products = category.getProducts();
        for(int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductName().equals(productdto.getProductName())) {
                isProductPresent = true;
                break;
            }

        }
       if(!isProductPresent) {
            Product product = modelMapper.map(productdto, Product.class);
            product.setCategory(category);
            double specialPrice = (product.getDiscount() * 0.01) * product.getPrice();
            product.setPrice(specialPrice);
            productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        }else{
           throw new APIException("Product already exists");
       }
    }

    @Override
    public ProductResponse getAllProduct(Integer pageNumber,
                                         Integer pageSize,
                                         String sortBy,
                                         String sortOrder) {
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageDetails= PageRequest.of(pageNumber-1,pageSize,sortByAndOrder);
        Page<Product> pageProducts= productRepository.findAll(pageDetails);
       List<Product> products= pageProducts.getContent();

       List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
       if(productdtos.isEmpty()) {
           throw new APIException("No products found");

       }
       ProductResponse productResponse= new ProductResponse();
       productResponse.setContent(productdtos);
       productResponse.setPageNumber(pageProducts.getNumber());
       productResponse.setPageSize(pageProducts.getSize());
       productResponse.setTotalPages(pageProducts.getTotalPages());
       productResponse.setTotalElements(pageProducts.getTotalElements());
       productResponse.setLastPage(pageProducts.isLast());
       return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Product> products= productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
        ProductResponse productResponse= new ProductResponse();
        productResponse.setContent(productdtos);
        return productResponse;

    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {

        List<Product> products= productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%');
        List<ProductDTO> productdtos= products.stream().map(p->modelMapper.map(p,ProductDTO.class)).toList();
        ProductResponse productResponse= new ProductResponse();
        productResponse.setContent(productdtos);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productdto) {
        Product product = modelMapper.map(productdto,Product.class);
        Product productFromDb = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setCategory(product.getCategory());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setQuantity(product.getQuantity());
        productRepository.save(productFromDb);


        return   modelMapper.map(productFromDb,ProductDTO.class);
    }


    public ProductDTO deleteProduct(Long productId) {
        Product product= productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));

            productRepository.delete(product);

        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        //get the product from db
        Product productFromDb=productRepository.findById(productId).
                orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));

        //upload image to server
            //get the file name of uploaded image
        String path= "images/";
            String fileName=fileService.uploadImage(path,image);

        //update the new file name in product
        productFromDb.setImage(fileName);

        productRepository.save(productFromDb);



        return  modelMapper.map(productFromDb,ProductDTO.class);
    }


}
