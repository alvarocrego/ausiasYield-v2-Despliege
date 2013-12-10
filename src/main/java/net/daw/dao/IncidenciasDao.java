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
import net.daw.bean.RepositorioBean;
import net.daw.bean.EstadoBean;
import net.daw.bean.IncidenciasBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;
import net.daw.helper.Enum;

/**
 *
 * @author Enrique Gimeno
 */
public class IncidenciasDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public IncidenciasDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("incidencias", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EstadoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("incidencias", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<IncidenciasBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<IncidenciasBean> arrIncidencias = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("incidencias", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                IncidenciasBean oIncidenciasBean = new IncidenciasBean(iterador.next());
                arrIncidencias.add(this.get(oIncidenciasBean));
            }
            oMysql.desconexion();
            return arrIncidencias;
        } catch (Exception e) {
            throw new Exception("EstadoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public IncidenciasBean get(IncidenciasBean oIncidenciasBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            EstadoBean oEstadoBean = new EstadoBean();
           RepositorioBean oRepositorioBean = new RepositorioBean();
            UsuarioBean oUsuarioBean = new UsuarioBean();

            String strEstado = oMysql.getOne("incidencias", "id_estado", oIncidenciasBean.getId());
            String strRepositorio=oMysql.getOne("incidencias", "id_repositorio", oIncidenciasBean.getId());
            String strUsuario=oMysql.getOne("incidencias", "id_usuario", oIncidenciasBean.getId());

            oIncidenciasBean.setResumen(oMysql.getOne("incidencias", "resumen", oIncidenciasBean.getId()));
            oIncidenciasBean.setCambios(oMysql.getOne("incidencias", "cambios", oIncidenciasBean.getId()));
           String strFechaAlta = oMysql.getOne("incidencias", "fechaAlta", oIncidenciasBean.getId());
            if (strFechaAlta!=null) {
                Date dFechaAlta = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaAlta);
                oIncidenciasBean.setFechaAlta(dFechaAlta);
            } else {
                oIncidenciasBean.setFechaAlta(new Date(0));
            }
            
              String strFechaResolucion = oMysql.getOne("incidencias", "fechaResolucion", oIncidenciasBean.getId());
            if (strFechaResolucion!=null) {
                Date dFechaResolucion = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaResolucion);
                oIncidenciasBean.setFechaResolucion(dFechaResolucion);
            } else {
                oIncidenciasBean.setFechaResolucion(new Date(0));
            }



            if(strEstado != null){
                oEstadoBean.setId(Integer.parseInt(strEstado));
            EstadoDao oEstadoDao = new EstadoDao(enumTipoConexion);
            oEstadoBean = oEstadoDao.get(oEstadoBean);
            oIncidenciasBean.setEstado(oEstadoBean);
            }
            
            if(strRepositorio != null){
            oRepositorioBean.setId(Integer.parseInt(strRepositorio));
            RepositorioDao oRepositorioDao = new RepositorioDao(enumTipoConexion);
            oRepositorioBean = oRepositorioDao.get(oRepositorioBean);
            oIncidenciasBean.setRepositorio(oRepositorioBean);
            }
            
            if(strUsuario != null){
            oUsuarioBean.setId(Integer.parseInt(strUsuario));
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
            oIncidenciasBean.setUsuario(oUsuarioBean);
            }

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EstadoDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oIncidenciasBean;
    }

    public void set(IncidenciasBean oIncidenciasBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oIncidenciasBean.getId() == 0) {
                oIncidenciasBean.setId(oMysql.insertOne("incidencias"));
            }
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_estado", Integer.toString(oIncidenciasBean.getEstado().getId()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_repositorio", Integer.toString(oIncidenciasBean.getRepositorio().getId()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "id_usuario", Integer.toString(oIncidenciasBean.getUsuario().getId()));

            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "resumen", oIncidenciasBean.getResumen());
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "cambios", oIncidenciasBean.getCambios());
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "fechaAlta", oSimpleDateFormat.format(oIncidenciasBean.getFechaAlta()));
            oMysql.updateOne(oIncidenciasBean.getId(), "incidencias", "fechaResolucion", oSimpleDateFormat.format(oIncidenciasBean.getFechaResolucion()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("IncidenciasDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     *
     * @param oIncidenciasBean
     * @throws Exception
     */
    public void remove(IncidenciasBean oIncidenciasBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oIncidenciasBean.getId(), "incidencias");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("IncidenciasDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
