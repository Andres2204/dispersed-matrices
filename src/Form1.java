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
        step3();
        
    }

    private void step3() {
        Node RC = head.getNextNode(), a;
        Node p = head.getNextNode(), q = p.getNextRow();
        
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

    public void addition(Form1 B) {

        if (head.getRow() != B.getHead().getRow() || head.getColumn() != B.getHead().getColumn()) return;

        Node ap = head.getNextNode(), bp = B.getHead().getNextNode();
        Node aq = null, bq = null;
        Node auxC = null;

        while (ap != head && bp != B.getHead()) {

            aq = ap.getNextRow(); bq = bp.getNextRow();

            System.out.println("\n-----------");

            while (aq != ap || bq != bp) {

                System.out.print(aq.getColumn() +" , " +bq.getColumn());

                if ((aq != ap && bq != bp) && (aq.getColumn() == bq.getColumn())) {
                    aq.setData(aq.getData()+bq.getData());
                    System.out.print("  1");
                }

                if (aq == ap && bq != bp) { // only aq endend

                    System.out.print("  2");
                    aq = aq.getNextRow();
                    while (aq.getNextRow() != ap) {
                        aq = aq.getNextRow();
                    }

                    Node x = new Node(bq.getRow(), bq.getColumn(), bq.getData());
                    x.setNextRow(aq.getNextRow());
                    aq.setNextRow(x);
                    aq = aq.getNextRow();

                } else if (bq != bp && bq.getColumn() == 0 && aq.getColumn() != 0) {

                    System.out.print("  3");
                    Node x = new Node(bq.getRow(), bq.getColumn(), bq.getData());
                    x.setNextRow(ap.getNextRow());
                    ap.setNextRow(x);
                                
                } else if ((aq != ap && bq != bp) && (aq.getColumn() != bq.getColumn())) {
                    if (aq.getColumn() < bq.getColumn()) {
                        System.out.print("  4");
                        // buscar en A
                        auxC = aq;
                        while (auxC != ap && auxC.getColumn() < bq.getColumn()) {
                            auxC = auxC.getNextRow();
                        }

                        if (auxC == ap || auxC.getColumn() > bq.getColumn()) {
                            Node x = new Node(bq.getRow(), bq.getColumn(), bq.getData());
                            x.setNextRow(aq.getNextRow());
                            aq.setNextRow(x);
                            aq = aq.getNextRow();
                        }
                        if (auxC.getColumn() == bq.getColumn()) auxC.setData(bq.getData()+auxC.getData());


                    } else if (aq.getColumn() > bq.getColumn()) {
                        System.out.print("  5");
                        //buscar en B
                        auxC = bq;
                        while (auxC != bp && auxC.getColumn() < aq.getColumn()) {
                            auxC = auxC.getNextRow();
                        }

                        if (auxC == bp || auxC.getColumn() > aq.getColumn()) {
                            Node x = new Node(bq.getRow(), bq.getColumn(), bq.getData());
                            x.setNextRow(aq.getNextRow());
                            aq.setNextRow(x);
                            aq = aq.getNextRow();
                        };
                        if (auxC.getColumn() == aq.getColumn()) auxC.setData(aq.getData()+auxC.getData());
                    }
                }

                if (aq != ap) aq = aq.getNextRow();
                if (bq != bp) bq = bq.getNextRow();
                System.out.println();
            }

            if (ap != head) ap = ap.getNextNode();
            if (ap != B.getHead()) bp = bp.getNextNode();
        }
        // appendSortRows(head.getNextNode(), 0, 1, 10);

        step3();
    }

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

    // Others

    private int rowSize(Node start) {
        if(!isEmptyRow(start)) {
            int c = 0;
            Node p = start.getNextRow();
            while (p != start) {
                c++;
                p = p.getNextRow();
            }
            return c;
        }
        return 0;
    }

    private void appendSortRows(Node start, int row, int column, int d) { 
            Node p = start.getNextRow(), q = p ,x = new Node(row, column, d);
            if (!isEmptyRow(start)) {
    
                // Loop through the list until some condition is met
                while (p != start) {
                    if (p.getColumn() > q.getColumn()) {
                        break; // exit to while
                    }
                    q = p;
                    p = p.getNextRow();
                }; 
    
                if (p == start) {
                    x.setNextRow(start.getNextRow());
                    start.setNextRow(x);
                } else {
                    q.setNextRow(p);
                    p.setNextRow(x);
                    x.setNextRow(start);
                }
    
            } else {
                start.setNextRow(x);
                x.setNextRow(start);
            }
    }

    public boolean isEmptyRow(Node start) {
        return start.getNextRow() == start ? true : false;
    }

    // [====================== Getters and Setters ======================]

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

}
