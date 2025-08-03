package attestation01;

public class Test2 {
    public static void main(String[] args) {
        Product t = new Product("Молока", 1000);
        System.out.println(t);

        Person p= new Person("Haris",5000);
        System.out.println(p);
        int i= p.hashCode();
        System.out.println(i);
    }
}