package Queue.Zadacha3;

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

class Person{
    int delay;
    int forget;

    public Person(int delay, int forget) {
        this.delay = delay;
        this.forget = forget;
    }

    public int getDelay() {
        return delay;
    }

    public int getForget() {
        return forget;
    }
}

public class Zadacha3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int delay = sc.nextInt();
        int forget = sc.nextInt();

        LinkedQueue<Person> queue = new LinkedQueue<>();
        queue.enqueue(new Person(delay + 1, forget + 1));
        for(int day=2;day<=n;day++){
            int currentSize = queue.size();
            for(int i=0;i<currentSize;i++){
                Person person = queue.dequeue();
                if(day != person.getForget()){
                    queue.enqueue(person);
                }
            }

            currentSize = queue.size();
            for(int i=0;i<currentSize;i++){
                Person person = queue.dequeue();
                if(person.getDelay() <= day){
                    queue.enqueue(new Person(delay + day, forget + day));
                }
                queue.enqueue(person);
            }
        }

        System.out.println(queue.size());
    }
}

