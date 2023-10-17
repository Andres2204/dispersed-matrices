import java.util.ArrayList;
import java.util.List;

public class Triplet {
    // [=================== Attributes ===================]
    
    private int[][] triplet;

    // Constructor Methods

    public Triplet(int[][] matrix) {
        triplet = createTriplet(matrix, 0,0,1);
    }
    
    private int[][] createTriplet(int[][] matrix, int i, int j, int k) {
        //base case
        if (i == matrix.length-1 && j == matrix[0].length)  {
            triplet = new int[k][3];
            
            triplet[0][0] = matrix.length;
            triplet[0][1] = matrix[0].length;
            triplet[0][2] = k-1;
            
            return triplet;
        }
        
        if (j == matrix[0].length) {
            j = 0;
            i++;
        }

        if (matrix[i][j] != 0) {
            triplet = createTriplet(matrix, i, j+1, k+1);
    
            triplet[k][0] = i;
            triplet[k][1] = j;
            triplet[k][2] = matrix[i][j]; 
        } else {
            triplet = createTriplet(matrix, i, j+1, k);
        }
        return triplet;
    }

    // [====================== Methods ======================]

    // Math Methods

    public void addition(Triplet B) {
        // get B matrix
        int[][] btriplet = B.getTriplet();

        if (triplet[0][0] != btriplet[0][0] || triplet[0][1] != btriplet[0][1]) return;

        int[][] resTriplet = new int[triplet[0][2] + triplet[0][2]+1][3];
        int a=1,b=1, k=1;

        for  (int i = 0; i < triplet[0][0]; i++) {  
            for (int j = 0; j < triplet[0][1]; j++) {
                int sum = 0;
                a = 1; b = 1;

                while(a<=triplet[0][2] && b<= btriplet[0][2]) {
                    if ( a<=triplet[0][2] && triplet[a][0] == i && triplet[a][1] == j){
                        sum += triplet[a][2]; a = triplet[0][2] + 2;
                    } else a++;

                    if ( b<=btriplet[0][2] && btriplet[b][0] == i && btriplet[b][1] == j){
                        sum += btriplet[b][2]; b = btriplet[0][2] + 2;
                    } else b++;
                }
                if (sum != 0) {
                    resTriplet[k][0] = i;
                    resTriplet[k][1] = j;
                    resTriplet[k][2] = sum;
                    k++;
                }
            }
        }

        resTriplet[0][0] = triplet[0][0];
        resTriplet[0][1] = triplet[0][1];
        resTriplet[0][2] = k-1;

        triplet = resTriplet;
        resize();
    }

    public void multiply(Triplet b) {
        
        int[][] btriplet = b.getTriplet();

        int numRowsA = triplet[0][0];
        int numColsA = triplet[0][1];
        int numElemsA = triplet[0][2];

        int numRowsB = btriplet[0][0];
        int numColsB = btriplet[0][1];
        int numElemsB = btriplet[0][2];

        // Verificar si las matrices son compatibles para la multiplicación
        if (numColsA != numRowsB) {
            System.out.println("No se pueden multiplicar las matrices. Las dimensiones no son compatibles.");
            return;
        }

        // Inicializar matriz resultado
        int[][] resTriplet = new int[numRowsA * numColsB + 1][3]; // Asegúrate de tener suficiente espacio
        int resIndex = 1;
        boolean flag = false;

        // Realizar la multiplicación
        for (int i = 1; i <= numElemsA; i++) {
            int rowA = triplet[i][0];
            int colA = triplet[i][1];
            int valA = triplet[i][2];

            for (int j = 1; j <= numElemsB; j++) {
                int rowB = btriplet[j][0];
                int colB = btriplet[j][1];
                int valB = btriplet[j][2];

                if (colA == rowB) {
                    int newRow = rowA;
                    int newCol = colB;
                    int newVal = valA * valB;

                    for (int k = 1; k < resIndex; k++) {
                        if ((resTriplet[k][0] == newRow) && (resTriplet[k][1] == newCol)) {
                            resTriplet[k][2] += newVal;
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        flag = false;
                    } else {
                        resTriplet[resIndex][0] = newRow;
                        resTriplet[resIndex][1] = newCol;
                        resTriplet[resIndex][2] = newVal;
                    }

                    resIndex++;
                }
            }
        }

        resTriplet[0][0] = numRowsA;
        resTriplet[0][1] = numColsB;
        resTriplet[0][2] = resIndex - 1;

        triplet = resTriplet;

    }

    public int[] additionRows() {
        int[] rowsResult = new int[triplet[0][0]];

        int i = 1, selectedRow=0;
        
        while (i <= triplet[0][2]) {

            if (triplet[i][0] == selectedRow) {
                rowsResult[selectedRow] += triplet[i][2];
                i++;
            } else {
                selectedRow++;
            }

        };

        return rowsResult;
    }

    public int[] additionColumns() {
        int[] columnsResult = new int[triplet[0][1]];
        int i = 1, selectedColumn=0;
        while (selectedColumn <= triplet[0][1]) {
            if (i > triplet[0][2]) {
                i = 1;
                selectedColumn++;
            }
            if (triplet[i][1] == selectedColumn) {
                columnsResult[selectedColumn] += triplet[i][2];
            }
            i++;
        };
        
        return columnsResult;
    }
    
    // Edition Methods

    public void insert(int row, int column, int d) {}

    public void deleteByNumber(int d) {}

    public void deleteByPosition(int row, int column) {}

    // [====================== Utility ======================]

    private void resize() {
        int[][] resTriplet = new int[triplet[0][2]+1][3];
        for (int i = 0; i <= triplet[0][2]; i++) {
            resTriplet[i][0] = triplet[i][0];
            resTriplet[i][1] = triplet[i][1];
            resTriplet[i][2] = triplet[i][2];
        }     
        triplet = resTriplet;
    }

    public String showTriplet() {
        String s="";
        for (int i = 0; i < triplet.length; i++) {            
            for (int j = 0; j < triplet[0].length; j++) {
                s += "| " + triplet[i][j];
            }
            s += "\n----------\n";
        }
        return s;
    }

    // [====================== Getters and Setters ======================]

    public int[][] getTriplet() {
        return triplet;
    }

    public void setTriplet(int[][] triplet) {
        this.triplet = triplet;
    }
}