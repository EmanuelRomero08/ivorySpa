package codigo;

import java.util.Scanner;

public class clienteFormulario {
    private static final Scanner sc = new Scanner(System.in);
    
    public static cliente pedirDatos() {
        System.out.println("===Registro de cliente===");
        
        System.out.print("Ingrese el Numero de documento: ");
        int id = sc.nextInt();
        sc.nextLine(); 
        
        System.out.print("Ingrese su Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese su Número de Teléfono: ");
        String telefono = sc.nextLine();
        
        System.out.print("Ingrese su Dirección: ");
        String direccion = sc.nextLine();
        
        return new cliente(id, nombre, telefono, direccion);
    }   
}