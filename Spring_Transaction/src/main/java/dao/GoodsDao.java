package dao;

import entity.Goods;

public interface GoodsDao {
    public int updateGoods(Goods good );
    public Goods selectGoods(Integer id);
}
