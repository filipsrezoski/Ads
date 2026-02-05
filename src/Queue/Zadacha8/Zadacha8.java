package Queue.Zadacha8;

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

public class Zadacha8 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];
        int[] doc = new int[n];
        int[] ind = new int[n];
        int[] zemiDoc = new int[n];

        int[] brojach = new int[n];

        ArrayQueue<Integer> queueDoc = new ArrayQueue<>(n);
        ArrayQueue<Integer> queueInd = new ArrayQueue<>(n);
        ArrayQueue<Integer> queueZemiDoc = new ArrayQueue<>(n);

        for(int i=0;i<n;i++){
            names[i] = sc.nextLine();
            doc[i] = sc.nextInt();
            ind[i] = sc.nextInt();
            zemiDoc[i] = sc.nextInt();
            sc.nextLine();

            brojach[i] = doc[i] + ind[i] + zemiDoc[i];

            if(doc[i] == 1){
                queueDoc.enqueue(i);
            }else if(ind[i] == 1){
                queueInd.enqueue(i);
            }else if(zemiDoc[i] == 1){
                queueZemiDoc.enqueue(i);
            }
        }

        while(!queueDoc.isEmpty() || !queueInd.isEmpty() || !queueZemiDoc.isEmpty()){
            for(int i=0;i<3;i++){
                if(queueDoc.isEmpty()){
                    break;
                }
                int index = queueDoc.dequeue();
                brojach[index]--;
                if(brojach[index] == 0){
                    System.out.println(names[index]);
                }else if(ind[index] == 1){
                    queueInd.enqueue(index);
                }else if(zemiDoc[index] == 1){
                    queueZemiDoc.enqueue(index);
                }
            }

            for(int i=0;i<2;i++){
                if(queueInd.isEmpty()){
                    break;
                }
                int index = queueInd.dequeue();
                brojach[index]--;
                if(brojach[index] == 0){
                    System.out.println(names[index]);
                }else if(zemiDoc[index]== 1){
                    queueZemiDoc.enqueue(index);
                }
            }

            for(int i=0;i<1;i++){
                if(queueZemiDoc.isEmpty()){
                    break;
                }
                int index = queueZemiDoc.dequeue();
                brojach[index]--;
                if(brojach[index] == 0){
                    System.out.println(names[index]);
                }
            }

        }
    }
}