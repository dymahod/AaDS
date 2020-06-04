import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new Queue<>();
        Deque<Integer> deque = new Deque<>();
        Scanner scanner = new Scanner(System.in);

        int i = 12;
        while(i!=4) {
            System.out.println("Работу чего увидеть хотите? 1-stack 2-queue 3-deque 4 exit");
            i = scanner.nextInt();
            switch (i) {
                case 1: {/*STACK*//*STACK*//*STACK*/
                    System.out.println("toStr) " + stack.toString());
                    stack.push(12);
                    System.out.println("after push) " + stack);
                    System.out.println("peek) " + stack.peek());
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    System.out.println("peek) " + stack.peek());
                    System.out.println("isEmpty?) " + stack.isEmpty());
                    stack.push(12);
                    System.out.println("after push) " + stack);
                    stack.push(23);
                    System.out.println("after push) " + stack);
                    stack.push(15);
                    System.out.println("after push) " + stack);
                    stack.push(2);
                    System.out.println("after push) " + stack);
                    System.out.println("size) " + stack.getSize());
                    stack.push(-5);
                    System.out.println("after push) " + stack);
                    System.out.println("isEmpty?) " + stack.isEmpty());
                    System.out.println("after 5 push) " + stack);
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    System.out.println("size) " + stack.getSize());
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    stack.pop();
                    System.out.println("after pop) " + stack);
                    /*STACK*//*STACK*//*STACK*/
                }
                break;
                case 2: {/*QUEUE*//*QUEUE*//*QUEUE*/
                    System.out.println("queue) " + queue);
                    queue.enqueue(12);
                    System.out.println("after push) " + queue);
                    queue.enqueue(-5);
                    System.out.println("after push) " + queue);
                    queue.enqueue(17);
                    System.out.println("after push) " + queue);
                    queue.enqueue(11232);
                    System.out.println("after push) " + queue);
                    queue.enqueue(112);
                    System.out.println("after push) " + queue);
                    System.out.println("size) " + queue.getSize());
                    System.out.println("after 5 push) " + queue);
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    System.out.println("peek) " + queue.peek());
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    System.out.println("peek) " + queue.peek());
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    System.out.println("size) " + deque.getSize());
                    queue.dequeue();
                    System.out.println("after dequeue) " + queue);
                    System.out.println("isEmpty?) " + queue.isEmpty());
                    queue.enqueue(112);
                    System.out.println("after push) " + queue);
                    System.out.println("isEmpty?) " + queue.isEmpty());
                    /*QUEUE*//*QUEUE*//*QUEUE*/
                }
                break;
                case 3: {
                    /*DEQUE*//*DEQUE*//*DEQUE*/
                    deque.enqueueFirst(23);
                    deque.enqueueFirst(12);
                    deque.enqueueFirst(3);
                    deque.enqueueFirst(17);
                    deque.enqueueFirst(25);
                    deque.enqueueFirst(198);
                    System.out.println(deque.toString());
                    deque.dequeueFirst();
                    System.out.println(deque);
                    System.out.println(deque);
                    System.out.println(deque);
                    deque.dequeueLast();
                    System.out.println("dellast1) " + deque);
                    deque.dequeueLast();
                    System.out.println("dellast2) " + deque);
                    deque.enqueueLast(1234500);

                    System.out.println(deque);
                    deque.enqueueLast(1200);

                    System.out.println(deque);

                    System.out.println("peekFirst) " + deque.peekFirst());
                    System.out.println("peekLast) " + deque.peekLast());
                    /*DEQUE*//*DEQUE*//*DEQUE*/

                }
                break;
                default: System.out.println("error");
            }
        }






    }


}
