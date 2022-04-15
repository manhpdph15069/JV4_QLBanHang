package dao.impl;

import dao.AbstractDao;
import dao.OrderDao;
import entitys._Orders;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends AbstractDao<_Orders> implements OrderDao {
    @Override
    public List<_Orders> findAll() {
        return super.finAll(_Orders.class);
    }

    @Override
    public _Orders findById(Integer id) {
        return super.findById(_Orders.class,id);
    }

    @Override
    public List<_Orders> findByCus(Integer idCus) {
        String sql = "SELECT o FROM _Orders o where o.cusId =?0";
        return super.findMany(_Orders.class,sql,idCus);
    }

    @Override
    public List<_Orders> findByDate(Date date) {
        String sql = "SELECT o FROM _Orders o where o.dateOrder =?0";
        return super.findMany(_Orders.class,sql,date);
    }
}
