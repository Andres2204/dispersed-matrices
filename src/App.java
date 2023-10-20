//import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
        // int[][] matrix = {
        //     {8,3,0},
        //     {0,0,1},
        //     {0,2,0},
        //     {19,0,11},
        //     {0,0,6},
        // };

        int[][] matrix = {
            {10,13,7},
            {0,0,5},
            {0,21,41},
            {2,0,0}
        };

        int[][] testMatrix = {
            {10,13,7,0},
            {0,5,0,21},
            {41,2,0,0}
        };

        int[][] testMatrix2 = {{0,1},{1,0}};

        int[][] testMatrix3 = {
            {0,10,0}, // 10, 10, 7
            {1,1,0},    // 1, 1, 5
            {2,21,0}, // 2, 42, 41
            {2,0,1} // 4, 0 , 1
        };

        DispersedMatrix ma1 = new DispersedMatrix(matrix);
        // System.out.println("Matriz 1: "); 
        // ma1.triplet.showTriplet();
        // System.out.println();

        DispersedMatrix ma2 = new DispersedMatrix(testMatrix);
        // System.out.println("Matriz 2: "); 
        // ma2.triplet.showTriplet();
        // System.out.println();

        DispersedMatrix ma3 = new DispersedMatrix(getRandomMatrix(4, 4));

        // System.out.println("Multiplicacion ");
        // ma1.triplet.multiply(ma2.triplet);
        // System.out.println(ma1.triplet.showTriplet());
        // System.out.println("\n\n");
        // System.out.println("añadir: \n");
        // ma1.triplet.insert(3, 2, -1);

        ma1.triplet.deleteByPosition(0, 2);
        

        // DispersedMatrix ma1 = new DispersedMatrix(getRandomMatrix());

        // System.out.println(ma1.triplet.showTriplet());
        // System.out.println("triplet AddittionRows -> "+ Arrays.toString(ma1.triplet.additionRows()));
        // System.out.println("triplet AddittionColumns -> "+ Arrays.toString(ma1.triplet.additionColumns()));

        System.out.println("\n ShowForm1 f1 -> "+ma1.form1.showForm1());
        System.out.println("\n ShowForm1 f2 -> "+ma2.form1.showForm1());
        ma1.form1.multiply(ma2.form1);
        System.out.println("\n ShowFormMultiply -> "+ma1.form1.showForm1());

        // System.out.println(ma1.form1.showForm1Columns());
        // System.out.print("\nf1 AdditionRows -> "+Arrays.toString(ma1.form1.additionRows()));
        // System.out.print("\nf1 AdditionColumns -> "+Arrays.toString(ma1.form1.additionColumns()));

        // System.out.println("\n\nf2 Rows -> "+ma1.form2.showForm2ByRows());
        // System.out.println("\nf2 Columns -> "+ma1.form2.showForm2ByColumns());
        // System.out.print("\nf2 AdditionRows -> "+Arrays.toString(ma1.form2.additionRows()));
        // System.out.print("\nf2 AdditionColumns -> "+Arrays.toString(ma1.form2.additionColumns()));

        // DispersedMatrix ma2 = new DispersedMatrix(testMatrix);
        // ma2.triplet.showTriplet();
        // System.out.println();

        // ma1.triplet.addition(ma1.triplet);
        // ma1.triplet.showTriplet();
        // System.out.println();

        /*
        80 0 29 33
        0 81 0 16
        87 0 0 81 

        62 0 0 
        20 79 44
        */

    }

    public static void getRandomMatrix() {
        getRandomMatrix(0, 0);
    }
    public static int[][] getRandomMatrix(int n, int m) {
        if (n == 0) {
            do {
                n = (int) Math.floor(Math.random() * 3 + 2);
            } while (n == 0);
        }
        if (m == 0) {
            do {
                n = (int) Math.floor(Math.random() * 3 + 2);
            } while (m == 0);
        }

        System.out.println(n+"x"+m);
        int Mat[][] = new int[n][m];

        for (int i = 0; i <= ((n * m) / 2) + 1; i++) {
            Mat[(int) Math.floor(Math.random() * n)][(int) Math.floor(Math.random() * m)] = (int) Math
                    .floor(Math.random() * 100 + 1);
        }

        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[i].length; j++) {
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println(); // Salto de línea al final de cada fila
        } System.out.println();

        return Mat;
    }

}
