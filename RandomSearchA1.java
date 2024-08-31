import java.util.Random;

public class RandomSearchA1 {
    public static void main(String[] args) {

    }

    private static long search(int n, int loop) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n*2);
        }
        int[] keys = new int[n];
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n*2);
        }
        int sum = 0;
        long t0 = System.nanoTime();
        for (int i = 0; i < loop; i++) {
            int key = keys[i];
            for (int j = 0; j < n; j++) {
                if (key == array[j]) {
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime();
        return (t1 - t0);
    }
}
