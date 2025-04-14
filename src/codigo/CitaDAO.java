package codigo;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO 
{
    public static void solicitarCita(cita cita) 
    {
        try (Connection con = dbConnection.conectar()) 
        {
            String sql = "INSERT INTO citas (cliente_id, manicurista_id, disponibilidad_id, " + "tipo_servicio, fecha, hora, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cita.getClienteId());
            ps.setInt(2, cita.getManicuristaId());
            ps.setInt(3, cita.getDisponibilidadId());
            ps.setString(4, cita.getTipoServicio());
            ps.setDate(5, cita.getFecha());
            ps.setTime(6, cita.getHora());
            ps.setString(7, "pendiente");
            ps.executeUpdate();
            System.out.println("Cita solicitada con éxito. Esperando confirmación del manicurista.");
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void actualizarEstadoCita(int citaId, String nuevoEstado) 
    {
        try (Connection con = dbConnection.conectar()) 
        {
            String sql = "UPDATE citas SET estado = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, citaId);
            
            int filas = ps.executeUpdate();
            if (filas > 0) 
            {
                System.out.println("Estado de la cita actualizado a: " + nuevoEstado);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static List<cita> obtenerCitasPorManicurista(int manicuristaId) 
    {
        List<cita> citas = new ArrayList<>();
        try (Connection con = dbConnection.conectar()) 
        {
            String sql = "SELECT * FROM citas WHERE manicurista_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, manicuristaId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                cita c = new cita
                (
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("manicurista_id"),
                    rs.getInt("disponibilidad_id"),
                    rs.getString("tipo_servicio"),
                    rs.getDate("fecha"),
                    rs.getTime("hora"),
                    rs.getString("estado")
                );
                citas.add(c);
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return citas;
    }
    
    public static List<cita> obtenerCitasPorCliente(int clienteId) 
    {
        List<cita> citas = new ArrayList<>();
        try (Connection con = dbConnection.conectar()) 
        {
            String sql = "SELECT * FROM citas WHERE cliente_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, clienteId);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) 
            {
                cita c = new cita
                (
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("manicurista_id"),
                    rs.getInt("disponibilidad_id"),
                    rs.getString("tipo_servicio"),
                    rs.getDate("fecha"),
                    rs.getTime("hora"),
                    rs.getString("estado")
                );
                citas.add(c);
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return citas;
    }

    public static List<cita> obtenerCitasPorManicuristaYEstado(int manicuristaId, String estado) 
    {
    List<cita> citas = new ArrayList<>();
    String sql = "SELECT * FROM citas WHERE manicurista_id = ? AND estado = ? ORDER BY fecha, hora";

    try (Connection con = dbConnection.conectar(); PreparedStatement ps = con.prepareStatement(sql)) 
    {
        
        ps.setInt(1, manicuristaId);
        ps.setString(2, estado);

        try (ResultSet rs = ps.executeQuery()) 
        {
            while (rs.next()) 
            {
                cita cita = new cita
                (
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("manicurista_id"),
                    rs.getInt("disponibilidad_id"),
                    rs.getString("tipo_servicio"),
                    rs.getDate("fecha"),
                    rs.getTime("hora"),
                    rs.getString("estado")
                );
                citas.add(cita);
            }
        }
    } 
    catch (SQLException e) 
    {
        System.err.println("Error al obtener citas: " + e.getMessage());
        e.printStackTrace();
    }
    return citas;
    }
}
