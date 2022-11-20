package post.post3;

import java.util.List;


public class Departments  {
    public  String name;
    public int smallpack;
    public int mediumpack;
    public int bigpack;
    public List<String> streets;


   public Departments(String name, int smallpack, int mediumpack, int bigpack, List<String> streets) {
        this.name = name;
        this.smallpack = smallpack;
        this.mediumpack = mediumpack;
        this.bigpack = bigpack;
        this.streets = streets;
    }

    @Override
    public String toString() {
        return "Name " + name + "\nsmallpack " + smallpack + "\nmediumpack " + mediumpack + "\nbigpack " + bigpack + "\nstreet " + streets;
    }

}
