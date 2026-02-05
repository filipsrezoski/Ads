package SLL_DLL.Zadacha14;

import java.util.Scanner;

class SLLNode {
    int id;
    int age;
    SLLNode succ;

    public SLLNode(int id, int age, SLLNode succ) {
        this.id = id;
        this.age = age;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;
    }

    public void deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if (first == node) {
                this.deleteFirst();
                return;
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }

    }

    public void insertFirst(int id, int age) {
        SLLNode ins = new SLLNode(id, age, first);
        first = ins;
    }

    public void insertAfter(int id, int age, SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(id, age, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(int id, int age, SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if (first == before) {
                this.insertFirst(id, age);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(id, age, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }


    public void insertLast(int id, int age) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, age, null);
            tmp.succ = ins;
        } else {
            insertFirst(id, age);
        }
    }

    public SLLNode getFirst() {
        return first;
    }

    @Override
    public String toString() {
        String s = new String();
        SLLNode dvizi = first;
        while (dvizi != null) {
            s = s + dvizi.id + " ";
            dvizi = dvizi.succ;
        }
        return s;
    }
}

public class Zadacha14{
    public static void alterTeams(SLL devTeam, SLL qaTeam) {
        SLLNode dt = devTeam.getFirst();
        SLLNode qt = qaTeam.getFirst();
        SLLNode pamti = qaTeam.first.succ;
        int najmlad = qt.age;
        while (pamti != null){
            if(najmlad >= pamti.age){
                najmlad = pamti.age;
                qt = pamti;
            }
            pamti = pamti.succ;
        }
        for(int i=0;i< (devTeam.length()+1) / 2;i++){
            dt = dt.succ;
        }
        devTeam.insertBefore(qt.id, qt.age, dt);
        qaTeam.delete(qt);

        System.out.println(devTeam);
        System.out.println(qaTeam);
    }

    public static void main(String[] args) {
        SLL dev = new SLL();
        SLL qa = new SLL();
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();

        for (int i = 0; i < n; i++) {
            int id = input.nextInt();
            int age = input.nextInt();
            dev.insertLast(id, age);
        }
        for (int j = 0; j < m; j++) {
            int id = input.nextInt();
            int age = input.nextInt();
            qa.insertLast(id, age);
        }
        alterTeams(dev, qa);
    }
}

