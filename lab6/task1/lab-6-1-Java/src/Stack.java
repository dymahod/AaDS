public class Stack<T> {
    //связный список на основе Node
    Integer top = -1; //номер последнего добавленного элемента
    private LinkedLiist<T> li = new LinkedLiist<>();

    //добавление в вершину
    public void push(T data) {
        top++;
        li.addFirst(data);
    }
    //удаление последнего
    public T pop() {
        if (li.first == null) return null;
        else{
            top--;
            return li.deleteFirst();
        }
    }
    //извлечение первого элемента
    public T peek() {
        if (li.first == null) return null;
        return li.first.data;
    }
    //возвращаем последний номер последнего элемента + 1 - размер стека
    public Integer getSize(){
        return top + 1;
    }
    //проверка на пустоту
    public boolean isEmpty() {
        return li.first == null;
    }
    @Override
    //понимает, если выводим наш stack, то пишем toString или нет - сработает проверка
    public String toString() {
        if (li.isEmpty()){
            return "empty";
        }
        return li.toString();
    }
}