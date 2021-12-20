package com.vtca.color.reader.consumer.domain.product;

import com.vtca.color.reader.common.exception.BusinessException;
import com.vtca.color.reader.common.logger.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    /**
     * Get all products in DB
     * @return
     */
    public List<Product> listAll() {
        LoggerUtils.info("Execute listAll method");
        return repo.findAll();
    }

    /**
     * Get a product by price
     * @param
     * @return
     */
    public Product getProductByPrice(Float price) {
        LoggerUtils.info("Execute getProductByPrice method");
        if (Objects.isNull(price)) {
            LoggerUtils.info("Execute getProductByPrice method got error due to ",
                    ProductError.PRODUCT_PRICE.toString());
            throw new BusinessException(ProductError.PRODUCT_PRICE);
        }

        Product product = repo.findByPrice(price);
        if (Objects.isNull(product)) {
            LoggerUtils.info("Execute getProductByPrice method got error due to not existing product", price);
        }

        return product;
    }

    /**
     * Insert new product into DB
     * @param product
     */
    public void save(Product product) {
        LoggerUtils.info("Execute save method", product.toString());
        if (Objects.isNull(product)) {
            throw new BusinessException(ProductError.PRODUCT);
        }
        repo.save(product);
    }

    /**
     * Update existing product
     * @param product
     * @param id
     */
    public void save(Product product, Integer id) {
        LoggerUtils.info("Execute save method", product.toString(), id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ProductError.PRODUCT_ID);
        }

        //get update product from DB
        Product existProduct = this.get(id);

        //check whether updating product is existed or not
        if (Objects.isNull(existProduct)) {
            throw new BusinessException(ProductError.PRODUCT_NOT_EXIST);
        }

        this.save(product);
    }

    /**
     * Get product info by id
     * @param id
     * @return
     */
    public Product get(Integer id) {
        LoggerUtils.info("Execute get product method", id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ProductError.PRODUCT_ID);
        }
        return repo.findById(id).get();
    }

    /**
     * Delete product by id
     * @param id
     */
    public void delete(Integer id) {
        LoggerUtils.info("Execute delete method", id);
        if (Objects.isNull(id)) {
            throw new BusinessException(ProductError.PRODUCT_ID);
        }
        repo.deleteById(id);
    }
}
