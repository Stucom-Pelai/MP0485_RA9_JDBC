package view;

import exception.DAO_Excep;
import exception.Student_Excep;
import controller.IStudentController;
import controller.StudentControllerImplementation;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.SQLException;

/**
 *
 * @author Fran Perez
 */
public class Main {

    private static IStudentController iStuCont;

    public static void menu() {
        System.out.println("*********MAIN MENU*******************************");
        System.out.println("* 1. Read all students                          *");
        System.out.println("* 2. Search students                            *");
        System.out.println("* 3. Add a student                              *");
        System.out.println("* 4. Update a student                           *");
        System.out.println("* 5. Delete a student                           *");
        System.out.println("* 6. Delete all student                         *");
        System.out.println("* 7. Reset students age to zero (transaction)   *");
        System.out.println("* 8. Exit                                       *");
        System.out.println("* --------------------------------------------- *");
        System.out.println("* Choose an option [1,7]:                       *");
        System.out.println("*************************************************");
    }

    public static void main(String[] args) throws SQLException, DAO_Excep {
 
        Scanner sc = new Scanner(System.in);
        iStuCont = new StudentControllerImplementation();
        boolean exit = false;
        do {
            try {
                menu();
                switch (sc.nextInt()) {
                    case 1:
                        iStuCont.readAllStudents();
                        break;
                    case 2:
                        iStuCont.readStudents();
                        break;
                    case 3:
                        iStuCont.insertStudent();
                        break;
                    case 4:
                        iStuCont.updateStudent();
                        break;
                    case 5:
                        iStuCont.deleteStudent();
                        break;
                    case 6:
                        iStuCont.deleteAllStudents();
                        break;
                    case 7:
                        iStuCont.resetAges();
                        break;
                    case 8:
                        exit = true;
                        System.out.println("Closing application");
                        break;
                    default:
                        System.out.println("Wrong option, choose again.");
                }
            } catch (InputMismatchException ex ) {
                System.out.println("Wrong option, choose again.");
                sc.nextLine();
            }catch (DAO_Excep | Student_Excep ex ) {
                System.out.println(ex.getMessage());
                sc.nextLine();
            }
        } while (!exit);
    }
}

