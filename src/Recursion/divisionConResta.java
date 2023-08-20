//Realizar un programa
package Recursion;
//USANDO RECURSIVIDAD
/*public class divisionConResta {
    public static int divisionConRestaRecursiva(int dividendo, int divisor) {
        if (divisor == 0) {
            System.out.println("No se puede dividir por cero");
        } else if (dividendo < divisor) {
            return 0;
        } else {
            return 1 + divisionConRestaRecursiva(dividendo - divisor, divisor);
        }
    }
    public static void main(String[] args) {
        int dividend = 20;
        int divisor = 4;

        int resultado = divisionConRestaRecursiva(dividend, divisor);
        System.out.println("El resultado de la división: " + resultado);
    }
}*/

//SIN RECURSIVIDAD

public class divisionConResta {
    public static int divisionConRestaSinRecursion(int dividendo, int divisor) {
        if (divisor == 0) {
            System.out.println("No se puede dividir por cero");

        }

        int cociente = 0;
        while (dividendo >= divisor) {
            dividendo -= divisor;
            cociente++;
        }
        return cociente;
    }

    public static void main(String[] args) {
        int dividendo = 20;
        int divisor = 2;

        int resultado = divisionConRestaSinRecursion(dividendo, divisor);
        System.out.println("Resultado de la división: " + resultado);
    }
}
