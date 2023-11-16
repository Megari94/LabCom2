import java.sql.*;
import java.util.ArrayList;

class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void ejecutarConsulta(String consulta) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(consulta);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class Productos {
    private int id;
    private String nombre;
    private double precio_por_unidad;
    private int stock;

    public Productos(int id, String nombre, double precio_por_unidad, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio_por_unidad = precio_por_unidad;
        this.stock = stock;
    }

    // Getters y setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio_por_unidad(double precio_por_unidad) {
        this.precio_por_unidad = precio_por_unidad;
    }

    public double getPrecio_por_unidad() {
        return precio_por_unidad;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
//Generación de Informe de Productos en Stock
    public static void generarInforme() {
        try {
            String consulta = "SELECT * FROM productos";
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            System.out.println("Informe de Productos en Stock:");
            System.out.printf("%-30s%-10s%-10s%-10s\n", "Producto", "Stock", "Precio", "Total");
            System.out.println("----------------------------------------------------------");

            double valorTotalProductos = 0;
            while (resultado.next()) {
                String nombreProducto = resultado.getString("nombre");
                int stock = resultado.getInt("stock");
                double precio = resultado.getDouble("precio_por_unidad");
                double valorTotal = stock * precio;
                valorTotalProductos += valorTotal;

                System.out.printf("%-30s%-10d%-10.2f%-10.2f\n", nombreProducto, stock, precio, valorTotal);
            }

            System.out.println("----------------------------------------------------------");
            System.out.printf("%-50s%-10.2f\n", "Total:", valorTotalProductos);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Obtener producto por id
    public static Productos obtenerProducto(int productoID) {
        try {
            String consulta = "SELECT * FROM productos WHERE producto_id = " + productoID;
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado != null && resultado.next()) {
                return new Productos(
                        resultado.getInt("producto_id"),
                        resultado.getString("nombre"),
                        resultado.getDouble("precio_por_unidad"),
                        resultado.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Obtener producto por nombre

    public static Productos obtenerProductoMasVendido() {
        try {
            String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido FROM ventas GROUP BY producto_id ORDER BY total_vendido DESC LIMIT 1";
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado != null && resultado.next()) {
                int productoIDMasVendido = resultado.getInt("producto_id");
                return obtenerProducto(productoIDMasVendido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class Vendedor {
    private int vendedor_id;
    private String nombre;
    private String apellido;
    private int dni;
    private String fecha_nacimiento;
    private String fecha_contratacion;
//Constructor
    public Vendedor(int vendedor_id, String nombre, String apellido, int dni, String fecha_nacimiento, String fecha_contratacion) {
        this.vendedor_id = vendedor_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
    }
// Getters y setters
public void setVendedor_id(int vendedor_id) {
    this.vendedor_id = vendedor_id;
}

    public int getVendedor_id() {
        return vendedor_id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_contratacion(String fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getFecha_contratacion() {
        return fecha_contratacion;
    }
//Buscar vendedor por id
    public Vendedor(String consultaBusqueda) {
        try {
            String consulta = "SELECT * FROM vendedores WHERE vendedor_id = " + vendedor_id;
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado.next()) {
                this.vendedor_id = resultado.getInt("vendedor_id");
                this.nombre = resultado.getString("nombre");
                this.apellido = resultado.getString("apellido");
                this.dni = resultado.getInt("dni");
                this.fecha_nacimiento = resultado.getString("fecha_nacimiento");
                this.fecha_contratacion = resultado.getString("fecha_contratacion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//obtener vendedor por id
    public static Vendedor obtenerVendedorPorID(int vendedorID) {
        try {
            String consulta = "SELECT * FROM vendedores WHERE vendedor_id = " + vendedorID;
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultado != null && resultado.next()) {
                return new Vendedor(
                        resultado.getInt("vendedor_id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getInt("dni"),
                        resultado.getString("fecha_nacimiento"),
                        resultado.getString("fecha_contratacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//Listado de vendedores
    public static ArrayList<Vendedor> listadoDeVendedores() {
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();

        try {
            String consulta = "SELECT * FROM vendedores";
            ResultSet resultado = DBHelper.ejecutarConsultaConResultado(consulta);

            while (resultado.next()) {
                Vendedor vendedor = new Vendedor(
                        resultado.getInt("vendedor_id"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getInt("dni"),
                        resultado.getString("fecha_nacimiento"),
                        resultado.getString("fecha_contratacion")
                );
                listaVendedores.add(vendedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaVendedores;
    }
}

public class SistemaDeVentas {
    public static void main(String[] args) {
        //Generar informe de producto
        //Productos.generarInforme();

        //Obtener producto
        /*int productoID = 1;
        Productos producto = Productos.obtenerProducto(productoID);

        if (producto != null) {
            System.out.println("Información del Producto:");
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio por Unidad: " + producto.getPrecio_por_unidad());
            System.out.println("Stock: " + producto.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }
        */

        //Obtener producto más vendido
       /* Productos productoMasVendido = Productos.obtenerProductoMasVendido();

        if (productoMasVendido!= null) {
            System.out.println("Información del Producto más vendido:");
            System.out.println("ID: " + productoMasVendido.getId());
            System.out.println("Nombre: " + productoMasVendido.getNombre());
            System.out.println("Precio por Unidad: " + productoMasVendido.getPrecio_por_unidad());
            System.out.println("Stock: " + productoMasVendido.getStock());
        } else {
            System.out.println("Producto más vendido no encontrado.");
        }
        */
        //Obtener Vendedores por ID
       /*
        int vendedorID = 1;
        Vendedor vendedor = Vendedor.obtenerVendedorPorID(vendedorID);
        if (vendedor != null) {
            System.out.println("Información del Vendedor:");
            System.out.println("ID: " + vendedor.getVendedor_id());
            System.out.println("Nombre: " + vendedor.getNombre());
            System.out.println("Apellido: " + vendedor.getApellido());
            System.out.println("DNI: " + vendedor.getDni());
            System.out.println("Fecha de Nacimiento: " + vendedor.getFecha_nacimiento());
            System.out.println("Fecha de Contratación: " + vendedor.getFecha_contratacion());
        } else {
            System.out.println("Vendedor no encontrado.");
        }
        */
        //Listado de Vendedores
       /* ArrayList<Vendedor> vendedores = Vendedor.listadoDeVendedores();

        if (!vendedores.isEmpty()) {
            System.out.println("Listado de Vendedores:");
            for (Vendedor vendedor : vendedores) {
                System.out.println("ID: " + vendedor.getVendedor_id() + ", Nombre: " + vendedor.getNombre());
            }
        } else {
            System.out.println("No hay vendedores registrados.");
        }
        */

    }
}
