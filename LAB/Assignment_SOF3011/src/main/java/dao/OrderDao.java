package dao;

import entitys._Orders;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    public List<_Orders> findAll();
    _Orders findById(Integer id);
    List<_Orders> findByCus(Integer idCus);
    List<_Orders> findByDate(Date date);
    _Orders create(_Orders entity);
    _Orders update(_Orders entity);
    _Orders delete(_Orders entity);
}
