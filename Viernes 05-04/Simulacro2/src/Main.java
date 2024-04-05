import controllers.SpecialtyController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int option = 0, option2 = 0;

        do {
            option = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        1. Administrar una especialidad.
                        2. Administrar medicos.
                        3. Administrar pacientes.
                        4. Administrar citas.
                        5. Salir.
                        
                        Ingrese una opción.                   
                    """));
            switch (option){
                case 1:
                    do {
                        option2 = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        1. Listar especialidades.
                        2. Crear especialidad.
                        3. Eliminar especialidad.
                        4. Actualizar especialidad.
                        5. Volver.
                        
                        Ingrese una opción.                   
                    """));
                        switch (option2){
                            case 1:
                                SpecialtyController.findAll();
                                break;
                            case 2:
                                SpecialtyController.insert();
                                break;
                            case 3:
                                SpecialtyController.delete();
                                break;
                        }
                    }while(option2 !=5);
                    break;
                case 2:
                    return;
            }
        }while(option != 5);
    }
}