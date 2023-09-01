package Excepciones;
import java.util.Scanner;

public class RangoNumerico {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese un número entre 1 y 100:");
            int nroUno = scanner.nextInt();

            if (nroUno < 1 || nroUno > 100) {
                throw new IllegalArgumentException("El número está fuera del rango permitido (1-100)");
            }

            System.out.println("Ingrese otro número:");
            int nroDos = scanner.nextInt();

            int resultado = nroUno / nroDos;
            System.out.println("Resultado: " + resultado);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede dividir por ZERO!");
        } catch (Exception e) {
            System.out.println("Error: Ingrese un número válido.");
        } finally {
            scanner.close();
        }

        System.out.println("Fin del programa");
    }
}
