package entities;

public class Specialty {
    private int id_specialty;
    private String specialty_name;
    private String description;

    public Specialty() {
    }
    public Specialty(String specialty_name, String description) {
        this.specialty_name = specialty_name;
        this.description = description;
    }

    //  Setter and getters
    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //      toString


    @Override
    public String toString() {
        return  "========================\n"+
                "Especialidad: \n" +
                "ID ESPECIALIDAD: " + id_specialty + "\n"+
                "NOMBRE ESPECIALIDAD: '" + specialty_name + '\n' +
                "DESCRIPCION ESPECIALIDAD: " + description + '\n';
    }
}
