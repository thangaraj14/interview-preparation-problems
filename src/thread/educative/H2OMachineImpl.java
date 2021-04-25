package thread.educative;

import java.util.Arrays;

import java.util.Collections;

class H2OMachine {

    final Object sync;
    String[] molecule;
    int count;

    public H2OMachine() {
        molecule = new String[3];
        count = 0;
        sync = new Object();
    }

    public void hydrogenAtom() {

        synchronized (sync) {
            System.out.println(System.currentTimeMillis() + "-->" + this.sync.hashCode());
            // if 2 hydrogen atoms already exist
            while (Collections.frequency(Arrays.asList(molecule), "H") == 2) {
                try {
                    sync.wait();
                } catch (Exception e) {
                }
            }
            molecule[count] = "H";
            count++;
            // if molecule is complete, then exit.
            if (count == 3) {
                for (String element : molecule) {
                    System.out.print(element);
                }
                Arrays.fill(molecule, null);
                count = 0;
            }
            sync.notifyAll();
        }
    }

    public void oxygenAtom() {

        synchronized (sync) {
            System.out.println(System.currentTimeMillis() + "-->" + this.sync.hashCode());
            // if 1 oxygen atom already exists
            while (Collections.frequency(Arrays.asList(molecule), "O") == 1) {
                try {
                    sync.wait();
                } catch (Exception e) {
                }
            }
            molecule[count] = "O";
            count++;
            // if molecule is complete, then exit.
            if (count == 3) {
                for (String element : molecule) {
                    System.out.print(element);
                }
                Arrays.fill(molecule, null);
                count = 0;
            }
            sync.notifyAll();
        }
    }
}

public class H2OMachineImpl {

    public static void main(String[] args) {
        H2OMachine molecule = new H2OMachine();
        Thread t1 = new Thread(molecule::oxygenAtom);
        Thread t2 = new Thread(molecule::oxygenAtom);
        Thread t3 = new Thread(molecule::hydrogenAtom);
        Thread t4 = new Thread(molecule::hydrogenAtom);
        t4.start();
        t2.start();
        t1.start();
        t3.start();
    }
}