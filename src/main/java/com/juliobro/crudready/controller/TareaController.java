package com.juliobro.crudready.controller;

import java.io.*;
import java.time.LocalDateTime;

import com.juliobro.crudready.model.tarea.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "TareaController", value = "/tarea-controller")
public class TareaController extends HttpServlet {

    private final TareaDAO TAREA_DAO = new TareaDAO();
    private final String PAG_LISTAR = "/view/listar.jsp";
    private final String PAG_CREAR = "/view/crear.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "crear":
                crear(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    /* --------------------- READ --------------------- */
    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("tareas", TAREA_DAO.ListarTareas());
        request.getRequestDispatcher(PAG_LISTAR).forward(request, response);
    }

    /* --------------------- CREATE --------------------- */
    protected void crear(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("tarea", new Tarea());
        request.getRequestDispatcher(PAG_CREAR).forward(request, response);
    }

    /* --------------------- UPDATE --------------------- */
    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Tarea tarea = TAREA_DAO.buscarPorId(id);

        if (tarea != null) {
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher(PAG_CREAR).forward(request, response);
        } else {
            request.getSession().setAttribute("error", "No se encontró una tarea con ID " + id);
            response.sendRedirect("tarea-controller?accion=listar");
        }
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Tarea tarea = new Tarea();

        String formId = request.getParameter("id");
        if (formId == null || formId.isEmpty()) {
            tarea.setId(0L);
        } else {
            tarea.setId(Long.parseLong(formId));
        }

        tarea.setTitulo(request.getParameter("titulo"));
        tarea.setDescripcion(request.getParameter("descripcion"));
        tarea.setFechaCreacion(LocalDateTime.now());
        tarea.setFechaTerminal(LocalDateTime.parse(request.getParameter("fecha_terminal")));
        tarea.setEstado(Estado.fromString(request.getParameter("estado")));

        int resultado;

        if (tarea.getId() == 0) {
            resultado = TAREA_DAO.crearTarea(tarea);
        } else {
            resultado = TAREA_DAO.editarTarea(tarea);
        }

        if (resultado > 0 ) {
            request.getSession().setAttribute("success", "Tarea guardada correctamente");
            response.sendRedirect("tarea-controller?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar los datos");
            request.setAttribute("tarea", tarea);
            request.getRequestDispatcher(PAG_CREAR).forward(request, response);
        }
    }

    /* --------------------- DELETE --------------------- */
    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        int resultado = TAREA_DAO.eliminarTarea(id);

        if (resultado > 0 ) {
            request.getSession().setAttribute("success", "La tarea con el ID " + id + " ha sido eliminada");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar la tarea");
        }
        response.sendRedirect("tarea-controller?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}