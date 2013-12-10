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
import net.daw.bean.UsuarioBean;
import net.daw.bean.ContestacionBean;
import net.daw.bean.OpcionBean;
import net.daw.bean.PreguntaBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author rafa
 */
public class ContestacionDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public ContestacionDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("contestacion", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("PreguntaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<ContestacionBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ContestacionBean> arrContestacion = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("contestacion", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ContestacionBean oContestacionBean = new ContestacionBean(iterador.next());
                arrContestacion.add(this.get(oContestacionBean));
            }
            oMysql.desconexion();
            return arrContestacion;
        } catch (Exception e) {
            throw new Exception("PreguntaDao.getPage: Error: " + e.getMessage());
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

    public ContestacionBean get(ContestacionBean oContestacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            UsuarioBean oUsuarioBean = new UsuarioBean();
            PreguntaBean oPreguntaBean = new PreguntaBean();
            OpcionBean oOpcionBean = new OpcionBean();

            oPreguntaBean.setId(Integer.parseInt(oMysql.getOne("contestacion", "id_pregunta", oContestacionBean.getId())));
            oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("contestacion", "id_usuario", oContestacionBean.getId())));
            oOpcionBean.setId(Integer.parseInt(oMysql.getOne("contestacion","id_opcion",oContestacionBean.getId())));

            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            PreguntaDao oPreguntaDao = new PreguntaDao(enumTipoConexion);
            OpcionDao oOpcionDao = new OpcionDao(enumTipoConexion);

            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
            oPreguntaBean = oPreguntaDao.get(oPreguntaBean);
            oOpcionBean = oOpcionDao.get(oOpcionBean);

            oContestacionBean.setUsuario(oUsuarioBean);
            oContestacionBean.setPregunta(oPreguntaBean);
            oContestacionBean.setOpcion(oOpcionBean);

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("PreguntaDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oContestacionBean;
    }

    public void set(ContestacionBean oContestacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oContestacionBean.getId() == 0) {
                oContestacionBean.setId(oMysql.insertOne("contestacion"));
            }
            oMysql.updateOne(oContestacionBean.getId(), "contestacion", "id_usuario", Integer.toString( oContestacionBean.getUsuario().getId()) );
            oMysql.updateOne(oContestacionBean.getId(), "contestacion", "id_pregunta", Integer.toString( oContestacionBean.getPregunta().getId()) );
            oMysql.updateOne(oContestacionBean.getId(), "contestacion", "id_opcion", Integer.toString(oContestacionBean.getOpcion().getId()) );
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ContestacionDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ContestacionBean oContestacionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oContestacionBean.getId(), "contestacion");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ContestacionDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
