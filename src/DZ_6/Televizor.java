package DZ_6;

import java.util.ArrayList;
import java.util.List;

public class Televizor {

    private String name;
    private int cost;
    private int size;
    private boolean stateis;
    private List<TV_Channel> channels;
    private TV_Channel curchan;

    public Televizor(String name, int cost, int size) {
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.stateis = false;
        this.channels = new ArrayList<>();
        this.curchan = curchan;
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
    // Добавление каналов
    public void addChannel(TV_Channel channel) {
        channels.add(channel);
    }
    // Установка текущего канала
    public void setCurchan(TV_Channel curchan) {
        this.curchan = curchan;
    }
    public TV_Channel getCurchan(){return curchan;}
    // Переключение каналов
    public void switching() {
        int t = channels.indexOf(curchan);
        int s = channels.size();
        if (t == s) {
            setCurchan(channels.get(0));
        } else {
            setCurchan(channels.get(t + 1));
        }
    }

    @Override
    public String toString() {
        return " Телевизор - " + name + ", цена - " + cost +", состояние - "+ (stateis ? "включён" : "выключен")
                + ". Каналы" + channels + "Текущий канал: " + curchan + ".";
    }
    // Методы для изменения состояния (включение/выключение)
    public void turnOn() {
        if (!stateis) {
            stateis = true;
            System.out.println(name + ": включен");
        } else {
            System.out.println(name + ": уже включен");
        }
    }

    public void turnOff() {
        if (stateis) {
            stateis = false;
            System.out.println(name + ": выключен");
        } else {
            System.out.println(name + ": уже выключен");
        }
    }

    // Получение текущего статуса телевизора
    public String getStateis() {
        return ("Телевизор "+name+" "+(stateis ? "включён" : "выключен"));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Televizor other = (Televizor) obj;
        return name.equals(other.name);
    }
}