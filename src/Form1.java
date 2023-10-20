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
        
        // STEP 1
        // create head registers.
        step1(matrix);

        //STEP 2
        // create nodes for head registers.
        step2(matrix);

        //STEP 3
        // Link nodes by column
        step3();
        
    }

    private void step1(int[][] matrix) {
        // find max
        int max = matrix.length > matrix[0].length ? matrix.length : matrix[0].length;
        // rows and columns
        int rows = matrix.length, columns = matrix[0].length;

        head = new Node(rows, columns, 0);
        Node p = head;
        for (int i = 0; i < max; i++) {
            p.setNextNode(new Node(i, i, 0)); 
            p = p.getNextNode();
            
            p.setNextColumn(p);
            p.setNextRow(p);
        }
        p.setNextNode(head);
    }

    private void step2(int[][] matrix) {
        Node p = head.getNextNode();
        Node q;

        // iterate de matrix
        for (int i = 0; i < head.getRow(); i++) { // iterate rows
            q = p;
            for (int j = 0; j < head.getColumn(); j++) { // iterate columns
                if (matrix[i][j] != 0) { // add a node
                    q.setNextRow(new Node(i, j, matrix[i][j]));
                    q = q.getNextRow();
                }
            }
            
            q.setNextRow(p);
            p = p.getNextNode();
        } 
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

        Form1 C = new Form1(new int[head.getRow()][head.getColumn()]);

        Node ap = head.getNextNode(), aq;
        Node bp = B.getHead().getNextNode(), bq, search; 
        
        while (ap != head && bp != B.getHead()) {

            aq = ap.getNextRow(); bq = bp.getNextRow();

            while (aq != ap || bq != bp) {

                if (bq == bp) { // bq ended
                    C.appendEndRows(aq.getRow(), aq.getColumn(), aq.getData());
                    aq = aq.getNextRow();

                } else if (aq == ap) { // aq ended
                    C.appendEndRows(bq.getRow(), bq.getColumn(), bq.getData());
                    bq = bq.getNextRow();

                } else if (aq.getColumn() == bq.getColumn()) {
                    C.appendEndRows(aq.getRow(), aq.getColumn(), aq.getData() + bq.getData());
                    aq = aq.getNextRow();
                    bq = bq.getNextRow();

                } else if (aq.getColumn() < bq.getColumn()) {
                    // search aq element in B
                    search = aq != ap ? B.searchByPos(aq.getRow(), aq.getColumn()) : null;
                    if ( search != null) {
                        C.appendEndRows(aq.getRow(), aq.getColumn(), aq.getData() + search.getData());
                    } else {
                        C.appendEndRows(aq.getRow(), aq.getColumn(), aq.getData());
                    }
                    aq = aq.getNextRow();

                } else {   
                    // search bq element in A
                    search = bq != bp ? searchByPos(bq.getRow(), bq.getColumn()) : null;
                    if ( search != null ) {// aq search
                        C.appendEndRows(bq.getRow(), bq.getColumn(), bq.getData() + search.getData());
                    } else {
                        C.appendEndRows(bq.getRow(), bq.getColumn(), bq.getData());
                    }
                    bq = bq.getNextRow();
                }

            }

            if (ap != head) ap = ap.getNextNode();
            if (ap != B.getHead()) bp = bp.getNextNode();
        }
        head = C.getHead();
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

    public void multiply(Form1 B) {

        Form1 C;
        if (head.getColumn() == B.getHead().getRow()){
            C = new Form1(new int[head.getRow()][B.getHead().getColumn()]);

        } else if (head.getRow() == B.getHead().getColumn() ) {
            C = new Form1(new int[B.getHead().getRow()][head.getColumn()]);

        } else return;

        int i = 0, j = 0, k=0, sum, adata, bdata;
        Node asearch, bsearch;
        while (i < head.getRow()) {
            j = 0;
            while (j < B.getHead().getColumn()) {
                k=0;
                sum = 0;
                while (k < head.getColumn() ) {
                    asearch = searchByPos(i, k);
                    bsearch = B.searchByPos(k, j);

                    adata = asearch != null ? asearch.getData() : 0;
                    bdata = bsearch != null ? bsearch.getData() : 0;
                    sum += adata*bdata;

                    k++;
                }
                if (sum != 0) C.appendEndRows(i, j, sum);
                j++;
            }
            i++;
        }
        head = C.getHead();
        step3();
    }

    // Editing Methods

    public void insert(int row, int column, int d) {
        Node p = head.getNextNode();
        // Node pAnt = p;

        Node newNode = new Node(row, column, d);

        if (searchByPos(row, column) != null) {
            System.out.println("Ya existe un dato en esta posición.");
        } else {
            
            while (p != null) {
                if (p.getRow() == row) {
                    if (p.getNextRow().getColumn() == column) {
                        p.setNextNode(newNode);

                        break;
                    }
                    
                }
                // pAnt = p;
                p = p.getNextRow();
                
                
                if (p == head) {
                    p = p.getNextNode();
                    // pAnt = p;
                }
            
            }
        }
    }

    public void deleteByNumber(int d) {
        Node p = head;
        Node q = head;
        Node qAnt = head;

        p = p.getNextNode();
        q = p.getNextRow();
        qAnt = q;
        
        if (head != null) {
            do {
                if (q.getData() == d) {
                    qAnt.setNextRow(q.getNextRow());
                    q.setData(0);
                    q.setColumn(-1);
                    q.setRow(-1);
                    step3();
                    System.out.println("Eliminado");
                    break;
                }

                qAnt = q;
                q = q.getNextRow();
                if (p == q) {
                    qAnt = p.getNextNode();
                    p = p.getNextNode();
                    q = p.getNextRow();
                }
            } while (p != q);
        }
    }

    public void deleteByPosition(int row, int column) {

    }

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

    private Node searchByPos(int row, int column) {
        Node p = head.getNextNode(), q;

        while (p != head) {
            q = p.getNextRow();
            while (q != p) {
                if (q.getRow() == row && q.getColumn() == column) {
                    return q;
                }
                q = q.getNextRow();
            }
            p = p.getNextNode();

        }

        return null;
    }

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

    private void appendEndRows(int row, int column, int d) { 
        if (head != null && (row >= 0 && row < head.getRow()) && (column >= 0 && column < head.getColumn())) {

            Node p = head.getNextNode(), q = null;
            while (p.getRow() != row) p = p.getNextNode();

            q = p.getNextRow();
            while (q.getNextRow() != p) q = q.getNextRow();

            Node x = new Node(row, column, d);
            x.setNextRow(p);
            q.setNextRow(x);
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
