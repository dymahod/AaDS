public class Deque<T> {
    Integer top = -1; //номер последнего добавленного элемента
    DoubleLinkedList<T> doubleLinkedList;
    //при создании чтобы был пустой как и нужно
    Deque() {
        doubleLinkedList = new DoubleLinkedList<>();
        //doubleLinkedList.first = doubleLinkedList.last = null;

    }
    //добавляет в начало
    void enqueueFirst(T data) {
        top++;
        doubleLinkedList.addFirst(data);
    }
    //добавляет в конец
    void enqueueLast(T data) {
        top++;
        doubleLinkedList.addLast(data);
    }

    //удаляет из начала
    void  dequeueFirst() {
        doubleLinkedList.deleteFirst();
        System.out.println("удалил с начала");
    }
    //удаляет из конца
    void  dequeueLast() {
        doubleLinkedList.deleteLast();
        System.out.println("удалил с конца");
    }
    //возвращаем последний номер последнего элемента + 1 - размер стека
    public Integer getSize(){
        return top+1;
    }
    //возвращает элемент в начала
    T peekFirst() {
        return doubleLinkedList.first.data;
    }
    //возвращает последний элемент
    T peekLast() {
        return doubleLinkedList.last.data;
    }
    //проверка на пустоту
    boolean isEmpty() {
        return doubleLinkedList.isEmpty();
    }
    //понимает, если выводим наш deque, то пишем toString или нет - сработает проверка
    @Override
    public String toString() {
        return doubleLinkedList.toString();
    }
}



