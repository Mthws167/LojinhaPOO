package dao;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Venda;
import models.Produto;
import models.Cliente;
import utils.Conexao;

public class VendaDAO {

		private Connection con = Conexao.getConexao();
		
		public void cadastrarVenda(Venda venda) {
			PreparedStatement ps = con.prepareStatement("INSERT INTO vendas (codigo_cliente, valor, data) VALUES (?,?,?)");
			try {
	            ps.setInt(1, venda.getIdCliente());
	            ps.setFloat(2, venda.getValorVenda());
	            ps.setString(3,  venda.getDataVenda());        
	            ps.execute();
	            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			ps.close();
		}
		
		public void atualizarVenda(Venda venda) {
			PreparedStatement ps = con.prepareStatement("UPDATE vendas SET  codigo_cliente=?, valor=?, data=?	WHERE idvenda=?");
	        try {
	            ps.setInt(1, venda.getIdCliente());
	            ps.setFloat(2, venda.getValorVenda());
	            ps.setString(3,  venda.getDataVenda()); 
	            ps.setInt(4, venda.getIdVenda());
	            ps.execute();
	            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
	        } catch(Exception e) {
	        	JOptionPane.showMessageDialog(null, e);
	        }
	        ps.close();
		}
		
	    public void deleteVenda(Venda venda) {
	    	PreparedStatement ps = con.prepareStatement("DELETE FROM vendas WHERE idvenda=?");
	        try {
	            ps.setInt(1, venda.getIdVenda());
	            ps.execute();
	            JOptionPane.showMessageDialog(null, "Venda deletada com sucesso!");
	        } catch (SQLException ex) {
	            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        ps.close();
	    }
	    
	    public List<Venda> getAll() {
	        List<Venda> vendas = new ArrayList<>();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM vendas");
	        try {
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Venda venda = new Venda();
	                venda.setIdVenda(rs.getInt("idvenda"));
	                venda.setIdCliente(rs.getInt("codigo_cliente"));      
	                venda.setValorVenda(rs.getFloat("valor"));
	                ps.setString(3,  venda.getDataVenda()); 
	                venda.add(venda);
	            }
	            ps.close();
	            return vendas;
	       }catch(Exception e) {
	    	   JOptionPane.showMessageDialog(null, e);
	       }
	       ps.close();
	       return null;
	    }

	}

