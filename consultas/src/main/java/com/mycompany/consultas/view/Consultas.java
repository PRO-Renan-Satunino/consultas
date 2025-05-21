package com.mycompany.consultas.view;

import com.mycompany.consultas.dao.Conexao;
import javax.swing.*;
import java.awt.*;

/**
 * Tela inicial do sistema com menu de navegação.
 */
public class Consultas extends JFrame {

    // Construtor da tela principal
    public Consultas() {
        setTitle("Sistema de Consultas Médicas"); // Define o título da janela
        setSize(400, 300); // Define o tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o programa ao clicar no X
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Botões principais
        JButton btnPaciente = new JButton("Pacientes"); // Botão para acessar pacientes
        JButton btnMedico = new JButton("Médicos"); // Botão para acessar médicos
        JButton btnConsulta = new JButton("Consultas"); // Botão para acessar consultas

        // Ações dos botões: abrem as respectivas telas ao serem clicados
        btnPaciente.addActionListener(e -> new PacienteView().setVisible(true));
        btnMedico.addActionListener(e -> new MedicoView().setVisible(true));
        btnConsulta.addActionListener(e -> new ConsultaView().setVisible(true));

        // Layout: organiza os botões em uma grade de 3 linhas e 1 coluna
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(btnPaciente); // Adiciona o botão de pacientes ao painel
        panel.add(btnMedico); // Adiciona o botão de médicos ao painel
        panel.add(btnConsulta); // Adiciona o botão de consultas ao painel

        add(panel, BorderLayout.CENTER); // Adiciona o painel ao centro da janela
    }

    // Método principal: inicia a aplicação e exibe a tela principal
    public static void main(String[] args) {
    if (Conexao.conectar() != null) {
        SwingUtilities.invokeLater(() -> new Consultas().setVisible(true));
    } else {
        JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

}
