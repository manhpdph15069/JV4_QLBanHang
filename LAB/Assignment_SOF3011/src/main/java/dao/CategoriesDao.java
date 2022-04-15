package dao;

import entitys._Categories;
import entitys._Users;

import java.util.List;

public interface CategoriesDao {
    public List<_Categories> findAll();
    public List<_Categories> findAll(Integer pageNumber,Integer pageSize);
    public _Categories findById(Integer id);
    public _Categories findByName(String name);
//    public _Categories findByUser();
    _Categories create(_Categories entity);
    _Categories update(_Categories entity);
    _Categories delete(_Categories entity);
}
