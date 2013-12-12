/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerOpenshift implements GenericConnection {

    @Override
    public Connection crearConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            String url = String.format("jdbc:mysql://%s:%s/databsexxxxxxxxxxxx", host, port);
            Connection connection = DriverManager.getConnection(url, "adminxxxxxx", "passwdxxxxxxxx");
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
