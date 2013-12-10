/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.LenguajeBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

/**
 *
 * @author Alvaro Crego
 */
public class LenguajeDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    /**
     * Constructor LenguajeDao.
     * @param tipoConexion
     */
    public LenguajeDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     * Introduce los datos almacenados en LenguajeBean en la Base de datos.
     * @param oLenguajeBean
     * @throws Exception
     */
    public void set(LenguajeBean oLenguajeBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oLenguajeBean.getId() == 0) {
                oLenguajeBean.setId(oMysql.insertOne("lenguaje"));
            }
            oMysql.updateOne(oLenguajeBean.getId(), "lenguaje", "nombre", oLenguajeBean.getNombre());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("LenguajeDao.setLenguaje: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Obtiene el numero total de paginas.
     * @param intRegsPerPag
     * @param alFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("lenguaje", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("LenguajeDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Obtiene la pagina.
     * @param intRegsPerPag
     * @param intPage
     * @param alFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public ArrayList<LenguajeBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<LenguajeBean> arrLenguaje = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("lenguaje", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                LenguajeBean oLenguajeBean = new LenguajeBean(iterador.next());
                arrLenguaje.add(this.get(oLenguajeBean));
            }
            oMysql.desconexion();
            return arrLenguaje;
        } catch (Exception e) {
            throw new Exception("LenguajeDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Obtiene los Valores de la Base de datos.
     * @param oLenguajeBean
     * @return Devuelve LenguajeBean con los valores de la base de datos.
     * @throws Exception
     */
    public LenguajeBean get(LenguajeBean oLenguajeBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oLenguajeBean.setNombre(oMysql.getOne("lenguaje", "nombre", oLenguajeBean.getId()));
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("LenguajeDao.getLenguaje: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oLenguajeBean;
    }

    /**
     * Borra una fila de la Base de datos de la id almacenada en DocumentoBean.
     * @param oLenguajeBean
     * @throws Exception
     */
    public void remove(LenguajeBean oLenguajeBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oLenguajeBean.getId(), "lenguaje");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("LenguajeDao.removeLenguaje: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Obtiene el numero de paginas.
     * @param hmFilter
     * @return
     * @throws Exception
     */
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("lenguaje", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("LenguajeDao.getCount: Error: " + e.getMessage());
        }
    }
}
