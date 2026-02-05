package Queue.Zadacha5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Aleksandar
 */
// Java Program to check if a queue
// of first n natural number can
// be sorted using a stack

import java.io.*;
import java.util.*;


import java.util.NoSuchElementException;

class ArrayQueue<E> {
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        if (length == elems.length)
            throw new NoSuchElementException();
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

public class Zadacha5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] names = new String[n];
        int[] brojach = new int[n];
        int[] sc = new int[n];
        int[] scf = new int[n];
        int[] hist = new int[n];

        ArrayQueue<Integer> queueSC = new ArrayQueue<>(n);
        ArrayQueue<Integer> queueSCF = new ArrayQueue<>(n);
        ArrayQueue<Integer> queueHIST = new ArrayQueue<>(n);


        for(int i=0;i<n;i++){
            names[i] = br.readLine();
            sc[i] = Integer.parseInt(br.readLine());
            scf[i] = Integer.parseInt(br.readLine());
            hist[i] = Integer.parseInt(br.readLine());

            brojach[i] = sc[i] + scf[i] + hist[i];

            if(sc[i] == 1){
                queueSC.enqueue(i);
            }else if(scf[i] == 1){
                queueSCF.enqueue(i);
            }else if(hist[i] == 1){
                queueHIST.enqueue(i);
            }
        }

        while(!queueSC.isEmpty()){
            int index = queueSC.dequeue();
            brojach[index]--;
            if(brojach[index] == 0){
                System.out.println(names[index]);
            }else{
                if(scf[index] == 1){
                    queueSCF.enqueue(index);
                }else if(hist[index] == 1){
                    queueHIST.enqueue(index);
                }
            }
        }

        while(!queueSCF.isEmpty()){
            int index = queueSCF.dequeue();
            brojach[index]--;
            if(brojach[index] == 0){
                System.out.println(names[index]);
            }else{
                if(hist[index] == 1){
                    queueHIST.enqueue(index);
                }
            }
        }

        while(!queueHIST.isEmpty()){
            int index = queueHIST.dequeue();
            brojach[index]--;
            if(brojach[index] == 0){
                System.out.println(names[index]);
            }
        }
    }
}