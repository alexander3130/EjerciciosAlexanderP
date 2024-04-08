package controladores;

import entity.Especialidad;
import entity.Paciente;
import model.EspecialidadModel;

import javax.swing.*;
import java.util.List;

public class EspecialidadControlador {
    EspecialidadModel objM = new EspecialidadModel();

    public String getAll(List<Object> objectList) {
        String list = "Lista de Especializaciones: \n";
        for (Object esp : objectList) {
            Especialidad objEsp = (Especialidad) esp;
            list += objEsp.toString() + "\n";
        }
        return list;
    }

    public void findById() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, getAll(objM.findAll()) + "\nIngrese el Id:"));
        JOptionPane.showMessageDialog(null, objM.findById(id).toString());
    }

    public void insertE() {
        Especialidad esp = new Especialidad();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad");
        String descriccion = JOptionPane.showInputDialog("Ingrese la descriccion de la especialidad");

        esp.setNombre(nombre);
        esp.setDescripcion(descriccion);

        esp = (Especialidad) this.objM.insert(esp);
        JOptionPane.showMessageDialog(null, esp);
    }

    public void updateE() {
        String list = getAll(objM.findAll());
        int id_especialidad = Integer.parseInt(JOptionPane.showInputDialog(list + "Ingrese el ID de la especialidad"));
        Especialidad esp = (Especialidad) objM.findById(id_especialidad);
        if (esp == null) {
            JOptionPane.showMessageDialog(null, "Busqueda sin resultados.");
        } else {
            esp.setNombre(JOptionPane.showInputDialog(null, "Ingrese el nombre si es necesario: ", esp.getNombre()));
            esp.setDescripcion(JOptionPane.showInputDialog(null, "Ingresa la descripcion si es necesario: ", esp.getDescripcion()));
            this.objM.update(esp);
        }
    }

    public void deleteE() {
        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(getAll(objM.findAll()) + "\nIngrese el Id de la especialidad a eliminar: "));
        Especialidad objEspecialidad = (Especialidad) objM.findById(idDelete);
        if (objEspecialidad == null) {
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Estas seguro? : \n" + objEspecialidad);
            if (confirm == 0) {
                this.objM.delete(objEspecialidad);
            }
        }
    }
}
