package BaseDeDatos;

import javafx.scene.transform.Scale;

import java.sql.*;
import java.util.Scanner;

public class VehiculoMain {
    public static void main(String[] args){

    }
}
abstract class Vehiculo{
    private String nombre;
    private int year;
    private String tipo;
    private String color;
    public Vehiculo(String nombre, int year, String tipo, String color){
        this.nombre=nombre;
        this.year=year;
        this.tipo=tipo;
        this.color=color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
         return "Vehiculo: " +
                ", nombre: " + nombre + '\'' +
                ", year: " + year +
                ", tipo: " + tipo + '\'' +
                ", color: " + color + '\'';
    }
}
class Motocicleta extends Vehiculo{
    private int cilindrada;
    public Motocicleta(String nombre, int Year, String tipo, String color, int cilindrada){
        super(nombre,Year,tipo,color);
        this.cilindrada=cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return super.toString()+
                "cilindrada: "+cilindrada;
    }
}
class Automovil extends Vehiculo{
    private int cantPuertas;
    public Automovil(String nombre, int Year, String tipo, String color,int cantPuertas){
        super(nombre,Year,tipo,color);
        this.cantPuertas=cantPuertas;
    }

    public int getCantPuertas() {
        return cantPuertas;
    }

    public void setCantPuertas(int cantPuertas) {
        this.cantPuertas = cantPuertas;
    }

    @Override
    public String toString() {
        return super.toString()+
                "nro de puertas: "+cantPuertas;
    }
}
class VehiculoBD{
    private static final String URL = "jdbc:mysql://localhost:3306/ponerla bd";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static void ejecutarConsulta(String consulta) {
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Crear la declaración
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                // Ejecutar la consulta
                statement.executeUpdate();//para modificados, eliminados, agregados
            }
            // Cerrar la conexión
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Método para ejecutar una consulta y devolver un conjunto de resultados
    public static ResultSet ejecutarConsultaConResultado(String consulta) {//devuelve un ResultSet
        try {
            // Establecer la conexión con la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Crear la declaración
            PreparedStatement statement = connection.prepareStatement(consulta);
            // Ejecutar la consulta y devolver el conjunto de resultados
            return statement.executeQuery();//quiero que me muestre lo que hizo, me trae
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
class Concecionaria {
    private void agregarMoto(Motocicleta motocicleta) throws SQLException {
        //Crear consulta
        String consulta = "INSERT INTO `motocicleta`(`nombre`, `Modelo`, `Color`, `Year`, `cilindrada`) VALUES ('" + motocicleta.getNombre() + "', '" + motocicleta.getYear() + "','" + motocicleta.getTipo() + "','" + motocicleta.getColor() + "','" + motocicleta.getCilindrada() + "')";
        VehiculoBD.ejecutarConsulta(consulta);//como no devuelve nada no necesito nada como en el result set
    }

    private void ListarMotocicletas() throws SQLException {
        String consulta = "SELECT * FROM `motocicleta` WHERE 1"; //definicion consulta
        ResultSet resultadoMoto = VehiculoBD.ejecutarConsultaConResultado(consulta);
        if (resultadoMoto != null) {
            System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "nombre", "Modelo", "Color", "Year", "Cilindrada");
            while (resultadoMoto.next()) {
                String nombre = resultadoMoto.getString("nombre");
                int year = resultadoMoto.getInt("year");
                String tipo = resultadoMoto.getString("tipo");
                String color = resultadoMoto.getString("color");
                int cilindrada = resultadoMoto.getInt("cilindrada");
                System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", nombre, year, tipo, color, cilindrada);
            }
        }
    }

    public static void ListarMotocicletasEspecial(ResultSet resultadoMoto) throws SQLException {
        if (resultadoMoto != null) {
            System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "nombre", "Modelo", "Color", "Year", "Cilindrada");
            while (resultadoMoto.next()) {
                String nombre = resultadoMoto.getString("nombre");
                int year = resultadoMoto.getInt("year");
                String tipo = resultadoMoto.getString("tipo");
                String color = resultadoMoto.getString("color");
                int cilindrada = resultadoMoto.getInt("cilindrada");
                System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", nombre, year, tipo, color, cilindrada);
            }
        }
    }

    private void agregarAuto(Automovil automovil) throws SQLException {
        String consulta = "INSERT INTO `motocicleta`(`Marca`, `Modelo`, `Color`, `Year`, `cilindrada`) VALUES ('" + automovil.getNombre() + "', '" + automovil.getYear() + "','" + automovil.getTipo() + "','" + automovil.getColor() + "','" + automovil.getCantPuertas() + "')";
        VehiculoBD.ejecutarConsulta(consulta);
    }

    private void ListarAutos() throws SQLException {
        String consulta = "SELECT * FROM `automovil` WHERE 1"; //definicion consulta
        ResultSet resultadoAuto = VehiculoBD.ejecutarConsultaConResultado(consulta);
        if (resultadoAuto != null) {
            System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "nombre", "Modelo", "Color", "Year", "nro de Puertas");

            while (resultadoAuto.next()) {
                String nombre = resultadoAuto.getString("nombre");
                int year = resultadoAuto.getInt("year");
                String tipo = resultadoAuto.getString("tipo");
                String color = resultadoAuto.getString("color");
                int nroPuertas = resultadoAuto.getInt("cantidadPuertas");
                System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", nombre, year, tipo, color, nroPuertas);
            }
        }
    }
   public static void ListarAutosEspecial(ResultSet resultadoAuto) throws SQLException {
        if (resultadoAuto != null) {
            System.out.printf("%-10s %-15s %-5s %-20s %-12s %-10s\n", "nombre", "Modelo", "Color", "Year", "nro de Puertas");

            while (resultadoAuto.next()) {
                String nombre = resultadoAuto.getString("nombre");
                int year = resultadoAuto.getInt("year");
                String tipo = resultadoAuto.getString("tipo");
                String color = resultadoAuto.getString("color");
                int nroPuertas = resultadoAuto.getInt("cantidadPuertas");
                System.out.printf("%-10d %-15s %-5d %-20s %-12s %-10d\n", nombre, year, tipo, color, nroPuertas);
            }
        }
    }

