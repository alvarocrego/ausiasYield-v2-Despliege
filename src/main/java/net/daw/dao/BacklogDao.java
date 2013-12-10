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
import net.daw.bean.BacklogBean;
import net.daw.bean.UsuarioBean;
import net.daw.bean.RequerimientoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;
import net.daw.helper.Enum;

/**
 *
 * @author Edu Membrillas
 */
public class BacklogDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public BacklogDao(Enum.Connection tipoConexion) throws Exception {

        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("backlog", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("BacklogDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("backlog", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("BacklogDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<BacklogBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<BacklogBean> arrBacklog = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("backlog", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                BacklogBean oBacklogBean = new BacklogBean(iterador.next());
                arrBacklog.add(this.get(oBacklogBean));
            }
            oMysql.desconexion();
            return arrBacklog;
        } catch (Exception e) {
            throw new Exception("BacklogDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public BacklogBean get(BacklogBean oBacklogBean) throws Exception {
        if (oBacklogBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);

                if (!oMysql.existsOne("backlog", oBacklogBean.getId())) {
                    oBacklogBean.setId(0);
                } else {

                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);

                    RequerimientoBean oRequerimientoBean = new RequerimientoBean();
                    RequerimientoDao oRequerimientoDao = new RequerimientoDao(enumTipoConexion);

                    oBacklogBean.setId(Integer.parseInt(oMysql.getOne("backlog", "id", oBacklogBean.getId())));

                    oBacklogBean.setEnunciado(oMysql.getOne("backlog", "enunciado", oBacklogBean.getId()));
                    oBacklogBean.setDescripciondetallada(oMysql.getOne("backlog", "descripciondetallado", oBacklogBean.getId()));
                    oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("backlog", "id_usuario", oBacklogBean.getId())));
                    oRequerimientoBean.setId(Integer.parseInt(oMysql.getOne("backlog", "id_requerimiento", oBacklogBean.getId())));

                    oBacklogBean.setFechainicioprevista(dateFormat.parse(oMysql.getOne("backlog", "fechainicioprevista", oBacklogBean.getId())));
                    oBacklogBean.setFechafinprevista(dateFormat.parse(oMysql.getOne("backlog", "fechafinprevista", oBacklogBean.getId())));
                    oBacklogBean.setFechainicio(dateFormat.parse(oMysql.getOne("backlog", "fechainicio", oBacklogBean.getId())));
                    oBacklogBean.setFechafin(dateFormat.parse(oMysql.getOne("backlog", "fechafin", oBacklogBean.getId())));

                    oBacklogBean.setHorasduracionprevista(Integer.parseInt(oMysql.getOne("backlog", "horasduracionprevista", oBacklogBean.getId())));
                    oBacklogBean.setPorcentajecompletado(Integer.parseInt(oMysql.getOne("backlog", "porcentajecompletado", oBacklogBean.getId())));

                    oBacklogBean.setFechaalta(dateFormat.parse(oMysql.getOne("backlog", "fechaalta", oBacklogBean.getId())));

                    oBacklogBean.setSprint(Integer.parseInt(oMysql.getOne("backlog", "sprint", oBacklogBean.getId())));
                    oBacklogBean.setRelease(Integer.parseInt(oMysql.getOne("backlog", "releasetabla", oBacklogBean.getId())));

                    oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
                    oBacklogBean.setUsuario(oUsuarioBean);

                    oRequerimientoBean = oRequerimientoDao.get(oRequerimientoBean);
                    oBacklogBean.setRequerimiento(oRequerimientoBean);

                }
            } catch (Exception e) {
                throw new Exception("BacklogDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oBacklogBean.setId(0);
        }
        return oBacklogBean;
    }

    public void set(BacklogBean oBacklogBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oBacklogBean.getId() == 0) {
                oBacklogBean.setId(oMysql.insertOne("backlog"));
            }

            oMysql.updateOne(oBacklogBean.getId(), "backlog", "id", Integer.toString(oBacklogBean.getId()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "enunciado", oBacklogBean.getEnunciado());
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "descripciondetallado", oBacklogBean.getDescripciondetallada());
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "id_usuario", Integer.toString(oBacklogBean.getUsuario().getId()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "id_requerimiento", Integer.toString(oBacklogBean.getRequerimiento().getId()));

            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "fechafinprevista", oSimpleDateFormat.format(oBacklogBean.getFechafinprevista()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "fechainicioprevista", oSimpleDateFormat.format(oBacklogBean.getFechainicioprevista()));

            oMysql.updateOne(oBacklogBean.getId(), "backlog", "fechafin", oSimpleDateFormat.format(oBacklogBean.getFechafin()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "fechainicio", oSimpleDateFormat.format(oBacklogBean.getFechainicio()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "fechaalta", oSimpleDateFormat.format(oBacklogBean.getFechaalta()));

            oMysql.updateOne(oBacklogBean.getId(), "backlog", "porcentajecompletado", Integer.toString(oBacklogBean.getPorcentajecompletado()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "horasduracionprevista", Integer.toString(oBacklogBean.getHorasduracionprevista()));

            oMysql.updateOne(oBacklogBean.getId(), "backlog", "sprint", Integer.toString(oBacklogBean.getSprint()));
            oMysql.updateOne(oBacklogBean.getId(), "backlog", "releasetabla", Integer.toString(oBacklogBean.getRelease()));

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("BacklogDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(BacklogBean oBacklogBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oBacklogBean.getId(), "backlog");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("BacklogDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }

}
