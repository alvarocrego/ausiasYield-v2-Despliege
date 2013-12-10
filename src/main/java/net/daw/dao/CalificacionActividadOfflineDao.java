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
import net.daw.bean.ActividadofflineBean;
import net.daw.bean.CalificacionActividadOfflineBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;
import net.daw.helper.Enum;

/**
 *
 * @author al037805
 */
public class CalificacionActividadOfflineDao {
    
    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

     public CalificacionActividadOfflineDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("calificacionactividadoffline", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("CalificacionActividadOfflineDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<CalificacionActividadOfflineBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<CalificacionActividadOfflineBean> arrCalificacionActividadOffline = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("calificacionactividadoffline", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                CalificacionActividadOfflineBean oCalificacionActividadOfflineBean = new CalificacionActividadOfflineBean(iterador.next());
                arrCalificacionActividadOffline.add(this.get(oCalificacionActividadOfflineBean));
            }
            oMysql.desconexion();
            return arrCalificacionActividadOffline;
        } catch (Exception e) {
            throw new Exception("CalificacionActividadOfflineDao.getPage: Error: " + e.getMessage());
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

    public CalificacionActividadOfflineBean get(CalificacionActividadOfflineBean oCalificacionActividadOfflineBean) throws Exception {
        //if (oCalificacionActividadOfflineBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                
                UsuarioBean oUsuarioBean = new UsuarioBean();
                ActividadofflineBean oActividadofflineBean = new ActividadofflineBean();
                
                oActividadofflineBean.setId(Integer.parseInt(oMysql.getOne("calificacionactividadoffline", "id_actividadoffline", oCalificacionActividadOfflineBean.getId())));
                oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("calificacionactividadoffline", "id_usuario", oCalificacionActividadOfflineBean.getId())));
                
                UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                ActividadofflineDao oActividadofflineDao = new ActividadofflineDao(enumTipoConexion);
                
                oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
                oActividadofflineBean = oActividadofflineDao.get(oActividadofflineBean);
                
                oCalificacionActividadOfflineBean.setUsuario(oUsuarioBean);
                oCalificacionActividadOfflineBean.setActividad_offline(oActividadofflineBean);
                
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formatoFecha.parse(oMysql.getOne("calificacionactividadoffline", "fecha", oCalificacionActividadOfflineBean.getId()) );
                    oCalificacionActividadOfflineBean.setFecha( fecha );
                
                /*if (!oMysql.existsOne("calificacionactividadoffline", oCalificacionActividadOfflineBean.getId())) {
                    oCalificacionActividadOfflineBean.setId(0);
                } else {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formatoFecha.parse(oMysql.getOne("calificacionactividadoffline", "fecha", oCalificacionActividadOfflineBean.getId()) );
                    oCalificacionActividadOfflineBean.setFecha( fecha );
                    String intId_calificacionactividadoffline = oMysql.getOne("calificacionactividadoffline", "id_usuario", oCalificacionActividadOfflineBean.getId());
                    if (intId_calificacionactividadoffline != null) {
                        oCalificacionActividadOfflineBean.getUsuario().setId(Integer.parseInt(intId_calificacionactividadoffline));
                        UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                        oCalificacionActividadOfflineBean.setUsuario(oUsuarioDao.get(oCalificacionActividadOfflineBean.getUsuario()));
                    }
                }
            } catch (Exception e) {
                throw new Exception("CalificacionActividadOfflineDao.get: Error: " + e.getMessage());
            } finally {*/
                oMysql.desconexion();
            } catch (Exception e) {
            throw new Exception("CalificacionActividadOfflineDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
     //   } else {
     //       oCalificacionActividadOfflineBean.setId(0);
      //  }
        return oCalificacionActividadOfflineBean;
    }

    public void set(CalificacionActividadOfflineBean oCalificacionActividadOfflineBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oCalificacionActividadOfflineBean.getId() == 0) {
                oCalificacionActividadOfflineBean.setId(oMysql.insertOne("calificacionactividadoffline"));
            }
            Integer id_usuario = oCalificacionActividadOfflineBean.getUsuario().getId();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format( oCalificacionActividadOfflineBean.getFecha() );
            oMysql.updateOne(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline", "fecha", fecha);
            oMysql.updateOne(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline", "id_usuario", Integer.toString( oCalificacionActividadOfflineBean.getUsuario().getId()) );
            oMysql.updateOne(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline", "id_actividadoffline", Integer.toString( oCalificacionActividadOfflineBean.getActividad_offline().getId()) );
            oMysql.commitTrans();
            /*if (id_usuario > 0) {
                oMysql.updateOne(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline", "id_usuario", id_usuario.toString());
            } else {
                oMysql.setNull(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline", "id_usuario");
            }
            oMysql.commitTrans();*/
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("CalificacionActividadOfflineDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(CalificacionActividadOfflineBean oCalificacionActividadOfflineBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oCalificacionActividadOfflineBean.getId(), "calificacionactividadoffline");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("CalificacionActividadOfflineDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
    
}
