package net.daw.operation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.daw.bean.AlumnoBean;
import net.daw.dao.AlumnoDao;
import net.daw.bean.UsuarioBean;
import net.daw.dao.UsuarioDao;
import net.daw.helper.Contexto;

/**
 *
 * @author Sergio Martín Tárraga
 * @version v1.0
 * @since mie, 20 noviembre 2013
 */
public class AlumnoRellena1 implements Operation {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contexto oContexto = (Contexto) request.getAttribute("contexto");
        oContexto.setVista("jsp/mensaje.jsp");
        AlumnoBean oAlumnoBean = new AlumnoBean();
        AlumnoDao oAlumnoDao = new AlumnoDao(oContexto.getEnumTipoConexion());
        String nombre = "";        

        ArrayList<String> arrDni = new ArrayList<>();
        arrDni.add("0077000A");
        arrDni.add("0008401B");
        arrDni.add("0007703C");
        arrDni.add("0780004F");
        arrDni.add("0000005A");
        arrDni.add("0000478A");
        arrDni.add("0050420C");
        arrDni.add("1100345B");
        arrDni.add("8500032V");
        
        ArrayList<String> arrExpediente = new ArrayList<>();
        arrExpediente.add("00770EXP");
        arrExpediente.add("00084EXP");
        arrExpediente.add("00077EXP");
        arrExpediente.add("07800EXP");
        arrExpediente.add("00000EXP");
        arrExpediente.add("00004EXP");
        arrExpediente.add("00504EXP");
        arrExpediente.add("11003EXP");
        arrExpediente.add("85000EXP");
        
        ArrayList<String> arrNombre = new ArrayList<>();
        arrNombre.add("Sergio");
        arrNombre.add("Javi");
        arrNombre.add("Pedro");
        arrNombre.add("Antonio");
        arrNombre.add("José");
        arrNombre.add("Eduardo");
        arrNombre.add("Ana");
        arrNombre.add("Diana");
        arrNombre.add("Noemí");
        arrNombre.add("Arancha");

        ArrayList<String> arrApe1 = new ArrayList<>();
        arrApe1.add("Martín");
        arrApe1.add("Benito");
        arrApe1.add("Navarro");
        arrApe1.add("Bonet");
        arrApe1.add("Martinez");
        arrApe1.add("Grancha");
        arrApe1.add("Gavilan");
        arrApe1.add("Perez");
        arrApe1.add("Lopez");
        arrApe1.add("Carreño");
        
        ArrayList<String> arrApe2 = new ArrayList<>();
        arrApe2.add("Soria");
        arrApe2.add("Tárraga");
        arrApe2.add("Muñoz");
        arrApe2.add("Vellisca");
        arrApe2.add("Aznar");
        arrApe2.add("Zapatero");
        arrApe2.add("Blanco");
        arrApe2.add("Soriano");
        arrApe2.add("Reig");
        arrApe2.add("Cabrera");
       
        ArrayList<String> arrSexo = new ArrayList<>();
        arrSexo.add("Hombre");
        arrSexo.add("Mujer");
        
        ArrayList<String> arrDomicilio = new ArrayList<>();
        arrDomicilio.add("Avda Reyes Catolicos 23");
        arrDomicilio.add("Avda Gomez Ferrer 69");
        arrDomicilio.add("Calle San Vicente Martir 112");
        arrDomicilio.add("C/ Alcalde sn");
        arrDomicilio.add("C/ Ambulatorio Viejo 35");
        arrDomicilio.add("Palo roto 2-3");
        arrDomicilio.add("C/ San Calletano Bajo");
        arrDomicilio.add("Avda Albufera 23");
        arrDomicilio.add("Valencia 6");
        arrDomicilio.add("Picassent 8b");
        
        ArrayList<String> arrCodpostal = new ArrayList<>();
        arrCodpostal.add("46910");
        arrCodpostal.add("46490");
        arrCodpostal.add("46001");
        arrCodpostal.add("46022");
        arrCodpostal.add("46013");
        
        ArrayList<String> arrPoblacion = new ArrayList<>();
        arrPoblacion.add("Valencia");
        arrPoblacion.add("Alfafar");
        arrPoblacion.add("Benetusser");
        arrPoblacion.add("Sedaví");
        arrPoblacion.add("Massanassa");
        
        
        ArrayList<String> arrProvincia = new ArrayList<>();
        arrProvincia.add("Valencia");
        arrProvincia.add("Alicante");
        arrProvincia.add("Castellón");
        arrProvincia.add("Albacete");
        arrProvincia.add("Madrid");
        
        ArrayList<String> arrTelefono = new ArrayList<>();
        arrTelefono.add("651795315");
        arrTelefono.add("961454884");
        arrTelefono.add("912485477");
        arrTelefono.add("802919090");
        arrTelefono.add("637900148");
        arrTelefono.add("678932741");
        arrTelefono.add("964884895");
        arrTelefono.add("631247955");
        arrTelefono.add("964171788");
        arrTelefono.add("912889578");
        
