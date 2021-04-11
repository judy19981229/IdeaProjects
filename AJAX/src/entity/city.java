package entity;

public class city {
    private Integer cityId;
    private String cityName;
    private String provinceId;

    public city(Integer cityId, String cityName, String provinceId) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.provinceId = provinceId;
    }

    public city() {
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "city{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", provinceId='" + provinceId + '\'' +
                '}';
    }
}
