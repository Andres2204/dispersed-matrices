public class Form2 {
    private Node head;

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

}
