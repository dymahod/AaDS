public class LinkedList<T> {
    //тут создаю структуру связного списка со своими функциями которые
    //буду использовать в функциях других структур
    //Node интерфейс является основным типом данных для всей Объектной модели документа.
    //Это представляет единственный узел в дереве документов.
    public Node<T> first = null;
    public Node<T> last = null;

    public void addFirst(T data) {
        Node<T> n = new Node<>(data);
        n.next = first;
        first = n;
    }

    public T deleteFirst() {
        Node<T> temp = first;
        if (first.next == null) last = null;
        first = first.next;
        return temp.data;
    }

    public void addLast(T data) {
        Node<T> n = new Node<>(data);
        if (isEmpty()) {
            first = n;
        } else {
            last.next = n;
        }
        last = n;
        last.next = null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = first;
        while (current != null) {
            builder.append(current).append(" ");
            current = current.next;
        }
        return builder.toString();
    }

    public boolean isEmpty() {
        return first == null;
    }
}