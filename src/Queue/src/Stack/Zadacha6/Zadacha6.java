package Stack.Zadacha6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

interface Stack<E> {
    // Elementi na stekot se objekti od proizvolen tip.
    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:
    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class LinkedStack<E> implements Stack<E> {
    // top e link do prviot jazol ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;
    int size;

    public LinkedStack() {
        // Konstrukcija na nov, prazen stek.
        top = null;
        size = 0;
    }

    public String toString() {
        SLLNode<E> current = top;
        StringBuilder s = new StringBuilder();
        while (current != null) {
            s.append(current.element);
            s.append(" ");
            current = current.succ;
        }
        return s.toString();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public void clear() {
        // Go prazni stekot.
        top = null;
        size = 0;
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
        size++;
    }

    public int size() {
        // Ja vrakja dolzinata na stekot.
        return size;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        size--;
        top = top.succ;
        return topElem;
    }

}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

public class Zadacha6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedStack<String> stack = new LinkedStack<>();
        int number = Integer.parseInt(br.readLine());
        for(int i=0;i<number;i++){
            String command = br.readLine();
            if(command.startsWith("T")){
                String bukva = command.substring(5);
                stack.push(bukva);
            }else if(command.startsWith("U")){
                if(!stack.isEmpty()){
                    stack.pop();
                }

            }else if(command.startsWith("S")){
                LinkedStack<String> stack2 = new LinkedStack<>();
                while (!stack.isEmpty()){
                    stack2.push(stack.pop());
                }
                while(!stack2.isEmpty()){
                    String c=stack2.pop();
                    System.out.println(c);
                    stack.push(c);
                }
            }
        }
    }
}
