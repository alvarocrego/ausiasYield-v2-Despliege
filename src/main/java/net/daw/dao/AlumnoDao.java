/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.AlumnoBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v2.0
 * @since mie, 12 noviembre 2013
 */
public class AlumnoDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    public AlumnoDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("alumno", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("AlumnoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

public ArrayList<AlumnoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<AlumnoBean> arrAlumno = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("alumno", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                AlumnoBean oAlumnoBean = new AlumnoBean(iterador.next());
                arrAlumno.add(this.get(oAlumnoBean));
            }
            oMysql.desconexion();
            return arrAlumno;
        } catch (Exception e) {
            throw new Exception("AlumnoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("alumno", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("AlumnoDao.getCount: Error: " + e.getMessage());
        }
    }

    public AlumnoBean get(AlumnoBean oAlumnoBean) throws Exception {
        if (oAlumnoBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("alumno", oAlumnoBean.getId())) {
                    oAlumnoBean.setId(0);
                } else {
                    UsuarioBean oUsuarioBean = new UsuarioBean();

                    oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("alumno", "id_usuario", oAlumnoBean.getId())));

                    UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                    oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
                    oAlumnoBean.setUsuario(oUsuarioBean);

                    oAlumnoBean.setId_usuario(Integer.parseInt(oMysql.getOne("alumno", "id_usuario", oAlumnoBean.getId())));
                    oAlumnoBean.setDni(oMysql.getOne("alumno", "dni", oAlumnoBean.getId()));
                    oAlumnoBean.setNumexpediente(oMysql.getOne("alumno", "numexpediente", oAlumnoBean.getId()));
                    oAlumnoBean.setNombre(oMysql.getOne("alumno", "nombre", oAlumnoBean.getId()));
                    oAlumnoBean.setApe1(oMysql.getOne("alumno", "ape1", oAlumnoBean.getId()));
                    oAlumnoBean.setApe2(oMysql.getOne("alumno", "ape2", oAlumnoBean.getId()));
                    oAlumnoBean.setSexo(oMysql.getOne("alumno", "sexo", oAlumnoBean.getId()));
                    oAlumnoBean.setDomicilio(oMysql.getOne("alumno", "domicilio", oAlumnoBean.getId()));
                    oAlumnoBean.setCodpostal(oMysql.getOne("alumno", "codpostal", oAlumnoBean.getId()));
                    oAlumnoBean.setPoblacion(oMysql.getOne("alumno", "poblacion", oAlumnoBean.getId()));
                    oAlumnoBean.setProvincia(oMysql.getOne("alumno", "provincia", oAlumnoBean.getId()));
                    oAlumnoBean.setTelefono(oMysql.getOne("alumno", "telefono", oAlumnoBean.getId()));
                    oAlumnoBean.setEmail(oMysql.getOne("alumno", "email", oAlumnoBean.getId()));
                    oAlumnoBean.setValidado(oMysql.getOne("alumno", "validado", oAlumnoBean.getId()));

                }
            } catch (Exception e) {
                throw new Exception("AlumnoDao.getAlumno: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oAlumnoBean.setId(0);
        }
        return oAlumnoBean;
    }

    public void set(AlumnoBean oAlumnoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();

            if (oAlumnoBean.getId() == 0) {
                oAlumnoBean.setId(oMysql.insertOne("alumno"));
            }
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            oUsuarioDao.set(oAlumnoBean.getUsuario());
            
            oAlumnoBean.setUsuario(oUsuarioDao.getFromLogin(oAlumnoBean.getUsuario()));
            
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "id_usuario", Integer.toString(oAlumnoBean.getUsuario().getId()));
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "dni", oAlumnoBean.getDni());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "numexpediente", oAlumnoBean.getNumexpediente());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "nombre", oAlumnoBean.getNombre());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "ape1", oAlumnoBean.getApe1());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "ape2", oAlumnoBean.getApe2());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "sexo", oAlumnoBean.getSexo());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "domicilio", oAlumnoBean.getDomicilio());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "codpostal", oAlumnoBean.getCodpostal());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "poblacion", oAlumnoBean.getPoblacion());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "provincia", oAlumnoBean.getProvincia());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "telefono", oAlumnoBean.getTelefono());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "email", oAlumnoBean.getEmail());
            oMysql.updateOne(oAlumnoBean.getId(), "alumno", "validado", oAlumnoBean.getValidado());
            
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("AlumnoDao.setAlumno: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(AlumnoBean oAlumnoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oAlumnoBean.getId(), "alumno");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("AlumnoDao.removeAlumno: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public AlumnoBean getFromId_usuario(UsuarioBean oUsuarioBean) throws Exception {
        AlumnoBean oAlumnoBean = new AlumnoBean();
        if (oUsuarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                String id_usuario = Integer.toString(oUsuarioBean.getId());
                Integer id_user = Integer.parseInt(oMysql.getId("alumno", "id_usuario", id_usuario));
                oAlumnoBean.setId(id_user);
            } catch (Exception e) {
                throw new Exception("AlumnoDao.getFromId_usuario: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oAlumnoBean.setId(0);
        }
        return oAlumnoBean;
    }
}
