package com.juliobro.crudready.config;

import org.junit.jupiter.api.Test;

class ConexionTest {

    @Test
    void BdConexionTest() {
        Conexion.getConnection();
    }
}
