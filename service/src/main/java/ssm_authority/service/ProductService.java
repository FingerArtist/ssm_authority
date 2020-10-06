package ssm_authority.service;

import org.springframework.stereotype.Service;
import ssm_authority.domain.Product;

import java.util.List;
public interface ProductService {

    public List<Product> findAll(Integer pageNum,Integer pageSize) throws Exception;

    void save(Product product) throws Exception;

    void saveRandom() throws Exception;
}
