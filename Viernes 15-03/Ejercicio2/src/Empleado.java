public class Empleado extends Persona{
    private static int idEmpleado = 1;
    private double salario;

    public Empleado(String nombre, int edad, double salario) {
        super(nombre, edad);
        this.idEmpleado ++;
        this.salario = salario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
