package data.structure;

public class Stack {
    private ListLinked elements;
    private int size, limit;
    private Object top;

    public Stack()
    {
        this.elements = new ListLinked();
        this.size = 0;
        this.top = null;
        this.limit = 10;
    }

    public Stack(int limit)
    {
        this.elements = new ListLinked();
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
            elements.addHead(element);
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
            elements.removeHead();
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

    public Object getTop()
    {
        return top;
    }
}
