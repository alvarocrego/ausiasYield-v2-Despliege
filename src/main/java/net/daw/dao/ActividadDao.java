/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author al037721
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.ActividadBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class ActividadDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public ActividadDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("actividad", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ActividadDao.getPages: Error: " + e.getMessage());
        }
    }

    public int getCount( ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("actividad",  hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ActividadDao.getCount: Error: " + e.getMessage());
        }
    }
  
    public ArrayList<ActividadBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ActividadBean> arrActividad = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("actividad", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ActividadBean oActividadBean = new ActividadBean(iterador.next());
                arrActividad.add(this.get(oActividadBean));
            }
            oMysql.desconexion();
            return arrActividad;
        } catch (Exception e) {
            throw new Exception("ActividadDao.getPage: Error: " + e.getMessage());
        }
    }


    public ActividadBean get(ActividadBean oActividadBean) throws Exception {
        if (oActividadBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("actividad", oActividadBean.getId())) {
                    oActividadBean.setId(0);
                } else {
                    oActividadBean.setEnunciado(oMysql.getOne("actividad", "enunciado", oActividadBean.getId()));
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formatoFecha.parse(oMysql.getOne("actividad", "fecha", oActividadBean.getId()) );
                    oActividadBean.setFecha( fecha );
                    oActividadBean.setEvaluacion(Integer.parseInt( oMysql.getOne("actividad", "evaluacion", oActividadBean.getId()) ));
                    String activo = oMysql.getOne("actividad", "activo", oActividadBean.getId());
                    oActividadBean.setActivo( activo.equals("1") );
                }
            } catch (Exception e) {
                throw new Exception("ActividadDao.getActividad: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oActividadBean.setId(0);
        }
        return oActividadBean;
    }

    public void set(ActividadBean oActividadBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oActividadBean.getId() == 0) {
                oActividadBean.setId(oMysql.insertOne("actividad"));
            }
            oMysql.updateOne(oActividadBean.getId(), "actividad", "enunciado", oActividadBean.getEnunciado());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format( oActividadBean.getFecha() );
            oMysql.updateOne(oActividadBean.getId(), "actividad", "fecha", fecha);
            oMysql.updateOne(oActividadBean.getId(), "actividad", "evaluacion", oActividadBean.getEvaluacion().toString());
            boolean activo = oActividadBean.getActivo();
            int intactivo = activo?1:0;
            oMysql.updateOne(oActividadBean.getId(), "actividad", "activo", Integer.toString(intactivo) );
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ActividadDao.setActividad: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ActividadBean oActividadBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oActividadBean.getId(), "actividad");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ActividadDao.removeActividad: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
