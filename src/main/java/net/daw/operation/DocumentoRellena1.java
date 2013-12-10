package net.daw.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.DocumentoBean;
import net.daw.dao.DocumentoDao;
import net.daw.helper.Contexto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Diana
 */
public class DocumentoRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        DocumentoBean oDocumentoBean = new DocumentoBean();
        DocumentoDao oDocumentoDao = new DocumentoDao(oContexto.getEnumTipoConexion());
        String nombre = "";

        ArrayList<String> arrContenidoPrimerGrupo = new ArrayList<>();
        arrContenidoPrimerGrupo.add("No obstante");
        arrContenidoPrimerGrupo.add("Por otra parte");
        arrContenidoPrimerGrupo.add("Asimismo");
        arrContenidoPrimerGrupo.add("Sin embargo no hemos de olvidar que");
        arrContenidoPrimerGrupo.add("De igual manera");
        arrContenidoPrimerGrupo.add("La práctica prueba que");
        arrContenidoPrimerGrupo.add("Y no es menos cierto que");
        arrContenidoPrimerGrupo.add("Las experiencias, ricas y diversas, muestran que");
        arrContenidoPrimerGrupo.add("Y aún así");
        arrContenidoPrimerGrupo.add("No obstante");
        arrContenidoPrimerGrupo.add("Incluso, bien pudiéramos atrevernos a sugerir que");
        arrContenidoPrimerGrupo.add("Es obvio señalar que");
        arrContenidoPrimerGrupo.add("De hecho");
        arrContenidoPrimerGrupo.add("También cabe añadir en este punto que");

        ArrayList<String> arrContenidoSegundoContenido = new ArrayList<>();
        arrContenidoSegundoContenido.add("la realización de las premisas del programa");
        arrContenidoSegundoContenido.add("la complejidad de los estudios de los dirigentes");
        arrContenidoSegundoContenido.add("el aumento constante, en cantidad y en extensión, de nuestra actividad");
        arrContenidoSegundoContenido.add("la estructura actual de la organización");
        arrContenidoSegundoContenido.add("el nuevo modelo de actividad de la organización");
        arrContenidoSegundoContenido.add("el desarrollo continuo de distintas formas de actividad");
        arrContenidoSegundoContenido.add("nuestra actividad de información y propaganda");
        arrContenidoSegundoContenido.add("el reforzamiento y desarrollo de las estructuras");
        arrContenidoSegundoContenido.add("la consulta con los numerosos militantes");
        arrContenidoSegundoContenido.add("el inicio de la acción general de formación de las actitudes");
        arrContenidoSegundoContenido.add("un relanzamiento específico de todos los sectores implicados");
        arrContenidoSegundoContenido.add("la superación de experiencias difíciles");
        arrContenidoSegundoContenido.add("una aplicación indiscriminada de los factores confluyentes");
        arrContenidoSegundoContenido.add("el proceso consensuado de unas y otras aplicaciones concurrentes");
        arrContenidoSegundoContenido.add("nos obliga a un exhaustivo análisis");
        arrContenidoSegundoContenido.add("cumple un rol esencial en la formación");
        arrContenidoSegundoContenido.add("exige la precisión y la determinación");
        arrContenidoSegundoContenido.add("ayuda a la preparación y a la realización");
        arrContenidoSegundoContenido.add("garantiza la participación de un grupo importante en la formación");
        arrContenidoSegundoContenido.add("cumple deberes importantes en la determinación");
        arrContenidoSegundoContenido.add("facilita la creación");
        arrContenidoSegundoContenido.add("obstaculiza la apreciación de la importancia");
        arrContenidoSegundoContenido.add("ofrece un ensayo interesante de verificación");
        arrContenidoSegundoContenido.add("implica el proceso de reestructuración y modernización");
        arrContenidoSegundoContenido.add("habrá de significar un auténtico y eficaz punto de partida");
        arrContenidoSegundoContenido.add("permite en todo caso explicitar las razones fundamentales");
        arrContenidoSegundoContenido.add("asegura, en todo caso, un proceso muy sensible de inversión");
        arrContenidoSegundoContenido.add("deriva de una indirecta incidencia superadora");

        ArrayList<String> arrContenidoTercerGrupo = new ArrayList<>();
        arrContenidoTercerGrupo.add("de las condiciones financieras y administrativas existentes.");
        arrContenidoTercerGrupo.add("de las directivas de desarrollo para el futuro.");
        arrContenidoTercerGrupo.add("del sistema de participación general.");
        arrContenidoTercerGrupo.add("de las actitudes de los miembros hacia sus deberes ineludibles.");
        arrContenidoTercerGrupo.add("de las nuevas proposiciones.");
        arrContenidoTercerGrupo.add("de las direcciones educativas en el sentido del progreso.");
        arrContenidoTercerGrupo.add("del sistema de formación de cuadros que corresponda a las necesidades.");
        arrContenidoTercerGrupo.add("de las condiciones de las actividades apropiadas.");
        arrContenidoTercerGrupo.add("del modelo de desarrollo.");
        arrContenidoTercerGrupo.add("de las formas de acción.");
        arrContenidoTercerGrupo.add("de las básicas premisas adoptadas.");
        arrContenidoTercerGrupo.add("de toda una casuística de amplio espectro.");
        arrContenidoTercerGrupo.add("de los elementos generadores.");
        arrContenidoTercerGrupo.add("de toda una serie de criterios ideológicamente sistematizados en un frente común de actuación regeneradora.");

        

//        int index;
//        Iterator<String> iterador = arrNombres.listIterator();
//        Random generator;
//
//        while (iterador.hasNext()) {
//            nombre = iterador.next();
//            generator = new Random();
//            oDocumentoBean.setId(0);
//
//            index = generator.nextInt(arrNombres.size());
//            String randomNOMBRE = arrNombres.get(index);
//            oDocumentoBean.setNombre(randomNOMBRE);
//
//            try {
//                oDocumentoDao.set(oDocumentoBean);
//
//            } catch (Exception e) {
//                throw new ServletException("DocumentoController: Update Error: Phase 2: " + e.getMessage());
//            }
//        }

        return "<div class=\"alert alert-info\">OK- Información Autorellena Documento generada.</div>";
    }
}
