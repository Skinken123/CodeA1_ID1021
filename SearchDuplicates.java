import java.util.Random;

public class SearchDuplicates {
    public static void main(String[] args){
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400}; //array som ska accessas , 6400, 12800
        int k = 1000; // så många gånger benchmark ska köras
        int formatting = 1;
        String f = "";

        duplicates(1000); // för att "varma upp" datorn

        for(int n : sizes) {
            long min = Long.MAX_VALUE;
            long max = 0;
            long total = 0;
            for (int i = 0; i < k; i++) {
                long t = duplicates(n);
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
            System.out.println("Array size: " + n + f + " min: " + ((double) min)/k + " ns" + " max: " + ((double) max)/k + " ns" + " avg: " + ((double) total)/k/k + " ns");
            }
    }

    private static long duplicates(int n) {
        Random rnd = new Random();
        int[] array_a = new int[n]; // skapa en array "array_a" som är n element stor
        for (int i = 0; i < n; i++) {
            array_a[i] = rnd.nextInt(n*2); // slumpar ett tal mellan 0 och 2n och lägger in i arrayen "array_a" tills hela arrayen är fylld
        }

        int[] array_b = new int[n]; // skapa en array "array_b" som är n element stor
        for (int i = 0; i < n; i++) {
            array_b[i] = rnd.nextInt(n*2); // slumpar ett tal mellan 0 och 2n och lägger in i arrayen "array_b" tills hela arrayen är fylld
        }

        int sum = 0;
        long t0 = System.nanoTime();  // tar tiden innan loopen börjar
        for (int i = 0; i < n; i++) { // loopar "n" antal gånger, alltså lika många gånger som array_a är stor
            int key = array_a[i];    // skapar en ny variabel "key" som är lika med array_a[i] i varje iteration av loopen, "key" är det tal som ska sökas efter i arrayen "array_b"

            for (int j = 0; j < n; j++) { // loopar igenom arrayen "array_b" för att hitta "key"
                if (key == array_b[j]) { // om "key" är lika med array_b[j] så ökas sum med 1 och loopen bryts
                    sum++;
                    break;
                }
            }
        }
        long t1 = System.nanoTime(); // tar tiden efter loopen är klar
        return t1 - t0;
        }

}
