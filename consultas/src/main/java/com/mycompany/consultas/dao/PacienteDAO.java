package com.mycompany.consultas.dao;

import com.mycompany.consultas.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void inserir(Paciente p) {
        String sql = "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.executeUpdate();

            // pega o ID gerado automaticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    p.setId(generatedKeys.getInt(1));
                }
            }

            System.out.println("Paciente inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir paciente: " + e.getMessage());
        }
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.setInt(4, paciente.getId());
            stmt.executeUpdate();
            System.out.println("Paciente atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Paciente deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone")); // adiciona telefone
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone")); // adiciona telefone
                return p;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        }
        return null;
    }
}
