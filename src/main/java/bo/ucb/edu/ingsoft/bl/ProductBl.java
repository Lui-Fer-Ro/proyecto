package bo.ucb.edu.ingsoft.bl;

import bo.ucb.edu.ingsoft.dao.ProductDao;
import bo.ucb.edu.ingsoft.dao.TransactionDao;
import bo.ucb.edu.ingsoft.dto.CompanyDto;
import bo.ucb.edu.ingsoft.dto.ProductDto;
import bo.ucb.edu.ingsoft.model.Company;
import bo.ucb.edu.ingsoft.model.Product;
import bo.ucb.edu.ingsoft.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBl {
    private TransactionDao transactionDao;
    private ProductDao productDao;

    @Autowired
    public ProductBl(TransactionDao transactionDao, ProductDao productDao) {

        this.transactionDao = transactionDao;
        this.productDao = productDao;
    }
    public ProductDto findProductById(Integer productId) {
        Product product = productDao.findByProductId(productId);
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setModel(product.getModel());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setStock(product.getStock());
        productDto.setWeight(product.getWeight());
        productDto.setCompanyId(product.getCompanyId());
        productDto.setProductTypeId(product.getProductTypeId());
        productDto.setCityId(product.getCityId());
        productDto.setUnitPrice(product.getUnitPrice());
        productDto.setCurrency(product.getCurrency());
        productDto.setBrandId(product.getBrandId());
        productDto.setImg(product.getImg());
        return productDto;
    }
    public List<ProductDto> selectProducts(){
        List<Product> products= productDao.getProducts();
        List<ProductDto> productsDto = new ArrayList<ProductDto>();
        for (int i=0; i< products.size(); i++){
            Product product= products.get(i);
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setModel(product.getModel());
            productDto.setProductDescription(product.getProductDescription());
            productDto.setStock(product.getStock());
            productDto.setWeight(product.getWeight());
            productDto.setCompanyId(product.getCompanyId());
            productDto.setProductTypeId(product.getProductTypeId());
            productDto.setCityId(product.getCityId());
            productDto.setUnitPrice(product.getUnitPrice());
            productDto.setCurrency(product.getCurrency());
            productDto.setBrandId(product.getBrandId());
            productDto.setImg(product.getImg());
            productsDto.add(i,productDto);
        }
        return productsDto;
    }
    public ProductDto createProduct(ProductDto productDto, Transaction transaction) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setModel(productDto.getModel());
        product.setProductDescription(productDto.getProductDescription());
        product.setStock(productDto.getStock());
        product.setWeight(productDto.getWeight());
        product.setCompanyId(productDto.getCompanyId());
        product.setProductTypeId(productDto.getProductTypeId());
        product.setCityId(productDto.getCityId());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setCurrency(productDto.getCurrency());
        product.setBrandId(productDto.getBrandId());
        product.setImg(productDto.getImg());
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.create(product);
        Integer getLastId = transactionDao.getLastInsertId();
        productDto.setProductId(getLastId);
        return productDto;
    }


    public ProductDto updateProduct(ProductDto productDto, Transaction transaction){
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setModel(productDto.getModel());
        product.setProductDescription(productDto.getProductDescription());
        product.setStock(productDto.getStock());
        //product.setWeight(productDto.getWeight());
        //product.setCompanyId(productDto.getCompanyId());
        //product.setProductTypeId(productDto.getProductTypeId());
        //product.setCityId(productDto.getCityId());
        product.setUnitPrice(productDto.getUnitPrice());
        //product.setCurrency(productDto.getCurrency())product.setBrandId(productDto.getBrandId());
        product.setImg(productDto.getImg());

        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(1);
        productDao.update(product);
        return productDto;
    }


    public void delete(Integer idProduct, Transaction transaction)
    {
        Product product = new Product();
        product.setProductId(idProduct);
        product.setTxId(transaction.getTxId());
        product.setTxUserId(transaction.getTxUserId());
        product.setTxHost(transaction.getTxHost());
        product.setTxDate(transaction.getTxDate());
        product.setStatus(0);
        productDao.delete(product);
    }



}
