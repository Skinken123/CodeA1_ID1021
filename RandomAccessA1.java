//This is a file to test the speed of reading and writing from arrays, test the speed of searching through an array and
//test the speed of finding common elements between two arrays. 

import java.util.Random;

public class RandomAccessA1 {
    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600}; //array som ska accessas
        int loop = 10000; // så många gånger som array ska accessas vid ett random index 1 till n
        int k = 10000; // så många gånger benchmark ska köras
        int formatting = 1;
        String f = "";

        for(int n : sizes) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long total = 0;
            for (int i = 0; i < k; i++) {
                long t = bench(n, loop);
                if (t < min) min = t;
                if (t > max) max = t;
                total = total + t;
            }
            if (formatting < 4) {
                f = "  ";
            }
            if (4 < formatting && formatting < 8) {
                f = " ";
            }
            if (formatting > 7) {
                f = "";
            }
            formatting++;
            System.out.println("Array size: " + n + f + " min: " + ((double) min)/loop + " ns" + " max: " + ((double) max)/loop + " ns" + " avg: " + ((double) total)/loop/k + " ns");
            }
            //+ " avg: " + ((double) total)/loop/k + " ns" 
            
        //System.out.println(" avg: " + ((double) total)/loop/k + " ns");
        //System.out.println(" min: " + ((double) min)/loop + " ns");
        //System.out.println(" max: " + ((double) max)/loop + " ns");
    }

    //Stage 4
    public static int run(int[] array, int[] indx) {
        int sum = 0;
        for (int i = 0; i < indx.length ; i++) {
            sum = sum + array[indx[i]];
        }
        return sum;
        }

    public static long bench(int n, int loop) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i;
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) indx[i] = rnd.nextInt(n);
        int sum = 0;
        long t0 = System.nanoTime();
        run(array, indx);
        long t1 = System.nanoTime();
        return (t1 - t0);
        }
}