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

import net.daw.bean.CuestionarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class CuestionarioDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public CuestionarioDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("cuestionario", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("CuestionarioDao.getPages: Error: " + e.getMessage());
        }
    }

    public int getCount( ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("cuestionario",  hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("CuestionarioDao.getCount: Error: " + e.getMessage());
        }
    }
  
    public ArrayList<CuestionarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<CuestionarioBean> arrCuestionario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("cuestionario", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                CuestionarioBean oCuestionarioBean = new CuestionarioBean(iterador.next());
                arrCuestionario.add(this.get(oCuestionarioBean));
            }
            oMysql.desconexion();
            return arrCuestionario;
        } catch (Exception e) {
            throw new Exception("CuestionarioDao.getPage: Error: " + e.getMessage());
        }
    }

    /*public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }*/

    public CuestionarioBean get(CuestionarioBean oCuestionarioBean) throws Exception {
        if (oCuestionarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("cuestionario", oCuestionarioBean.getId())) {
                    oCuestionarioBean.setId(0);
                } else {
                    oCuestionarioBean.setDescripcion(oMysql.getOne("cuestionario", "descripcion", oCuestionarioBean.getId()));
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formatoFecha.parse(oMysql.getOne("cuestionario", "fecha", oCuestionarioBean.getId()) );
                    oCuestionarioBean.setFecha( fecha );
                    oCuestionarioBean.setEvaluacion(Integer.parseInt( oMysql.getOne("cuestionario", "evaluacion", oCuestionarioBean.getId()) ));
                    String activo = oMysql.getOne("cuestionario", "activo", oCuestionarioBean.getId());
                    oCuestionarioBean.setActivo( activo.equals("1") );
                }
            } catch (Exception e) {
                throw new Exception("CuestionarioDao.getCuestionario: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oCuestionarioBean.setId(0);
        }
        return oCuestionarioBean;
    }

    public void set(CuestionarioBean oCuestionarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oCuestionarioBean.getId() == 0) {
                oCuestionarioBean.setId(oMysql.insertOne("cuestionario"));
            }
            oMysql.updateOne(oCuestionarioBean.getId(), "cuestionario", "descripcion", oCuestionarioBean.getDescripcion());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format( oCuestionarioBean.getFecha() );
            oMysql.updateOne(oCuestionarioBean.getId(), "cuestionario", "fecha", fecha);
            oMysql.updateOne(oCuestionarioBean.getId(), "cuestionario", "evaluacion", oCuestionarioBean.getEvaluacion().toString());
            boolean activo = oCuestionarioBean.getActivo();
            int intactivo = activo?1:0;
            oMysql.updateOne(oCuestionarioBean.getId(), "cuestionario", "activo", Integer.toString(intactivo) );
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("CuestionarioDao.setCuestionario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(CuestionarioBean oCuestionarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oCuestionarioBean.getId(), "cuestionario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("CuestionarioDao.removeCuestionario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}

