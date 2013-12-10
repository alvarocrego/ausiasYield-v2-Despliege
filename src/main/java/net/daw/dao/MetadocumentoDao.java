/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.dao;

/**
 *
 * @author al037431
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.MetadocumentoBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class MetadocumentoDao {

    private Mysql oMysql;
    private Enum.Connection enumTipoConexion;

    /**
     *
     * @param tipoConexion
     */
    public MetadocumentoDao(net.daw.helper.Enum.Connection tipoConexion) {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public void set(MetadocumentoBean oMetadocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oMetadocumentoBean.getId() == 0) {
                oMetadocumentoBean.setId(oMysql.insertOne("metadocumento"));
            }

            oMysql.updateOne(oMetadocumentoBean.getId(), "metadocumento", "titulo", oMetadocumentoBean.getTitulo());
            oMysql.updateOne(oMetadocumentoBean.getId(), "metadocumento", "fecha", oMetadocumentoBean.getFecha());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("MetadocumentoDao.setMetadocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("metadocumento", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("MetadocumentoDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<MetadocumentoBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<MetadocumentoBean> arrMetadocumento = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("metadocumento", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                MetadocumentoBean oMetadocumentoBean = new MetadocumentoBean(iterador.next());
                arrMetadocumento.add(this.get(oMetadocumentoBean));
            }
            oMysql.desconexion();
            return arrMetadocumento;
        } catch (Exception e) {
            throw new Exception("MetadocumentoDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

     public MetadocumentoBean get(MetadocumentoBean oMetadocumentoBean) throws Exception {
        if (oMetadocumentoBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("metadocumento", oMetadocumentoBean.getId())) {
                    oMetadocumentoBean.setId(0);
                } else {

                    oMysql.conexion(enumTipoConexion);
                    oMetadocumentoBean.setTitulo(oMysql.getOne("metadocumento", "titulo", oMetadocumentoBean.getId()));
                    oMetadocumentoBean.setFecha(oMysql.getOne("metadocumento", "fecha", oMetadocumentoBean.getId()));
//                    

                }


            } catch (Exception e) {
                throw new Exception("MetadocumentoDao.getMetadocumento: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oMetadocumentoBean.setId(0);
        }
        return oMetadocumentoBean;
    }

    public void remove(MetadocumentoBean oMetadocumentoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oMetadocumentoBean.getId(), "metadocumento");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("MetadocumentoDao.removeMetadocumento: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
}
