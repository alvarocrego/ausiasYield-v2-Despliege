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
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Alvaro Crego
 */
public class DocumentoDao {

    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    /**
     * Constructor Del DocumentoDao.
     *
     * @param tipoConexion
     * @throws Exception
     */
    public DocumentoDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
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
            pages = oMysql.getPages("documento", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getPages: Error: " + e.getMessage());
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
    public ArrayList<DocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<DocumentoBean> arrDocumento = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("documento", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                DocumentoBean oDocumentoBean = new DocumentoBean(iterador.next());
                arrDocumento.add(this.get(oDocumentoBean));
            }
            oMysql.desconexion();
            return arrDocumento;
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getPage: Error: " + e.getMessage());
        }
    }

    /**
     * Obtiene los Valores de la Base de datos.
     *
     * @param oDocumentoBean
     * @return Devuelve DocumentoBean con los valores de la base de datos.
     * @throws Exception
     */
    public DocumentoBean get(DocumentoBean oDocumentoBean) throws Exception {

        try {
            oMysql.conexion(enumTipoConexion);

            oDocumentoBean.setTitulo(oMysql.getOne("documento", "titulo", oDocumentoBean.getId()));

            oDocumentoBean.setContenido(oMysql.getOne("documento", "contenido", oDocumentoBean.getId()));

            String strFecha = oMysql.getOne("documento", "fecha", oDocumentoBean.getId());
            if (strFecha != null) {
                Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                oDocumentoBean.setFecha(dFecha);
            } else {
                oDocumentoBean.setFecha(new Date(0));
            }
            String strNota = oMysql.getOne("documento", "nota", oDocumentoBean.getId());
            if (strNota != null) {
                oDocumentoBean.setNota(Integer.parseInt(strNota));
            }
            
            String strUsuario = oMysql.getOne("documento", "id_usuario", oDocumentoBean.getId());
            if(strUsuario != null){
            UsuarioBean oUsuarioBean = new UsuarioBean();
            oUsuarioBean.setId(Integer.parseInt(strUsuario));

            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);

            oDocumentoBean.setUsuario(oUsuarioBean);
            }
            oDocumentoBean.setEtiquetas(oMysql.getOne("documento", "etiquetas", oDocumentoBean.getId()));

            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getDocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

        return oDocumentoBean;
    }

    /**
     * Introduce los valores de DocumentoBean a la Base de datos.
     *
     * @param oDocumentoBean
     * @throws Exception
     */
    public void set(DocumentoBean oDocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oDocumentoBean.getId() == 0) {
                oDocumentoBean.setId(oMysql.insertOne("documento"));
            }
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "titulo", oDocumentoBean.getTitulo());
            /*String contenido = "";
             if (oDocumentoBean.getContenido() != null) {
             contenido = oDocumentoBean.getContenido().replace("'''", "''''''");
             //contenido = contenido.replace("''", "''''");
             System.out.println(contenido);
             }*/
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "contenido", oDocumentoBean.getContenido());
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "fecha", oSimpleDateFormat.format(oDocumentoBean.getFecha()));
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "nota", Integer.toString(oDocumentoBean.getNota()));
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "id_usuario", Integer.toString(oDocumentoBean.getUsuario().getId()));

            String etiquetasFormateadas = "";
            if (oDocumentoBean.getEtiquetas() != null) {
                String[] etiquetas = oDocumentoBean.getEtiquetas().split(",");
                for (int f = 0; f < etiquetas.length; f++) {
                    etiquetasFormateadas += etiquetas[f].replaceAll(" +", " ").trim();
                    if (f != etiquetas.length - 1) {
                        etiquetasFormateadas += ",";
                    }
                }
            }
            oMysql.updateOne(oDocumentoBean.getId(), "documento", "etiquetas", etiquetasFormateadas);
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("DocumentoDao.setDocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /**
     * Borra una fila de la Base de datos de la id almacenada en DocumentoBean.
     *
     * @param oDocumentoBean
     * @throws Exception
     */
    public void remove(DocumentoBean oDocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oDocumentoBean.getId(), "documento");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("DocumentoDao.removeDocumento: Error: " + e.getMessage());
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
            pages = oMysql.getCount("documento", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("DocumentoDao.getCount: Error: " + e.getMessage());
        }
    }
}
