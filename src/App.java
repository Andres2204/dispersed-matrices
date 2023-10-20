import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static Scanner read = new Scanner(System.in);
    static ArrayList<DispersedMatrix> matrix = new ArrayList<DispersedMatrix>();
    static int m = 0;

    public static void main(String[] args) throws Exception {
        // int[][] matrix = {
        // {8,3,0},
        // {0,0,1},
        // {0,2,0},
        // {19,0,11},
        // {0,0,6},
        // };

        int[][] m1 = {
                { 10, 13, 7 },
                { 0, 0, 5 },
                { 0, 21, 41 },
                { 2, 0, 0 }
        };

        int[][] m1_2 = {
                { 10, 13, 7, 0 },
                { 0, 5, 0, 21 },
                { 41, 2, 0, 0 }
        };

        int[][] m2 = { { 0, 1 }, { 1, 0 } };

        int[][] m3 = {
                { 0, 10, 0 }, // 10, 10, 7
                { 1, 1, 0 }, // 1, 1, 5
                { 2, 21, 0 }, // 2, 42, 41
                { 2, 0, 1 } // 4, 0 , 1
        };

        matrix.add(new DispersedMatrix(m1));
        matrix.add(new DispersedMatrix(m1_2));
        matrix.add(new DispersedMatrix(m2));
        matrix.add(new DispersedMatrix(m3));

        int opt = 0;
        int mathOpt;
        int auxM;
        int row = 0, column = 0, d = 0;

        while (true) {

            System.out.println("\n\n" + menu());
            opt = read.nextInt();

            switch (opt) {

                case 1:

                    do {
                        System.out.print("\n Desea ingresar un tamaño especifico? (0: si, 1: no) ");
                        mathOpt = read.nextInt();

                    } while (mathOpt != 1 && mathOpt != 0);

                    if (mathOpt == 0) {
                        do {
                            System.out.print("\nIngresar filas (mayor que 0): ");
                            row = read.nextInt();

                            System.out.print("\nIngresar columnas (mayor que 0): ");
                            column = read.nextInt();
                        } while (row < 1 || column < 1);
                        matrix.add(new DispersedMatrix(getRandomMatrix(row, column)));
                        m = matrix.size() - 1;

                    } else if (mathOpt == 1) {
                        matrix.add(new DispersedMatrix(getRandomMatrix()));
                        m = matrix.size() - 1;
                        
                    } else
                        System.out.println("Opcion invalida");

                    break;

                case 2:
                    System.out.println("\n" + matrix.get(m).showAllForms());

                    break;

                case 3:
                    do {
                        System.out.print("\nQue Operacion desea hacer: " +
                                "\n1. Suma entre matrices." +
                                "\n2. Multiplicacion entre matrices" +
                                "\n3. Mostrar la suma de las filas." +
                                "\n4. Mostrar la suma de las columnas." +
                                "\nOpcion: ");
                        mathOpt = read.nextInt();

                    } while (mathOpt < 1 || mathOpt > 4);

                    if (mathOpt == 1) {
                        for (int i = 0; i < matrix.size(); i++) {
                            System.out.println((i + 1) + ". " + matrix.get(i));
                        }
                        System.out.print("Seleccionar matriz a usar: ");
                        auxM = read.nextInt() - 1;
                        matrix.get(m).adittion(matrix.get(auxM));

                    } else if (mathOpt == 2) {
                        for (int i = 0; i < matrix.size(); i++) {
                            System.out.println((i + 1) + ". " + matrix.get(i));
                        }
                        System.out.print("Seleccionar matriz a usar: ");
                        auxM = read.nextInt() - 1;
                        matrix.get(m).multiply(matrix.get(auxM));

                    } else if (mathOpt == 3) {
                        matrix.get(m).additionRows();
                    } else if (mathOpt == 4) {
                        matrix.get(m).additionColumns();
                        ;
                    } else
                        System.out.println("Opcion imposible.");

                    break;

                case 4:
                    do {
                        System.out.print("\nIngresar fila: ");
                        row = read.nextInt();

                        System.out.print("\nIngresar columna: ");
                        column = read.nextInt();
                    } while (row < 1 || column < 1);

                    System.out.print("\nIngresar dato: ");
                    d = read.nextInt();

                    matrix.get(m).form2.insert(row, column, d);

                    break;

                case 5:
                    System.out.print("\nIngresar dato: ");
                    d = read.nextInt();

                    matrix.get(m).deleteByNumber(d);
                    break;

                case 6:
                    do {
                        System.out.print("\nIngresar filas (mayor que 0): ");
                        row = read.nextInt();

                        System.out.print("\nIngresar columnas (mayor que 0): ");
                        column = read.nextInt();
                    } while (row < 1 || column < 1);

                    matrix.get(m).deleteByPosition(row, column);

                    break;

                case 7:
                    for (int i = 0; i < matrix.size(); i++) {
                        System.out.println((i + 1) + ". " + matrix.get(i));
                    }
                    System.out.print("Seleccionar matriz a usar: ");
                    m = read.nextInt() - 1;
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        }

    }

    public static String menu() {
        return ("Menu de polinomios." +
                state() +
                "\n1. Generar una matriz aleatoria" +
                "\n2. Mostrar matriz en todas sus formas." +
                "\n3. Operaciones Matematicas entre matrices." +
                "\n4. Insertar un dato." +
                "\n5. Eliminar por datos." +
                "\n6. Eliminar por posicion" +
                "\n7. Seleccionar matriz." +
                "\n0. Salir." +
                "\nOpcion: ");
    }

    public static String state() {
        return "\nMatriz Selecionada: " + (m + 1) +
                "\nNumero de matrices: " + matrix.size();
    }

    public static int[][] getRandomMatrix() {
        return getRandomMatrix(0, 0);
    }

    public static int[][] getRandomMatrix(int n, int m) {
        if (n == 0) {
            do {
                n = (int) Math.floor(Math.random() * 3 + 2);
            } while (n == 0);
        }
        if (m == 0) {
            do {
                n = (int) Math.floor(Math.random() * 3 + 2);
            } while (m == 0);
        }

        System.out.println(n + "x" + m);
        int Mat[][] = new int[n][m];

        for (int i = 0; i <= ((n * m) / 2) + 1; i++) {
            Mat[(int) Math.floor(Math.random() * n)][(int) Math.floor(Math.random() * m)] = (int) Math
                    .floor(Math.random() * 100 + 1);
        }

        for (int i = 0; i < Mat.length; i++) {
            for (int j = 0; j < Mat[i].length; j++) {
                System.out.print(Mat[i][j] + " ");
            }
            System.out.println(); // Salto de línea al final de cada fila
        }
        System.out.println();

        return Mat;
    }

}
