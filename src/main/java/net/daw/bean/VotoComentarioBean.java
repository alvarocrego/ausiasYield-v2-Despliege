/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

/**
 *
 * @author Diana Ortega
 */
public class VotoComentarioBean {
    
    private int id = 0;
    private ComentBean id_comentario = null;
    private UsuarioBean id_usuario = null;
    private int valor = 0;
    
    
    public VotoComentarioBean() { 
        this.id_comentario = new ComentBean();
        this.id_comentario.setId(0);
        this.id_usuario = new UsuarioBean();
        this.id_usuario.setId(0);
    }

    public VotoComentarioBean(int id) {
        this.id = id;
        this.id_comentario = new ComentBean();
        this.id_comentario.setId(0);
        this.id_usuario = new UsuarioBean();
        this.id_usuario.setId(0);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ComentBean getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(ComentBean id_comentario) {
        this.id_comentario = id_comentario;
    }

    public UsuarioBean getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(UsuarioBean id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

   
}