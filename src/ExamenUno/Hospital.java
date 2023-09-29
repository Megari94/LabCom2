package ExamenUno;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Persona implements Serializable {
    private String nombre;
    private String dni;
    private Date fechaNacimiento;

    public Persona(String nombre, String dni, Date fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}

class Doctor extends Persona {
    private String especialidad;

    public Doctor(String nombre, String dni, Date fechaNacimiento, String especialidad) {
        super(nombre, dni, fechaNacimiento);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

interface Informacion {
    void verHistorialDeEventos();
}

class Paciente extends Persona implements Informacion, Serializable {
    private String telefono;
    private String fechaNacimientoStr;
    private String tipoSangre;
    private List<String> historialMedico;

    public Paciente(String nombre, String dni, String fechaNacimientoStr, String telefono, String tipoSangre) {
        super(nombre, dni, null);
        this.fechaNacimientoStr = fechaNacimientoStr;
        this.telefono = telefono;
        this.tipoSangre = tipoSangre;
        this.historialMedico = new ArrayList<>();
    }

    public void agregarEventoHistorial(String evento) {
        historialMedico.add(evento);
    }

    public void verHistorialDeEventos() {
        for (String evento : historialMedico) {
            System.out.println(evento);
        }
    }

    public void setFechaNacimientoStr(String fechaNacimientoStr) {
        this.fechaNacimientoStr = fechaNacimientoStr;
    }

    public String getFechaNacimientoStr() {
        return fechaNacimientoStr;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public List<String> getHistorialMedico() {
        return historialMedico;
    }
}

public class Hospital {
    private static List<Doctor> doctores = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static String datosContacto = "";

    public static void main(String[] args) {
        cargarDatosDeArchivo();
        cargarHistorialDesdeArchivo();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarDoctores();
                    break;
                case 2:
                    registrarNuevoPaciente(scanner);
                    break;
                case 3:
                    actualizarInformacionPaciente(scanner);
                    break;
                case 4:
                    nuevoHistorialMedico(scanner);
                    break;
                case 5:
                    guardarHistorialEnArchivo();
                    break;
                case 6:
                    cargarHistorialDesdeArchivo();
                    break;
                case 7:
                    System.out.println("Gracias por ingresar al sistema del Hospital Perrando.");
                    break;
                default:
                    System.out.println("Error! Solo números del 1 al 7.");
            }
        } while (opcion != 7);
    }

    private static void mostrarMenu() {
        System.out.println("Hospital Julio C. Perrando - Av. 9 de Julio 1100 Tel 0362 444-2399");
        System.out.println("Menu:");
        System.out.println("1. Listar Doctores");
        System.out.println("2. Registrar un nuevo paciente");
        System.out.println("3. Actualizar información personal de un paciente");
        System.out.println("4. Nuevo historial médico para un paciente");
        System.out.println("5. Guardar Historial de pacientes en archivo");
        System.out.println("6. Cargar Historial de pacientes desde archivo");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void listarDoctores() {
        System.out.println("Lista de Doctores:");
        for (Doctor doctor : doctores) {
            System.out.println("Nombre: " + doctor.getNombre());
            System.out.println("DNI: " + doctor.getDni());
            System.out.println("Especialidad: " + doctor.getEspecialidad());
            System.out.println();
        }
    }

    private static void registrarNuevoPaciente(Scanner scanner) {
        System.out.print("Nombre del Paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI del Paciente: ");
        String dni = scanner.nextLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        String fechaNacimientoStr = scanner.nextLine();
        System.out.print("Numero de telefono (Celular o Fijo): ");
        String telefono = scanner.nextLine();
        System.out.print("Tipo de sangre: ");
        String tipoSangre = scanner.nextLine();

        Paciente nuevoPaciente = new Paciente(nombre, dni, fechaNacimientoStr, telefono, tipoSangre);
        pacientes.add(nuevoPaciente);
        System.out.println("Paciente registrado.");
    }

    private static void actualizarInformacionPaciente(Scanner scanner) {
        System.out.print("Introduce el DNI del paciente que deseas buscar: ");
        String dniBuscado = scanner.nextLine();

        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dniBuscado)) {
                System.out.println("Paciente encontrado! Actualizar número de teléfono.");
                System.out.print("Nuevo número de teléfono: ");
                String nuevoTelefono = scanner.nextLine();
                paciente.setTelefono(nuevoTelefono);
                System.out.println("Información actualizada. ¡Muchas Gracias!");
                return;
            }
        }

        System.out.println("Lo siento, no se ha encontrado al paciente.");
    }

    private static void nuevoHistorialMedico(Scanner scanner) {
        System.out.print("Introduce el DNI del paciente que deseas buscar: ");
        String dniBuscado = scanner.nextLine();

        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dniBuscado)) {
                System.out.println("Paciente encontrado! Nuevo historial médico por favor: ");
                System.out.print("Fecha en el siguiente orden (dd/MM/yyyy): ");
                String fechaStr = scanner.nextLine();
                System.out.print("Observaciones a realizar: ");
                String observaciones = scanner.nextLine();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fecha = dateFormat.parse(fechaStr);
                    paciente.agregarEventoHistorial(dateFormat.format(fecha) + " - " + observaciones);
                    System.out.println("Proceso realizado exitosamente. Historial médico actualizado.");
                    return;
                } catch (ParseException e) {
                    System.out.println("Error! Revisar la fecha.");
                }
            }
        }

        System.out.println("Lo siento, no se ha encontrado al paciente.");
    }

    private static void guardarHistorialEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("historial.dat"))) {
            oos.writeObject(pacientes);
            System.out.println("Historial de pacientes guardado en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de pacientes en el archivo.");
        }
    }

    private static void cargarHistorialDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("historial.dat"))) {
            pacientes = (List<Paciente>) ois.readObject();
            System.out.println("Historial de pacientes cargado desde el archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el historial de pacientes desde el archivo.");
        }
    }

    private static void cargarDatosDeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                datosContacto += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Error! Surgió un problema al cargar los datos de contacto desde el archivo.");
        }

        
        doctores.add(new Doctor("Dr. Eduardo Diaz de la fuente", "16705700", new Date(), "Clinica General"));
        doctores.add(new Doctor("Dr. Mario Casas", "35123432", new Date(), "Cardiología"));
        doctores.add(new Doctor("Dra. Ayelen Escalante", "33789987", new Date(), "Pediatría"));
        doctores.add(new Doctor("Dra. Gabriela Cabrera", "38209876", new Date(), "Cirugía General"));
    }
}
