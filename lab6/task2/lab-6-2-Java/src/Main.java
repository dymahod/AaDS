import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack[] stacks = new Stack[3]; //3 ящика
        for (int i = 0; i < 3; i++) {
            stacks[i] = new Stack();
        }
        Queue Free = new Queue(); //5 свободных роботов
        for (int i = 1; i <= 5; i++) {
            Free.enqueue(1);
        }
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        Queue Busy = new Queue(); //5 занятых роботов

        Deque dec = new Deque<>();
        // FR = freeRobot   BR = busyRobot conv = conveyor
        try {
            for (int i = 1; ; i++) {
                TimeUnit.SECONDS.sleep(1);
                switch ((int) (Math.random() * 4)) {
                    case 0: //добавление в ящики
                        System.out.println(
                                ANSI_CYAN + i + ". Доставка продуктов в контейнеры... (box1:" + stacks[0].getSize()+
                                                                                     " box2:" + stacks[1].getSize()+
                                                                                     " box3:" + stacks[2].getSize()+
                                                                                     "; FR: " + Free.getSize()+
                                                                                     "; BR: " + Busy.getSize()+
                                                                                     "; Сonv:"+ dec.getSize() + ")"+ANSI_RESET);
                        for (int j = 0; j < 3; j++) {
                            stacks[j].push(1);//добавляет один продукт в любой контейнер
                        }
                        System.out.println(ANSI_GREEN + "   Продукты в контейнер доставлены."+ANSI_RESET);
                        break;
                    case 1: //сбор с ящиков и уход робота
                        System.out.println(ANSI_BLUE + i + ". Сбор продуктов из контейнеров... (box1:" + stacks[0].getSize()+
                                                                                              " box2:" + stacks[1].getSize()+
                                                                                              " box3:" + stacks[2].getSize()+
                                                                                              "; FR: " + Free.getSize()+
                                                                                              "; BR: " + Busy.getSize()+
                                                                                              "; Сonv:"+ dec.getSize() + ")"+ANSI_RESET);
                        if (stacks[0].getSize() == 0 || stacks[1].getSize() == 0 || stacks[2].getSize() == 0) {
                            System.out.println(ANSI_RED+"   Не удалось взять продукты(нет продуктов)."+ANSI_RESET);
                        }
                        else
                        if (Free.dequeue() != null) {
                            for (int j = 0; j < 3; j++) {//если есть в каждом ящике хоть 1 элемент, то робот его забирает
                                stacks[j].pop();
                            }
                            Busy.enqueue(1);
                            System.out.println(ANSI_GREEN + "   Робот взял продукты."+ANSI_RESET);
                        }
                        else System.out.println(ANSI_RED+"   Не удалось взять продукты(нет роботов)."+ANSI_RESET);
                        break;
                    case 2: //приход в сортировочный цех и становление обратно в очередь
                        System.out.println(ANSI_PURPLE + i + ". Складывание салатов на ленту...  (box1:" + stacks[0].getSize()+
                                                                                                " box2:" + stacks[1].getSize()+
                                                                                                " box3:" + stacks[2].getSize()+
                                                                                                "; FR: " + Free.getSize()+
                                                                                                "; BR: " + Busy.getSize()+
                                                                                                "; Сonv:"+ dec.getSize() + ")"+ANSI_RESET);
                        if (Busy.getSize() == 0) {
                            System.out.println(ANSI_RED+"   Не удалось положить салаты(нет роботов)."+ANSI_RESET);
                        } else {
                            if(((int) (Math.random() * 4)) <2){
                            dec.enqueueFirst(1);
                                System.out.println("Сейчас в начало");}
                            else {dec.enqueueLast(1);System.out.println("Сейчас в конец");}
                            Free.enqueue(1);
                            System.out.println(ANSI_GREEN + "   Салаты положены на конвеер."+ANSI_RESET);
                        }
                        break;
                    case 3: //погрузка в машину
                        System.out.println(ANSI_YELLOW + i + ". Погрузка салатов в машину...  (box1:" + stacks[0].getSize()+
                                                                                             " box2:" + stacks[1].getSize()+
                                                                                             " box3:" + stacks[2].getSize()+
                                                                                             "; FR: " + Free.getSize()+
                                                                                             "; BR: " + Busy.getSize()+
                                                                                             "; Сonv:"+ dec.getSize() + ")"+ANSI_RESET);
                        if (dec.getSize() == 0) {
                            System.out.println(ANSI_RED+"   Не удалось погрузить салаты в машину(нет салатов)."+ANSI_RESET);
                        } else {
                            if (dec.getSize() == 1) {
                                dec.dequeueFirst();
                                System.out.println(ANSI_GREEN + "   1 салат загружен в машину."+ANSI_RESET);
                            } else {
                                dec.dequeueFirst();
                                dec.dequeueLast();
                                System.out.println(ANSI_GREEN + "   2 салата загружено в машину."+ANSI_RESET);
                            }
                        }
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
