package DZ_6;

import java.util.ArrayList;
import java.util.List;

public class Televizor {

    private String name;
    private int cost;
    private int size;
    private boolean stateisOn;
    private List<TV_Channel> channels;
    private TV_Channel curchan;

    public Televizor(String name, int cost, int size) {
        this.name = name;
        this.cost = cost;
        this.size = size;
        this.stateisOn = false;
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

    public void addChannel(TV_Channel channel) {
        channels.add(channel);
    }

    public void setCurchan(TV_Channel curchan) {
        this.curchan = curchan;
    }

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
        return " Телевизор - " + name + ", цена - " + cost + (stateisOn ? "включён" : "выключен") + "Каналы" + channels + "Текущий канал: " + curchan + ".";
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