package entity;

import model.MedicoModel;
import model.PacienteModel;

import java.sql.Time;
import java.sql.Date;

public class Cita {
    private int id_cita;
    private int id_paciente;
    private int id_medico;
    private Date fecha_cita;
    private Time hora_cita;
    private String motivo;
    private Medico objM;
    private Paciente objP;

    public Cita() {
    }

    public Medico getObjM() {
        return objM;
    }

    public void setObjM(Medico objM) {
        this.objM = objM;
    }

    public Paciente getObjP() {
        return objP;
    }

    public void setObjP(Paciente objP) {
        this.objP = objP;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public Time getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(Time hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "\nCita\n" +
                "   Id Cita=" + id_cita + "\n" +
                "   Fecha Cita= " + fecha_cita + "\n" +
                "   Hora Cita= " + hora_cita + "\n" +
                "   Motivo= " + motivo + "\n" +
                "   Medico= " + objM.getNombre() + " " + objM.getApellidos() + "\n" +
                "   Paciente= " + objP.getNombre() + " " + objP.getApellidos() + "\n";
    }
}
