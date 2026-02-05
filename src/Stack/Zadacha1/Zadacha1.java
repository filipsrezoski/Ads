package Stack.Zadacha1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
class ArrayStack<E> implements Stack<E> {
    private E[] elems; //elems[0...depth-1] se negovite elementi.
    private int depth; //depth e dlabochinata na stekot

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }

    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }

    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }

    public int size() {
        // Ja vrakja dolzinata na stack-ot.
        return depth;
    }

    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class Zadacha1 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ArrayStack<Character> stack = new ArrayStack<>(line.length());
        int flag = 1;
        int kruzhna = 0;
        int kockesta = 0;
        int jaglesta = 0;
        for(char c : line.toCharArray()){
            if(c == '('){
                stack.push(c);
                kruzhna++;
            }else if(c == '{'){
                stack.push(c);
                jaglesta++;
            }else if(c == '['){
                stack.push(c);
                kockesta++;
            }

            if(c == ')'){
                if(stack.isEmpty()){
                    flag = 0;
                    break;
                }else if(!stack.pop().equals('(')){
                    flag = 0;
                    break;
                }
            }

            if(c == '}'){
                if(stack.isEmpty()){
                    flag = 0;
                    break;
                }else if(!stack.pop().equals('{')){
                    flag = 0;
                    break;
                }
            }

            if(c == ']'){
                if(stack.isEmpty()){
                    flag = 0;
                    break;
                }else if(!stack.pop().equals('[')){
                    flag = 0;
                    break;
                }
            }
        }

        if(kockesta != kruzhna || kockesta != jaglesta){
            flag = 0;
            System.out.println(flag);
        }

        System.out.println(flag);
    }
}