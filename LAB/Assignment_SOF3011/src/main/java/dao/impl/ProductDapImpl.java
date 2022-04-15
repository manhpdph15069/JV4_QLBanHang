package dao.impl;

import dao.AbstractDao;
import dao.ProductDao;
import entitys._Products;

import java.util.List;

public class ProductDapImpl extends AbstractDao<_Products> implements ProductDao {
    @Override
    public List<_Products> findAll() {
        return super.findAll(_Products.class,1);
    }

    @Override
    public List<_Products> findAll(Integer pageNumber, Integer pageSize) {
        return super.findAll(_Products.class,1,pageNumber,pageSize);
    }

    @Override
    public _Products findById(Integer id) {
        return super.findById(_Products.class,id);
    }

//    @Override
//    public _Products findBySize(String size) {
//        return null;
//    }
}
