package GestionEmpleados;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

// Definición de la clase abstracta Empleado
abstract class Empleado {
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    // Método abstracto para calcular el sueldo
    abstract double calcularSueldo();
}

// Clase EmpleadoPorHoras
class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private static final double PRECIO_HORA = 3500;

    public EmpleadoPorHoras(String nombre, int id, int horasTrabajadas) {
        super(nombre, id, 0);
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    double calcularSueldo() {
        return sueldoBase + (horasTrabajadas * PRECIO_HORA);
    }
}

// Clase EmpleadoAsalariado
class EmpleadoAsalariado extends Empleado {
    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    double calcularSueldo() {
        return sueldoBase;
    }
}

// Clase EmpleadoComision
class EmpleadoComision extends Empleado implements Impuesto {
    private double ventasRealizadas;

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    double calcularSueldo() {
        return sueldoBase + (0.1 * ventasRealizadas); // 10% de comisión
    }

    @Override
    public double calcularImpuesto() {
        return calcularSueldo() * 0.15; // 15% de impuesto
    }
}

// Interfaz Impuesto
interface Impuesto {
    double calcularImpuesto();
}

// Clase GestorEmpleados
class GestorEmpleados {
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
        System.out.println("Empleado agregado: " + empleado.nombre);
    }

    public void mostrarEmpleados() {
        System.out.println("Lista de empleados:");
        for (Empleado empleado : empleados) {
            System.out.println(empleado.nombre + " - Sueldo: $" + empleado.calcularSueldo());
            if (empleado instanceof Impuesto) {
                System.out.println("Impuesto a pagar: $" + ((Impuesto) empleado).calcularImpuesto());
            }
        }
    }
}

