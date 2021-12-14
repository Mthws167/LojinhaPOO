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
import models.Produto;
import utils.Conexao;

public class ProdutoDAO {
	private Connection con = Conexao.getConexao();
	
	public void cadastrarProduto(Produto produto) {
		PreparedStatement ps = con.prepareStatement("INSERT INTO produtos (nome, descricao, quantidade, valor) VALUES (?,?,?,?)");
		try {
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQuantidade());
            ps.setFloat(4, produto.getValor());            
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		ps.close();
	}
	
	public void atualizarProduto(Produto produto) {
		PreparedStatement ps = con.prepareStatement("UPDATE produtos SET  nome=?, descricao=?, quantidade=?, valor=? WHERE idproduto=?");
        try {
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getValor());
            ps.setInt(5, produto.getIdProduto());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch(Exception e) {
        	JOptionPane.showMessageDialog(null, e);
        }
        ps.close();
	}
	
    public void deleteProduto(Produto produto) {
    	PreparedStatement ps = con.prepareStatement("DELETE FROM produtos WHERE idproduto=?");
        try {
            ps.setInt(1, produto.getIdProduto());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ps.close();
    }
    
    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM produtos");
        try {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("idproduto"));
                produto.setNomeProduto(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getFloat("valor"));
                produtos.add(produto);
            }
            ps.close();
            return produtos;
       }catch(Exception e) {
    	   JOptionPane.showMessageDialog(null, e);
       }
       ps.close();
       return null;
    }

}
