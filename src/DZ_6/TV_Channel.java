package DZ_6;

import attestation01.Person;

public class TV_Channel {
    private String chname;
    private int chnum;
    private TV_Programm programm;

    public TV_Channel(String chname, int chnum, TV_Programm programm) {
        this.chname = chname;
        this.chnum = chnum;
        this.programm = programm;
    }

    @Override
    public String toString() {
        return " Канал - " + chname + ", номер канала - " + chnum + " " + programm;
    }

    @Override
    public int hashCode() {
        return chname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        TV_Channel other = (TV_Channel) obj;
        return chname.equals(other.chname);
    }
}