
//   < class BinomialHeap >
//  
//  ����� BinomialHeap �������������
//  ���������� ������������ ����


public class BinomialHeap {
    private Node root;

	//  ������������

    public BinomialHeap() {
        root = null;
    }

    public BinomialHeap(Node node) {
        root = node;
    }

	//	< insert >
	//
	//  ���������� ������� �
	//	����. ������ ����� ����
	//	� ���������� � ������������.

    public void insert(int value) {
        Node node = new Node(value);
        BinomialHeap binomialHeap = new BinomialHeap(node);
        root = union(binomialHeap);
    }

	//	< extract >
	//
	//  �������� ����������� �������.
	//	������� ������� �����������
	//	������ � ����� ������� ���.

    public int extract() throws IndexOutOfBoundsException {
        if (root == null) {
            throw new IndexOutOfBoundsException();
        }

        Node min = root;
        Node previousMin = null;
        Node next = min.getSibling();
        Node nextPrevious = min;

        while (next != null) {
            if (next.getValue() < min.getValue()) {
                min = next;
                previousMin = nextPrevious;
            }

            nextPrevious = next;
            next = next.getSibling();
        }

        remove(min, previousMin);

        return min.getValue();
    }

	//	< remove >
	//
	//  ������� ��������� ������ �
	//	������������ ������, ������
	//	�������� �������. � �����
	//	�������� ����������� �
	//	����� �����.

    private void remove(Node node1, Node node2) {
        if (node1 == root) 
            root = node1.getSibling();
        else 
            node2.setSibling(node1.getSibling());

        Node newRoot = null;
        Node child = node1.getChild();

        while (child != null) {
            Node next = child.getSibling();

            child.setSibling(newRoot);
            child.setParent(null);
            newRoot = child;
            child = next;
        }

        BinomialHeap heap = new BinomialHeap(newRoot);

        root = union(heap);
    }

	//	< union >
	//
	//  ������������ ���.
	//	(���������� ������� �������)

    private Node union(BinomialHeap heap) {
        Node newRoot = merge(this, heap);

        root = null;
        heap.root = null;

        Node previous = null;
        Node current = newRoot;
        Node next = newRoot.getSibling();

        while (next != null) {
            if (current.getDegree() != next.getDegree() || (next.getSibling() != null
                    && next.getSibling().getDegree() == current.getDegree()
                    )
            ) {
                previous = current;
                current = next;
            } else {
                if (current.getValue() < next.getValue()) {
                    current.setSibling(next.getSibling());
                    linkTree(current, next);
                } else {
                    if (previous == null) {
                        newRoot = next;
                    } else {
                        previous.setSibling(next);
                    }

                    linkTree(next, current);

                    current = next;
                }
            }

            next = current.getSibling();
        }

        return newRoot;
    }

	//	< merge >
	//
	//  ���������� 2 ������ � ����.

    private Node merge(BinomialHeap heap1, BinomialHeap heap2) {//����������� ���� ���,��� ����� ������

        // �������� �� �������
        if (heap1.root == null) {
            return heap2.root;
        }

        if (heap2.root == null) {
            return heap1.root;
        }


        Node root;
        Node heap1Next = heap1.root;
        Node heap2Next = heap2.root;
		
		//���������� �� ������
        if (heap1.root.getDegree() <= heap2.root.getDegree()) { //����� ����������� �� ������
            root = heap1.root;
            heap1Next = heap1Next.getSibling();
        } else {
            root = heap2.root;
            heap2Next = heap2Next.getSibling();
        }

        Node tail = root;//�����
		
		//���������, ���� ������ ���� �������� �����, �� ���������� �� �� �������� ������(�������) (�� �������)
        while (heap1Next != null && heap2Next != null) {
            if (heap1Next.getDegree() <= heap2Next.getDegree()) {
                tail.setSibling(heap1Next);
                heap1Next = heap1Next.getSibling();
            } else {
                tail.setSibling(heap2Next);
                heap2Next = heap2Next.getSibling();
            }

            tail = tail.getSibling();
        }

        if (heap1Next != null) {
            tail.setSibling(heap1Next);
        } else {
            tail.setSibling(heap2Next);
        }

        return root;
    }

	//	< linkTree >
	//
	//  ������ ����� �����
	// 	���������. 

    private void linkTree(Node node1, Node node2) {
        node2.setParent(node1);
        node2.setSibling(node1.getChild());
        node1.setChild(node2);
        node1.setDegree(node1.getDegree() + 1);
    }

    @Override
    public String toString() {
        if (root != null) {
            return root.toString(1);
        }

        return "";
    }
}