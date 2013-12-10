/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import net.daw.bean.ActividadofflineBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import java.text.SimpleDateFormat;
import net.daw.helper.FilterBean;

/**
 *
 * @author Javier Bonet
 */
public class ActividadofflineDao {

   private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public ActividadofflineDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("actividadoffline", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ActividadofflineDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("actividadoffline", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ACtividadofflineDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<ActividadofflineBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ActividadofflineBean> arrActividadoffline = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("actividadoffline", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ActividadofflineBean oACtividadofflineBean = new ActividadofflineBean(iterador.next());
                arrActividadoffline.add(this.get(oACtividadofflineBean));
            }
            oMysql.desconexion();
            return arrActividadoffline;
        } catch (Exception e) {
            throw new Exception("ACtividadofflineDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ActividadofflineBean get(ActividadofflineBean oActividadofflineBean) throws Exception {
        if (oActividadofflineBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("actividadoffline", oActividadofflineBean.getId())) {
                    oActividadofflineBean.setId(0);
                } else {
                    oActividadofflineBean.setEnunciado(oMysql.getOne("actividadoffline", "enunciado", oActividadofflineBean.getId()));
                    
                    String strFecha = oMysql.getOne("actividadoffline", "fecha", oActividadofflineBean.getId());

                    if (strFecha != null) {
                        Date dFecha = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                        oActividadofflineBean.setFecha(dFecha);
                    } else {
                        oActividadofflineBean.setFecha(new Date(0));
                    }
                    
                     oActividadofflineBean.setCalificacion(Double.parseDouble(oMysql.getOne("actividadoffline", "calificacion", oActividadofflineBean.getId())));
                     oActividadofflineBean.setEvaluacion(Integer.parseInt(oMysql.getOne("actividadoffline", "evaluacion", oActividadofflineBean.getId())));
                     oActividadofflineBean.setActivo(Integer.parseInt(oMysql.getOne("actividadoffline", "activo", oActividadofflineBean.getId())));

                }
            } catch (Exception e) {
                throw new Exception("ACtividadofflineDao.getActividadoffline: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
           oActividadofflineBean.setId(0);
        }
        return oActividadofflineBean;
    }

    public void set(ActividadofflineBean oActividadofflineBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oActividadofflineBean.getId() == 0) {
                oActividadofflineBean.setId(oMysql.insertOne("actividadoffline"));
            }
            
            
           
            oMysql.updateOne(oActividadofflineBean.getId(), "actividadoffline", "enunciado", oActividadofflineBean.getEnunciado());
             java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oActividadofflineBean.getId(), "actividadoffline", "fecha", oSimpleDateFormat.format(oActividadofflineBean.getFecha()));
            oMysql.updateOne(oActividadofflineBean.getId(), "actividadoffline", "calificacion", Double.toString(oActividadofflineBean.getCalificacion()));
            oMysql.updateOne(oActividadofflineBean.getId(), "actividadoffline", "evaluacion", Integer.toString(oActividadofflineBean.getEvaluacion()));
            oMysql.updateOne(oActividadofflineBean.getId(), "actividadoffline", "activo", Integer.toString(oActividadofflineBean.getActivo()));
            
         

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ActividadofflineDao.setACtividadoffline: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ActividadofflineBean oActividadofflineBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oActividadofflineBean.getId(), "actividadoffline");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ActividadofflineDao.removeActividadoffline: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}