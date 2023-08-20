package Recursion;

import java.util.Scanner;

public class calculadoraFactorial {
    public static int calcularFacRecursivo(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calcularFacRecursivo(n - 1);
        }
    }
    public static int calcularFacIterativo(int n) {
        if (n < 0) {
            System.out.println("El número debe ser positivo");
        }
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        System.out.println("Ingrese un numero entero positivo");
        Scanner scanner= new Scanner(System.in);
        int numero = scanner.nextInt();

        int factorialRecursivo = calculadoraFactorial.calcularFacRecursivo(numero);
        int factorialIterativo = calculadoraFactorial.calcularFacIterativo(numero);

        System.out.println("Factorial de " + numero + " (recursivo): " + factorialRecursivo);
        System.out.println("Factorial de " + numero + " (iterativo): " + factorialIterativo);
    }
}
