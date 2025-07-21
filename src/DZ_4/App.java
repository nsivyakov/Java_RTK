package DZ_4;
import java.util.Random;
public class App {
    public static void main(String[] args) {
        Televizor cometa= new Televizor("Комета",1000,32);
        System.out.println("Телевизор "+cometa.getName()+". Стоимость - " + cometa.getCost() + " рублей." + " Размер " + cometa.getSize() + " inch");

        Televizor pulsar= new Televizor("Пульсар",5000,54);
        System.out.println("Телевизор "+pulsar.getName()+". Стоимость - " + pulsar.getCost() + " рублей." + " Размер " + pulsar.getSize() + " inch");

        Random random = new Random();
        int rcost = random.nextInt(10000);
        int rsize = random.nextInt(70);
        cometa.setName("Комета-2");
        cometa.setCost(rcost);
        cometa.setSize(rsize);

        System.out.println("Телевизор "+cometa.getName()+". Стоимость - " + cometa.getCost() + " рублей." + " Размер " + cometa.getSize() + " inch");
        cometa.printParam();
    }
}
