package dao;

import entitys._OrderDetails;
import entitys._Orders;

import java.util.Date;
import java.util.List;

public interface OrderDetailDao {
    List<_OrderDetails> findAll();
    List<_OrderDetails> findByOrder(Integer id);
    _OrderDetails findById(Integer id);
    _OrderDetails create(_OrderDetails entity);
    _OrderDetails update(_OrderDetails entity);
    _OrderDetails delete(_OrderDetails entity);
}
