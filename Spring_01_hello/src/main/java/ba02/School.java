package ba02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mySchool")
public class School {
    @Value("华中科技大学")
    private String name;
    @Value("武汉市")
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
