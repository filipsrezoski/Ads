package Queue.Zadacha4;

import java.util.Arrays;
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



public class Zadacha4 {
    /*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().split(",");
        int[] karti = new int[parts.length];
        for(int i=0;i< parts.length;i++){
            karti[i] = Integer.parseInt(parts[i]);
        }

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for(int i=0;i< karti.length;i++){
            queue.enqueue(i);
        }
        Arrays.sort(karti);

        int[] result = new int[karti.length];
        for (int i = 0; i < karti.length; i++) {
            result[queue.dequeue()] = karti[i];
            if(!queue.isEmpty()){
                queue.enqueue(queue.dequeue());
            }

        }
        System.out.println(Arrays.toString(result));;
    }*/
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String[] parts = sc.nextLine().split(",");
        int[] numbers = new int[parts.length];
        for(int i=0;i< parts.length;i++){
            numbers[i] = Integer.parseInt(parts[i]);
        }

        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for(int i=0;i<numbers.length;i++){
            queue.enqueue(i);
        }

        Arrays.sort(numbers);

        int[] result = new int[numbers.length];
        for(int i=0;i< numbers.length;i++){
            result[queue.dequeue()] = numbers[i];
            if(!queue.isEmpty()){
                queue.enqueue(queue.dequeue());
            }
        }

        System.out.println(Arrays.toString(result));
    }
}