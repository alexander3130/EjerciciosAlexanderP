package entity;

import model.EspecialidadModel;

public class Medico {
    private int id_medico;
    private String nombre;
    private String apellidos;
    private int id_especialidad;
    private Especialidad objE;

    public Medico() {
    }

    public Medico(int id_medico, String nombre, String apellidos, int id_especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.id_especialidad = id_especialidad;
    }

    public Especialidad getObjE() {
        return objE;
    }

    public void setObjE(Especialidad objE) {
        this.objE = objE;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    @Override
    public String toString() {
        return "Medico\n" +
                "   Id Medico= " + id_medico + "\n" +
                "   Nombre= " + nombre + '\n' +
                "   Apellidos= " + apellidos + '\n' +
                "   Especialidad= " + objE.getNombre() + "\n";
    }
}
