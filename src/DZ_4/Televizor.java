package DZ_4;

public class Televizor {

    private String name;
    private int cost;
    private int size;

    public Televizor (String name,int cost, int size) {
        this.name=name;
        this.cost=cost;
        this.size=size;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public void printParam() {
        System.out.println("Это телевизор!");
    }
}
