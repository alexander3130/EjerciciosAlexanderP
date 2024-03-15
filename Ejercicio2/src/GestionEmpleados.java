import java.util.ArrayList;

public class GestionEmpleados {
    private ArrayList<EmpleadoTemporal> empleadosT;
    private ArrayList<EmpleadoPermanente> empleadosP;


    public GestionEmpleados() {
        this.empleadosT = new ArrayList<>();
        this.empleadosP = new ArrayList<>();
    }

    public void agregarEmpleadoT(EmpleadoTemporal empleado) {
        empleadosT.add(empleado);
    }
    public void agregarEmpleadoP(EmpleadoPermanente empleado) {
        empleadosP.add(empleado);
    }

    public void eliminarEmpleadoT(int idEmpleado) {
        for (EmpleadoTemporal empleado : empleadosT) {
            if (empleado.getIdEmpleado() == idEmpleado) {
                empleadosT.remove(empleado);
                break;
            }
        }
    }

    public void eliminarEmpleadoP(int idEmpleado) {
        for (EmpleadoPermanente empleado : empleadosP) {
            if (empleado.getIdEmpleado() == idEmpleado) {
                empleadosP.remove(empleado);
                break;
            }
        }
    }

    public void mostrarEmpleadosT() {
        for (EmpleadoTemporal empleado : empleadosT) {
            System.out.println("Nombre: " + empleado.getNombre() + "\n"+
                    "Edad: " + empleado.getEdad() + "\n"+
                    "ID Empleado: " + empleado.getIdEmpleado() +"\n"+
                    "Salario: " + empleado.getSalario()+"\n"+
                    "Inicio Contrato: "+ empleado.getInicioContrato()+"\n"+
                    "Fin Contrato: "+empleado.getFinContrato()+"\n"
            );
        }
    }

    public void mostrarEmpleadosP() {
        for (EmpleadoPermanente empleado : empleadosP) {
            System.out.println("Nombre: " + empleado.getNombre() + "\n"+
                    "Edad: " + empleado.getEdad() + "\n"+
                    "ID Empleado: " + empleado.getIdEmpleado() +"\n"+
                    "Salario: " + empleado.getSalario()+"\n"+
                    "Tipo de Contrato: "+ empleado.getContrato()+"\n"
            );
        }
    }


}