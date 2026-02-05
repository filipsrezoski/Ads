package Stack.Zadacha4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
public class Zadacha4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        ArrayStack<String> stack = new ArrayStack<>(n);
        int flag = 1;
        for (int i = 0; i < n; i++) {
            String tag = sc.next();
                if (tag.startsWith("[/")) {
                    if (stack.isEmpty() || !stack.peek().equals(tag.substring(2, tag.length() - 1))) {
                        flag = 0;
                        break;
                    } else {
                        stack.pop();
                    }
                } else {
                    stack.push(tag.substring(1, tag.length() - 1));
                }
        }
        System.out.println(flag);
    }
}
    /*public static int ValidOrNot(int n, String[] niza){
        ArrayStack<String> stack = new ArrayStack<>(n);

        int flag = 1;
        for(int i=0;i<n;i++){
            if(niza[i].charAt(0) == '['){
                if(niza[i].charAt(1) == '/'){
                    if(!stack.peek().equals(niza[i].substring(2, niza[i].length() - 1))){
                     flag = 0;
                     break;
                    }else{
                        stack.pop();
                    }
                }else{
                 stack.push(niza[i].substring(1, niza[i].length() - 1));
                }
            }
        }
        //System.out.println(flag);
        return flag;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] parts = br.readLine().split(" ");
        String[] niza = new String[n];

        for(int i=0;i<n;i++){
            niza[i] = parts[i];
        }

        System.out.println(ValidOrNot(n, niza));
    }*/



