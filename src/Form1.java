public class Form1 {

    // [=================== Attributes ===================]
    
    private Node head;

    // Constructors And Creation Methods

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
            
            p.setNextColumn(p);
            p.setNextRow(p);
        }
        p.setNextNode(head);

        //STEP 2
        // create nodes for head registers.
        p = head.getNextNode();
        Node q;

        // iterate de matrix
        for (int i = 0; i < rows; i++) { // iterate rows
            q = p;
            for (int j = 0; j < columns; j++) { // iterate columns
                if (matrix[i][j] != 0) { // add a node
                    q.setNextRow(new Node(i, j, matrix[i][j]));
                    q = q.getNextRow();
                }
            }
            
            q.setNextRow(p);
            p = p.getNextNode();
        } 

        //STEP 3
        // Link nodes by column
        Node RC = head.getNextNode(), a;
        p = head.getNextNode(); q = p.getNextRow();
        
        while(RC != head){
            a = RC;
            p = head.getNextNode();
            while(p != head){
                q = p.getNextRow();
                while(q != p && q != null){
                    if(q.getColumn() == RC.getColumn()){
                        a.setNextColumn(q);
                        a = q;
                    }
                    q = q.getNextRow();
                }
                p = p.getNextNode();
            }
            a.setNextColumn(RC);
            RC = RC.getNextNode();
        }
    }

    // [====================== Methods ======================]

    // Math methods

    public int[] additionRows() {
        int[] rowsResult = new int[head.getRow()];
        Node p = head.getNextNode(), q;
        while (p != head) {
            q = p.getNextRow();
            while (q != p) {
                rowsResult[q.getRow()] += q.getData();
                q = q.getNextRow();
            }
            p = p.getNextNode();
        }
        return rowsResult;
    }

    public int[] additionColumns() {
        int[] columnsResult = new int[head.getColumn()];
        Node p = head.getNextNode(), q;

        //int selectedColumn = 0;

        while (p != head) {
            q = p.getNextColumn();

            while (q != p) {
                columnsResult[q.getColumn()] += q.getData();
                q = q.getNextColumn();
            }

            p = p.getNextNode();
        }

        return columnsResult;
    }

    public void multiply(Form1 B) {}

    // Editing Methods

    public void insert(int row, int column, int d) {}

    public void deleteByNumber(int d) {}

    public void deleteByPosition(int row, int column) {}

    // [====================== Utility ======================]

    // Show Triplet Methods

    private String showList(Node start) {
        Node p = start;
        String nextText, output = "Is Empty";
        if (start != null) {
            output = "";
            if (p == head) {
                output += "[ " + p.getRow() + " | " + p.getColumn() + " ]";
                return output;
            }
            do {
                nextText = p.getNextRow() == null ? " / " : " ] R-> ";
                output += "[ " + p.getRow() + " | " + p.getColumn() + " | " + p.getData() + nextText;
                p = p.getNextRow();
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
                output += showList(p) + (p.getNextNode() != null ? "\nRC |\n" : "\nx"); 
                p = p.getNextNode();

            } while (p != head);
        }
        return output;
    }

    public String showForm1Columns() {
        String output = "Is Empty";
        if (head != null) {
            Node p = head.getNextNode(), q;
            output = "ShowForm1Columns -> ";
            while (p != head) {
                q = p.getNextColumn();
                while (q != p) {
                    output += "[" + q.getData() + "] ";
                    q = q.getNextColumn();
                }
                p = p.getNextNode();
            } 
        }
        return output;
    }

    // [====================== Getters and Setters ======================]

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

}
