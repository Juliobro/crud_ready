package com.juliobro.crudready.model.tarea;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarea {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaTerminal;
    private Estado estado;

    /* ---------------------------- Getters & Setters Zone ---------------------------- */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getFormattedFechaCreacion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
        return fechaCreacion.format(formatter);
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaTerminal() {
        return fechaTerminal;
    }

    public String getFormattedFechaTerminal() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm a");
        return fechaTerminal.format(formatter);
    }

    public void setFechaTerminal(LocalDateTime fechaTerminal) {
        this.fechaTerminal = fechaTerminal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
