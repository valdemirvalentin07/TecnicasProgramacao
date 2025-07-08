/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package provatc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tibd8
 */
public class Dados {
    
    Connection con;
    public boolean conectar(String local, String banco, String usuario, String senha)
    {
       boolean retorno = false;
       try 
       {   String url = "jdbc:mysql://" + local + "/" + banco + "?useTimezone=true&serverTimezone=UTC";
           con = (Connection) DriverManager.getConnection(url,usuario,senha);
           retorno = true;
        } catch (SQLException e) {
            System.err.println("Erro de conexão:\n" + e);
        }
        return retorno;
    }
    
   public boolean inserir(String tabela, String campos, String valor)
   {
       boolean retorno = false;
       try {
           String parametros = "INSERT INTO " + tabela + " (" + campos + ") values ("+ valor + ")";
           PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(parametros);
           stmt.execute();
           stmt.close();
           retorno = true;
       } catch (SQLException ex) {
           System.err.println("Erro INSERT: " + ex);
       }
       return retorno;
   }
/*   */
   public ResultSet consultar(String consulta) {
       ResultSet rs = null;
       try {
           PreparedStatement stmt = (PreparedStatement) this.con.prepareStatement(consulta);
           rs = stmt.executeQuery();
       } catch (Exception e) {
           System.err.println("Erro CONSULTA: " + e);
       }
       return rs;
    }

}
