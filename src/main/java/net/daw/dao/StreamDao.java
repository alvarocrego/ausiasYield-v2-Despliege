package net.daw.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import net.daw.bean.StreamBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class StreamDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;

    public StreamDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("stream", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("StreamDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<StreamBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<StreamBean> arrStream = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("stream", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                StreamBean oStreamBean = new StreamBean(iterador.next());
                arrStream.add(this.get(oStreamBean));
            }
            oMysql.desconexion();
            return arrStream;
        } catch (Exception e) {
            throw new Exception("StreamDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }


    public StreamBean get(StreamBean oStreamBean) throws Exception {
        if (oStreamBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("stream", oStreamBean.getId())) {
                    oStreamBean.setId(0);
                } else {
                    oStreamBean.setContenido(oMysql.getOne("stream", "contenido", oStreamBean.getId()));
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formatoFecha.parse(oMysql.getOne("stream", "fecha", oStreamBean.getId()) );
                    oStreamBean.setFecha( fecha );
                    String intId_usuario = oMysql.getOne("stream", "id_usuario", oStreamBean.getId());
                    if (intId_usuario != null) {
                        oStreamBean.getUsuario().setId(Integer.parseInt(intId_usuario));
                        UsuarioDao oUsuarioDao = new UsuarioDao(enumTipoConexion);
                        oStreamBean.setUsuario(oUsuarioDao.get(oStreamBean.getUsuario()));
                    }
                }
            } catch (Exception e) {
                throw new Exception("StreamDao.get: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oStreamBean.setId(0);
        }
        return oStreamBean;
    }

    public void set(StreamBean oStreamBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oStreamBean.getId() == 0) {
                oStreamBean.setId(oMysql.insertOne("stream"));
            }
            oMysql.updateOne(oStreamBean.getId(), "stream", "contenido", oStreamBean.getContenido());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = formatoFecha.format( oStreamBean.getFecha() );
            oMysql.updateOne(oStreamBean.getId(), "stream", "fecha", fecha);
            Integer id_usuario = oStreamBean.getUsuario().getId();
            if ( oStreamBean.getUsuario().getId() > 0 ) {
                oMysql.updateOne(oStreamBean.getId(), "stream", "id_usuario", id_usuario.toString());
            } else {
                oMysql.setNull(oStreamBean.getId(), "stream", "id_usuario");
            }
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("StreamDao.set: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public void remove(StreamBean oStreamBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oStreamBean.getId(), "stream");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("StreamDao.remove: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }

    }
}