        ArrayList<String> arrEmail = new ArrayList<>();
        arrEmail.add("@hotmail.com");
        arrEmail.add("@hotmail.es");
        arrEmail.add("@gmail.com");
        arrEmail.add("@yahoo.com");
        arrEmail.add("@ono.es");
        arrEmail.add("@outlook.es");
        arrEmail.add("@outlook.com");
        arrEmail.add("@gmail.es");
        arrEmail.add("@yahoo.es");
        arrEmail.add("@ono.com");
        
        ArrayList<String> arrValidado = new ArrayList<>();
        arrValidado.add("Si");
        arrValidado.add("No");
        
        ArrayList<String> arrLogin = new ArrayList<>();
        arrLogin.add("1");
        arrLogin.add("2");
        arrLogin.add("3");
        arrLogin.add("4");
        arrLogin.add("5");
        arrLogin.add("6");
        arrLogin.add("7");
        arrLogin.add("8");
        arrLogin.add("9");
        arrLogin.add("10");
        
        ArrayList<String> arrPass = new ArrayList<>();
        arrPass.add("Perro");
        arrPass.add("Gato");
        arrPass.add("Loro");
        arrPass.add("Ballena");
        arrPass.add("Canguro");
        arrPass.add("Panda");
        arrPass.add("Elefante");
        arrPass.add("Jirafa");
        arrPass.add("Rinoceronte");
        arrPass.add("Leon");
        
        int index;
        int contador = 0;
        Iterator<String> iterador = arrNombre.listIterator();
        Random generator;
        while (iterador.hasNext()) {
            contador++;
            nombre = iterador.next();
            generator = new Random();
            oAlumnoBean.setId(0);
            oAlumnoBean.setId_usuario(0);
            
            index = generator.nextInt(arrDni.size());
            String randomDNI = arrDni.get(index);
            oAlumnoBean.setDni(contador + randomDNI);
            generator = new Random();
            
            index = generator.nextInt(arrExpediente.size());
            String randomNUMEXPEDIENTE = arrExpediente.get(index);
            oAlumnoBean.setNumexpediente(contador + randomNUMEXPEDIENTE);
            generator = new Random();
            
            index = generator.nextInt(arrNombre.size());
            String randomNOMBRE = arrNombre.get(index);
            oAlumnoBean.setNombre(randomNOMBRE);
            generator = new Random();
            
            index = generator.nextInt(arrApe1.size());
            String randomAPE1 = arrApe1.get(index);
            oAlumnoBean.setApe1(randomAPE1);
            generator = new Random();
            
            index = generator.nextInt(arrApe2.size());
            String randomAPE2 = arrApe2.get(index);
            oAlumnoBean.setApe2(randomAPE2);
            generator = new Random();
            
            index = generator.nextInt(arrSexo.size());
            String randomSEXO = arrSexo.get(index);
            oAlumnoBean.setSexo(randomSEXO);
            generator = new Random();
            
            index = generator.nextInt(arrDomicilio.size());
            String randomDOMICILIO = arrDomicilio.get(index);
            oAlumnoBean.setDomicilio(randomDOMICILIO);
            generator = new Random();
            
            index = generator.nextInt(arrCodpostal.size());
            String randomCODPOSTAL = arrCodpostal.get(index);
            oAlumnoBean.setCodpostal(randomCODPOSTAL);
            generator = new Random();
            
            index = generator.nextInt(arrPoblacion.size());
            String randomPOBLACION = arrPoblacion.get(index);
            oAlumnoBean.setPoblacion(randomPOBLACION);
            generator = new Random();
            
            index = generator.nextInt(arrProvincia.size());
            String randomPROVINCIA = arrProvincia.get(index);
            oAlumnoBean.setProvincia(randomPROVINCIA);
            generator = new Random();
            
            index = generator.nextInt(arrTelefono.size());
            String randomTELEFONO = arrTelefono.get(index);
            oAlumnoBean.setTelefono(randomTELEFONO);
            generator = new Random();
            
            index = generator.nextInt(arrEmail.size());
            String randomEMAIL = arrEmail.get(index);
            oAlumnoBean.setEmail(randomNOMBRE + randomAPE1 + randomEMAIL);
            generator = new Random();
            
            index = generator.nextInt(arrValidado.size());
            String randomVALIDADO = arrValidado.get(index);
            oAlumnoBean.setValidado(randomVALIDADO);
            generator = new Random();
            
            index = generator.nextInt(arrLogin.size());
            String randomLOGIN = arrLogin.get(index);
            oAlumnoBean.getUsuario().setLogin(randomLOGIN + contador);
            generator = new Random();
            
            index = generator.nextInt(arrPass.size());
            String randomPASSWORD = arrPass.get(index);
            oAlumnoBean.getUsuario().setPassword(randomPASSWORD + contador);
            generator = new Random();
            
            try {
                oAlumnoDao.set(oAlumnoBean);

            } catch (Exception e) {
                throw new ServletException("AlumnoController: Update Error: Phase 2: " + e.getMessage());
            }
        }

        return "OK- Información Autorellena Alumno generada.";
    }
}
