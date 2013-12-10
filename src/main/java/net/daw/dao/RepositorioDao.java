/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.AbstractCollection;
import net.daw.bean.RepositorioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;
import java.util.Date;
import net.daw.bean.LenguajeBean;
import net.daw.bean.DocumentoBean;
import net.daw.bean.UsuarioBean;

/**
 *
 * @author Ana
 */
public class RepositorioDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    public RepositorioDao(Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("repositorio", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getPages: Error: " + e.getMessage());

        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("repositorio", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<RepositorioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<RepositorioBean> arrRepositorio = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("repositorio", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                RepositorioBean oRepositorioBean = new RepositorioBean(iterador.next());
                arrRepositorio.add(this.get(oRepositorioBean));
            }
            oMysql.desconexion();
            return arrRepositorio;
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public RepositorioBean get(RepositorioBean oRepositorioBean) throws Exception {

        try {
            oMysql.conexion(enumTipoConexion);

            LenguajeBean oLenguajeBean = new LenguajeBean();
            DocumentoBean oDocumentoBean = new DocumentoBean();
            UsuarioBean oUsuarioBean = new UsuarioBean();

            oRepositorioBean.setTitulo(oMysql.getOne("repositorio", "titulo", oRepositorioBean.getId()));
            oRepositorioBean.setContenido(oMysql.getOne("repositorio", "contenido", oRepositorioBean.getId()));
            oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("repositorio", "id_usuario", oRepositorioBean.getId())));
            oLenguajeBean.setId(Integer.parseInt(oMysql.getOne("repositorio", "id_lenguaje", oRepositorioBean.getId())));
            oDocumentoBean.setId(Integer.parseInt(oMysql.getOne("repositorio", "id_documento", oRepositorioBean.getId())));
            String strFecha = oMysql.getOne("repositorio", "fecha", oRepositorioBean.getId());
            if (strFecha != null) {
                Date dFecha = new SimpleDateFormat("yyyy-MM-dd").parse(strFecha);
                oRepositorioBean.setFecha(dFecha);
            } else {
                oRepositorioBean.setFecha(new Date(0));
            }

            LenguajeDao oLenguajeDao = new LenguajeDao(enumTipoConexion);
            DocumentoDao oDocumentoDao = new DocumentoDao(enumTipoConexion);
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);

            oLenguajeBean = oLenguajeDao.get(oLenguajeBean);
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean);
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);

            oRepositorioBean.setLenguaje(oLenguajeBean);
            oRepositorioBean.setDocumento(oDocumentoBean);
            oRepositorioBean.setUsuario(oUsuarioBean);
            
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.getRespositorio: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

        return oRepositorioBean;
    }

    public void set(RepositorioBean oRepositorioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oRepositorioBean.getId() == 0) {
                oRepositorioBean.setId(oMysql.insertOne("repositorio"));
            }
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "titulo", oRepositorioBean.getTitulo());
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "contenido", oRepositorioBean.getContenido());
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_usuario", Integer.toString(oRepositorioBean.getUsuario().getId()));
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_lenguaje", Integer.toString(oRepositorioBean.getLenguaje().getId()));
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "id_documento", Integer.toString(oRepositorioBean.getDocumento().getId()));
            java.text.SimpleDateFormat oSimpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            oMysql.updateOne(oRepositorioBean.getId(), "repositorio", "fecha", oSimpleDateFormat.format(oRepositorioBean.getFecha()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("RepositorioDao.setRepositorio: Error: " + e.getMessage());

        } finally {
            oMysql.desconexion();
        }

    }

    public void remove(RepositorioBean oRepositorioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oRepositorioBean.getId(), "repositorio");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("RepositorioDao.removeRepositorio: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
