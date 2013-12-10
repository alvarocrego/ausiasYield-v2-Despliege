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
import net.daw.bean.BacklogBean;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogParam {

    private final HttpServletRequest request;

    public BacklogParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public BacklogBean loadId(BacklogBean oBacklog) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oBacklog.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oBacklog.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oBacklog;

    }

    public BacklogBean load(BacklogBean oBacklog) throws NumberFormatException, ParseException {
        try {
            if ((request.getParameter("id_usuario") != null)) {
                oBacklog.getUsuario().setId(Integer.parseInt(request.getParameter("id_usuario")));
            }

            if ((request.getParameter("id_requerimiento") != null)) {
                oBacklog.getRequerimiento().setId(Integer.parseInt(request.getParameter("id_requerimiento")));
            }

            if ((request.getParameter("enunciado") != null)) {
                oBacklog.setEnunciado(request.getParameter("enunciado"));
            }
            if ((request.getParameter("descripciondetallada") != null)) {
                oBacklog.setDescripciondetallada(request.getParameter("descripciondetallada"));
            }

            if ((request.getParameter("fechaalta") != null)) {
                oBacklog.setFechaalta(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fechaalta")));
            }

            if ((request.getParameter("fechainicio") != null)) {
                oBacklog.setFechainicio(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fechainicio")));
            }

            if ((request.getParameter("fechafin") != null)) {
                oBacklog.setFechafin(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fechafin")));
            }

            if ((request.getParameter("fechainicioprevista") != null)) {
                oBacklog.setFechainicioprevista(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fechainicioprevista")));
            }

            if ((request.getParameter("fechafinprevista") != null)) {
                oBacklog.setFechafinprevista(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("fechafinprevista")));
            }

            if ((request.getParameter("horasduracionprevista") != null)) {
                oBacklog.setHorasduracionprevista(Integer.parseInt(request.getParameter("horasduracionprevista")));
            }

            if ((request.getParameter("porcentajecompletado") != null)) {
                oBacklog.setPorcentajecompletado(Integer.parseInt(request.getParameter("porcentajecompletado")));
            }

            if ((request.getParameter("sprint") != null)) {
                oBacklog.setSprint(Integer.parseInt(request.getParameter("sprint")));
            }

            if ((request.getParameter("release") != null)) {
                oBacklog.setRelease(Integer.parseInt(request.getParameter("release")));
            }

        } catch (NumberFormatException e) {
            throw new NumberFormatException("CompraParam: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oBacklog;
    }
}
