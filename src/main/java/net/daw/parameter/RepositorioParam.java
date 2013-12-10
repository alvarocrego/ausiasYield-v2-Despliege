/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.RepositorioBean;
import net.daw.helper.TextParser;

/**
 *
 * @author Ana
 */
public class RepositorioParam {

    private HttpServletRequest request;

    public RepositorioParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public RepositorioBean loadId(RepositorioBean oRepositorioBean) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oRepositorioBean.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oRepositorioBean.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oRepositorioBean;
    }

    public RepositorioBean load(RepositorioBean oRepositorioBean) throws NumberFormatException, ParseException, UnsupportedEncodingException, ServletException {
        try {
            if ((request.getParameter("titulo") != null)) {
                oRepositorioBean.setTitulo(request.getParameter("titulo"));
            }
            if ((request.getParameter("contenido") != null)) {
                //rafael tocar
                oRepositorioBean.setContenido(TextParser.textEncode(request.getParameter("contenido")));
                
            }
            if ((request.getParameter("id_usuario") != null)) {
                oRepositorioBean.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("id_lenguaje") != null)) {
                oRepositorioBean.getLenguaje().setId(Integer.parseInt(request.getParameter("id_lenguaje")));
            }
            if ((request.getParameter("id_documento") != null)) {
                oRepositorioBean.getDocumento().setId(Integer.parseInt(request.getParameter("id_documento")));
            }
             if ((request.getParameter("fecha") != null)) {
                oRepositorioBean.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(RepositorioParam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oRepositorioBean;
    }

}
