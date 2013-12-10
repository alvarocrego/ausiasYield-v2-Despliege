/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.parameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.DocumentoBean;

/**
 *
 * @author al037294
 */
public class DocumentoParam {

    private final HttpServletRequest request;

    /**
     *
     * @param request
     * @throws Exception
     */
    public DocumentoParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    /**
     *
     * @param oDocumento
     * @return
     * @throws NumberFormatException
     */
    public DocumentoBean loadId(DocumentoBean oDocumento) throws NumberFormatException {
        try {
            if (request.getParameter("id") != null) {
                oDocumento.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oDocumento.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oDocumento;
    }

    /**
     *
     * @param oDocumento
     * @return
     * @throws NumberFormatException
     */
    public DocumentoBean load(DocumentoBean oDocumento) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("titulo") != null)) {
                oDocumento.setTitulo(request.getParameter("titulo"));
            }
            if ((request.getParameter("contenido") != null)) {
                oDocumento.setContenido(request.getParameter("contenido"));
            }
            if ((request.getParameter("fecha") != null)&&request.getParameter("fecha")!="") {
                oDocumento.setFecha(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fecha")));
            }
            if ((request.getParameter("nota") != null)) {
                oDocumento.setNota(Integer.parseInt(request.getParameter("nota")));
            }
            if ((request.getParameter("id_usuario") != null)) {
                oDocumento.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }
            if ((request.getParameter("etiquetas") != null)) {
                oDocumento.setEtiquetas(request.getParameter("etiquetas"));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("DocumentoParam: Error: Load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oDocumento;
    }
}
