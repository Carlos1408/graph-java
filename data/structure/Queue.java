package data.structure;

import java.util.LinkedList;

public class Queue {
    LinkedList elements;
    int size, limit;

    public Queue()
    {
        this.elements = new LinkedList();
        this.size = 0;
        this.limit = 10;
    }

    public Queue(int limit)
    {
        this.elements = new LinkedList();
        this.size = 0;
        this.limit = limit;
    }

    public boolean isEmpty()
    {
        if(elements.getFirst()==null)
            return true;
        else
            return false;
    }

    public boolean isFull()
    {
        if(size == limit)
            return true;
        else
            return false;
    }

    public void add(Object aux)
    {
        if(!isFull())
        {
            elements.addFirst(aux);
            size++;
        }else
            System.out.println("COLA LLENA");
    }

    public Object element()
    {
        Object aux = null;
        if(!isEmpty())
            aux = elements.getLast();
        else
            System.out.println("COLA VACIA");
        return aux;
    }

    public Object remove()
    {
        Object aux = null;
        if(!isEmpty())
        {
            aux = elements.getLast();
            elements.removeLast();
            size--;
        }else
            System.out.println("COLA VACIA");
        return aux;
    }
}
