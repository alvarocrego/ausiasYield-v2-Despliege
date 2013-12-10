<%-- 
    Document   : form
    Created on : 07-nov-2013, 12:58:06
    Author     : Pedro 
--%>

<%@page import="net.daw.helper.Contexto"%>
<%@page import="net.daw.bean.ProfesorBean"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");
    String strTitulo = "";
    String strControlEnabled = "";
    String strValueBoton = "Enviar";
    Integer id = 0;
    Integer id_usuario = 0;
    String dni = "";
    String nombre = "";
    String ape1 = "";
    String ape2 = "";
    String sexo = "";
    String telefono = "";
    String email = "";
    String login = "";
    String password = "";

    if (oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("view")) {
        ProfesorBean oProfesorBean = (ProfesorBean) oContexto.getParametro();
        id = oProfesorBean.getId();
        id_usuario = oProfesorBean.getId_usuario();
        dni = oProfesorBean.getDni();
        nombre = oProfesorBean.getNombre();
        ape1 = oProfesorBean.getApe1();
        ape2 = oProfesorBean.getApe2();
        sexo = oProfesorBean.getSexo();
        telefono = oProfesorBean.getTelefono();
        email = oProfesorBean.getEmail();
        login = oProfesorBean.getUsuario().getLogin();
        password = oProfesorBean.getUsuario().getPassword();

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
<h1><%=strTitulo%> de Profesor</h1>
<form class="form-horizontal" action="Controller" method="post" id="profesorForm ">
    <fieldset>
        <legend>Formulario de Profesor</legend>
        <input type="hidden" name="id" value="<%=id%>" /> 
        <input type="hidden" name="class" value="profesor" /> 
        <input type="hidden" name="method" value="<%=oContexto.getMetodo()%>" /> 
        <input type="hidden" name="phase" value="2" />
        <!--
        <div class="control-group">
            <label class="control-label" for="id_usuario">Id Usuario: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="id_usuario" name="id_usuario" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=id_usuario%>" /><br />
            </div>
        </div>
        -->
        <div class="control-group">
            <label class="control-label" for="dni">DNI: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="dni" name="dni" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=dni%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="nombre">Nombre: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="nombre" name="nombre" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=nombre%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ape1">Primer Apellido: </label>
            <div class="controls">
                <input <%=strControlEnabled%> id="ape1" name="ape1" type="text" size="30" maxlength="50" value="<%=ape1%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="ape2">Segundo Apellido: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="ape2" name="ape2" type="text" size="30" maxlength="50" value="<%=ape2%>" /> <br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="sexo">Sexo: </label> 
            <div class="controls">

                <select <%=strControlEnabled%> id="sexo" name="sexo" value="<%=sexo%>">
                    <option selected=""></option>
                    <option <%if (sexo.equals("Hombre")) {
                            out.print("selected");
                        }%>>Hombre</option>
                    <option <%if (sexo.equals("Mujer")) {
                            out.print("selected");
                        }%>>Mujer</option>
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="telefono">Teléfono: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="telefono" name="telefono" type="text" size="30" maxlength="50" autofocus="autofocus" value="<%=telefono%>" /><br />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email: </label> 
            <div class="controls">
                <input <%=strControlEnabled%> id="email" name="email" type="text" size="30" maxlength="50" value="<%=email%>" /><br />
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