public class Node<T> {
    //Node интерфейс является основным типом данных для всей Объектной модели документа.
    //Это представляет единственный узел в дереве документов.
    public T data;
    public Node<T> next;
    public Node<T> before;
    //возврат значение или всех значений
    public Node(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return data.toString();
    }
}