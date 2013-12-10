<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.EmpresaBean"%>
<%@page import="net.daw.bean.UsuarioBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    Integer id_usuario = 0;
    String nombre = "";
    String cif = "";
    String direccion = "";
    String localidad = "";
    String provincia = "";
    String pais = "";
    String telefono = "";
    String fax = "";
    String actividad = "";
    String nombrecontacto = "";
    String emailcontacto = "";
    String validada = "";
    String login = "";
    String password = "";

    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        EmpresaBean oEmpresaBean = (EmpresaBean) oContexto.getParametro();
        //UsuarioBean oUsuarioBean = (UsuarioBean) oContexto.getParametro();
        id = oEmpresaBean.getId();
        id_usuario = oEmpresaBean.getId_usuario();
        nombre = oEmpresaBean.getNombre();
        cif = oEmpresaBean.getCif();
        direccion = oEmpresaBean.getDireccion();
        localidad = oEmpresaBean.getLocalidad();
        provincia = oEmpresaBean.getProvincia();
        pais = oEmpresaBean.getPais();
        telefono = oEmpresaBean.getTelefono();
        fax = oEmpresaBean.getFax();
        actividad = oEmpresaBean.getActividad();
        nombrecontacto = oEmpresaBean.getNombrecontacto();
        emailcontacto = oEmpresaBean.getEmailcontacto();
        validada = oEmpresaBean.getValidada();
        login = oEmpresaBean.getUsuario().getLogin();
        password = oEmpresaBean.getUsuario().getPassword();

    }
    if (oContexto.getMetodo().equals("view")) {
        strTitulo = "Vista";
        strControlEnabled = "disabled=\"true\"";
        strValueBoton = "Cerrar";
    }
    if (oContexto.getMetodo().equals("update")) {
        strTitulo = "Edición";
        strValueBoton = "Modificar";
    }
    if (oContexto.getMetodo().equals("new")) {
        strTitulo = "Alta";
        strValueBoton = "Crear";
    }
%>
<h1><%=strTitulo%> de Empresa</h1>
<form class="form-horizontal" action="Controller" method="post" id="empresaForm">
    <fieldset>
        <legend>Formulario de Empresa</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="empresa" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <!--
        <div class="control-group">
            <label class="control-label" for="id_usuario">Id de Empresa: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="id_usuario" name="id_usuario" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=id_usuario%>" /><br />
            </div>
        </div>
        -->
        <div class="control-group">
            <label class="control-label" for="nombre">Nombre: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="nombre" name="nombre" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=nombre%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="cif">CIF: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="cif" name="cif" type="text" size="30" maxlength="50" value="<%=cif%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="direccion">Dirección: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="direccion" name="direccion" type="text" size="30" maxlength="50" value="<%=direccion%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="localidad">Localidad: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="localidad" name="localidad" type="text" size="30" maxlength="50" value="<%=localidad%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="provincia">Provincia: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="provincia" name="provincia" type="text" size="30" maxlength="50" value="<%=provincia%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="pais">Pais: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="pais" name="pais" type="text" size="30" maxlength="50" value="<%=pais%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="telefono">Teléfono: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="telefono" name="telefono" type="text" size="30" maxlength="50" value="<%=telefono%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="fax">Fax: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="fax" name="fax" type="text" size="30" maxlength="50" value="<%=fax%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="actividad">Actividad: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="actividad" name="actividad" type="text" size="30" maxlength="50" value="<%=actividad%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="nombrecontacto">Nombre de Contacto: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="nombrecontacto" name="nombrecontacto" type="text" size="30" maxlength="50" value="<%=nombrecontacto%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="emailcontacto">E-mail de Contacto: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="emailcontacto" name="emailcontacto" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=emailcontacto%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="validada">Validada: </label>
            <div class="controls">
                <select <%=strControlEnabled%> id="validada" name="validada" type="text" autofocus="autofocus" value="<%=validada%>" >
                    <option>Seleccione una opción...</option>
                    <option <%if (validada.equals("Si")) {
                            out.print("selected");
                        }%>>Si</option>
                    <option <%if (validada.equals("No")) {
                            out.print("selected");
                        }%>>No</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="login">Usuario: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="login" name="login" type="text" size="30" maxlength="50" value="<%=login%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="password">Contraseña: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="password" name="password" type="text" size="30" maxlength="50" value="<%=password%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar" value="<%=strValueBoton%>" />
            </div>
        </div>   
    </fieldset>
</form>
