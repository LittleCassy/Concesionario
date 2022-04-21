package concesionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.*;
/**
 *
 * @author Casandra McJack
 */
public class DBShenaningans 
{
    public static void init(JTable myTable)
    {
        SQLConnection connection = new SQLConnection("casandra", "1234", "clases");

        try
        {
            connection.connect();
            ResultSet result = connection.runSelect("select * from persona");
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Marca", "Modelo", "Matricula"}, 0);
            while(result.next()){
                model.addRow(new String[]{result.getString(1), result.getString(2), result.getString(3)});
            }
            myTable.setModel(model);
        }catch (SQLException ex){
            System.err.println(ex);
        }
    } 
}
