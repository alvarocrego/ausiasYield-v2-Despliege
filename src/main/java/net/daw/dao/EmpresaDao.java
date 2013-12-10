package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.EmpresaBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

/**
 *
 * @author AntonioNP
 */
public class EmpresaDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public EmpresaDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("empresa", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EmpresaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<EmpresaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<EmpresaBean> arrEmpresa = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("empresa", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                EmpresaBean oEmpresaBean = new EmpresaBean(iterador.next());
                arrEmpresa.add(this.get(oEmpresaBean));
            }
            oMysql.desconexion();
            return arrEmpresa;
        } catch (Exception e) {
            throw new Exception("EmpresaDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("empresa", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EmpresaDao.getCount: Error: " + e.getMessage());
        }
    }

    public EmpresaBean get(EmpresaBean oEmpresaBean) throws Exception {
        if (oEmpresaBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("empresa", oEmpresaBean.getId())) {
                    oEmpresaBean.setId(0);
                } else {
                    
                   UsuarioBean oUsuarioBean = new UsuarioBean();

                    oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("empresa", "id_usuario", oEmpresaBean.getId())));

                    UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                    oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
                    oEmpresaBean.setUsuario(oUsuarioBean);
                    
                    oEmpresaBean.setId_usuario(Integer.parseInt(oMysql.getOne("empresa", "id_usuario", oEmpresaBean.getId())));
                    oEmpresaBean.setNombre(oMysql.getOne("empresa", "nombre", oEmpresaBean.getId()));
                    oEmpresaBean.setCif(oMysql.getOne("empresa", "cif", oEmpresaBean.getId()));
                    oEmpresaBean.setDireccion(oMysql.getOne("empresa", "direccion", oEmpresaBean.getId()));
                    oEmpresaBean.setLocalidad(oMysql.getOne("empresa", "localidad", oEmpresaBean.getId()));
                    oEmpresaBean.setProvincia(oMysql.getOne("empresa", "provincia", oEmpresaBean.getId()));
                    oEmpresaBean.setPais(oMysql.getOne("empresa", "pais", oEmpresaBean.getId()));
                    oEmpresaBean.setTelefono(oMysql.getOne("empresa", "telefono", oEmpresaBean.getId()));
                    oEmpresaBean.setFax(oMysql.getOne("empresa", "fax", oEmpresaBean.getId()));
                    oEmpresaBean.setActividad(oMysql.getOne("empresa", "actividad", oEmpresaBean.getId()));
                    oEmpresaBean.setNombrecontacto(oMysql.getOne("empresa", "nombrecontacto", oEmpresaBean.getId()));
                    oEmpresaBean.setEmailcontacto(oMysql.getOne("empresa", "emailcontacto", oEmpresaBean.getId()));
                    oEmpresaBean.setValidada(oMysql.getOne("empresa", "validada", oEmpresaBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("EmpresaDao.getEmpresa: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oEmpresaBean.setId(0);
        }
        return oEmpresaBean;
    }

    public void set(EmpresaBean oEmpresaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();

            if (oEmpresaBean.getId() == 0) {
                oEmpresaBean.setId(oMysql.insertOne("empresa"));
            }
            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            oUsuarioDao.set(oEmpresaBean.getUsuario());

            
            oEmpresaBean.setUsuario(oUsuarioDao.getFromLogin(oEmpresaBean.getUsuario()));
            
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "id_usuario", Integer.toString(oEmpresaBean.getUsuario().getId()));
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "nombre", oEmpresaBean.getNombre());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "cif", oEmpresaBean.getCif());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "direccion", oEmpresaBean.getDireccion());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "localidad", oEmpresaBean.getLocalidad());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "provincia", oEmpresaBean.getProvincia());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "pais", oEmpresaBean.getPais());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "telefono", oEmpresaBean.getTelefono());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "fax", oEmpresaBean.getFax());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "actividad", oEmpresaBean.getActividad());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "nombrecontacto", oEmpresaBean.getNombrecontacto());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "emailcontacto", oEmpresaBean.getEmailcontacto());
            oMysql.updateOne(oEmpresaBean.getId(), "empresa", "validada", oEmpresaBean.getValidada());

            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("EmpresaDao.setEmpresa: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(EmpresaBean oEmpresaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oEmpresaBean.getId(), "empresa");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EmpresaDao.removeEmpresa: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
     public EmpresaBean getFromId_usuario(UsuarioBean oUsuarioBean) throws Exception {
        EmpresaBean oEmpresaBean = new EmpresaBean();
        if (oUsuarioBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                String id_usuario = Integer.toString(oUsuarioBean.getId());
                Integer id_user = Integer.parseInt(oMysql.getId("empresa", "id_usuario", id_usuario));
                oEmpresaBean.setId(id_user);
            } catch (Exception e) {
                throw new Exception("EmpresaDao.getEmpresa: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oEmpresaBean.setId(0);
        }
        return oEmpresaBean;
    }
}
