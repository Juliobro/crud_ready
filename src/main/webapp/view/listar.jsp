<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Tareas</title>
</head>
<body>
    <section class="container mt-3">
        <div class="card">
            <div class="card-body">
                <h3 class="text-center">Mis Tareas</h3>
                <div class="text-end">
                    <a href="tarea-controller?accion=crear" class="btn btn-success btn-sm"><i
                            class="fa fa-plus-circle"></i> Nueva tarea</a>
                </div>
                <jsp:include page="../components/mensaje.jsp"/>
                <table class="table table-bordered table-striped mt-2">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Titulo</th>
                        <th>Descripción</th>
                        <th>Fecha de Creación</th>
                        <th>Fecha de Terminación</th>
                        <th>Estado</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tareas}" var="item">
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.titulo}</td>
                            <td>${item.descripcion}</td>
                            <td>${item.formattedFechaCreacion}</td>
                            <td>${item.formattedFechaTerminal}</td>
                            <td>${item.estado}</td>
                            <td>
                                <a href="tarea-controller?accion=editar&id=${item.id}"
                                   class="btn btn-info btn-sm">
                                    <i class="fa fa-edit"></i>
                                </a>
                                <a href="tarea-controller?accion=eliminar&id=${item.id}"
                                   onclick="return confirm('¿Está seguro que deseas eliminar la tarea con ID ${item.id}?')"
                                   class="btn btn-danger btn-sm">
                                    <i class="fa fa-trash"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${tareas.size() == 0}">
                        <tr>
                            <td colspan="5">No hay registros</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</body>
</html>
