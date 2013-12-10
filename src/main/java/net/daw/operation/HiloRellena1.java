package net.daw.operation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.HiloBean;
import net.daw.dao.HiloDao;
import net.daw.helper.Contexto;

public class HiloRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        HiloBean oHiloBean = new HiloBean();
        HiloDao oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());
        String fecha = "";
        ArrayList<String> titulo = new ArrayList<>();

        titulo.add("Hilo50");
        titulo.add("Hilo51");
        titulo.add("Hilo82");
        titulo.add("Hilo53");
        titulo.add("Hilo74");
        titulo.add("Hilo530");
        titulo.add("Hilo450");
        titulo.add("Hilo70");

        SimpleDateFormat oSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        fecha = ("2013-10-25");

        int index;
        String nombre;
        Iterator<String> iterador = titulo.listIterator();

        Random generator;
        while (iterador.hasNext()) {
            nombre = iterador.next();
            generator = new Random();
            index = generator.nextInt(titulo.size());
            oHiloBean.setId(0);
            oHiloBean.setNombre(nombre);
            oHiloBean.setFecha(oSimpleDateFormat.parse(fecha));
            try {
                oHiloDao.set(oHiloBean);

            } catch (Exception e) {
                throw new ServletException("HiloController: Update Error: Phase 2: " + e.getMessage());
            }
        }

        return "OK- información generada.";
    }
}
