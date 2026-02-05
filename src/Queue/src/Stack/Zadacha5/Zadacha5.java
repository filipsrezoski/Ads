package Stack.Zadacha5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;

import java.util.NoSuchElementException;
import java.util.Scanner;


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

public class Zadacha5 {
    public static void main(String[] args) throws IOException {
        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        LinkedStack<String> stack = new LinkedStack<>();
        int flag = 1;
        while (true){
            line = br.readLine();
            if(line.equals("x")){
                break;
            }
            if(!line.startsWith("end")){
                stack.push(line);
            }else{
                String zbor = line.substring(3, line.length());
                if(stack.isEmpty()){
                    flag = 0;
                    break;
                }
                if(!zbor.equals(stack.peek())){
                    flag = 0;
                    break;
                }else{
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty() && flag == 1){
            System.out.println("Valid");
        }else{
            System.out.println("Invalid");
        }*/
        LinkedStack<String> stack = new LinkedStack<>();
        Scanner sc = new Scanner(System.in);
        int flag = 1;
        while(true){

            String line = sc.next();
            if(line.equals("x")){
                if(!stack.isEmpty()){
                    flag = 0;
                }
                break;
            }
            if(!line.startsWith("end")){
                stack.push(line);
            }else{
                String zbor = line.substring(3, line.length());
                if(stack.isEmpty()){
                    flag = 0;
                    break;
                }
                if(!zbor.equals(stack.peek())){
                    flag = 0;
                    break;
                }else{
                    stack.pop();
                }
            }

        }
        if(flag == 1 && stack.isEmpty()){
            System.out.println("Valid");
        }else{
            System.out.println("Invalid");
        }

    }
}
