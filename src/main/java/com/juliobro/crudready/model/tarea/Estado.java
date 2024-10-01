package com.juliobro.crudready.model.tarea;

public enum Estado {
    PENDIENTE,
    EN_PROCESO,
    COMPLETADA;

    public static Estado fromString(String estado) {
        if (estado == null) {
            return null;
        }
        return Estado.valueOf(estado.toUpperCase());
    }
}