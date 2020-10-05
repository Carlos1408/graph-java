package data.structure;

public class Node<E> {
    private Object data;
    private Node<E> link;

    public Node(Object data) {
        this.data = data;
        this.link = null;
    }

    public Node(Object data, Node<E> link) {
        this.data = data;
        this.link = link;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node<E> getLink() {
        return link;
    }

    public void setLink(Node<E> link) {
        this.link = link;
    }

    public String toString() {
        return "Node={data={" + data + "},link={" + link + "}}";
    }

}
