package codigo;

import java.util.List;
import java.util.Scanner;

public class Menu 
{
    public static void mostrarMenuPrincipal() 
    {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do 
        {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Soy cliente");
            System.out.println("2. Soy manicurista");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch(opcion) 
            {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuManicurista();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } 
        while (opcion != 3);
    }
    
    private static void menuCliente() 
    {
        Scanner sc = new Scanner(System.in);
        int opcion;
     
        System.out.println("\n=== REGISTRO/IDENTIFICACIÓN DE CLIENTE ===");
        cliente c = clienteFormulario.pedirDatos();
        clienteDAO.insertarCliente(c);
        
        do 
        {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver manicuristas disponibles");
            System.out.println("2. Solicitar cita");
            System.out.println("3. Ver mis citas");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 
            
            switch(opcion) 
            {
                case 1:
                    break;
                case 2:
                    List<Disponibilidad> disponibilidades = DisponibilidadDAO.obtenerTodasDisponibilidades();
                    cita nuevaCita = CitaFormulario.pedirDatosCita(c.getId(), disponibilidades);
                    if (nuevaCita != null) 
                    {
                        CitaDAO.solicitarCita(nuevaCita);
                    }
                    break;
                case 3:
                    List<cita> citas = CitaDAO.obtenerCitasPorCliente(c.getId());
                    System.out.println("\n=== MIS CITAS ===");
                    for (cita cita : citas) 
                    {
                        System.out.printf("ID: %d, Fecha: %s, Hora: %s, Servicio: %s, Estado: %s\n", cita.getId(), cita.getFecha(), cita.getHora(), cita.getTipoServicio(), cita.getEstado());
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } 
        while (opcion != 4);
    }
    
    private static void menuManicurista() 
    {
        Scanner sc = new Scanner(System.in);
        int opcion;
       
        System.out.println("\n=== REGISTRO/IDENTIFICACIÓN DE MANICURISTA ===");
        manicurista m = manicuristaFormulario.pedirDatos();
        manicuristaDAO.insertarManicurista(m);
        
        do 
        {
            System.out.println("\n=== MENÚ MANICURISTA ===");
            System.out.println("1. Registrar disponibilidad");
            System.out.println("2. Ver mis disponibilidades");
            System.out.println("3. Ver citas pendientes");
            System.out.println("4. Aceptar/Rechazar citas");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch(opcion) 
            {
                case 1:
                    Disponibilidad nuevaDisp = DisponibilidadFormulario.pedirDatosDisponibilidad(m.getId());
                    DisponibilidadDAO.insertarDisponibilidad(nuevaDisp);
                    break;
                case 2:
                    List<Disponibilidad> disponibilidades = DisponibilidadDAO.obtenerDisponibilidadPorManicurista(m.getId());
                    System.out.println("\n=== MIS DISPONIBILIDADES ===");
                    for (Disponibilidad d : disponibilidades) 
                    {
                        System.out.printf("ID: %d, Fecha: %s, Hora: %s a %s\n", d.getId(), d.getFecha(), d.getHoraInicio(), d.getHoraFin());
                        System.out.printf("Precios: Semi sencilla: %.2f, Semi diseño: %.2f, Permanente sencilla: %.2f, Permanente diseño: %.2f\n", d.getPrecioSemiSencilla(), d.getPrecioSemiDiseño(),d.getPrecioPermanenteSencilla(), d.getPrecioPermanenteDiseño());
                    }
                    break;
                case 3:
                    List<cita> citasPendientes = CitaDAO.obtenerCitasPorManicuristaYEstado(m.getId(), "pendiente");
                    System.out.println("\n=== CITAS PENDIENTES ===");
                    for (cita cita : citasPendientes) 
                    {
                        System.out.printf("ID: %d, Fecha: %s, Hora: %s, Servicio: %s, Cliente ID: %d\n", cita.getId(), cita.getFecha(), cita.getHora(), cita.getTipoServicio(), cita.getClienteId());
                    }
                    break;
                case 4:
                    List<cita> citas = CitaDAO.obtenerCitasPorManicuristaYEstado(m.getId(), "pendiente");
                    if (citas.isEmpty()) 
                    {
                        System.out.println("No hay citas pendientes");
                        break;
                    }
                    
                    System.out.println("\n=== CITAS PENDIENTES ===");
                    for (cita cita : citas) 
                    {
                        System.out.printf("ID: %d, Fecha: %s, Hora: %s, Servicio: %s, Cliente ID: %d\n", cita.getId(), cita.getFecha(), cita.getHora(), cita.getTipoServicio(), cita.getClienteId());
                    }
                    
                    System.out.print("\nIngrese ID de la cita a gestionar: ");
                    int citaId = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.println("1. Aceptar cita");
                    System.out.println("2. Rechazar cita");
                    System.out.print("Seleccione una opción: ");
                    int accion = sc.nextInt();
                    sc.nextLine(); 
                    
                    if (accion == 1) 
                    {
                        CitaDAO.actualizarEstadoCita(citaId, "aceptada");
                        System.out.println("Cita aceptada con éxito");
                    } else if (accion == 2) 
                    {
                        CitaDAO.actualizarEstadoCita(citaId, "rechazada");
                        System.out.println("Cita rechazada");
                    } 
                    else 
                    {
                        System.out.println("Opción inválida");
                    }
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        while (opcion != 5);
    }
}