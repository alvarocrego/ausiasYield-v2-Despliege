/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.ActividadBean;
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.bean.EntregaBean;
import net.daw.bean.OpcionBean;
import net.daw.bean.EntregaBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class EntregaDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public EntregaDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("entrega", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EntregaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<EntregaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<EntregaBean> arrEntrega = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("entrega", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                EntregaBean oEntregaBean = new EntregaBean(iterador.next());
                arrEntrega.add(this.get(oEntregaBean));
            }
            oMysql.desconexion();
            return arrEntrega;
        } catch (Exception e) {
            throw new Exception("EntregaDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /*public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
     oMysql.conexion(enumTipoConexion);
     ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
     oMysql.desconexion();
     return n;
     }*/
    public EntregaBean get(EntregaBean oEntregaBean) throws Exception {
        if (oEntregaBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);

                DocumentoBean oDocumentoBean = new DocumentoBean();
                ActividadBean oActividadBean = new ActividadBean();

                oDocumentoBean.setId(Integer.parseInt(oMysql.getOne("entrega", "id_documento", oEntregaBean.getId())));
                oActividadBean.setId(Integer.parseInt(oMysql.getOne("entrega", "id_actividad", oEntregaBean.getId())));
                
                oEntregaBean.setNota( Integer.valueOf( oMysql.getOne("entrega","nota", oEntregaBean.getId())) );
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                oEntregaBean.setFecha(formatoFecha.parse( oMysql.getOne("entrega","fecha",oEntregaBean.getId() )));
                
                DocumentoDao oDocumentoDao = new DocumentoDao(enumTipoConexion);
                ActividadDao oActividadDao = new ActividadDao(enumTipoConexion);

                oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
                oActividadBean = oActividadDao.get(oActividadBean);

                oEntregaBean.setDocumento(oDocumentoBean);
                oEntregaBean.setActividad(oActividadBean);

                oMysql.desconexion();
            } catch (Exception e) {
                throw new Exception("EntregaDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oEntregaBean.setId(0);
        }
        return oEntregaBean;
    }

    public void set(EntregaBean oEntregaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oEntregaBean.getId() == 0) {
                oEntregaBean.setId(oMysql.insertOne("entrega"));
            }
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "id_documento", Integer.toString(oEntregaBean.getDocumento().getId()));
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "id_actividad", Integer.toString(oEntregaBean.getActividad().getId()));
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "nota", Integer.toString(oEntregaBean.getNota()));
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date date = oEntregaBean.getFecha();
            if(date == null){
                date = new Date();
            }
            String fecha = formatoFecha.format(date);
            
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "fecha", fecha);
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("EntregaDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(EntregaBean oEntregaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oEntregaBean.getId(), "entrega");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EntregaDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
