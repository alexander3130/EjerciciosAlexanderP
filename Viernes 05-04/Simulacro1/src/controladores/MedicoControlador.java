package controladores;

import entity.Especialidad;
import entity.Medico;
import model.EspecialidadModel;
import model.MedicoModel;

import javax.swing.*;
import java.util.List;

public class MedicoControlador {
    MedicoModel objM = new MedicoModel();
    EspecialidadModel objEM = new EspecialidadModel();
    EspecialidadControlador objE = new EspecialidadControlador();

    public String getAll(List<Object> objectList) {
        String list = "Lista de Medicos: \n";
        for (Object med : objectList) {
            Medico objMed = (Medico) med;
            list += objMed.toString() + "\n";
        }
        return list;
    }

    public void getEsp() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(objEM.findAll()+"Ingrese el Id de la especialidad"));
        JOptionPane.showMessageDialog(null, getAll(objM.findyEsp(id)));
    }

    public void insertM() {
        Medico medico = new Medico();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del medico");
        String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos del medico");
        String listE = objE.getAll(objEM.findAll());
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(listE + "Ingrese el Id de la especialidad "));

        medico.setNombre(nombre);
        medico.setApellidos(apellidos);
        medico.setId_especialidad(id_especialidad);

        medico = (Medico) this.objM.insert(medico);
    }

    public void updateM() {
        String listaM = getAll(objM.findAll());
        int id_medico = Integer.parseInt(JOptionPane.showInputDialog(listaM + "\nIngresa el Id del Medico: "));
        Medico obj = (Medico) this.objM.findById(id_medico);

        int confirm = 1;
        if (obj == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            obj.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre si es necesario: ", obj.getNombre()));
            obj.setApellidos(JOptionPane.showInputDialog(null, "Ingrese los nuevos apellidos si es necesario: ", obj.getApellidos()));
            Especialidad temp = (Especialidad) objEM.findById(obj.getId_especialidad());

            confirm = JOptionPane.showConfirmDialog(null, "Deseas actualizar la especialidad? : \n" + temp.getNombre());
            int id_especialidad = obj.getId_especialidad(); 

            if (confirm == 0) {
                String lista = objE.getAll(objEM.findAll());
                id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(lista + "Ingrese el ID de la especialidad"));
            }
            obj.setId_especialidad(id_especialidad);
            this.objM.update(obj);
        }
    }

    public void deleteM() {
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(getAll(objM.findAll()) + "\nIngrese el Id del medico a eliminar: "));
        Medico objMedico = (Medico) objM.findById(idDelete);
        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Medico no encontrado");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objMedico);
            if (confirm == 0) {
                this.objM.delete(objMedico);
            }
        }
    }
}
