package com.mycompany.consultas.dao;

import com.mycompany.consultas.model.Medico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void inserir(Medico m) {
        String sql = "INSERT INTO medico (nome, crm, especialidade) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        m.setId(generatedKeys.getInt(1));
                    }
                }
            }

            System.out.println("Médico inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir médico: " + e.getMessage());
        }
    }

    public void atualizar(Medico m) {
        String sql = "UPDATE medico SET nome=?, crm=?, especialidade=? WHERE id=?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCrm());
            stmt.setString(3, m.getEspecialidade());
            stmt.setInt(4, m.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar médico: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM medico WHERE id=?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar médico: " + e.getMessage());
        }
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT id, nome, crm, especialidade FROM medico";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico m = new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("crm"),
                    rs.getString("especialidade")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar médicos: " + e.getMessage());
        }

        return lista;
    }

    public Medico buscarPorId(int id) {
        String sql = "SELECT id, nome, crm, especialidade FROM medico WHERE id=?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Medico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("crm"),
                        rs.getString("especialidade")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
        }
        return null;
    }
}
