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
        // System.out.println(matrix.length);
        // System.out.println(matrix[0].length);

        int n = 0;
        int m = 0;
        do {
            n = (int) Math.floor(Math.random() * 3+ 2);
            m = (int) Math.floor(Math.random() * 3 + 2);
        } while (n == 0 && m == 0);

        System.out.println(n+"x"+m);
        System.out.println("");
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
        }

        DispersedMatrix ma1 = new DispersedMatrix(Mat);
        System.out.println(ma1.triplet.showTriplet());

        System.out.println(ma1.form1.showForm1());
        
        System.out.println(ma1.form2.showForm2ByRows());
        System.out.println(ma1.form2.showForm2ByColumns());

        // DispersedMatrix ma2 = new DispersedMatrix(testMatrix);
        // ma2.triplet.showTriplet();
        // System.out.println();

        // ma1.triplet.addition(ma1.triplet);
        // ma1.triplet.showTriplet();
        // System.out.println();

    }
}
