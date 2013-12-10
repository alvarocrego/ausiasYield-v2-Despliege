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
import net.daw.bean.ComentBean;
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Jordi Eslava Barrera
 */
public class ComentDao {

    private Mysql oMysql;
    private net.daw.helper.Enum.Connection enumTipoConexion;

    public ComentDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public void set(ComentBean oComentBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oComentBean.getId() == 0) {
                oComentBean.setId(oMysql.insertOne("comentario"));
            }
            oMysql.updateOne(oComentBean.getId(), "comentario", "titulo", oComentBean.getTitulo());
            oMysql.updateOne(oComentBean.getId(), "comentario", "contenido", oComentBean.getContenido());
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oComentBean.getId(), "comentario", "fecha", oSimpleDateFormat.format(oComentBean.getFecha()));
            oMysql.updateOne(oComentBean.getId(), "comentario", "id_usuario", String.valueOf(oComentBean.getId_usuario()));
            oMysql.updateOne(oComentBean.getId(), "comentario", "id_documento", String.valueOf(oComentBean.getId_documento()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ComentDao.setComent: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("comentario", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ComentDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<ComentBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ComentBean> arrComent = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("comentario", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ComentBean oComentBean = new ComentBean(iterador.next());
                arrComent.add(this.get(oComentBean));
            }
            oMysql.desconexion();
            return arrComent;
        } catch (Exception e) {
            throw new Exception("ComentDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ComentBean get(ComentBean oComentBean) throws Exception {
        if (oComentBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("producto", oComentBean.getId())) {
                    oComentBean.setId(0);
                } else {
                    oComentBean.setTitulo(oMysql.getOne("comentario", "titulo", oComentBean.getId()));
                    oComentBean.setContenido(oMysql.getOne("comentario", "contenido", oComentBean.getId()));
                    String intId_documento = oMysql.getOne("comentario", "id_documento", oComentBean.getId());
                  String intId_usuario = oMysql.getOne("comentario", "id_usuario", oComentBean.getId());
                    if (intId_documento != null) {
                        oComentBean.getId_documento().setId(Integer.parseInt(intId_documento));
                        DocumentoDao oDocumentoDao = new DocumentoDao(enumTipoConexion);
                        oComentBean.setId_documento(oDocumentoDao.get(oComentBean.getId_documento()));
                    }
                   if (intId_usuario != null) {
                        oComentBean.getId_documento().setId(Integer.parseInt(intId_documento));
                        UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                        oComentBean.setId_usuario(oUsuarioDao.get(oComentBean.getId_usuario()));
                    }
                    String strFecha = oMysql.getOne("comentario", "fecha", oComentBean.getId());
                    if (strFecha != null) {
                        Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                        oComentBean.setFecha(dFecha);
                    } else {
                        oComentBean.setFecha(new Date(0));
                    }
                }
            } catch (Exception e) {
                throw new Exception("ComentDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oComentBean.setId(0);
        }
        return oComentBean;
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("comentario", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ComentDao.getCount: Error: " + e.getMessage());
        }
    }

    public void remove(ComentBean oComentBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oComentBean.getId(), "comentario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ComentDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
