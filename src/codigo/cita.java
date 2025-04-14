package codigo;

import java.sql.Date;
import java.sql.Time;

public class cita 
{
    private int id;
    private int clienteId;
    private int manicuristaId;
    private int disponibilidadId;
    private String tipoServicio; 
    private Date fecha;
    private Time hora;
    private String estado;
    
    public cita(int id, int clienteId, int manicuristaId, int disponibilidadId, String tipoServicio, Date fecha, Time hora, String estado) 
    {
        this.id = id;
        this.clienteId = clienteId;
        this.manicuristaId = manicuristaId;
        this.disponibilidadId = disponibilidadId;
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getId() 
    { 
        return id; 
    }
    public void setId(int id) 
    { 
        this.id = id; 
    }
    
    public int getClienteId() 
    { 
        return clienteId; 
    }
    public void setClienteId(int clienteId) 
    { 
        this.clienteId = clienteId; 
    }
    
    public int getManicuristaId() 
    { 
        return manicuristaId; 
    }
    public void setManicuristaId(int manicuristaId) 
    { 
        this.manicuristaId = manicuristaId; 
    }
    
    public int getDisponibilidadId() 
    { 
        return disponibilidadId; 
    }
    public void setDisponibilidadId(int disponibilidadId) 
    { 
        this.disponibilidadId = disponibilidadId; 
    }
    
    public String getTipoServicio() 
    { 
        return tipoServicio; 
    }
    public void setTipoServicio(String tipoServicio) 
    { 
        this.tipoServicio = tipoServicio; 
    }
    
    public Date getFecha() 
    { 
        return fecha; 
    }
    public void setFecha(Date fecha) 
    { 
        this.fecha = fecha; 
    }
    
    public Time getHora() 
    { 
        return hora; 
    }
    public void setHora(Time hora) 
    { 
        this.hora = hora; 
    }
    
    public String getEstado() 
    { 
        return estado; 
    }
    public void setEstado(String estado) 
    { 
        this.estado = estado; 
    }
}