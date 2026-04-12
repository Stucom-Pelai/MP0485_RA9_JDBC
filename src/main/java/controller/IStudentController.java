/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOSQL;
import exception.DAO_Excep;
import exception.Student_Excep;
import model.Student;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fran Perez
 */
public interface IStudentController {

    public abstract void readAllStudents() throws DAO_Excep, Student_Excep;
    
    public abstract void readStudents() throws DAO_Excep, Student_Excep;

    public abstract void insertStudent() throws DAO_Excep, Student_Excep;

    public abstract void updateStudent() throws DAO_Excep, Student_Excep;

    public void deleteStudent() throws DAO_Excep, Student_Excep;
    
    public void deleteAllStudents() throws DAO_Excep, Student_Excep;    
    
    public void resetAges() throws DAO_Excep, Student_Excep;      

}
