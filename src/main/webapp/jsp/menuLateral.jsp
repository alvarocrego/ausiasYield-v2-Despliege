<%@page import="net.daw.helper.Contexto"%>
<% Contexto oContexto = (Contexto) request.getAttribute("contexto");%>
<%
    String menu = "<div class=\"well sidebar-nav\"><ul class=\"nav nav-list\">";

    menu += "<li class=\"nav-header\">Sesión</li>";
    if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("ocioso")) {
        menu += "<li class=\"active\">";
    } else {
        menu += "<li>";
    }
    menu += "<a href=\"Controller\"><i class=\"icon-home\"></i> home</a></li>";

    if (!oContexto.getHaySesion()) {
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("login")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=login\">login</a></li>";
    } else {
        if (oContexto.getClase().equals("usuario") && (oContexto.getMetodo().equals("list") || oContexto.getMetodo().equals("update") || oContexto.getMetodo().equals("remove") || oContexto.getMetodo().equals("view"))) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=list\"><i class=\"icon-user\"></i> usuario</a></li>";
        if (oContexto.getClase().equals("usuario") && oContexto.getMetodo().equals("logout")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=usuario&method=logout\"><i class=\"icon-off\"></i> logout</a></li>";
        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">usuarios</li>";

        if (oContexto.getClase().equals("alumno")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=alumno&method=list\"><i class=\"icon-user\"></i> alumno</a></li>";

        if (oContexto.getClase().equals("profesor")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=profesor&method=list\"><i class=\"icon-user\"></i> profesor</a></li>";

        if (oContexto.getClase().equals("empresa") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=empresa&method=list\"><i class=\"icon-user\"></i> empresa</a></li>";

        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">documentos</li>";

        if (oContexto.getClase().equals("metadocumento") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=metadocumento&method=list\"><i class=\"icon-book\"></i> metadoc.</a></li>";

        if (oContexto.getClase().equals("metadocumentos") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=metadocumentos&method=list\"><i class=\"icon-book\"></i> metadocs.</a></li>";

        if (oContexto.getClase().equals("documento") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=documento&method=list\"><i class=\"icon-file\"></i> documento</a></li>";

        if (oContexto.getClase().equals("tipodocumento") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=tipodocumento&method=list\"><i class=\"icon-list\"></i> tipodocumento</a></li>";

        if (oContexto.getClase().equals("coment") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=coment&method=list\"><i class=\"icon-comment\"></i> comentario</a></li>";

        if (oContexto.getClase().equals("votoComentario") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=votoComentario&method=list\"><i class=\"icon-thumbs-up\"></i> voto comtario.</a></li>";

        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">actividades</li>";

        if (oContexto.getClase().equals("actividad") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=actividad&method=list\"><i class=\"icon-wrench\"></i> actividad</a></li>";

        if (oContexto.getClase().equals("entrega") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=entrega&method=list\"><i class=\"icon-upload\"></i> entrega</a></li>";

        if (oContexto.getClase().equals("actividadoffline") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=actividadoffline&method=list\"><i class=\"icon-eye-close\"></i> actividad offline</a></li>";
        
        if (oContexto.getClase().equals("CalificacionActividadOffline") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=CalificacionActividadOffline&method=list\"><i class=\"icon-th\"></i> califn. act. off.</a></li>";

        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">cuestionarios</li>";

        if (oContexto.getClase().equals("cuestionario") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=cuestionario&method=list\"><i class=\"icon-th-list\"></i> cuestionario</a></li>";

        if (oContexto.getClase().equals("pregunta") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=pregunta&method=list\"><i class=\"icon-question-sign\"></i> pregunta</a></li>";

        if (oContexto.getClase().equals("opcion") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=opcion&method=list\"><i class=\"icon-check\"></i> opcion</a></li>";

        if (oContexto.getClase().equals("contestacion") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=contestacion&method=list\"><i class=\"icon-adjust\"></i> contestacion</a></li>";
        
        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">forum</li>";
        if (oContexto.getClase().equals("hilo") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=hilo&method=list\"><i class=\"icon-random\"></i> hilo</a></li>";
        if (oContexto.getClase().equals("entrada") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=entrada&method=list\"><i class=\"icon-resize-horizontal\"></i> entrada</a></li>";

         //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">social</li>";
        if (oContexto.getClase().equals("follower") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=follower&method=list\"><i class=\"icon-heart\"></i> follower</a></li>";
        if (oContexto.getClase().equals("stream") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=stream&method=list\"><i class=\"icon-facetime-video\"></i> stream</a></li>";
        
        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">scrum</li>";
        if (oContexto.getClase().equals("requerimiento") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=requerimiento&method=list\"><i class=\"icon-bullhorn\"></i> requerimiento</a></li>";
        if (oContexto.getClase().equals("backlog") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=backlog&method=list\"><i class=\"icon-tasks\"></i> backlog</a></li>";

        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">código</li>";

        if (oContexto.getClase().equals("lenguaje") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=lenguaje&method=list\"><i class=\"icon-font\"></i> lenguaje</a></li>";

        if (oContexto.getClase().equals("incidencias") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=incidencias&method=list\"><i class=\"icon-exclamation-sign\"></i> incidencias</a></li>";

        if (oContexto.getClase().equals("repositorio") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=repositorio&method=list\"><i class=\"icon-hdd\"></i> repositorio</a></li>";

        

        //----------------------------------------------------------------------
        menu += "<li class=\"nav-header\">bolsa</li>";

        if (oContexto.getClase().equals("bolsa") && oContexto.getMetodo().equals("list")) {
            menu += "<li class=\"active\">";
        } else {
            menu += "<li>";
        }
        menu += "<a href=\"Controller?class=bolsa&method=list\"><i class=\"icon-briefcase\"></i> bolsa</a></li>";

       

    }
    menu += "</ul></div>";


%>
<%=menu%>
