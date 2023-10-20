public class DispersedMatrix {
 
    public Triplet triplet;
    public Form1 form1;
    public Form2 form2;

    public DispersedMatrix(int[][] matrix) {
        triplet = new Triplet(matrix);
        form1 = new Form1(matrix);
        form2 = new Form2(matrix);
    }

    public String showAllForms() {
        return "Triplet: \n" + triplet.showTriplet() + "\n\nForm1: \n" + form1.showForm1() + "\n\nForm2: \n" + form2.showForm2ByRows();
    }

    public void adittion(DispersedMatrix matrix) {
        triplet.addition(matrix.triplet);
        form1.addition(matrix.form1);
        form2.addition(matrix.form2);
    }

    public void multiply(DispersedMatrix matrix) {
        triplet.multiply(matrix.triplet);
        form1.multiply(matrix.form1);
        form2.multiply(matrix.form2);
    }

    public void additionRows() {
        triplet.additionRows();
        form1.additionRows();
        form2.additionRows();
    }

    public void additionColumns() {
        triplet.additionColumns();
        form1.additionColumns();
        form2.additionColumns();
    }

    public void insert(int row, int column, int d) {
        triplet.insert(row, column, d);
        form1.insert(row, column, d);
        form2.insert(row, column, d);
    }
    

    public void deleteByNumber(int d) {
        triplet.deleteByNumber(d);
        form1.deleteByNumber(d);
        form2.deleteByNumber(d);
    }

    public void deleteByPosition(int row, int columnm) {
        triplet.deleteByPosition(row, columnm);
        form1.deleteByPosition(row, columnm);
        form2.deleteByPosition(row, columnm);
    }
}
