public class Form1 {

    // [=================== Attributes ===================]
    private Node head;

    public Form1() {
        head = null;
    }

    public Form1(int matrix[][]) {
        createForm1(matrix);
    }

    private void createForm1(int[][] matrix) {
        // find max
        int max = matrix.length > matrix[0].length ? matrix.length : matrix[0].length;

        // rows and columns
        int rows = matrix.length, columns = matrix[0].length;

        // STEP 1
        // create head registers.
        head = new Node(rows, columns, 0);
        Node p = head;
        for (int i = 0; i < max; i++) {
            p.setNextNode(new Node(i, i, 0)); 
            p = p.getNextNode();           
        }
        p.setNextNode(head);

        //STEP 2
        // create nodes for head registers.
        p = head;
        Node q = null;

        // iterate de matrix
        for (int i = 0; i < rows; i++) { // iterate rows
            p = p.getNextNode();
            q = p;
            for (int j = 0; j < columns; j++) { // iterate columns
                if (matrix[i][j] != 0) { // add a node
                    q.setNextColumn(new Node(i, j, matrix[i][j]));
                    q = q.getNextNode();
                }
            }
            q.setNextColumn(p);
        } 

        //STEP 3
        //
    }

    // [====================== Methods ======================]

    public String showList(Node start) {
        Node p = start;
        String nextText, output = "Is Empty";
        if (start != null) {
            output = "";
            do {
                nextText = p.getNextNode() == null ? " / " : " ] -> ";
                output += "[ " + p.getRow() + " - " + p.getColumn() + nextText;
                p = p.getNextNode();
            } while (p != start);
        }
        return output;
    }

    

    public String showForm1() {
        Node p = head;
        String output = "Is Empty";
        if (head != null) {
            output = "";
            do {
                p = p.getNextNode();
                output += showList(p) + "\n"; 

            } while (p != head);
        }
        return output;
    }

}
