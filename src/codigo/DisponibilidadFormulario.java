package codigo;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;

public class DisponibilidadFormulario 
{
    public static Disponibilidad pedirDatosDisponibilidad(int manicuristaId) 
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n=== Registrar Disponibilidad ===");
        System.out.print("Fecha (YYYY-MM-DD): ");
        Date fecha = Date.valueOf(sc.nextLine());
        
        System.out.print("Hora de inicio (HH:MM:SS): ");
        Time horaInicio = Time.valueOf(sc.nextLine() + ":00");
        
        System.out.print("Hora de fin (HH:MM:SS): ");
        Time horaFin = Time.valueOf(sc.nextLine() + ":00");
        
        System.out.print("Precio uñas semipermanentes sencillas: ");
        double precioSemiSencilla = sc.nextDouble();
        
        System.out.print("Precio uñas semipermanentes con diseño: ");
        double precioSemiDiseno = sc.nextDouble();
        
        System.out.print("Precio uñas permanentes sencillas: ");
        double precioPermanenteSencilla = sc.nextDouble();
        
        System.out.print("Precio uñas permanentes con diseño: ");
        double precioPermanenteDiseno = sc.nextDouble();
        
        return new Disponibilidad(0, manicuristaId, fecha, horaInicio, horaFin, precioSemiSencilla, precioSemiDiseno, precioPermanenteSencilla, precioPermanenteDiseno);
    }
}