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
import net.daw.bean.HiloBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author jose
 */
public class HiloDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public HiloDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("hilo", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("HiloDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("hilo", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("HiloDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<HiloBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<HiloBean> arrHilo = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("hilo", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                HiloBean oHiloBean = new HiloBean(iterador.next());
                arrHilo.add(this.get(oHiloBean));
            }
            oMysql.desconexion();
            return arrHilo;
        } catch (Exception e) {
            throw new Exception("HiloDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public HiloBean get(HiloBean oHiloBean) throws Exception {
        if (oHiloBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("hilo", oHiloBean.getId())) {
                    oHiloBean.setId(0);
                } else {
                    oHiloBean.setNombre(oMysql.getOne("hilo", "nombre", oHiloBean.getId()));
                    String strFecha = oMysql.getOne("hilo", "fecha", oHiloBean.getId());

                    if (strFecha != null) {
                        Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                        oHiloBean.setFecha(dFecha);
                    } else {
                        oHiloBean.setFecha(new Date(0));
                    }

                }
            } catch (Exception e) {
                throw new Exception("HiloDao.getHilo: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oHiloBean.setId(0);
        }
        return oHiloBean;
    }

    public void set(HiloBean oHiloBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oHiloBean.getId() == 0) {
                oHiloBean.setId(oMysql.insertOne("hilo"));
            }
            oMysql.updateOne(oHiloBean.getId(), "hilo", "nombre", oHiloBean.getNombre());
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oHiloBean.getId(), "hilo", "fecha", oSimpleDateFormat.format(oHiloBean.getFecha()));

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("HiloDao.setHilo: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(HiloBean oHiloBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oHiloBean.getId(), "hilo");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("HiloDao.removeHilo: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
