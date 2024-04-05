package controllers;

import entities.Specialty;
import jdk.jshell.execution.Util;
import models.SpecialtyModel;
import utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialtyController {
    public static void insert(){
        String name = JOptionPane.showInputDialog("Ingrese el nombre de la especialidad: ");
        String description = JOptionPane.showInputDialog("Ingrese la descripción de la especialidad: ");

        instanceModel().insert(new Specialty(name, description));
    }
    public static void findAll(){
        JOptionPane.showMessageDialog(null, instanceModel().findAll());
    }

    public static void getAll(){
        String list = getAll(instanceModel().findAll());
        JOptionPane.showMessageDialog(null, list);
    }

    public static String getAll(List<Object> list){
        String listString = "Lista de registros: \n";

        for (Object temp: list ){
            Specialty objSpecialty = (Specialty) temp;
            listString+= objSpecialty.toString() + "\n";
        }
        return listString;
    }
    public static void delete(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Specialty objSelected = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        instanceModel().delete(objSelected);


    }
    public static void update(){
        Object[] options = Utils.listToArray(instanceModel().findAll());
        Specialty objSelected = (Specialty) JOptionPane.showInputDialog(
                null,
                "Selecciona una especialidad para actualizar:",
                "",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
    public static SpecialtyModel instanceModel(){
        return new SpecialtyModel();
    }
}
