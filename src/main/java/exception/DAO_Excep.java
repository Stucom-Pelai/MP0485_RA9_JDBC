/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author Fran Perez
 */
public class DAO_Excep extends Exception{

    /**
     * Creates a new instance of <code>ExcepcionesAccesoDatos</code> without
     * detail message.
     */
    public DAO_Excep() {
    }

    /**
     * Constructs an instance of <code>ExcepcionesAccesoDatos</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DAO_Excep(String msg) {
        super(msg);
    }
}
