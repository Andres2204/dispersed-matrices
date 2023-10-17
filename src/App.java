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
        // System.out.println(matrix.length);
        // System.out.println(matrix[0].length);

        DispersedMatrix ma1 = new DispersedMatrix(matrix);
        System.out.println("Matriz 1: "); 
        ma1.triplet.showTriplet();
        System.out.println();

        DispersedMatrix ma2 = new DispersedMatrix(testMatrix);
        System.out.println("Matriz 2: "); 
        ma2.triplet.showTriplet();
        System.out.println();

        ma1.triplet.multiply(ma2.triplet);
        ma1.triplet.showTriplet();
        System.out.println();
    }
}
