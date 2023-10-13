public class Form1 {

    // [=================== Attributes ===================]
    private Node head;


    // constructors
    public Form1() {
        head = null;
    }

    public Form1(int matrix[][]) {
        createForm1(matrix);
    }

    // [====================== Methods ======================]

    private void createForm1(int[][] matrix) {
        // find max
        int max = matrix.length > matrix[0].length ? matrix.length : matrix[0].length;
        // rows and columns
        int rows = matrix.length, columns = matrix[0].length;

        // STEP 1
        // create head registers.
        head = new Node(rows, columns, 0);
        Node p = head;
        for (int i = 0; i < rows; i++) {
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
                    q.setNextRow(new Node(i, j, matrix[i][j]));
                    q = q.getNextRow();
                }
            }
            
            q.setNextRow(p);
        } 

        //STEP 3
        // Link nodes by column
        Node RC = head.getNextNode(),a = RC;
        p = head.getNextNode(); q = p.getNextRow();
        
        while(RC != head){
            a = RC;
            p = head.getNextNode();
            while(p != head){
                q = p.getNextRow();
                while(q != p && q != null){
                    if(q.getColumn() == RC.getColumn()){
                        a.setNextColumn(q);
                        a = a.getNextColumn();
                    }
                    q = q.getNextRow();
                }
                p = p.getNextNode();
            }
            a.setNextColumn(RC);
            RC = RC.getNextNode();
        }
    }


    // [====================== Utility ======================]
    // show triplet methods
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

}
