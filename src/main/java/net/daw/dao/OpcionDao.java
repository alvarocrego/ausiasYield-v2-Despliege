package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.OpcionBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class OpcionDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public OpcionDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("opcion", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("OpcionDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<OpcionBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<OpcionBean> arrOpcion = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("opcion", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                OpcionBean oOpcionBean = new OpcionBean(iterador.next());
                arrOpcion.add(this.get(oOpcionBean));
            }
            oMysql.desconexion();
            return arrOpcion;
        } catch (Exception e) {
            throw new Exception("OpcionDao.getPage: Error: " + e.getMessage());
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

    public OpcionBean get(OpcionBean oOpcionBean) throws Exception {
        if (oOpcionBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("opcion", oOpcionBean.getId())) {
                    oOpcionBean.setId(0);
                } else {
                    oOpcionBean.setDescripcion(oMysql.getOne("opcion", "descripcion", oOpcionBean.getId()));
                    String intId_pregunta = oMysql.getOne("opcion", "id_pregunta", oOpcionBean.getId());
                    if (intId_pregunta != null) {
                        oOpcionBean.getPregunta().setId(Integer.parseInt(intId_pregunta));
                        PreguntaDao oPreguntaDao = new PreguntaDao(enumTipoConexion);
                        oOpcionBean.setPregunta(oPreguntaDao.get(oOpcionBean.getPregunta()));
                    }
                    String correcta = oMysql.getOne("opcion","correcta",oOpcionBean.getId());
                    oOpcionBean.setCorrecta( correcta.equals("1") );
                }
            } catch (Exception e) {
                throw new Exception("OpcionDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oOpcionBean.setId(0);
        }
        return oOpcionBean;
    }

    public void set(OpcionBean oOpcionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oOpcionBean.getId() == 0) {
                oOpcionBean.setId(oMysql.insertOne("opcion"));
            }
            oMysql.updateOne(oOpcionBean.getId(), "opcion", "descripcion", oOpcionBean.getDescripcion());
            Integer id_pregunta = oOpcionBean.getPregunta().getId();
            if ( oOpcionBean.getPregunta().getId() > 0 ) {
                oMysql.updateOne(oOpcionBean.getId(), "opcion", "id_pregunta", id_pregunta.toString());
            } else {
                oMysql.setNull(oOpcionBean.getId(), "opcion", "id_pregunta");
            }
            boolean correcta = oOpcionBean.getCorrecta();
            int intcorrecta = correcta?1:0;
            oMysql.updateOne(oOpcionBean.getId(),"opcion","correcta",Integer.toString(intcorrecta));
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("OpcionDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(OpcionBean oOpcionBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oOpcionBean.getId(), "opcion");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("OpcionDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
