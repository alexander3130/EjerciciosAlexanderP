package controladores;

import entity.Cita;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SimpleTimeZone;

public class CitaControlador {
    PacienteControlador objPC = new PacienteControlador();
    PacienteModel objPM = new PacienteModel();
    MedicoControlador objMC = new MedicoControlador();
    MedicoModel objMM = new MedicoModel();
    CitaModel objCitaModal = new CitaModel();


    public String getAll(List<Object> object) {
        String list = "Lista de Citas: \n";
        for (Object cita : object) {
            Cita objCita = (Cita) cita;
            list += objCita.toString() + "\n";
        }
        return list;
    }

    public void insertC() {
        Cita cita = new Cita();
        String id_pac = objPC.getAll(objPM.findAll());
        int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(id_pac + "\nIngrese el Id del paciente" + "\nSi el paciente no esta registrado marque 0 !"));
        if (id_paciente != 0) {
            cita.setId_paciente(id_paciente);
        } else {
            objPC.insertP();
            id_paciente = Integer.parseInt(JOptionPane.showInputDialog(id_pac + "\nIngrese el Id del paciente"));
            cita.setId_paciente(id_paciente);
        }
        String id_med = objMC.getAll(objMM.findAll());
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(id_med + "\nIngrese el Id del medico"));
        cita.setId_medico(id_medico);
        boolean dateCorrect = false;
        while (!dateCorrect) {
            try {
                String inputDate = JOptionPane.showInputDialog("Ingrese la fecha de la cita (YYYY-MM-DD):");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimiento = LocalDate.parse(inputDate, formatter);
                Date dateOfBirth = null;
                // Convertimos el String a un objeto Date
                dateOfBirth = Date.valueOf(fechaNacimiento);
                cita.setFecha_cita(dateOfBirth);
                dateCorrect = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
            }
        }
        String horaStr = JOptionPane.showInputDialog(null, "Introduce la hora (formato HH:mm:ss):");
        if (horaStr == null) {
            JOptionPane.showMessageDialog(null, "Hora inválida. Introduce una hora en formato HH:mm:ss");
        }
        try {
            // Convertir la hora al formato adecuado para Java
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            System.out.println("hola");
            java.util.Date date = sdf.parse(horaStr);
            // Convertir la hora al formato adecuado para SQL (time)
            Time timestamp = new Time(date.getTime());
            cita.setHora_cita(timestamp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la hora: " + e.getMessage());
        }
        String motivo = JOptionPane.showInputDialog("Ingrese el motivo de la cita: ");
        cita.setMotivo(motivo);
        cita = (Cita) this.objCitaModal.insert(cita);
    }

    public void updateC() {
        String listaC = getAll(objCitaModal.findAll());
        int id_cita = Integer.parseInt(JOptionPane.showInputDialog(listaC + "\nIngrese el Id de la cita: "));
        Cita objCita = (Cita) this.objCitaModal.findById(id_cita);
        int confirm = 1;
        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            boolean dateCorrect = false;
            while (!dateCorrect) {
                try {
                    String inputDate = JOptionPane.showInputDialog(null, "Ingrese la fecha de la cita (YYYY-MM-DD):", objCita.getFecha_cita());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate fechaNacimiento = LocalDate.parse(inputDate, formatter);
                    Date dateOfBirth = null;
                    // Convertimos el String a un objeto Date
                    dateOfBirth = Date.valueOf(fechaNacimiento);
                    objCita.setFecha_cita(dateOfBirth);
                    dateCorrect = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Utilice el formato YYYY-MM-DD.\n" + e.getMessage());
                }
            }
            String horaStr = JOptionPane.showInputDialog(null, "Introduce la hora (formato HH:mm:ss): ", objCita.getHora_cita());
            if (horaStr == null || !horaStr.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")) {
                JOptionPane.showMessageDialog(null, "Hora inválida. Introduce una hora en formato HH:mm:ss");
            }
            try {
                // Convertir la hora al formato adecuado para Java
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                java.util.Date date = sdf.parse(horaStr);
                // Convertir la hora al formato adecuado para SQL (time)
                Time timestamp = new Time(date.getTime());
                objCita.setHora_cita(timestamp);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al convertir la hora: " + e.getMessage());
            }
            Medico temp = (Medico) objMM.findById(objCita.getId_medico());
            confirm = JOptionPane.showConfirmDialog(null, "¿Deseas actualizar al medico?: \n" + temp.toString());
            if (confirm == 0){
                String lista = objMC.getAll(objMM.findAll());
                int id_medico = Integer.parseInt(JOptionPane.showInputDialog(lista+"Ingrese el Id del medico: "));
                objCita.setId_medico(id_medico);
            }
            Paciente tempP = (Paciente) objPM.findById(objCita.getId_paciente());
            int confirmP = 1;
            if (confirmP == 0){
                String lista = objPC.getAll(objPM.findAll());
                int id_paciente = Integer.parseInt(JOptionPane.showInputDialog(lista+"Ingrese el Id del paciente: " ));
                objCita.setId_paciente(id_paciente);
            }
            this.objCitaModal.update(objCita);
        }
    }

    public void deleteC(){
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(getAll(objCitaModal.findAll())+"\nIngrise el Id de la cita a eliminar: "));
        Cita objCita = (Cita) objCitaModal.findById(idDelete);
        if (objCita==null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro? : "+objCita);
            if (confirm==0){
                this.objCitaModal.delete(objCita);
            }
        }
    }

    public void getCita(){
        int id_cita = Integer.parseInt(JOptionPane.showInputDialog(null,getAll(objCitaModal.findAll())+"\nIngrese el id de la cita: "));
        Cita objCita = (Cita) objCitaModal.findById(id_cita);
        JOptionPane.showMessageDialog(null,"\nCita\n" +
                "   Id Cita=" + objCita.getId_cita() +
                "   Fecha Cita= " + objCita.getFecha_cita() + "\n" +
                "   Hora Cita= " + objCita.getHora_cita() + "\n" +
                "   Motivo= " + objCita.getMotivo() + "\n" +
                "   ***" + objCita.getObjM().toString() + "\n" +
                "   ***" + objCita.getObjP().toString() + "\n");
    }
}
