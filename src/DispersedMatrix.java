public class DispersedMatrix {
 
    public Triplet triplet;
    public Form1 form1;

    public DispersedMatrix(int[][] matrix) {
        triplet = new Triplet(matrix);
        form1 = new Form1(matrix);
    }
    
}
