package attestation01;

public class Test1 {

    private String name;
    private int cost;
    private int size;

    public Test1(String name, int cost, int size) {
        setName(name);
        this.cost = cost;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name.length()<3) {
            throw new IllegalArgumentException("Имя не может быть меньше 3!");
        } else {
            this.name = name;
        }
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
