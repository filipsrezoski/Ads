package Queue.Zadacha1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
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



class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

/*public class Zadacha1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(",");
        int[] tickets = new int[parts.length];
        for(int i=0;i< parts.length;i++){
            tickets[i] = Integer.parseInt(parts[i]);
        }

        int k = Integer.parseInt(br.readLine());

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        LinkedQueue<Boolean> isKqueue = new LinkedQueue<>();

        for(int i=0;i< tickets.length;i++){
            queue.enqueue(tickets[i]);
            isKqueue.enqueue(i == k);
        }

        int vrtenja = 0;
        int index = 0;
        while(!queue.isEmpty()){
            int x = queue.dequeue();
            if(x - 1 > 0){
                queue.enqueue(x - 1);
                vrtenja++;
                index = (index + 1) % tickets.length;
            }else{
                vrtenja++;
                if(index == k){
                    break;
                }
                index = (index + 1) % tickets.length;
            }

        }
        System.out.println(vrtenja);
    }
}*/


public class Zadacha1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(",");
        int[] tickets = new int[parts.length];
        for(int i=0;i< parts.length;i++){
            tickets[i] = Integer.parseInt(parts[i]);
        }

        int k = Integer.parseInt(br.readLine());

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        LinkedQueue<Boolean> isKqueue = new LinkedQueue<>();

        for(int i=0;i< tickets.length;i++){
            queue.enqueue(tickets[i]);
            isKqueue.enqueue(i == k);
        }

        int vrtenja = 0;
        while(!queue.isEmpty()){
            int x = queue.peek();
            boolean isK = isKqueue.peek();
            if(x - 1 > 0){
                queue.dequeue();
                isKqueue.dequeue();

                queue.enqueue(x - 1);
                isKqueue.enqueue(isK);

                vrtenja++;
            }else{
                queue.dequeue();
                isKqueue.dequeue();

                vrtenja++;

                if(isK){
                    break;
                }
            }

        }
        System.out.println(vrtenja);
    }
}
