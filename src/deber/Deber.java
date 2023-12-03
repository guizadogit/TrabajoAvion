package deber;

import java.util.Date;
import java.util.Scanner;

public class Deber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double multaCancelacion = 20 / 100.0;
        double porcentajeMultaCancelacion=20;
        int asientosDisponibles,aTotales, tiempoReservaMinutos,opcion;
        opcion=0;

        System.out.print("Ingrese la cantidad de asientos disponibles: ");
        asientosDisponibles = scanner.nextInt();
        aTotales=asientosDisponibles;

        System.out.print("Ingrese el tiempo de reserva permitido en minutos: ");
        tiempoReservaMinutos = scanner.nextInt();
        Date tiempoLimiteReserva = new Date(System.currentTimeMillis() + (tiempoReservaMinutos * 60 * 1000));


        OUTER:
        while (opcion!=3) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Reservar vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Ingrese su nombre: ");
                    String nombrePasajero = scanner.nextLine();
                    System.out.print("Ingrese la ruta (local/internacional): ");
                    String tipoBoleto = scanner.nextLine();
                    System.out.print("Ingrese la cantidad de equipaje (en kg): ");
                    double pesoEquipaje = scanner.nextDouble();

                    if (asientosDisponibles > 0 && new Date().before(tiempoLimiteReserva)) {
                        asientosDisponibles--;
                        System.out.println("Reserva exitosa para " + nombrePasajero + ". Asiento asignado.");

                        if (tipoBoleto.equalsIgnoreCase("internacional") && pesoEquipaje > 30) {
                            System.out.println("Atencion: El equipaje internacional no puede exceder los 30 kg.");
                        }

                    } else {
                        double precioPasaje = 500.0;
                        double multa = precioPasaje * multaCancelacion;
                        System.out.println("Cancelacion fuera de tiempo. Se aplica una multa del " + (porcentajeMultaCancelacion * 100) + "%: $" + multa);
                    }
                    break;
                case 2:
                    if (asientosDisponibles<aTotales){
                        System.out.println("Vuelo cancelado. Reembolso procesado.");
                        asientosDisponibles++;
                    }else{
                        System.out.println("No existen vuelos para cancelar");
                    }
                    
                    break;
                case 3:
                    System.out.println("Saliendo del programa. Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no valida. Por favor, seleccione nuevamente.");
                    break;
            }
        }
    }
}
