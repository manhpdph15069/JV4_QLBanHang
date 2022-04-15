package dao.impl;

import dao.AbstractDao;
import dao.CategoriesDao;
import entitys._Categories;

import java.util.List;

public class CategoriesDaoImpl extends AbstractDao<_Categories> implements CategoriesDao {
    @Override
    public List<_Categories> findAll() {
        return super.findAll(_Categories.class,1);
    }

    @Override
    public List<_Categories> findAll(Integer pageNumber, Integer pageSize) {
        return super.findAll(_Categories.class,1,pageNumber,pageSize);
    }

    @Override
    public _Categories findById(Integer id) {
        return super.findById(_Categories.class,id);
    }

    @Override
    public _Categories findByName(String name) {
        String sql ="SELECT c FROM _Categories c WHERE c.name = ?0";
        return super.findOne(_Categories.class,sql,name);
    }
}
