/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.RequerimientoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class RequerimientoDao {

    private Mysql oMysql;
    private net.daw.helper.Enum.Connection enumTipoConexion;

    /**
     *
     * @param tipoConexion
     */
    public RequerimientoDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public void set(RequerimientoBean oRequerimientoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oRequerimientoBean.getId() == 0) {
                oRequerimientoBean.setId(oMysql.insertOne("requerimiento"));
            }
            oMysql.updateOne(oRequerimientoBean.getId(), "requerimiento", "enunciado", oRequerimientoBean.getEnunciado());
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oRequerimientoBean.getId(), "requerimiento", "fechaalta", oSimpleDateFormat.format(oRequerimientoBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("RequerimientoDao.setRequerimiento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("requerimiento", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("RequerimientoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
        public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("requerimiento", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("RequerimientoDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<RequerimientoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<RequerimientoBean> arrRequerimiento = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("requerimiento", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                RequerimientoBean oRequerimientoBean = new RequerimientoBean(iterador.next());
                arrRequerimiento.add(this.get(oRequerimientoBean));
            }
            oMysql.desconexion();
            return arrRequerimiento;
        } catch (Exception e) {
            throw new Exception("RequerimientoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public RequerimientoBean get(RequerimientoBean oRequerimientoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            oRequerimientoBean.setEnunciado(oMysql.getOne("requerimiento", "enunciado", oRequerimientoBean.getId()));
            String strFecha = oMysql.getOne("requerimiento", "fechaalta", oRequerimientoBean.getId());
            if (strFecha != null) {
                Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                oRequerimientoBean.setFecha(dFecha);
            } else {
                oRequerimientoBean.setFecha(new Date(0));
            }
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RequerimientoDao.getRequerimiento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oRequerimientoBean;
    }

    public void remove(RequerimientoBean oRequerimientoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oRequerimientoBean.getId(), "requerimiento");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RequerimientoDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
