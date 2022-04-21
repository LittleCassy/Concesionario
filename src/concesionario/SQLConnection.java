package concesionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;

public class SQLConnection {
    // Base de datos a la que nos conectamos
    private String DB;
    // Usuario de la base de datos
    private String USER;
    // Contraseña del usuario de la base de datos
    private String PASS;
    // Objeto donde se almacenará nuestra conexión
    private Connection connection;
    // Indica que está en localhost
    private String HOST;
    // Zona horaria
    private TimeZone timezone;

    /**
     * Constructor de la clase
     *
     * @param usuario Usuario de la base de datos
     * @param pass    Contraseña del usuario
     * @param bd      Base de datos a la que nos conectamos
     */
    public SQLConnection(String usuario, String pass, String bd) {
        HOST = "localhost";
        USER = usuario;
        PASS = pass;
        DB = bd;
        connection = null;
    }

    /**
     * Comprueba que el driver de MySQL esté correctamente integrado
     *
     * @throws SQLException Se lanzará cuando haya un fallo con la base de datos
     */
    private void driverRegister() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al conectar con MySQL: " + e.getMessage());
        }
    }

    /**
     * Conecta con la base de datos
     *
     * @throws SQLException Se lanzará cuando haya un fallo con la base de datos
     */
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            driverRegister();
            // Obtengo la zona horaria
            Calendar now = Calendar.getInstance();
            timezone = now.getTimeZone();
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DB + "?user="
                    + USER + "&password=" + PASS + "&useLegacyDatetimeCode=false&serverTimezone="
                    + timezone.getID());
        }
    }

    /**
     * Cierra la conexión con la base de datos
     *
     * @throws SQLException Se lanzará cuando haya un fallo con la base de datos
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     * Ejecuta una consulta SELECT
     *
     * @param consulta Consulta SELECT a ejecutar
     * @return Resultado de la consulta
     * @throws SQLException Se lanzará cuando haya un fallo con la base de datos
     */
    public ResultSet runSelect(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery(consulta);

        return rset;
    }

    /**
     * Ejecuta una consulta INSERT, DELETE o UPDATE
     *
     * @param consulta Consulta INSERT, DELETE o UPDATE a ejecutar
     * @return Cantidad de filas afectadas
     * @throws SQLException Se lanzará cuando haya un fallo con la base de datos
     */
    public int runInsertDeleteUpdate(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        int row = stmt.executeUpdate(consulta);

        return row;
    }
}