import javax.swing.*;
import java.util.Date;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GestionEmpleados gestionEmpleados = new GestionEmpleados();
        int option = 0;
        int tipoContrato;
        Scanner objScan = new Scanner(System.in);

        do {
            System.out.println("""
                    MENU DE OPCIONES!
                    1. Registrar nuevo empleado
                    2. Eliminar Empleado
                    3. Mostrar Empleados
                    4. Salir
                    """);
            option = objScan.nextInt();
            switch (option){
                case 1:
                    System.out.println("Ingrese el nombre completo del empleado: ");
                    String nombre = objScan.next();
                    System.out.println("Ingrese la edad: ");
                    int edad = objScan.nextInt();
                    System.out.println("Ingrese el salario: ");
                    double salario = objScan.nextDouble();
                    int option2=0;
                    System.out.println("""
                            Elige el tipo de contrato:
                            1. Termino Indefinido
                            2. Termino Fijo
                            """);
                    option2 = objScan.nextInt();
                    if (option2==2){
                        System.out.println("Ingrese el año de inicio contrato: ");
                        int año = objScan.nextInt();
                        System.out.println("Ingrese el mes de inicio contrato: ");
                        int mes =objScan.nextInt();
                        System.out.println("Ingrese el dia de inicio contrato: ");
                        int dia =objScan.nextInt();
                        Date inicio = new Date(año, mes, dia);
                        System.out.println("Ingrese el año de inicio contrato: ");
                        int añof = objScan.nextInt();
                        System.out.println("Ingrese el mes de inicio contrato: ");
                        int mesf =objScan.nextInt();
                        System.out.println("Ingrese el dia de inicio contrato: ");
                        int diaf = objScan.nextInt();
                        Date fin = new Date(añof, mesf, diaf);
                        EmpleadoTemporal temporal1 = new EmpleadoTemporal(nombre, edad, salario,inicio,fin);
                        gestionEmpleados.agregarEmpleadoT(temporal1);
                        System.out.println("Empledo Registrado Correctamente");
                    }else {
                        EmpleadoPermanente permanente1 = new EmpleadoPermanente(nombre, edad, salario);
                        gestionEmpleados.agregarEmpleadoP(permanente1);
                        System.out.println("Empledo Registrado Correctamente");
                    }
                    break;
                case 2:
                    System.out.println("""
                            Elige el tipo de Empleado
                            1. Empleado Permanente
                            2. Empleado Temporal
                            """);
                    int tipoE = objScan.nextInt();
                    if (tipoE==1){
                        gestionEmpleados.mostrarEmpleadosP();
                        int empleadoE = objScan.nextInt();
                        gestionEmpleados.eliminarEmpleadoP(empleadoE);
                    }else {
                        gestionEmpleados.mostrarEmpleadosT();
                        int empleadoE = objScan.nextInt();
                        gestionEmpleados.eliminarEmpleadoT(empleadoE);
                    }
                    break;
                case 3:
                    gestionEmpleados.mostrarEmpleadosT();
                    gestionEmpleados.mostrarEmpleadosP();
                    break;
            }
        }while (option !=4);
    }
}