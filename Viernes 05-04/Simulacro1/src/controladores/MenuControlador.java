package controladores;

import model.EspecialidadModel;

import javax.swing.*;

public class MenuControlador {
    public void menuPrincipal() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Registros:
                    2. Actualizaciones:
                    3. Eliminar:
                    4. Información:
                    5. Citas:
                    6. Salir
                    """);
            switch (option) {
                case "1":
                    menuRegistros();
                    break;
                case "2":
                    menuActualizaciones();
                    break;
                case "3":
                    menuEliminar();
                    break;
                case "4":
                    menuInfo();
                    break;
                case "5":
                    menuCita();
                    break;
            }
        } while (!option.equals("6"));
    }

    EspecialidadControlador objE = new EspecialidadControlador();
    MedicoControlador objM = new MedicoControlador();
    PacienteControlador objP = new PacienteControlador();
    CitaControlador objC = new CitaControlador();

    public void menuRegistros() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU REGISTRO
                    1. Nueva Especialidad
                    2. Nuevo Medico
                    3. Nuevo Paciente
                    4. Salir
                    """);
            switch (option) {
                case "1":
                    objE.insertE();
                    break;
                case "2":
                    objM.insertM();
                    break;
                case "3":
                    objP.insertP();
                    break;
            }
        } while (!option.equals("4"));
    }

    public void menuActualizaciones() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU ACTUALIZACIONES
                    1. Actualizar Especialidad
                    2. Actualizar Medico
                    3. Actualizar Paciente
                    4. Salir
                    """);
            switch (option) {
                case "1":
                    objE.updateE();
                    break;
                case "2":
                    objM.updateM();
                    break;
                case "3":
                    objP.updateP();
                    break;
            }
        } while (!option.equals("4"));
    }

    public void menuEliminar() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Eliminar Especialidad:
                    2. Eliminar Medico:
                    3. Eliminar Paciente:
                    4. Salir
                    """);
            switch (option) {
                case "1":
                    objE.deleteE();
                    break;
                case "2":
                    objM.deleteM();
                    break;
                case "3":
                    objP.deleteP();
                    break;
            }
        } while (!option.equals("4"));
    }
    public void menuInfo () {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                        MENU
                        1. Buscar Especialidad:
                        2. Listar Medicos por Especialidad:
                        3. Buscar Paciente por documento:
                        4. Salir
                        """);
            switch (option) {
                case "1":
                    objE.findById();
                    break;
                case "2":
                    objM.getEsp();
                    break;
                case "3":
                    objP.getByCC();
                    break;
            }
        } while (!option.equals("4"));
    }

    public void menuCita() {
        String option = "";
        do {
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Nueva Cita:
                    2. Actualizar Cita:
                    3. Eliminar Cita:
                    4. Buscar Cita:
                    5. Salir
                    """);
            switch (option) {
                case "1":
                    objC.insertC();
                    break;
                case "2":
                    objC.updateC();
                    break;
                case "3":
                    objC.deleteC();
                    break;
                case "4":
                    objC.getCita();
                    break;
            }
        } while (!option.equals("5"));

    }
}
