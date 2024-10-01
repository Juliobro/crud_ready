package com.juliobro.crudready.model.tarea;

import com.juliobro.crudready.config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static java.sql.Types.VARCHAR;

public class TareaDAO {
    private Connection cn = null;
    private PreparedStatement ps = null;
    private static ResultSet rs = null;


    /* --------------------- READ --------------------- */
    public ArrayList<Tarea> ListarTareas() {
        ArrayList<Tarea> lista = new ArrayList<>();

        try {
            cn = Conexion.getConnection();
            String sql = "SELECT * FROM tareas";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tarea tarea = new Tarea();
                llenarObjetoTarea(tarea);
                lista.add(tarea);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            cerrarRecursos();
        }

        return lista;
    }


    /* --------------------- CREATE --------------------- */
    public int crearTarea(Tarea tarea) {
        int resultado = 0;

        try {
            cn = Conexion.getConnection();
            String sql =
                    "INSERT INTO tareas(titulo, descripcion, fecha_creacion, fecha_terminal, estado) " +
                            "VALUES (?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, tarea.getTitulo());
            ps.setString(3, tarea.getFechaCreacion().toString());
            ps.setString(4, tarea.getFechaTerminal().toString());
            ps.setString(5, tarea.getEstado().toString());

            if (tarea.getDescripcion() == null || tarea.getDescripcion().trim().isEmpty()) {
                ps.setNull(2, VARCHAR);
            } else {
                ps.setString(2, tarea.getDescripcion());
            }

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }


    /* --------------------- UPDATE --------------------- */
    public int editarTarea(Tarea tarea) {
        int resultado = 0;

        try {
            cn = Conexion.getConnection();
            String sql =
                    "UPDATE tareas SET titulo=?, descripcion=?, fecha_creacion=?, fecha_terminal=?, estado=? " +
                            "WHERE id=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, tarea.getTitulo());
            ps.setString(3, tarea.getFechaCreacion().toString());
            ps.setString(4, tarea.getFechaTerminal().toString());
            ps.setString(5, tarea.getEstado().toString());
            ps.setLong(6, tarea.getId());

            if (tarea.getDescripcion() == null || tarea.getDescripcion().trim().isEmpty()) {
                ps.setNull(2, VARCHAR);
            } else {
                ps.setString(2, tarea.getDescripcion());
            }

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    /* --------------------- DELETE --------------------- */
    public int eliminarTarea(Long id) {
        int resultado = 0;

        try {
            cn = Conexion.getConnection();
            String sql = "DELETE FROM tareas WHERE id=?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, id);
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    public Tarea buscarPorId(Long id) {
        Tarea tarea = null;

        try {
            cn = Conexion.getConnection();
            String sql = "SELECT * FROM tareas WHERE id = ?";
            ps = cn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                tarea = new Tarea();
                llenarObjetoTarea(tarea);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            cerrarRecursos();
        }

        return tarea;
    }

    private void llenarObjetoTarea(Tarea tarea) throws SQLException {
        tarea.setId(rs.getLong("id"));
        tarea.setTitulo(rs.getString("titulo"));
        tarea.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
        tarea.setFechaTerminal(rs.getTimestamp("fecha_terminal").toLocalDateTime());
        tarea.setEstado(Estado.fromString(rs.getString("estado")));

        String descripcion = rs.getString("descripcion");
        tarea.setDescripcion(Objects.requireNonNullElse(descripcion, "Sin Descripción"));
    }

    private void cerrarRecursos() {
        try {
            if (cn != null) {
                cn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
