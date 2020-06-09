package learning.dcl;

import java.util.ArrayList;

class Buf {
    private final int MAX = 5;
    private final ArrayList<Integer> list = new ArrayList<>();
    synchronized void put(int v) throws InterruptedException {
        if (list.size() == MAX) {
            wait();
        }
        list.add(v);
        notifyAll();
    }

    synchronized int get() throws InterruptedException {
        // line 0
        if (list.size() == 0) {  // line 1
            wait();  // line2
            // line 3
        }
        int v = list.remove(0);  // line 4
        notifyAll(); // line 5
        return v;
    }

    synchronized int size() {
        return list.size();
    }
}