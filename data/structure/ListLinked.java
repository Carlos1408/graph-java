package data.structure;

public class ListLinked<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public ListLinked() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addHead(E data) {
        Node<E> node = new Node<E>(data);
        if (isEmpty()) {
            tail = node;
        }
        node.setLink(head);
        head = node;
        size++;
    }

    public void addTail(E data) {
        Node<E> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
    }

    public void add(E data)
    {
        Node<E> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
    }

    public void removeHead()
    {
        if(!isEmpty())
            head = head.getLink();
    }

    public void removeTail()
    {
        Node<E> aux=null;
        if(!isEmpty())
        {
            while(aux.getLink()!=tail)
                aux=aux.getLink();
            aux.setLink(null);
            tail = aux;
        }
    }

    public Node<E> getNode(int j)
    {
        Node<E> aux=head;
        int i=0;
        if(!isEmpty())
        {
            while(aux.getLink()!=null)
            {
                if(j==i)
                    break;
                aux = aux.getLink();
                i++;
            }
        }
        return aux;
    }
    
    public Node<E> getHead()
    {
        return head;
    }

    public Node<E> getTail()
    {
        return tail;
    }

    public int size()
    {
        return size;
    }

    public String toString() {
        return "List={head={" + head + "},tail={" + tail + "},size={" + size + "}}";
    }
}
