import java.util.Scanner;

//   < class Lab9 >
//  
//  ����� Lab9 �������������
//  ��������� ��� ��������������
//  � ������� BinomialHeap
//  
//  �� ���� ����� ���� ����������
//  � � �� ����, ��� ����� 
//  ������������� �������� � ����
//  ������, ������� �� �� �
//  ������:)

public class Lab9 {
    private static Scanner scanner;
    private static boolean isRunning;
    private static BinomialHeap binomialHeap;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        isRunning = true;
        binomialHeap = new BinomialHeap();

        while (isRunning) {
            consoleMenu();
            int command = 0;
            command = scanner.nextInt();
            handleInput(command);
        }
    }

    private static void consoleMenu() {
        System.out.println();
        System.out.println("\t<==========X===========>");
        System.out.println("\t1 - insert node      - 1");
        System.out.println("\t2 - delete tree      - 2");
        System.out.println("\t3 - print heap       - 3");
        System.out.println("\t9 - program shutdown - 9");
        System.out.println("\t<==========X===========>");
        System.out.print("\t:>> ");
    }

    private static void handleInput(int command) {
        switch (command) {
            case 1:
                System.out.print("\n\tEnter node \n\t:>> ");
                addNode();
                System.out.println("\n\t<node add successfully>");
                break;
            case 2:
                System.out.print("\n\tDeleted node \n\t:>> ");
                removeNode();
                System.out.println("\n\t<node delete successfully>");
                break;
            case 3:
                System.out.println("\n");
                printHeap();
                break;
            case 9:
                System.out.println("\n\t<Program shutdown>\n");
                isRunning = false;
                break;
        }
    }

    private static void addNode() {
        int value = scanner.nextInt();
        binomialHeap.insert(value);
    }

    private static void removeNode() {
        System.out.println(binomialHeap.extract());
    }

    private static void printHeap() {
     System.out.println(binomialHeap.toString());
    }
}
