package dao;

import entitys._Users;

import java.util.List;

public interface UserDao {
    public List<_Users> findAll();
    public List<_Users> findAll(Integer pageNumber,Integer pageSize);
    public _Users findById(Integer id);
    public _Users findByEmail(String email);
    public _Users findByEmailAndOrder(String email);
    public _Users findByEmailAndPass(String user,String pass);
    _Users create(_Users entity);
    _Users update(_Users entity);
    _Users delete(_Users entity);
}
