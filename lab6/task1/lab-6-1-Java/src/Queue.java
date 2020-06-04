public class Queue<T> {
    Integer top = -1; //номер последнего добавленного элемента
    LinkedLiist<T> li = new LinkedLiist<>();
    //при создании чтобы был пустой как и нужно
    public Queue() {
        li.first = li.last = null;
    }
    //добавление
    void enqueue(T data) {
        top++;
        li.addLast(data);
    }
    //удаление с начала
    T dequeue() {
        if(!li.isEmpty()){
            T first = li.first.data;
            li.deleteFirst();
            top--;
            return first;
        }
        else return null;
    }
    //возвращаем последний номер последнего элемента + 1 - размер стека
    public Integer getSize(){
        return top+1;
    }
    //выборка вершины
    T peek() {
        if (li.isEmpty()) return null;
        return li.first.data;
    }
    //проверка на пустоту
    public boolean isEmpty() {
        return li.last == null;
    }
    //понимает, если выводим наш queue, то пишем toString или нет - сработает проверка
    @Override
    public String toString() {
        return li.toString();
    }
}
