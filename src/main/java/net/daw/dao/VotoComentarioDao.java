/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.ComentBean;
import net.daw.bean.UsuarioBean;
import net.daw.bean.VotoComentarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author Diana Ortega
 */
public class VotoComentarioDao {
    
    private Mysql oMysql;
    private net.daw.helper.Enum.Connection enumTipoConexion;

 
    public VotoComentarioDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("votocomentario", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    
    public Integer getCount(ArrayList<FilterBean> alFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("votocomentario",  alFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getCount: Error: " + e.getMessage());
        }
    }
    
    
    public ArrayList<VotoComentarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean>  hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<VotoComentarioBean> arrVotoComentario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("votocomentario", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                VotoComentarioBean oVotoComentarioBean = new VotoComentarioBean(iterador.next());
                arrVotoComentario.add(this.get(oVotoComentarioBean));
            }
            oMysql.desconexion();
            return arrVotoComentario;
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    
     public VotoComentarioBean get(VotoComentarioBean oVotoComentarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

            UsuarioBean oUsuarioBean = new UsuarioBean();
            ComentBean oComentBean = new ComentBean();
            
            oUsuarioBean.setId(Integer.parseInt(oMysql.getOne("votocomentario", "id_usuario", oVotoComentarioBean.getId())));
            oComentBean.setId(Integer.parseInt(oMysql.getOne("votocomentario", "id_comentario", oVotoComentarioBean.getId())));
            oVotoComentarioBean.setValor(Integer.parseInt(oMysql.getOne("votocomentario", "valor", oVotoComentarioBean.getId())));

            UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
            ComentDao oComentDao = new ComentDao(enumTipoConexion);
           
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean);
            oComentBean = oComentDao.get(oComentBean);         

            oVotoComentarioBean.setId_usuario(oUsuarioBean);
            oVotoComentarioBean.setId_comentario(oComentBean);
         
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("UsuarioDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oVotoComentarioBean;
    }
    
    
     public void set(VotoComentarioBean oVotoComentarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oVotoComentarioBean.getId() == 0) {
                oVotoComentarioBean.setId(oMysql.insertOne("votocomentario"));
            }
            oMysql.updateOne(oVotoComentarioBean.getId(), "votocomentario", "id_comentario", Integer.toString(oVotoComentarioBean.getId_comentario().getId()));
            oMysql.updateOne(oVotoComentarioBean.getId(), "votocomentario", "id_usuario", Integer.toString(oVotoComentarioBean.getId_usuario().getId()));
            oMysql.updateOne(oVotoComentarioBean.getId(), "votocomentario", "valor", Integer.toString(oVotoComentarioBean.getValor()));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("VotoComentarioDao.setVotoComentario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
     
     public void remove(VotoComentarioBean oVotoComentarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oVotoComentarioBean.getId(), "votocomentario");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("VotoComentarioDao.removeVotoComentario: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    
    
    
     

    
     

    
   
    
    

    

    
    }