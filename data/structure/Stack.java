package data.structure;

import java.util.LinkedList;

public class Stack {
    private LinkedList elements;
    private int size, limit;
    private Object top;

    public Stack()
    {
        this.elements = new LinkedList();
        this.size = 0;
        this.top = null;
        this.limit = 10;
    }

    public Stack(int limit)
    {
        this.elements = new LinkedList();
        this.size = 0;
        this.top = null;
        this.limit = limit;
    }

    public boolean isEmpty()
    {
        if(top == null)
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

    public int getSize()
    {
        return size;
    }

    public void push(Object element)
    {
        if(!isFull())
        {
            top = element;
            elements.addFirst(element);
            size++;
        }else
            System.out.println("PILA LLENA");
    }

    public void remove()
    {
        if(!isEmpty())
        {
            Object aux;
            aux = top;
            elements.remove();
            size--;
            System.out.println("Se extrajo el elemento: "+aux);
        }else
            System.out.println("PILA VACIA");
    }

    public void showStack()
    {
        if(!isEmpty())
        {
            for(int i=0; i<size; i++)
                System.out.println("|"+elements.get(i)+"|");
        }else
            System.out.println("PILA VACIA");
    }
    public void clearStack()
    {
        elements.clear();
        size = 0;
        top = null;
    }

    public Object getTop()
    {
        return top;
    }
}
