package com.mycompany.consultas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/consultas_db",
                "root", 
                ""
            );
            System.out.println("Conexão realizada com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
        return conn;
    }
}
