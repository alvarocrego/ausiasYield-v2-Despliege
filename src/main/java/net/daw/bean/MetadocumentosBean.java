/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

/**
 *
 * @author Alvaro Crego
 */
public class MetadocumentosBean {

    private int id = 0;
    private MetadocumentoBean metadocumento = null;
    private DocumentoBean documento = null;
    private int orden = 0;

    public MetadocumentosBean() {
        this.metadocumento = new MetadocumentoBean();
        this.metadocumento.setId(0);
        this.documento = new DocumentoBean();
        this.documento.setId(0);
    }

    public MetadocumentosBean(int id) {
        this.id = id;
        this.metadocumento = new MetadocumentoBean();
        this.metadocumento.setId(0);
        this.documento = new DocumentoBean();
        this.documento.setId(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MetadocumentoBean getMetadocumento() {
        return metadocumento;
    }

    public void setMetadocumento(MetadocumentoBean metadocumento) {
        this.metadocumento = metadocumento;
    }

    public DocumentoBean getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoBean documento) {
        this.documento = documento;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

}
