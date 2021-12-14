package dao;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Cliente;
import utils.Conexao;

public class ClienteDAO {
	private Connection con = Conexao.getConexao();
	
	public void cadastarCliente(Cliente cliente) {
		PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (nome, email, sexo, telefone) VALUES (?,?,?,?)");
		try {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getTelefone());       
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		ps.close();
	}
	
	public void atualizarCliente(Cliente cliente) {
		PreparedStatement ps = con.prepareStatement("UPDATE clientes SET  nome=?, email=?, sexo=?, telefone=? WHERE idcliente=?");
        try {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getTelefone());
            ps.setInt(5, cliente.getIdCliente());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, e);
        }
        ps.close();
	}
	
    public void deleteCliente(Cliente cliente) {
    	PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE idcliente=?");
        try {
            ps.setInt(1, cliente.getIdCliente());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cliente deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
    }
    
    
    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM clientes");
        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
            ps.close();
            return clientes;
       }catch(Exception e) {
    	   JOptionPane.showMessageDialog(null, e);
       }
       ps.close();
       return null;
    }

}
