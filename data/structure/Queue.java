package data.structure;

public class Queue {
    ListLinked elements;
    int size, limit;

    public Queue()
    {
        this.elements = new ListLinked<>();
        this.size = 0;
        this.limit = 10;
    }

    public Queue(int limit)
    {
        this.elements = new ListLinked<>();
        this.size = 0;
        this.limit = limit;
    }

    public boolean isEmpty()
    {
        if(elements.getHead()==null)
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
            elements.addHead(aux);
            size++;
        }else
            System.out.println("COLA LLENA");
    }

    public Object element()
    {
        Object aux = null;
        if(!isEmpty())
            aux = elements.getTail();
        else
            System.out.println("COLA VACIA");
        return aux;
    }

    public Object remove()
    {
        Object aux = null;
        if(!isEmpty())
        {
            aux = elements.getTail();
            elements.removeTail();
            size--;
        }else
            System.out.println("COLA VACIA");
        return aux;
    }
}
