package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.PreguntaBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class PreguntaDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public PreguntaDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("pregunta", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("PreguntaDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<PreguntaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<PreguntaBean> arrPregunta = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("pregunta", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                PreguntaBean oPreguntaBean = new PreguntaBean(iterador.next());
                arrPregunta.add(this.get(oPreguntaBean));
            }
            oMysql.desconexion();
            return arrPregunta;
        } catch (Exception e) {
            throw new Exception("PreguntaDao.getPage: Error: " + e.getMessage());
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

    public PreguntaBean get(PreguntaBean oPreguntaBean) throws Exception {
        if (oPreguntaBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("pregunta", oPreguntaBean.getId())) {
                    oPreguntaBean.setId(0);
                } else {
                    oPreguntaBean.setDescripcion(oMysql.getOne("pregunta", "descripcion", oPreguntaBean.getId()));
                    String intId_cuestionario = oMysql.getOne("pregunta", "id_cuestionario", oPreguntaBean.getId());
                    if (intId_cuestionario != null) {
                        oPreguntaBean.getCuestionario().setId(Integer.parseInt(intId_cuestionario));
                        CuestionarioDao oCuestionarioDao = new CuestionarioDao(enumTipoConexion);
                        oPreguntaBean.setCuestionario(oCuestionarioDao.get(oPreguntaBean.getCuestionario()));
                    }
                }
            } catch (Exception e) {
                throw new Exception("PreguntaDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oPreguntaBean.setId(0);
        }
        return oPreguntaBean;
    }

    public void set(PreguntaBean oPreguntaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oPreguntaBean.getId() == 0) {
                oPreguntaBean.setId(oMysql.insertOne("pregunta"));
            }
            oMysql.updateOne(oPreguntaBean.getId(), "pregunta", "descripcion", oPreguntaBean.getDescripcion());
            Integer id_cuestionario = oPreguntaBean.getCuestionario().getId();
            if (id_cuestionario > 0) {
                oMysql.updateOne(oPreguntaBean.getId(), "pregunta", "id_cuestionario", id_cuestionario.toString());
            } else {
                oMysql.setNull(oPreguntaBean.getId(), "pregunta", "id_cuestionario");
            }
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("PreguntaDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(PreguntaBean oPreguntaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oPreguntaBean.getId(), "pregunta");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("PreguntaDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
