/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.EntradaBean;

/**
 *
 * @author rafa
 */
public class EntradaParam {

    private final HttpServletRequest request;

    public EntradaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public EntradaBean loadId(EntradaBean oEntrada) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oEntrada.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oEntrada.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEntrada;

    }

    public EntradaBean load(EntradaBean oEntrada) throws NumberFormatException, ParseException {
        try {
            
            if ((request.getParameter("titulo") != null)) {
                oEntrada.setTitulo(request.getParameter("titulo"));
            }
            if ((request.getParameter("contenido") != null)) {
                oEntrada.setContenido(request.getParameter("contenido"));
            }
            if ((request.getParameter("id_hilo") != null)) {
                oEntrada.getHilo().setId(Integer.parseInt(request.getParameter("id_hilo")));
            }
            if ((request.getParameter("id_usuario") != null)) {
                oEntrada.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("fecha") != null)) {
                oEntrada.setFecha(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fecha")));
            }
            
        } catch (NumberFormatException e) {
            throw new NumberFormatException("EntradaParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEntrada;
    }
}
