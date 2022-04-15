package dao.impl;

import dao.AbstractDao;
import dao.OrderDetailDao;
import entitys._OrderDetails;

import java.util.List;

public class OrderDetailDaoImpl extends AbstractDao<_OrderDetails> implements OrderDetailDao {
    @Override
    public List<_OrderDetails> findAll() {
        return super.findAll(_OrderDetails.class,1);
    }

    @Override
    public List<_OrderDetails> findByOrder(Integer id) {
        String sql = "SELECT o FROM _OrderDetails o where o.ordid =?0";
        return super.findMany(_OrderDetails.class,sql,id);
    }

    @Override
    public _OrderDetails findById(Integer id) {
        return super.findById(_OrderDetails.class,id);
    }
}
