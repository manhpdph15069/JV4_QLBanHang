package dao;

import entitys._Products;

import java.util.List;

public interface ProductDao {
    public List<_Products> findAll();
    public List<_Products> findAll(Integer pageNumber,Integer pageSize);
    public _Products findById(Integer id);
//    public _Products findBySize(String size);
    _Products create(_Products entity);
    _Products update(_Products entity);
    _Products delete(_Products entity);
}
