package workbench.service;

import workbench.dao.GoodsDao;
import workbench.dao.SalesDao;
import workbench.entity.Goods;
import workbench.entity.Sales;
import exception.GoodsNotEnoughException;
import exception.GoodsNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SalesGoodsServiceImpl implements SalesGoodsService{
    @Resource(name="goodsDao")
    GoodsDao goodsDao;
    @Resource(name="salesDao")
    SalesDao salesDao;

    @Override
    //@Transactional
    public void buy(Integer goodId, Integer goodNumber) {
        System.out.println("buy方法的开始");
        Sales sale=new Sales();
        sale.setGoodId(goodId);
        sale.setGoodNumber(goodNumber);
        salesDao.insertSales(sale);
        Goods good=goodsDao.selectGoods(goodId);
        System.out.println(good);
        if(good==null){
            throw new GoodsNotFoundException("编号："+goodId+"商品不存在");
        } else if(goodNumber>good.getAmount()){
            throw new GoodsNotEnoughException("编号："+goodId+"商品库存不足");
        } else {
            Goods buyGoods = new Goods();
            buyGoods.setId(goodId);
            buyGoods.setAmount(goodNumber);
            goodsDao.updateGoods(buyGoods);
        }
        System.out.println("buy方法的结束");
    }

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public SalesDao getSalesDao() {
        return salesDao;
    }

    public void setSalesDao(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

}
