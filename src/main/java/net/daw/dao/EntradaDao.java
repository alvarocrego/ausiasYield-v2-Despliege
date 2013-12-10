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
import net.daw.bean.EntradaBean;
import net.daw.bean.HiloBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author jose
 */
public class EntradaDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public EntradaDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("entrada", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EntradaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("entrada", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EntradaDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<EntradaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<EntradaBean> arrEntrada = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("entrada", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                EntradaBean oEntradaBean = new EntradaBean(iterador.next());
                arrEntrada.add(this.get(oEntradaBean));
            }
            oMysql.desconexion();
            return arrEntrada;
        } catch (Exception e) {
            throw new Exception("EntradaDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public EntradaBean get(EntradaBean oEntradaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            HiloBean oHiloBean = new HiloBean();
            UsuarioBean oUsuarioBean = new UsuarioBean();

            oEntradaBean.setTitulo(oMysql.getOne("entrada", "titulo", oEntradaBean.getId()));
            oEntradaBean.setContenido(oMysql.getOne("entrada", "contenido", oEntradaBean.getId()));
            oHiloBean.setId(Integer.parseInt(oMysql.getOne("entrada", "id_hilo", oEntradaBean.getId())));
            oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("entrada", "id_usuario", oEntradaBean.getId())));
            String strFecha = oMysql.getOne("entrada", "fecha", oEntradaBean.getId());

            if (strFecha != null) {
                Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                oEntradaBean.setFecha(dFecha);
            } else {
                oEntradaBean.setFecha(new Date(0));
            }

            HiloDao oHiloDao = new HiloDao(enumTipoConexion);
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);

            oHiloBean = oHiloDao.get(oHiloBean);
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);

            oEntradaBean.setHilo(oHiloBean);
            oEntradaBean.setUsuario(oUsuarioBean);

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EntradaDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oEntradaBean;
    }

    public void set(EntradaBean oEntradaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oEntradaBean.getId() == 0) {
                oEntradaBean.setId(oMysql.insertOne("entrada"));
            }
            oMysql.updateOne(oEntradaBean.getId(), "entrada", "titulo", oEntradaBean.getTitulo());
            oMysql.updateOne(oEntradaBean.getId(), "entrada", "contenido", oEntradaBean.getContenido());
            oMysql.updateOne(oEntradaBean.getId(), "entrada", "id_hilo", Integer.toString(oEntradaBean.getHilo().getId()));
            oMysql.updateOne(oEntradaBean.getId(), "entrada", "id_usuario", Integer.toString(oEntradaBean.getUsuario().getId()));
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oEntradaBean.getId(), "entrada", "fecha", oSimpleDateFormat.format(oEntradaBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("EntradaDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(EntradaBean oEntradaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oEntradaBean.getId(), "entrada");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EntradaDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
