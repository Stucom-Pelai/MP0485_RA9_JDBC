/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOSQL;
import dao.IDAO;
import exception.DAO_Excep;
import exception.Student_Excep;
import model.Student;
import static api.DataValidation.intFromTo;
import static api.DataValidation.wordNotEmpty;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Fran Perez
 */
public class StudentControllerImplementation implements IStudentController {

    private IDAO daoEst;
    
    @Override
    public void readAllStudents() throws DAO_Excep, Student_Excep {
        daoEst = new DAOSQL();
        List<Student> students = daoEst.readALL();
        if (students.isEmpty()) {
            throw new Student_Excep("There aren't students registered yet.");
        } else {
            students.forEach(e -> {
                System.out.println(e.toString());
            });
        }
    }

    public void readStudents() throws DAO_Excep, Student_Excep {
        Scanner sc = new Scanner(System.in);
        daoEst = new DAOSQL();
        Student studentToSearch = null;
        List<Student> returned = new ArrayList<>();
        do {
            menuReadStudents();
            try {
                returned.clear();
                switch (sc.nextInt()) {
                    case 1:
                        String name = wordNotEmpty("Enter the student name: ");
                        studentToSearch = new Student(name);
                        returned = daoEst.read(studentToSearch);
                        if(returned.isEmpty()){
                            throw new Student_Excep(name + "doesn't exist.");
                        }else{
                            for(Student a : returned){
                                System.out.println(a);
                            }
                        }
                        break;
                    case 2:
                        int age = intFromTo(0, 120, "Enter the student age: ");
                        studentToSearch = new Student(age);
                        returned = daoEst.readByAge(studentToSearch);
                          if(returned.isEmpty()){
                            throw new Student_Excep("There aren't students with this age: " + age);
                        }else{
                            for(Student a : returned){
                                System.out.println(a);
                            } 
                        }
                        break;
                    case 3:
                        throw new Student_Excep("Exiting from menu Student Update");
                    default:
                        System.out.println("Wrong option, choose again.");
                        sc.nextLine();
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Wrong option, choose again.");
                sc.nextLine();
            }
        } while (true);
    }

    @Override
    public void insertStudent() throws DAO_Excep, Student_Excep {
        Scanner sc = new Scanner(System.in);
        String name = wordNotEmpty("Enter the student name: ");
        Student studentToInsert = new Student(name);
        daoEst = new DAOSQL();
        if (daoEst.read(studentToInsert).isEmpty()) {
            int age = intFromTo(0, 120, "Enter the student age: ");
            int registeredStudents = daoEst.insert(new Student(name, age));
            if (registeredStudents == 1) {
                throw new Student_Excep(studentToInsert.getName() + " has been registered.");
            }
        } else {
            throw new Student_Excep("There's a student named " + studentToInsert.getName() + " registered in database.");
        }
    }

    @Override
    public void deleteStudent() throws DAO_Excep, Student_Excep {
        readAllStudents();
        Scanner sc = new Scanner(System.in);
        String name = wordNotEmpty("Enter the student name: ");
        Student studentToDelete = new Student(name);
        daoEst = new DAOSQL();
        if (daoEst.read(studentToDelete) == null) {
            throw new Student_Excep(studentToDelete.getName() + " doesn't exist.");
        } else {
            if (daoEst.delete(new Student(name)) == 1) {
                throw new Student_Excep(studentToDelete.getName() + " has been deleted.");
            } else {
                throw new Student_Excep(studentToDelete.getName() + " has not been deleted.");
            }
        }
    }

    @Override
    public void deleteAllStudents() throws DAO_Excep, Student_Excep {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure to delete all students? [Y/N]");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            daoEst = new DAOSQL();
            if (daoEst.deleteALL() >= 1) {
                throw new Student_Excep("All students have been deleted.");
            } else {
                throw new Student_Excep("There were no registered students");
            }
        } else {

        }
    }

    @Override
    public void updateStudent() throws DAO_Excep, Student_Excep {
        readAllStudents();
        Scanner sc = new Scanner(System.in);
        String name = wordNotEmpty("Enter the student name: ");
        Student studentToUpdate = new Student(name);
        daoEst = new DAOSQL();
        if (daoEst.read(studentToUpdate) == null) {
            throw new Student_Excep(studentToUpdate.getName() + " doesn't exist.");
        } else {
            boolean modification = false;
            do {
                menuUpdateStudent();
                try {
                    switch (sc.nextInt()) {
                        case 1:
                            int age = intFromTo(0, 120, "Enter the new age of " + studentToUpdate.getName() + " [0, 120]: ");
                            studentToUpdate.setAge(age);
                            daoEst.update(studentToUpdate);
                            modification = true;
                            break;
                        case 2:
                            if (modification) {
                                throw new Student_Excep(studentToUpdate.getName() + " has been updated.");
                            } else {
                                throw new Student_Excep("Exiting from menu Student Update");
                            }
                        default:
                            System.out.println("Wrong option, choose again.");
                            sc.nextLine();
                            break;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Wrong option, choose again.");
                    sc.nextLine();
                }
            } while (true);
        }
    }
    
    
    @Override
    public void resetAges() throws DAO_Excep, Student_Excep {
            daoEst = new DAOSQL();
            System.out.println("Se han actualizado " + daoEst.resetAges() + " estudiantes") ;
    }

    private void menuUpdateStudent() {
        System.out.println("---------STUDENT UPDATE------");
        System.out.println("- 1. Modify Age             -");
        System.out.println("- 2. Exit                   -");
        System.out.println("- ::::::::::::::::::::::::: -");
        System.out.println("- Choose an option [1,2]:   -");
        System.out.println("-----------------------------");
    }

    private void menuReadStudents() {
        System.out.println("---------STUDENT SEARCH------");
        System.out.println("- 1. By name                -");
        System.out.println("- 2. Bye Age                -");
        System.out.println("- 3. Exit                   -");
        System.out.println("- ::::::::::::::::::::::::: -");
        System.out.println("- Choose an option [1,3]:   -");
        System.out.println("-----------------------------");
    }

}
