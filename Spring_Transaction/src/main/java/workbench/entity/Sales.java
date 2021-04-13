package workbench.entity;

public class Sales {
    Integer id;
    Integer goodId;
    Integer goodNumber;

    public Sales() {
    }

    public Sales(Integer id, Integer goodId, Integer goodNumber) {
        this.id = id;
        this.goodId = goodId;
        this.goodNumber = goodNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }
}
