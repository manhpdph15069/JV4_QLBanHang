package dao.impl;

import dao.AbstractDao;
import dao.UserDao;
import entitys._Users;
import org.apache.log4j.Logger;
import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDao<_Users> implements UserDao {

    @Override
    public List<_Users> findAll() {
        return super.findAll(_Users.class,true);
    }

    @Override
    public List<_Users> findAll(Integer pageNumber, Integer pageSize) {
        return super.findAll(_Users.class,true,pageNumber,pageSize);
    }

    @Override
    public _Users findById(Integer id) {
        return super.findById(_Users.class,id);
    }

    @Override
    public _Users findByEmail(String email) {
        String sql = "SELECT o FROM _Users o where o.email = ?0";
        return super.findOne(_Users.class,sql,email);
    }
    @Override
    public _Users findByEmailAndOrder(String email) {
        String sql = "SELECT o FROM _Users o join _Orders o2 on o.id = o2.cusId where o.email = ?0 ORDER BY o2.dateOrder DESC ";
        return super.findOne(_Users.class,sql,email);
    }

    @Override
    public _Users findByEmailAndPass(String user , String pass) {
        String sql = "SELECT o FROM _Users o where o.email= ?0 AND o.pass= ?1 AND isActive = 1";
        return super.findOne(_Users.class,sql,user,pass);
    }
}
