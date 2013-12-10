/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnection implements GenericConnection {

    @Override
    public Connection crearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ausiasyield", "root", "bitnami");
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
