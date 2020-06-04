public class DoubleLinkedList<T> {
    public Node<T> first = null;
    public Node<T> last = null;

    LinkedList<T> li = new LinkedList<>();

    public void addFirst(T data) {
        Node<T> n = new Node<>(data);
        if(first == null)   last = first = n;
        else
        {
            n.next = first;
            first.before = n;
            first = n;
        }
    }

    public void addLast(T data) {
        Node<T> n = new Node<>(data);
        if(last == null)    first = last = n;
        else{
            n.before = last;
            last.next = n;
            last = n;
        }
    }

    public void deleteFirst() {
        if(isEmpty()) System.out.println("Underflow\n");
        else{
            Node<T> temp = first;
            first = first.next;
            if(first==null) last = null;
            else first.before = null;
        }
    }

    public  void  deleteLast() {
        if(isEmpty())
            System.out.println("Underflow\n");
        else{
            Node<T> temp = last;
            last = last.before;
            if(last ==null) first = null;
            else last.next = null;
        }
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
