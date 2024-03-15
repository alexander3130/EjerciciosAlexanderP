import java.util.ArrayList;
import java.util.Date;

public class EmpleadoTemporal extends Empleado{
    private Date inicioContrato;
    private Date finContrato;

    public EmpleadoTemporal(String nombre, int edad, double salario, Date inicioContrato, Date finContrato) {
        super(nombre, edad, salario);
        this.inicioContrato = inicioContrato;
        this.finContrato = finContrato;
    }

    public Date getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Date inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Date getFinContrato() {
        return finContrato;
    }

    public void setFinContrato(Date finContrato) {
        this.finContrato = finContrato;
    }


}
