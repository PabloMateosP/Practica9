import java.sql.*;

public class BaseDeDatos {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/intervenciones";
        //Las primeras veces daba fallo, ya que, había puesto 3360 en vez de 3306.

        try {
            Connection cn = DriverManager.getConnection(url, "root", "");

            Statement stn = cn.createStatement();

            ResultSet rs = stn.executeQuery("SELECT * FROM alumnos");

            while (rs.next()){

                System.out.println("Nombre: " + rs.getString("nombre"));

                System.out.println("Intervenciones: " + rs.getString("intervenciones"));

                System.out.println("Fecha " + rs.getString("fecha_intervenciones"));

            }

            stn.close();
            cn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
