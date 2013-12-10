package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import net.daw.bean.AlumnoBean;
import net.daw.bean.EmpresaBean;
import net.daw.bean.ProfesorBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class UsuarioDao extends HttpServlet {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public UsuarioDao(Enum.Connection tipoConexion) throws Exception {
        super();
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public UsuarioBean getFromLogin(UsuarioBean oUsuario) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            String strId = oMysql.getId("usuario", "login", oUsuario.getLogin());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                oUsuario.setId(Integer.parseInt(strId));
                oUsuario.setPassword(oMysql.getOne("usuario", "password", oUsuario.getId()));
            }
            oMysql.desconexion();
            return oUsuario;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPage: Error: " + e.getMessage());
        }
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("usuario", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("usuario", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getCount: Error: " + e.getMessage());
        }
    }

    public ArrayList<UsuarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("usuario", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                UsuarioBean oUsuarioBean = new UsuarioBean(iterador.next());
                arrUsuario.add(this.get(oUsuarioBean));
            }
            oMysql.desconexion();
            return arrUsuario;
        } catch (Exception e) {
            throw new Exception("HiloDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public UsuarioBean get(UsuarioBean oUsuarioBean) throws Exception {
        if (oUsuarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("usuario", oUsuarioBean.getId())) {
                    oUsuarioBean.setId(0);
                } else {
                    oUsuarioBean.setLogin(oMysql.getOne("usuario", "login", oUsuarioBean.getId()));
                    oUsuarioBean.setPassword(oMysql.getOne("usuario", "password", oUsuarioBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("UsuarioDao.getUsuario: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oUsuarioBean.setId(0);
        }
        return oUsuarioBean;
    }

    public UsuarioBean type(UsuarioBean oUsuarioBean) throws Exception {

        try {
            AlumnoDao oAlumnoDao = new AlumnoDao(enumTipoConexion);
            AlumnoBean oAlumnoBean = oAlumnoDao.getFromId_usuario(oUsuarioBean);
            oUsuarioBean.setTipoUsuario(Enum.TipoUsuario.Alumno);
        } catch (Exception e1) {
            try {
                ProfesorDao oProfesorDao = new ProfesorDao(enumTipoConexion);
                ProfesorBean oProfesorBean = oProfesorDao.getFromId_usuario(oUsuarioBean);
                oUsuarioBean.setTipoUsuario(Enum.TipoUsuario.Profesor);
            } catch (Exception e2) {
                try {
                    EmpresaDao oEmpresaDao = new EmpresaDao(enumTipoConexion);                  
                    EmpresaBean oEmpresaBean = oEmpresaDao.getFromId_usuario(oUsuarioBean);
                    oUsuarioBean.setTipoUsuario(Enum.TipoUsuario.Empresa);
                } catch (Exception e3) {
                    throw new Exception("UsuarioDao.type: Error: " + e3.getMessage());
                }
            }
        } finally {
            oMysql.desconexion();
        }
        return oUsuarioBean;
    }

    public void set(UsuarioBean oUsuarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oUsuarioBean.getId() == 0) {
                oUsuarioBean.setId(oMysql.insertOne("usuario"));
            }
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "login", oUsuarioBean.getLogin());
            oMysql.updateOne(oUsuarioBean.getId(), "usuario", "password", oUsuarioBean.getPassword());

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("UsuarioDao.setCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(UsuarioBean oUsuarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oUsuarioBean.getId(), "usuario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("UsuarioDao.removeCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
