package data.structure;

import javax.lang.model.util.ElementScanner14;

public class ListLinked<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public ListLinked() {
        head = tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addHead(Object data) {
        Node<E> node = new Node<E>(data);
        if (isEmpty()) {
            tail = node;
        }
        node.setLink(head);
        head = node;
        size++;
    }

    public void addTail(Object data) {
        Node<E> node = new Node<>(data);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setLink(node);
        }
        tail = node;
        size++;
    }

    public void add(Object data)
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
        Node aux=null;
        if(!isEmpty())
        {
            while(aux.getLink()!=tail)
                aux=aux.getLink();
            aux.setLink(null);
            tail = aux;
        }
    }

    public Object get(int j)
    {
        Node aux=null;
        int i=0;
        Object data=null;
        if(!isEmpty())
        {
            aux = head;
            while(aux!=null)
            {
                if(j==i)
                    data=aux.getData();
                aux = aux.getLink();
            }
        }
        return data;
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
