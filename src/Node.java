public class Node {
    
    // [=================== Attributes ===================]
    private int row, column, data;
    private Node nextColumn = null, nextRow = null, nextNode = null;

    public Node(int row, int column, int data) {
        this.row = row;
        this.column = column;
        this.data = data;
    }


    
    // [========== Getters and Setters ==========]
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getNextColumn() {
        return nextColumn;
    }
    public void setNextColumn(Node nextColumn) {
        this.nextColumn = nextColumn;
    }
    public Node getNextRow() {
        return nextRow;
    }
    public void setNextRow(Node nextRow) {
        this.nextRow = nextRow;
    }
    public Node getNextNode() {
        return nextNode;
    }
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
  
}