    private void buscarMotoxAnio(int anio) throws SQLException {
        String consulta = "SELECT * FROM `motocicletas` WHERE `year` = '" + anio + "'";
        ResultSet resultadoMoto = VehiculoBD.ejecutarConsultaConResultado(consulta);
        Concecionaria.ListarMotocicletasEspecial(resultadoMoto);
    }
    private void buscarMotoEntreAnios(int anio1, int anio2)throws Exception{
        String consulta="SELECT * FROM `motocicletas` WHERE `year` BETWEEN '"+anio1+"' AND '"+anio2+"'";
        ResultSet resultado=VehiculoBD.ejecutarConsultaConResultado(consulta);
        Concecionaria.ListarMotocicletasEspecial(resultado);
    }
    private void buscarMotoAntesAnio(int anio1)throws Exception{
        String consulta="SELECT * FROM `motocicletas` WHERE `year` < '"+anio1+"'";
        ResultSet resultado=VehiculoBD.ejecutarConsultaConResultado(consulta);
        Concecionaria.ListarMotocicletasEspecial(resultado);
    }
    private void buscarAutoporInicial(String inicial)throws Exception{
        String consulta="SELECT * FROM `automoviles` WHERE `nombre` LIKE '"+inicial+"%'";
        ResultSet resultado=VehiculoBD.ejecutarConsultaConResultado(consulta);
        Concecionaria.ListarAutosEspecial(resultado);
    }
    private void buscarAutoporPatronDeCaracteres(String sec)throws Exception{
        String consulta="SELECT * FROM `automoviles` WHERE `nombre` LIKE '%"+sec+"%'";
        ResultSet resultado=VehiculoBD.ejecutarConsultaConResultado(consulta);
        Concecionaria.ListarAutosEspecial(resultado);
    }
    private void EliminarMoto(String nombre)throws SQLException{
        String consulta="DELETE FROM `motocicletas` WHERE nombre= '"+nombre+"'";
        VehiculoBD.ejecutarConsulta(consulta);
    }
    private void EliminarAuto(String nombre)throws SQLException{
        String consulta="DELETE FROM `automoviles` WHERE nombre= '"+nombre+"'";
        VehiculoBD.ejecutarConsulta(consulta);
    }
    private void ModificarMoto(String nombre, Scanner scanner) throws SQLException{
    System.out.println("Ingrese nuevo nombre");
    String newnombre=scanner.nextLine();
    System.out.println("Ingrese nuevo modelo");
    String newmodelo=scanner.nextLine();
        System.out.println("Ingrese nuevo color");
        String newcolor=scanner.nextLine();
        System.out.println("Ingrese nuevo año");
        int newanio=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nueva cilindrada");
        int newcin=scanner.nextInt();
        scanner.nextLine();
        String consulta="UPDATE `motocicletas` SET `nombre`='"+newnombre+"',`modelo`='"+newmodelo+"'," +
                "`color`='"+newcolor+"',`year`='"+newanio +
                "',`cilindrada`='"+newcin+"'WHERE nombre='"+nombre+"'";
        VehiculoBD.ejecutarConsulta(consulta);

    }
    private void ModificarAuto(String nombre, Scanner scanner) throws SQLException{
        System.out.println("Ingrese nuevo nombre");
        String newnombre=scanner.nextLine();
        System.out.println("Ingrese nuevo modelo");
        String newmodelo=scanner.nextLine();
        System.out.println("Ingrese nuevo color");
        String newcolor=scanner.nextLine();
        System.out.println("Ingrese nuevo año");
        int newanio=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nuevo nro de puertas");
        int newnroPuertas=scanner.nextInt();
        scanner.nextLine();
        String consulta="UPDATE `motocicletas` SET `nombre`='"+newnombre+"',`modelo`='"+newmodelo+"'," +
                "`color`='"+newcolor+"',`year`='"+newanio +
                "',`cantidadPuertas`='"+newnroPuertas+"'WHERE nombre='"+nombre+"'";
        VehiculoBD.ejecutarConsulta(consulta);
    }


}

class Vehiculo_Ejecutable {
    public static void main(String[] args) {

        Motocicleta moto1= new Motocicleta("Motomel", 2023, "Moto", "Blanco", 150);

    }
}

