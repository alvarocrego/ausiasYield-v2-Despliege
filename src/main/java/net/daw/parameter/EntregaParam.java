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
import net.daw.bean.EntregaBean;
import net.daw.bean.PreguntaBean;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author rafa
 */
public class EntregaParam {

    private final HttpServletRequest request;

    public EntregaParam(HttpServletRequest request) throws Exception {
        this.request = request;
    }

    public EntregaBean loadId(EntregaBean oEntrega) throws ServletException {
        try {
            if (request.getParameter("id") != null) {
                oEntrega.setId(Integer.parseInt(request.getParameter("id")));
            } else {
                oEntrega.setId(0);
            }
        } catch (NumberFormatException e) {
            throw new ServletException("Controller: Error: loadId: Formato de datos en parámetros incorrecto " + e.getMessage());
        }
        return oEntrega;

    }

    public EntregaBean load(EntregaBean oEntrega) throws NumberFormatException, ParseException {
        try {
            if (request.getParameter("id_documento") != null) {
                oEntrega.getDocumento().setId(Integer.parseInt(request.getParameter("id_documento")));
            }
            if (request.getParameter("id_actividad") != null) {
                oEntrega.getActividad().setId(Integer.parseInt(request.getParameter("id_actividad")));
            }
            if (request.getParameter("nota") != null) {
                oEntrega.setNota( Integer.valueOf(request.getParameter("nota")) );
            }
            if(request.getParameter("fecha") != null) {
                String fecha = request.getParameter("fecha");
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                oEntrega.setFecha(formatoFecha.parse(fecha));
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto " + e.getMessage());
        } catch (ParseException e) {
            throw new NumberFormatException("Controller: Error: load: Formato de datos en parámetros incorrecto (fecha) " + e.getMessage());
        }
        return oEntrega;
    }

//    public EntregaBean loadTipoEntrega(EntregaBean oEntrega) throws NumberFormatException {
//        try {
//            if ((request.getParameter("id_tipoentrega") != null)) {
//                TipoentregaBean oTipoentrega = new TipoentregaBean(Integer.parseInt(request.getParameter("id_tipoentrega")));
//                oEntrega.setTipoEntrega(oTipoentrega);
//            }
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException("Controller: Error: loadTipoEntrega: Formato de datos en parámetros incorrecto " + e.getMessage());
//        }
//        return oEntrega;
//    }
}
