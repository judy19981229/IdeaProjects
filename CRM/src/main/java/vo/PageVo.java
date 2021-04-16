package vo;

import java.util.List;
//将来分页查询，每一个模块都有，所以我们选择使用一个通用vo，操作方便
public class PageVo<T> {
    private int total;
    private List<T> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "total=" + total +
                ", dataList=" + dataList +
                '}';
    }
}
