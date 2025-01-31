package DoubleLinkedList;

/**
 * Implememt an application that support undo/redo functionality. Use a linked list to maintain a sequence of states.\
 * Each state change is stored as a node in the list, allowoing for easy navigation
 * 1<>2<>3<>4<>5
 */

public class UndoRedoManager<T> {

    private class Node {
        private T state;
        private Node prev;
        private Node next;
        private Node (T state) {
            this.state = state;
        }

    }
    private Node currentState;

    //Undo operation
    public T undo(){
        Node prevvalue = currentState.prev;
        currentState = prevvalue;
        if (prevvalue == null) {
            System.out.println("Undo not possible");
            return null;
        }
        return currentState.state;
    }

    //perform an operation
    public void  addState (T newState) {
        Node newnode = new Node(newState);
        newnode.prev = currentState;
        newnode.next = null;
        if (currentState != null){
            currentState.next = newnode;
        }
        currentState = newnode;
    }

    //Redo Operation
    public T redo(){
        Node nextvalue = currentState.next;
        currentState = nextvalue;
        if (nextvalue == null) {
            System.out.println("Redo not possible");
            return null;
        }
        return currentState.state;
    }

    public static void main(String[] args) {
        UndoRedoManager<Integer> first = new UndoRedoManager<>();
        first.addState(15);
        first.addState(20);
        first.addState(25);
        first.undo();
        System.out.println(first.currentState.state);
        first.redo();
        System.out.println(first.currentState.state);
    }
}

