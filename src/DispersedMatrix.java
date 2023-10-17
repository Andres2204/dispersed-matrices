public class DispersedMatrix {
 
    public Triplet triplet;
    public Form1 form1;
    public Form2 form2;

    public DispersedMatrix(int[][] matrix) {
        triplet = new Triplet(matrix);
        form1 = new Form1(matrix);
        form2 = new Form2(matrix);
    }
    
}
