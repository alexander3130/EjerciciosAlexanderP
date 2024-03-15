public class EmpleadoPermanente extends Empleado{
    private String contrato;

    public EmpleadoPermanente(String nombre, int edad, double salario) {
        super(nombre, edad, salario);
        this.contrato = "Termino Indefinivo";
    }

    public String getContrato() {
        return contrato;
    }
}
