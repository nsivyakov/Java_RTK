package DZ_6;

public class App {
    public static void main(String[] args) {
        // Создаём и наполняем объекты классов
        TV_Programm news = new TV_Programm("Нововсти",8,1000);
        TV_Programm films = new TV_Programm("Фильмы",9,5000);
        TV_Programm comedy = new TV_Programm("Комедии",10,18000);
        TV_Programm natscience = new TV_Programm("Научпоп",8,8000);
        TV_Channel first = new TV_Channel("Первый канал",1,news);
        TV_Channel tnt = new TV_Channel("ТНТ",9,comedy);
        TV_Channel sts = new TV_Channel("СТС",11,films);
        TV_Channel mir = new TV_Channel("Мир",7,natscience);
        System.out.println(tnt);
        System.out.println(news);

        Televizor proton = new Televizor("Протон",5000,65);
        // Добавляем каналы
        proton.addChannel(tnt);
        proton.addChannel(sts);
        proton.addChannel(first);
        proton.addChannel(mir);
        System.out.println(proton);
        // Устанавливаем текущий канал
        proton.setCurchan(tnt);
        System.out.println("Текущий канал - " + proton.getCurchan());
        // Переключаем каналы
        proton.switching();
        System.out.println("Текущий канал - " + proton.getCurchan());
        proton.switching();
        System.out.println("Текущий канал - " + proton.getCurchan());
        // Получаем состояние телевизора и меняем его
        System.out.println(proton.getStateis());
        proton.turnOn();
        proton.turnOff();
        System.out.println(proton);
    }
}
