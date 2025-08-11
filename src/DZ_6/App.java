package DZ_6;

public class App {
    public static void main(String[] args) {
        TV_Programm news = new TV_Programm("Нововсти",8,1000);
        TV_Programm films = new TV_Programm("Фильмы",9,5000);
        TV_Programm comedy = new TV_Programm("Комедии",10,18000);
        TV_Channel first = new TV_Channel("Первый канал",1,news);
        TV_Channel tnt = new TV_Channel("ТНТ",9,comedy);
        TV_Channel sts = new TV_Channel("СТС",11,films);
        System.out.println(tnt);
        System.out.println(news);

        Televizor proton = new Televizor("Протон",5000,65);
        proton.addChannel(tnt);
        proton.addChannel(sts);
        proton.addChannel(first);
        System.out.println(proton);
        proton.setCurchan(tnt);
        System.out.println(proton);
        proton.switching();
        System.out.println(proton);
        proton.switching();
        System.out.println(proton);
    }
}
