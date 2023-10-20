public class Form2 {
    // [=================== Attributes ===================]
    private Node head;

    // Contructor methods

    public Form2() {
        head = null;
    }

    public Form2(int[][] matrix) {

        // create simple list of a sparce matrix
        head = new Node(matrix.length, matrix[0].length, 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    appendToEndRow(i, j, matrix[i][j]);
                }
            }
        }

        // link de columns
        step2();
    }

    private void step2() {
        Node p = head.getNextRow();
        Node q = head;
        int i = 0;

        while (i <= head.getColumn()) {
            if (p == null) {
                p = head;
                i++;
            }
            if (p.getColumn() == i && p != head) {
                q.setNextColumn(p);
                q = p;
            }
            p = p.getNextRow();
        }
    }

    // [====================== Methods ======================]

    // Math Methods

    public int[] additionRows() {
        int[] rowsResult = new int[head.getRow()];
        Node p = head.getNextRow();

        while (p != null) {
            rowsResult[p.getRow()] += p.getData();
            p = p.getNextRow();
        }

        return rowsResult;
    }

    public int[] additionColumns() {
        int[] columnsResult = new int[head.getColumn()];
        Node p = head.getNextColumn();

        while (p != null) {
            columnsResult[p.getColumn()] += p.getData();
            p = p.getNextColumn();
        }
        return columnsResult;
    }

    public void addition(Form2 B) {

        if (head.getRow() != B.getHead().getRow() || head.getColumn() != B.getHead().getColumn())
            return;

        Form2 C = new Form2(new int[head.getRow()][head.getColumn()]);

        int i = 0, j = 0, sum, adata, bdata;
        Node asearch, bsearch;
        while (i < head.getRow()) {
            j = 0;
            while (j < head.getColumn()) { 
                sum = 0;
                asearch = searchByPos(i, j);
                bsearch = B.searchByPos(i, j);

                adata = asearch != null ? asearch.getData() : 0;
                bdata = bsearch != null ? bsearch.getData() : 0;
                sum = adata + bdata;

                if (sum != 0) C.appendToEndRow(i, j, sum);
                j++;
            }
            i++;
        }
        head = C.getHead();
        step2();
    }

    public void multiply(Form2 B) {
        Form2 C;
        if (head.getColumn() == B.getHead().getRow()) {
            C = new Form2(new int[head.getRow()][B.getHead().getColumn()]);

        } else if (head.getRow() == B.getHead().getColumn()) {
            C = new Form2(new int[B.getHead().getRow()][head.getColumn()]);

        } else
            return;

        int i = 0, j = 0, k = 0, sum, adata, bdata;
        Node asearch, bsearch;
        while (i < head.getRow()) {
            j = 0;
            while (j < B.getHead().getColumn()) {
                k = 0;
                sum = 0;
                while (k < head.getColumn()) {
                    asearch = searchByPos(i, k);
                    bsearch = B.searchByPos(k, j);

                    adata = asearch != null ? asearch.getData() : 0;
                    bdata = bsearch != null ? bsearch.getData() : 0;
                    sum += adata * bdata;

                    k++;
                }
                if (sum != 0)
                    C.appendToEndRow(i, j, sum);
                j++;
            }
            i++;
        }
        head = C.getHead();
        step2();
    }

    // Editing Methods

    public void insert(int row, int column, int d) {
    }

    public void deleteByNumber(int d) {

        Node p = head;
        Node q = head.getNextRow();
        Node qAnt = head;
        
        if (head != null) {
            do {
                if (q.getData() == d) {
                    
                    qAnt.setNextRow(q.getNextRow());
                    q.setData(0);
                    q.setColumn(-1);
                    q.setRow(-1);
                    
                    System.out.println("Eliminado.");
                    break;
                }
                qAnt = q;
                q = q.getNextRow();
                if (p == q) {
                    System.out.println("No se encontró el dato.");
                }

            } while (p != q);

        }
    }

    public void deleteByPosition(int row, int column) {
    }

    // [====================== Utility ======================]

    // Show Methods

    public String showForm2ByRows() {
        Node p = head;
        String nextText, output = "Is Empty";
        if (!isEmpty()) {
            output = "";
            while (p != null) {
                nextText = p.getNextRow() == null ? " / " : " ] R-> ";
                output += "[ " + p.getRow() + " | " + p.getColumn() + " | " + p.getData() + nextText;
                p = p.getNextRow();
            }
        }
        return output;
    }

    public String showForm2ByColumns() {
        Node p = head;
        String nextText, output = "Is Empty";
        if (!isEmpty()) {
            output = "";
            while (p != null) {
                nextText = p.getNextColumn() == null ? " / " : " ] C-> ";
                output += "[ " + p.getRow() + " | " + p.getColumn() + " | " + p.getData() + nextText;
                p = p.getNextColumn();
            }
        }
        return output;
    }

    // Others

    public void appendToEndRow(int row, int column, int d) { // append to the end of the list
        Node newNode = new Node(row, column, d), p = head;
        if (isEmpty()) {
            head = newNode;
        } else {
            while (p.getNextRow() != null) {
                p = p.getNextRow();
            }
            p.setNextRow(newNode);
        }
    }

    public int size() {
        if (!isEmpty()) {
            int items = 0;
            Node p = head.getNextRow();
            while (p != null) {
                p = p.getNextRow();
                items++;
            }
            return items;
        }
        return 0;
    }

    public boolean isEmpty() {
        return head == null ? true : false;
    }

    public Node searchByPos(int row, int column) {
        Node p = head;
        while (p != null) {
            if (p.getRow() == row && p.getColumn() == column) {
                return p;
            }
            p = p.getNextRow();
        }
        return null;
    }

    // [====================== Getters and Setters ======================]

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

}
