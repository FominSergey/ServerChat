import static java.lang.Thread.sleep;

public class Car implements Runnable {
    public static boolean bstop = false;
    @Override
    public void run() {
        while (!bstop) {
            System.out.println(bstop);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
