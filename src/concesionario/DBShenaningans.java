package concesionario;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.*;
/**
 *
 * @author Casandra McJack
 */
public class DBShenaningans 
{
    public static JTable mainMenuTable;
    public static JComboBox<String> vehicleComboBox;
    
    public static void updateMainMenuTable()
    {
        SQLConnection connection = new SQLConnection("casandra", "1234", "concesionario");

        try
        {
            connection.connect();
            ResultSet result = connection.runSelect("select * from vehiculos");
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Marca", "Modelo", "Matricula"}, 0);
            while(result.next()){
                model.addRow(new String[]{result.getString(1), result.getString(2), result.getString(3)});
            }
            mainMenuTable.setModel(model);
        }catch (SQLException ex){
            System.err.println(ex);
        }
    } 
    
    public static void insertNewCar(String brand, String model, String licensePlate)
    {
        SQLConnection connection = new SQLConnection("casandra", "1234", "concesionario");

        try
        {
            connection.connect();
            connection.runInsertDeleteUpdate("INSERT INTO vehiculos VALUES('"+brand+"','"+model+"','"+licensePlate+"')");
            updateMainMenuTable();
        }catch (SQLException ex){
            System.err.println(ex);
        }
    }
    
    public static void insertNewPerson(String name, String surname, String DNI)
    {
        SQLConnection connection = new SQLConnection("casandra", "1234", "concesionario");

        try
        {
            connection.connect();
            connection.runInsertDeleteUpdate("INSERT INTO personas VALUES('"+name+"','"+surname+"','"+DNI+"')");
            updateMainMenuTable();
        }catch (SQLException ex){
            System.err.println(ex);
        }
    }
    
    public static void selectDropBoxVehicles()
    {
        SQLConnection connection = new SQLConnection("casandra", "1234", "concesionario");

        try
        {
            connection.connect();
            ResultSet result = connection.runSelect("select * from vehiculos");
            while(result.next()){
                vehicleComboBox.add(new Component() {
                    
                });
            }
        }catch (SQLException ex){
            System.err.println(ex);
        }
    }
}
