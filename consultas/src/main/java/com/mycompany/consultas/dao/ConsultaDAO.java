package com.mycompany.consultas.dao;

import com.mycompany.consultas.model.Consulta;
import com.mycompany.consultas.model.Medico;
import com.mycompany.consultas.model.Paciente;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void inserir(Consulta consulta) {
        String sql = "INSERT INTO consulta (data, hora, observacao, paciente_id, medico_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(consulta.getData()));
            stmt.setTime(2, Time.valueOf(consulta.getHora()));
            stmt.setString(3, consulta.getObservacao());
            stmt.setInt(4, consulta.getPaciente().getId());
            stmt.setInt(5, consulta.getMedico().getId());

            stmt.executeUpdate();
            System.out.println("Consulta inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir consulta: " + e.getMessage());
        }
    }

    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consulta SET data = ?, hora = ?, observacao = ?, paciente_id = ?, medico_id = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(consulta.getData()));
            stmt.setTime(2, Time.valueOf(consulta.getHora()));
            stmt.setString(3, consulta.getObservacao());
            stmt.setInt(4, consulta.getPaciente().getId());
            stmt.setInt(5, consulta.getMedico().getId());
            stmt.setInt(6, consulta.getId());

            stmt.executeUpdate();
            System.out.println("Consulta atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Consulta deletada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar consulta: " + e.getMessage());
        }
    }

    public List<Consulta> listarTodas() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.id, c.data, c.hora, c.observacao, " +
                     "p.id AS paciente_id, p.nome AS paciente_nome, p.cpf AS paciente_cpf, " +
                     "m.id AS medico_id, m.nome AS medico_nome, m.crm AS medico_crm, m.especialidade AS medico_especialidade " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                paciente.setCpf(rs.getString("paciente_cpf"));

                Medico medico = new Medico();
                medico.setId(rs.getInt("medico_id"));
                medico.setNome(rs.getString("medico_nome"));
                medico.setCrm(rs.getString("medico_crm"));
                medico.setEspecialidade(rs.getString("medico_especialidade"));

                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getTime("hora").toLocalTime());
                consulta.setObservacao(rs.getString("observacao"));
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }

        return consultas;
    }

    public List<Consulta> buscarPorData(LocalDate data) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.id, c.data, c.hora, c.observacao, " +
                     "p.id AS paciente_id, p.nome AS paciente_nome, p.cpf AS paciente_cpf, " +
                     "m.id AS medico_id, m.nome AS medico_nome, m.crm AS medico_crm, m.especialidade AS medico_especialidade " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "WHERE c.data = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                paciente.setCpf(rs.getString("paciente_cpf"));

                Medico medico = new Medico();
                medico.setId(rs.getInt("medico_id"));
                medico.setNome(rs.getString("medico_nome"));
                medico.setCrm(rs.getString("medico_crm"));
                medico.setEspecialidade(rs.getString("medico_especialidade"));

                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getTime("hora").toLocalTime());
                consulta.setObservacao(rs.getString("observacao"));
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por data: " + e.getMessage());
        }

        return consultas;
    }

    public List<Consulta> buscarPorNomePaciente(String nome) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT c.id, c.data, c.hora, c.observacao, " +
                     "p.id AS paciente_id, p.nome AS paciente_nome, p.cpf AS paciente_cpf, " +
                     "m.id AS medico_id, m.nome AS medico_nome, m.crm AS medico_crm, m.especialidade AS medico_especialidade " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.paciente_id = p.id " +
                     "JOIN medico m ON c.medico_id = m.id " +
                     "WHERE LOWER(p.nome) LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("paciente_id"));
                paciente.setNome(rs.getString("paciente_nome"));
                paciente.setCpf(rs.getString("paciente_cpf"));

                Medico medico = new Medico();
                medico.setId(rs.getInt("medico_id"));
                medico.setNome(rs.getString("medico_nome"));
                medico.setCrm(rs.getString("medico_crm"));
                medico.setEspecialidade(rs.getString("medico_especialidade"));

                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getTime("hora").toLocalTime());
                consulta.setObservacao(rs.getString("observacao"));
                consulta.setPaciente(paciente);
                consulta.setMedico(medico);

                consultas.add(consulta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar consultas por nome do paciente: " + e.getMessage());
        }

        return consultas;
    }
}
