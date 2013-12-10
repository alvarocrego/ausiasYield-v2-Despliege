/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.operation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.daw.bean.ProfesorBean;
import net.daw.dao.ProfesorDao;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;


/**
 *
 * @author al037184
 */
public class ProfesorRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        ProfesorBean oProfesorBean = new ProfesorBean();
        ProfesorDao oProfesorDao = new ProfesorDao(oContexto.getEnumTipoConexion());
        String dni = "";
        String nombre = "";
        String ape1 = "";
        String ape2 = "";
        String sexo = "";
        String telefono = "";
        String email = "";

        ArrayList<String> arrDnis = new ArrayList<>();
        arrDnis.add("1188111B");
        arrDnis.add("1119512C");
        arrDnis.add("0007703C");
        arrDnis.add("1891105E");
        arrDnis.add("111105A");
        arrDnis.add("1161531D");
        arrDnis.add("1212589F");
        arrDnis.add("2211006C");
        arrDnis.add("9611243J");

        ArrayList<String> arrNombres = new ArrayList<>();
        arrNombres.add("Javier");
        arrNombres.add("Lucia");
        arrNombres.add("Lucas");
        arrNombres.add("Javier");
        arrNombres.add("Marco");
        arrNombres.add("Sandra");
        arrNombres.add("Margarita");
        arrNombres.add("Pedro");
        arrNombres.add("Toni");
        arrNombres.add("José");
        arrNombres.add("Guillermo");
        arrNombres.add("Rosa");
        arrNombres.add("Adela");
        arrNombres.add("Lucia");
        arrNombres.add("Paco");
        arrNombres.add("Sergio");
        arrNombres.add("Almudena");
        arrNombres.add("Francisca");
        arrNombres.add("Lola");
        arrNombres.add("Manuel");
        arrNombres.add("Miguel");
        arrNombres.add("Agustin");
        arrNombres.add("Noelia");
        arrNombres.add("Amparo");
        arrNombres.add("Benito");
        arrNombres.add("Ana");
        
        ArrayList<String> arrApellidos = new ArrayList<>();
         arrApellidos.add("Garcia");
        arrApellidos.add("Benito");
        arrApellidos.add("Soria");
        arrApellidos.add("Grancha");
        arrApellidos.add("Martinez");
        arrApellidos.add("Rodriguez");
        arrApellidos.add("Gutierrez");
        arrApellidos.add("Rubio");
        arrApellidos.add("Moreno");
        arrApellidos.add("ARagon");
        arrApellidos.add("Atienza");
        arrApellidos.add("Pons");
        arrApellidos.add("Otxoa");
        arrApellidos.add("Delicado");
        arrApellidos.add("Sanchez");
        arrApellidos.add("Sistiaga");
        arrApellidos.add("Urkullu");
        arrApellidos.add("Albert");
        arrApellidos.add("Laplaza");
        arrApellidos.add("Alpuente");
        arrApellidos.add("Pons");
        arrApellidos.add("Garrido");
        arrApellidos.add("Lopez");
        arrApellidos.add("Cuerda");
        arrApellidos.add("Cañizares");

        ArrayList<String> arrSexos = new ArrayList<>();
        arrSexos.add("Hombre");
        arrSexos.add("Mujer");

       
        ArrayList<String> arrTelefonos = new ArrayList<>();
        arrTelefonos.add("666123547");
        arrTelefonos.add("963758847");
        arrTelefonos.add("963332564");
        arrTelefonos.add("606606606");
        arrTelefonos.add("607225368");
        arrTelefonos.add("658123321");
        arrTelefonos.add("963752193");
        arrTelefonos.add("961255255");
        arrTelefonos.add("961012654");
        arrTelefonos.add("602323211");
        arrTelefonos.add("645321789");
        arrTelefonos.add("632145211");
        arrTelefonos.add("602111111");
        arrTelefonos.add("608989632");
        arrTelefonos.add("666222333");
        arrTelefonos.add("622332112");
        arrTelefonos.add("963942296");
        arrTelefonos.add("963992211");
        arrTelefonos.add("963992200");
        arrTelefonos.add("608888777");
        arrTelefonos.add("605444333");
        arrTelefonos.add("605333444");
        arrTelefonos.add("601222333");
        arrTelefonos.add("652841369");


        ArrayList<String> arrEmails = new ArrayList<>();
        arrEmails.add("@hotmail.com");
        arrEmails.add("@hotmail.es");
        arrEmails.add("@gmail.com");
        arrEmails.add("@ono.es");
        arrEmails.add("@outlook.es");
        arrEmails.add("@outlook.com");
        arrEmails.add("@gmail.es");
        arrEmails.add("@yahoo.es");

        int index;
        int contador = 0;
        Iterator<String> iterador = arrNombres.listIterator();
        Random generator;
        
        
        while (iterador.hasNext()) {
            contador++;
            nombre = iterador.next();
            generator = new Random();
            oProfesorBean.setId(0);
            oProfesorBean.setId_usuario(0);
            
            index = generator.nextInt(arrDnis.size());
            String randomDNI = arrDnis.get(index);
            oProfesorBean.setDni(contador + randomDNI);
            generator = new Random();

            index = generator.nextInt(arrNombres.size());
            String randomNOMBRE = arrNombres.get(index);
            oProfesorBean.setNombre(randomNOMBRE);
            generator = new Random();

            index = generator.nextInt(arrApellidos.size());
            String randomAPE1 = arrApellidos.get(index);
            oProfesorBean.setApe1(randomAPE1);
            generator = new Random();

            index = generator.nextInt(arrApellidos.size());
            String randomAPE2 = arrApellidos.get(index);
            oProfesorBean.setApe2(randomAPE2);
            generator = new Random();

            index = generator.nextInt(arrSexos.size());
            String randomSEXO = arrSexos.get(index);
            oProfesorBean.setSexo(randomSEXO);
            generator = new Random();

            index = generator.nextInt(arrTelefonos.size());
            String randomTELEFONO = arrTelefonos.get(index);
            oProfesorBean.setTelefono(randomTELEFONO);
            generator = new Random();

            index = generator.nextInt(arrEmails.size());
            String randomEMAIL = arrEmails.get(index);
            oProfesorBean.setEmail(randomNOMBRE + randomAPE1 + randomEMAIL);
            generator = new Random();

            
            index = generator.nextInt(arrNombres.size());
            String randomLOGIN = arrNombres.get(index);
            oProfesorBean.getUsuario().setLogin(randomLOGIN + contador);
            
            
            index = generator.nextInt(arrNombres.size());
            String randomPASSWORD = arrNombres.get(index);
            oProfesorBean.getUsuario().setPassword(randomPASSWORD + contador);
            
            try {
                oProfesorDao.set(oProfesorBean);

            } catch (Exception e) {
                throw new ServletException("ProfesorController: Update Error: Phase 2: " + e.getMessage());
            }
        }

        return "OK- Información Autorellena Profesor generada.";
    }
}
