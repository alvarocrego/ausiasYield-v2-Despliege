/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.TipodocumentoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037877
 */
public class TipodocumentoDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public TipodocumentoDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("tipodocumento", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("TipodocumentoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<TipodocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<TipodocumentoBean> arrLenguaje = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("tipodocumento", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                TipodocumentoBean oLenguajeBean = new TipodocumentoBean(iterador.next());
                arrLenguaje.add(this.get(oLenguajeBean));
            }
            oMysql.desconexion();
            return arrLenguaje;
        } catch (Exception e) {
            throw new Exception("TipodocumentoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    public TipodocumentoBean get(TipodocumentoBean oTipodocumentoBean) throws Exception {
        try {
            
            oMysql.conexion(enumTipoConexion);
            oTipodocumentoBean.setDescripcion(oMysql.getOne("tipodocumento", "descripcion", oTipodocumentoBean.getId()));
            //no convierte bien el 1 string a true boolean, lo deja en false, por lo que tengo que consultarlo con if
            if(oMysql.getOne("tipodocumento", "privado", oTipodocumentoBean.getId()).equals("1")){
                oTipodocumentoBean.setPrivado(true);
            }else{
                oTipodocumentoBean.setPrivado(false);
            }

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TipodocumentoDao.getRespositorio: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oTipodocumentoBean;
    }

    public void set(TipodocumentoBean oTipodocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oTipodocumentoBean.getId() == 0) {
                oTipodocumentoBean.setId(oMysql.insertOne("tipodocumento"));
            }
            oMysql.updateOne(oTipodocumentoBean.getId(), "tipodocumento", "descripcion", oTipodocumentoBean.getDescripcion());    
            if(oTipodocumentoBean.isPrivado()){
                oMysql.updateOne(oTipodocumentoBean.getId(), "tipodocumento", "privado", "1");
            }else{
                oMysql.updateOne(oTipodocumentoBean.getId(), "tipodocumento", "privado", "0");
            }
            
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("TipodocumentoDao.setTipodocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(TipodocumentoBean oTipodocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oTipodocumentoBean.getId(), "tipodocumento");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TipodocumentoDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }

    public Integer getCount(ArrayList<FilterBean> alFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("tipodocumento", alFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("TipodocumentoDao.getCount: Error: " + e.getMessage());
        }
    }
}
