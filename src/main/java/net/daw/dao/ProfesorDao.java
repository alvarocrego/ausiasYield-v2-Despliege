/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.ProfesorBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

/**
 * @author Pedro Benito
 */
public class ProfesorDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    public ProfesorDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("profesor", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProfesorDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<ProfesorBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ProfesorBean> arrProfesor = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("profesor", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ProfesorBean oProfesorBean = new ProfesorBean(iterador.next());
                arrProfesor.add(this.get(oProfesorBean));
            }
            oMysql.desconexion();
            return arrProfesor;
        } catch (Exception e) {
            throw new Exception("ProfesorDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("profesor", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProfesorDao.getCount: Error: " + e.getMessage());
        }
    }

    public ProfesorBean get(ProfesorBean oProfesorBean) throws Exception {
        if (oProfesorBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("profesor", oProfesorBean.getId())) {
                    oProfesorBean.setId(0);
                } else {
                    UsuarioBean oUsuarioBean = new UsuarioBean();

                    oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("profesor", "id_usuario", oProfesorBean.getId())));

                    UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                    oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
                    oProfesorBean.setUsuario(oUsuarioBean);

                    oProfesorBean.setId_usuario(Integer.parseInt(oMysql.getOne("profesor", "id_usuario", oProfesorBean.getId())));
                    oProfesorBean.setDni(oMysql.getOne("profesor", "dni", oProfesorBean.getId()));
                    oProfesorBean.setNombre(oMysql.getOne("profesor", "nombre", oProfesorBean.getId()));
                    oProfesorBean.setApe1(oMysql.getOne("profesor", "ape1", oProfesorBean.getId()));
                    oProfesorBean.setApe2(oMysql.getOne("profesor", "ape2", oProfesorBean.getId()));
                    oProfesorBean.setSexo(oMysql.getOne("profesor", "sexo", oProfesorBean.getId()));
                    oProfesorBean.setTelefono(oMysql.getOne("profesor", "telefono", oProfesorBean.getId()));
                    oProfesorBean.setEmail(oMysql.getOne("profesor", "email", oProfesorBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("ProfesorDao.getProfesor: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oProfesorBean.setId(0);
        }
        return oProfesorBean;
    }

    public void set(ProfesorBean oProfesorBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();

            if (oProfesorBean.getId() == 0) {
                oProfesorBean.setId(oMysql.insertOne("profesor"));
            }
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            oUsuarioDao.set(oProfesorBean.getUsuario());

            oProfesorBean.setUsuario(oUsuarioDao.getFromLogin(oProfesorBean.getUsuario()));

            oMysql.updateOne(oProfesorBean.getId(), "profesor", "id_usuario", Integer.toString(oProfesorBean.getUsuario().getId()));
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "dni", oProfesorBean.getDni());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "nombre", oProfesorBean.getNombre());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "ape1", oProfesorBean.getApe1());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "ape2", oProfesorBean.getApe2());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "sexo", oProfesorBean.getSexo());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "telefono", oProfesorBean.getTelefono());
            oMysql.updateOne(oProfesorBean.getId(), "profesor", "email", oProfesorBean.getEmail());

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ProfesorDao.setProfesor: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(ProfesorBean oProfesorBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oProfesorBean.getId(), "profesor");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ProfesorDao.removeProfesor: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ProfesorBean getFromId_usuario(UsuarioBean oUsuarioBean) throws Exception {
        ProfesorBean oProfesorBean = new ProfesorBean();
        if (oUsuarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                String id_usuario = Integer.toString(oUsuarioBean.getId());
                Integer id_user = Integer.parseInt(oMysql.getId("profesor", "id_usuario", id_usuario));
                oProfesorBean.setId(id_user);
            } catch (Exception e) {
                throw new Exception("ProfesorDao.getEmpresa: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oProfesorBean.setId(0);
        }
        return oProfesorBean;
    }
}
