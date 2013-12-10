package net.daw.operation;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.EntradaBean;
import net.daw.dao.EntradaDao;
import net.daw.dao.HiloDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;

public class EntradaRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        EntradaBean oEntradaBean = new EntradaBean();
        EntradaDao oEntradaDao = new EntradaDao(oContexto.getEnumTipoConexion());

        HiloDao oHiloDao = new HiloDao(oContexto.getEnumTipoConexion());
        UsuarioDao oUsuarioDao = new UsuarioDao(oContexto.getEnumTipoConexion());

      

        int num1;
        int num2;
     
        for (int i = 1; i <= 100; i++) {

           
            oEntradaBean.setId(0);

            do {
                num1 = 1 + (int) (Math.random() * ((200 - 1) + 1));
                oEntradaBean.getHilo().setId(num1);
                oHiloDao.get(oEntradaBean.getHilo());
            } while (oEntradaBean.getHilo().getId() == 0);

            do {
                num2 = 1 + (int) (Math.random() * ((100 - 1) + 1));
                oEntradaBean.getUsuario().setId(num2);
                oUsuarioDao.get(oEntradaBean.getUsuario());
            } while (oEntradaBean.getUsuario().getId() == 0);

            oEntradaBean.setCantidad(num1 + num2 );
            Date date = new Date(97, 1, 23);

            oEntradaBean.setFecha(date);

            try {
                oEntradaDao.set(oEntradaBean);

            } catch (Exception e) {
                throw new ServletException("EntradaRellena: Update Error: Phase 1: " + e.getMessage());
            }

        }

        return "OK- información generada.";
    }
}
