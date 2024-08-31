import java.util.Random;

public class RandomSearchA1 {
    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600}; //array som ska accessas
        int loop = 100; // så många gånger som vi ska leta efter en radom key i arrayen "array"
        int k = 1000; // så många gånger benchmark ska köras
        int formatting = 1;
        String f = "";

        search(1000, 10000); // kör metoden search för att förhindra att den första mätningen blir missvisande

        for(int n : sizes) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long total = 0;
            for (int i = 0; i < k; i++) {
                long t = search(n, loop);
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
    }

    //Metoden mäter hur lång tid det tar att söka efter ett slumpmässigt tal i en array, metoden söker efter ett tal "key" i arrayen "array", "loop" antal gånger
    private static long search(int n, int loop) {
        Random rnd = new Random();

        int[] array = new int[n]; // skapa en array "array" som är n element stor
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(n*2); // slumpar ett tal mellan 0 och 2n och lägger in i arrayen "array" tills hela arrayen är fylld
        }

        int[] keys = new int[loop]; // skapar en ny array "keys" som är "loop" element stor
        for (int k = 0; k < loop; k++) {
            keys[k] = rnd.nextInt(n*2); // slumpar ett tal mellan 0 och 2n och lägger in i arrayen "keys", detta görs "loop" antal gånger
        }

        int sum = 0;
        long t0 = System.nanoTime(); // tar tiden innan loopen börjar
        for (int i = 0; i < loop; i++) { // loopar "loop" antal gånger
            int key = keys[i];           // skapar en ny variabel "key" som är lika med keys[i] i varje iteration av loopen, "key" är det tal som ska sökas efter i arrayen "array"  
            for (int j = 0; j < n; j++) { // loopar igenom arrayen "array" för att hitta "key" 
                if (key == array[j]) {   // om "key" är lika med array[j] så ökas sum med 1 och loopen bryts
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime(); // tar tiden efter loopen är klar
        return (t1 - t0); // returnerar tiden det tog att köra loopen alltså att hitta en radom key i arrayen "array"
    }
}
