package DZ_6;

import attestation01.Person;

public class TV_Programm {
    private String tvname;
    private int rating;
    private int numviewers;

    public TV_Programm(String tvname, int rating, int numviewers) {
        this.tvname = tvname;
        this.rating = rating;
        this.numviewers = numviewers;
    }

    public String getTvName() {
        return tvname;
    }

    public void setTvName(String name) {
        this.tvname = tvname;
    }

    public int getrating() {
        return rating;
    }

    public void setrating(int cost) {
        this.rating = rating;
    }

    public int getnumviewers() {
        return numviewers;
    }

    public void setSize(int numviewers) {
        this.numviewers = numviewers;
    }

    @Override
    public String toString() {
        return " Программа - " + tvname + ", рейтинг - " + rating + ".";
    }

    @Override
    public int hashCode() {
        return tvname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        TV_Programm other = (TV_Programm) obj;
        return tvname.equals(other.tvname);
    }
}