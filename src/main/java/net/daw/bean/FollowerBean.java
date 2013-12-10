/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.bean;

/**
 *
 * @author al037570
 */
public class FollowerBean {
    
    private Integer id = 0;
    private UsuarioBean idusuario1 = null;
    private UsuarioBean idusuario2 = null;
 

    public FollowerBean() {
        this.idusuario1 = new UsuarioBean();
        this.idusuario2 = new UsuarioBean();
       
    }

    public FollowerBean(Integer id) {
        this.id = id;
        this.idusuario1 = new UsuarioBean();
        this.idusuario2 = new UsuarioBean();
     
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioBean getUsuario1() {
        return this.idusuario1;
    }
    
    public void setUsuario1(UsuarioBean idusuario1) {
        this.idusuario1 = idusuario1;
    }
    
    public UsuarioBean getUsuario2() {
        return this.idusuario2;
    }

    public void setUsuario2(UsuarioBean idusuario2) {
        this.idusuario2 = idusuario2;
    }
   
    
}
