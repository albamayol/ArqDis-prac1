import java.util.LinkedList;

public class exercici3/* implements Runnable*/{
    private final LinkedList<Integer> linkedList;
    private final int target;
    private int foundBy;

    public void setFoundBy(int num) {
        this.foundBy = (this.foundBy == 0) ? num : this.foundBy;
    }

    public exercici3(int[] array, int target) {
        this.linkedList = new LinkedList<>();
        for (int j : array) {
            this.linkedList.add(j);
        }
        this.target = target;
        this.foundBy = 0;   //if target not found will be 0, so we can check afterwards

        Thread thread1 = new Thread(this::searchBeginning);
        Thread thread2 = new Thread(this::searchEnd);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Target found by thread nº: " + this.foundBy + "\n");
    }

    public void searchBeginning() {
        for (Integer num : this.linkedList) {
            if (num == this.target) {
                setFoundBy(1);
            }
        }
    }

    public void searchEnd() {
        int i = this.linkedList.size() - 1;
        while (i >= 0) {
            if (this.linkedList.get(i) == this.target) {
                setFoundBy(2);
            }
            i--;
        }
    }

    /*@Override
    public void run() {
        switch (this.startingPoint) {
            case 1: //search from the beginning
                searchBeginning();
                break;
            case 2: //search from the end
                searchEnd();
                break;
            default:
                break;
        }
        System.out.println("found by: " + this.foundBy + "\n");
    }*/
}
