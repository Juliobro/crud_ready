<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Configurar Tarea</title>
</head>
<body>
    <div class="container mt-3">
        <div class="card">
            <div class="card-body">
                <h3 class="text-center">${tarea.id == null ? "Crear Nueva" : "Editar"} Tarea</h3>
                <form action="tarea-controller" method="post">
                    <div class="mb-3">
                        <label>Título</label>
                        <input value="${tarea.titulo}" name="titulo" type="text" maxlength="100"
                               class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label>Descripción</label>
                        <input value="${tarea.descripcion}" name="descripcion" type="text" maxlength="510"
                               class="form-control" placeholder="Opcional">
                    </div>
                    <div class="mb-3">
                        <label>Fecha Límite</label>
                        <input value="${tarea.fechaTerminal}" name="fecha_terminal" type="datetime-local"
                               class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <fieldset>
                            <legend class="fs-6">Establecer estado</legend>
                            <select name="estado">
                                <option value="PENDIENTE">Pendiente</option>
                                <option value="EN_PROCESO">En Proceso</option>
                                <option value="COMPLETADA">Completada</option>
                            </select>
                        </fieldset>
                    </div>
                    <div class="mb-3">
                        <input type="hidden" name="id" value="${tarea.id}">
                        <input type="hidden" name="accion" value="guardar">
                        <button class="btn btn-primary btn-sm">
                            <i class="fa fa-save"></i> Guardar
                        </button>
                        <div class="text-end">
                            <a class="btn btn-dark btn-sm"
                               href="tarea-controller?accion=listar">
                                <i class="fa fa-arrow-left"></i> Volver atrás</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
