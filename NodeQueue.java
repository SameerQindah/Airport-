package application;

public class NodeQueue {
    private Object Element;
    private NodeQueue next;

    public NodeQueue(Object element) {
        this.Element = element;
    }

    public Object getElement() {
        return Element;
    }

    public void SetElemant(Object element) {
        this.Element = element;
    }

    public NodeQueue getNext() {
        return next;
    }

    public void setNext(NodeQueue next) {
        this.next = next;
    }

}