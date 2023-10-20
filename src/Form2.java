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
        Node p = head.getNextRow();
        Node q = head;
        int i = 0;

        while (i <= head.getColumn()) {
            if (p == null) {
                p = head;
                i++;
            }
            if(p.getColumn() == i  && p != head) {
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

    // Editing Methods

    public void insert(int row, int column, int d) {}

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

    public void deleteByPosition(int row, int column) {}

    // [====================== Utility ======================]

    // Show Methods

    public String showForm2ByRows() {
        Node p = head;
        String nextText, output = "Is Empty";
        if (!isEmpty()) {
            output = "";
            while (p!=null) {
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
            while (p!=null) {
                nextText = p.getNextColumn() == null ? " / " : " ] C-> ";
                output += "[ " + p.getRow() + " | " + p.getColumn() + " | " + p.getData() + nextText;
                p = p.getNextColumn();
            }
        }
        return output;
    }

    // Others

    public void appendToEndRow(int row, int column, int d) { // append to the end of the list
        Node newNode = new Node(row,column,d), p = head;
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
            int items=0;
            Node p = head.getNextRow();
            while (p!=null) {
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

    // [====================== Getters and Setters ======================]

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

}
