public class LinkedListDeque<T> {

    private class Node {
        public T    item;
        public Node previous;
        public Node next;


        public Node(T i, Node n, Node p) {
            previous = p;
            item     = i;
            next     = n;

        }
    }
    /* The first item (if it exists) is at sentinel.next/previous */
    private Node sentinel;
    private int     size;

    public LinkedListDeque() {
        sentinel = new Node(null, sentinel, sentinel);
        size     = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new Node(null, sentinel, sentinel);
        sentinel.next = new Node(x, sentinel,sentinel);
        sentinel.previous = sentinel.next;
        size  = 1;

    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.previous = sentinel.next;
            size = 1;
        } else  {
            Node prevPtr = sentinel.next;
            sentinel.next = new Node(item, sentinel.next, sentinel);
            size = size + 1;
            prevPtr.previous = sentinel.next;
        }

    }
    public void addLast(T item) {
        if (size == 0) {
            sentinel.previous = new Node(item, sentinel, sentinel);
            sentinel.next = sentinel.previous;
            size  = 1;
        } else  {
            Node prevPtr = sentinel.previous;
            sentinel.previous = new Node(item, sentinel, sentinel.previous);
            size = size + 1;
            prevPtr.next = sentinel.previous;
        }

    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node temp = sentinel.next;
        System.out.print(temp.item + " ");
        while (temp.next != sentinel) {
            temp = temp.next;
            System.out.print(temp.item + " ");
        }

    }

    public T removeFirst() {
        T returnFirst = sentinel.next.item;
        if (size == 0) {
            size = 0;
            return null;
        }
        else if (size == 1) {
            sentinel.next     = sentinel;
            sentinel.previous = sentinel;
            size = size - 1;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.next.previous = sentinel.next;
            size = size - 1;
        }

        return returnFirst;

    }

    public T removeLast() {
        T returnLast = sentinel.previous.item;
        if (size == 0) {
            size = 0;
            return null;
        }
        else if (size == 1) {
            sentinel.next     = sentinel;
            sentinel.previous = sentinel;
            size = size - 1;
        } else {
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size = size - 1;
        }

        return returnLast;

    }

    public T get(int index) {
        Node pointer = sentinel.next;
        int i = 0;
        while (i < index) {
            pointer = pointer.next;
            i = i + 1;
        }
        return pointer.item;
    }

    public T getRecursive(int index) {
        T pointer;

        if (index == 0) {
            pointer = sentinel.next.item;
            return pointer;
        } else {
            sentinel = sentinel.next;
            return getRecursive(index - 1);
        }
    }

    public static void main(String[] args) {
//        LinkedListDeque<Integer> a = new LinkedListDeque<>();
//        a.addFirst(500);
//        a.addFirst(200);
//        a.addFirst(100);
//        a.addFirst(800);
//        a.addLast(256);
//        a.addLast(987);
//        a.removeLast();
//
//        System.out.println("size: " + a.size());
//        System.out.print(" " + a.removeFirst());
//        System.out.print(" " + a.removeFirst());
//        a.addLast(35);
//        a.addLast(22);
//        System.out.println(" Non-recurr " + a.get(6));
//        System.out.println(" Recurr " + a.getRecursive(6));
//        a.printDeque();
//        System.out.print(" " + a.get(0));
//        System.out.print(" " + a.get(1));
//        System.out.print(" " + a.get(2));
//        System.out.print(" " + a.get(3));
    }


}
