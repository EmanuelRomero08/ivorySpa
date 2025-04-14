package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class clienteDAO
{
    public static void insertarCliente(cliente c)
    {
        Connection con = dbConnection.conectar();
        try
        {
            String sql = "INSERT INTO cliente (ID_CLIENTE, nombre, telefono, direccion) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            
            int filas = ps.executeUpdate();
            if (filas > 0)
            {
                System.out.println("Cliente agregado con Exito.");
            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public static cliente autenticarCliente(String id)
    {
        //login del cliente
    }
}