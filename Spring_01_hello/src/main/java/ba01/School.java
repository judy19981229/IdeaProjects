package ba01;

public class School {
    private String name;
    private String loc;

    public School(String name, String loc) {
        this.name = name;
        this.loc = loc;
    }

    public School() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
