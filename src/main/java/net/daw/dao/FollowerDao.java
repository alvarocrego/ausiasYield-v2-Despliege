/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.FollowerBean;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.FilterBean;

/**
 *
 * @author al037570
 */
public class FollowerDao {
    
    private final Mysql oMysql;
    private final net.daw.helper.Enum.Connection enumTipoConexion;

    public FollowerDao(net.daw.helper.Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("follower", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            /*????*/
            throw new Exception("UsuarioDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<FollowerBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<FollowerBean> arrFollower = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("follower", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                FollowerBean oFollowerBean = new FollowerBean(iterador.next());
                
                
                
                arrFollower.add(this.get(oFollowerBean));
            }
            oMysql.desconexion();
            return arrFollower;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    /*public ArrayList<String> getNeighborhood(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        oMysql.conexion(enumTipoConexion);
        ArrayList<String> n = oMysql.getNeighborhood(strLink, intPageNumber, intTotalPages, intNeighborhood);
        oMysql.desconexion();
        return n;
    }*/

    
    public FollowerBean get(FollowerBean oFollowerBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);

//            UsuarioBean oUsuarioBean1 = new UsuarioBean();
//            UsuarioBean oUsuarioBean2 = new UsuarioBean();
          
            UsuarioBean oUsuarioBean1=oFollowerBean.getUsuario1();
            UsuarioBean oUsuarioBean2=oFollowerBean.getUsuario2();
            
            oUsuarioBean1.setId(Integer.parseInt(oMysql.getOne("follower", "id_usuario1", oFollowerBean.getId())));
            oUsuarioBean2.setId(Integer.parseInt(oMysql.getOne("follower", "id_usuario2", oFollowerBean.getId())));
            
            
      
            
            
            
            
            UsuarioDao oUsuarioDao1 = new UsuarioDao(enumTipoConexion);
            UsuarioDao oUsuarioDao2 = new UsuarioDao(enumTipoConexion);
            
            oUsuarioBean1 = oUsuarioDao1.get(oUsuarioBean1);
            oUsuarioBean2 = oUsuarioDao2.get(oUsuarioBean2);

            oFollowerBean.setUsuario1(oUsuarioBean1);
            oFollowerBean.setUsuario2(oUsuarioBean2);


            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("UsuarioDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oFollowerBean;
    }

    public void set(FollowerBean oFollowerBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oFollowerBean.getId() == 0) {
                oFollowerBean.setId(oMysql.insertOne("follower"));
            }
            oMysql.updateOne(oFollowerBean.getId(), "follower", "id_usuario1", Integer.toString( oFollowerBean.getUsuario1().getId()) );
            oMysql.updateOne(oFollowerBean.getId(), "follower", "id_usuario2", Integer.toString( oFollowerBean.getUsuario2().getId()) );
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("FollowerDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(FollowerBean oFollowerBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oFollowerBean.getId(), "follower");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("FollowerDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
    
    
    
}
