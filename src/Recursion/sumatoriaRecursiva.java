package Recursion;
import java.util.Scanner;
//Sumatoria utilizando recursividad
public class sumatoriaRecursiva {
    public static int calcularSumatoriaRecursiva(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + calcularSumatoriaRecursiva(n - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("Ingrese un numero entero positivo");
        Scanner scanner= new Scanner(System.in);
        int numero = scanner.nextInt();
        int sumatoria = calcularSumatoriaRecursiva(numero);

        System.out.println("La sumatoria de los números enteros desde 1 hasta " + numero + " es " + sumatoria);
    }
}
