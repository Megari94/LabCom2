package GestionEmpleados;

import java.util.InputMismatchException;
import java.util.Scanner;

// Programa principal
public class Main {
    public static void main(String[] args) {
        GestorEmpleados gestor = new GestorEmpleados();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1. Agregar empleado por horas");
                System.out.println("2. Agregar empleado asalariado");
                System.out.println("3. Agregar empleado por comisión");
                System.out.println("4. Mostrar empleados y cálculos");
                System.out.println("5. Salir");
                System.out.print("Ingrese la opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después de leer el número

                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Horas trabajadas: ");
                        int horasTrabajadas = scanner.nextInt();
                        if (horasTrabajadas < 0) {
                            throw new IllegalArgumentException("Lo siento, las horas trabajadas no pueden ser negativas.");
                        }
                        EmpleadoPorHoras empleadoPorHoras = new EmpleadoPorHoras(nombre, id, horasTrabajadas);
                        gestor.agregarEmpleado(empleadoPorHoras);
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        nombre = scanner.nextLine();
                        System.out.print("ID: ");
                        id = scanner.nextInt();
                        double sueldoBase;
                        do {
                            System.out.print("Sueldo base: ");
                            sueldoBase = scanner.nextDouble();
                            if (sueldoBase < 0) {
                                System.out.println("Error!! El sueldo base no puede ser negativo.");
                            }
                        } while (sueldoBase < 0);
                        EmpleadoAsalariado empleadoAsalariado = new EmpleadoAsalariado(nombre, id, sueldoBase);
                        gestor.agregarEmpleado(empleadoAsalariado);
                        break;
                    case 3:
                        System.out.print("Nombre: ");
                        nombre = scanner.nextLine();
                        System.out.print("ID: ");
                        id = scanner.nextInt();
                        double sueldoBaseComision;
                        do {
                            System.out.print("Sueldo base: ");
                            sueldoBaseComision = scanner.nextDouble();
                            if (sueldoBaseComision < 0) {
                                System.out.println("Error!! El sueldo base no puede ser negativo.");
                            }
                        } while (sueldoBaseComision < 0);
                        System.out.print("Ventas realizadas: ");
                        double ventasRealizadas = scanner.nextDouble();
                        EmpleadoComision empleadoComision = new EmpleadoComision(nombre, id, sueldoBaseComision, ventasRealizadas);
                        gestor.agregarEmpleado(empleadoComision);
                        break;
                    case 4:
                        gestor.mostrarEmpleados();
                        break;
                    case 5:
                        System.out.println("Adios!");
                        System.exit(0);
                    default:
                        System.out.println("Lo siento, la opcion no es valida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error!! Debes ingresar un valor numérico.");
                scanner.nextLine(); // Limpiar el búfer de entrada
            } catch (IllegalArgumentException e) {
                System.out.println("Error!! " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
