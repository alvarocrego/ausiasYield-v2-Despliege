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
import net.daw.bean.BolsaBean;
import net.daw.bean.DocumentoBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037877
 */
public class BolsaDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    /**
     * Constructor Del BolsaDao.
     *
     * @param tipoConexion
     * @throws Exception
     */
    public BolsaDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    /**
     * Obtiene el numero total de paginas.
     *
     * @param intRegsPerPag
     * @param hmFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("bolsa", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("BolsaDao.getPages: Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene la pagina.
     *
     * @param intRegsPerPag
     * @param intPage
     * @param hmFilter
     * @param hmOrder
     * @return
     * @throws Exception
     */
    public ArrayList<BolsaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<BolsaBean> arrBolsa = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("bolsa", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                BolsaBean oBolsaBean = new BolsaBean(iterador.next());
                arrBolsa.add(this.get(oBolsaBean));
            }
            oMysql.desconexion();
            return arrBolsa;
        } catch (Exception e) {
            throw new Exception("BolsaDao.getPage: Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene los Valores de la Base de datos.
     *
     * @param oBolsaBean
     * @return Devuelve BolsaBean con los valores de la base de datos.
     * @throws Exception
     */
    public BolsaBean get(BolsaBean oBolsaBean) throws Exception {
        if (oBolsaBean.getId() > 0) {
            try {
                DocumentoBean oDocumentoBean1 = new DocumentoBean();
                DocumentoBean oDocumentoBean2 = new DocumentoBean();

                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("bolsa", oBolsaBean.getId())) {
                    oBolsaBean.setId(0);
                } else {                    
                    
                    oDocumentoBean1.setId(Integer.parseInt(oMysql.getOne("bolsa", "id_documento1", oBolsaBean.getId())));
                    oDocumentoBean1.setTitulo(oMysql.getOne("documento", "titulo", oDocumentoBean1.getId()));
                    oDocumentoBean2.setId(Integer.parseInt(oMysql.getOne("bolsa", "id_documento2", oBolsaBean.getId())));
                    oDocumentoBean2.setTitulo(oMysql.getOne("documento", "titulo", oDocumentoBean2.getId()));

                    DocumentoDao oDocumentoDao = new DocumentoDao(enumTipoConexion);

                    oDocumentoBean1 = oDocumentoDao.get(oDocumentoBean1);
                    oDocumentoBean2 = oDocumentoDao.get(oDocumentoBean2);

                    oBolsaBean.setDocumento1(oDocumentoBean1);
                    oBolsaBean.setDocumento2(oDocumentoBean2);


                    String strFecha = oMysql.getOne("bolsa", "fecha", oBolsaBean.getId());
                    if (strFecha != null) {
                        Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                        oBolsaBean.setFecha(dFecha);
                    } else {
                        oBolsaBean.setFecha(new Date(0));
                    }

                }
            } catch (Exception e) {
                throw new Exception("BolsaDao.getBolsa: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oBolsaBean.setId(0);
        }
        return oBolsaBean;
    }

    /**
     * Introduce los valores de BolsaBean a la Base de datos.
     *
     * @param oBolsaBean
     * @throws Exception
     */
    public void set(BolsaBean oBolsaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oBolsaBean.getId() == 0) {
                oBolsaBean.setId(oMysql.insertOne("bolsa"));
            }
            oMysql.updateOne(oBolsaBean.getId(), "bolsa", "id_documento1", String.valueOf(oBolsaBean.getDocumento1().getId()));
            oMysql.updateOne(oBolsaBean.getId(), "bolsa", "id_documento2", String.valueOf(oBolsaBean.getDocumento2().getId()));
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oBolsaBean.getId(), "bolsa", "fecha", oSimpleDateFormat.format(oBolsaBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("BolsaDao.setBolsa: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Borra una fila de la Base de datos de la id almacenada en BolsaBean.
     *
     * @param oBolsaBean
     * @throws Exception
     */
    public void remove(BolsaBean oBolsaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oBolsaBean.getId(), "bolsa");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("BolsaDao.removeBolsa: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Obtiene el numero de paginas.
     *
     * @param hmFilter
     * @return
     * @throws Exception
     */
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("bolsa", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("BolsaDao.getCount: Error: " + e.getMessage());
        }
    }
}
