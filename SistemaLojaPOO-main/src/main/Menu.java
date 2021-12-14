package main;

import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import models.Cliente;
import models.Produto;
import models.Venda;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.Conexao;

public class Menu {

	public static void main(String args[]) throws SQLException {
		int opcao, operacao;
		String option = JOptionPane.showInputDialog(null, "Bem vindo a lojinha de POO! \nSelecione a opção desejada: \n1-Cliente \n2-Produto \n3-Venda");
		if(option == null) {
			JOptionPane.showMessageDialog(null, "Sistema Encerrado!");
			option = "0";
		}
		opcao = Integer.parseInt(option);
        operacao = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha a operação que deja realizar: \n1-Cadastrar \n2-Atualizar \n3-Deletar \n4-Listar"));
        Cliente cliente = new Cliente();
        Produto produto = new Produto();
        Venda venda = new Venda();
        switch (opcao) {
		case 1:
			switch (operacao) {
			case 1: //cadastrar cliente
				String nome = JOptionPane.showInputDialog(null, "Informe o nome do cliente: ");
				String email = JOptionPane.showInputDialog(null, "Informe o email do cliente: ");
				String sexo = JOptionPane.showInputDialog(null, "Informe o sexo do cliente: \n Insira F para Feminino e M para Maculino ");
				String telefone = JOptionPane.showInputDialog(null, "Informe o número do cliente: ");
				cliente.setNome(nome);
				cliente.setEmail(email);
				cliente.setSexo(sexo);
				cliente.setTelefone(telefone);
				if(nome == null || email == null || sexo == null || telefone == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.cadastarCliente(cliente);
				break;
			case 2: //atualizar cliente
				String idCliente = JOptionPane.showInputDialog(null, "Informe o código do cliente que deseja fazer a alteração: ");
				String nome = JOptionPane.showInputDialog(null, "Informe o novo nome do cliente: ");
				String email = JOptionPane.showInputDialog(null, "Informe o novo email do cliente: ");
				String sexo = JOptionPane.showInputDialog(null, "Informe o sexo do cliente: \n Insira F para Feminino e M para Maculino ");
				String telefone = JOptionPane.showInputDialog(null, "Informe o novo número do cliente: ");
				if(idCliente == null || nome == null || email == null || sexo == null || telefone == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				cliente.setIdCliente(Integer.parseInt(idCliente));
				cliente.setNome(nome);
				cliente.setEmail(email);
				cliente.setSexo(sexo);
				cliente.setTelefone(telefone);
				ClienteDAO clienteDAOAt = new ClienteDAO();
				clienteDAOAt.atualizarCliente(cliente);
				break;
	                   
			case 3: //deletar cliente
				String idCliente = JOptionPane.showInputDialog(null, "Informe o código do cliente que deseja deletar: ");
				if(idCliente == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				cliente.setIdCliente(Integer.parseInt());
				ClienteDAO clienteDAODel = new ClienteDAO();
				clienteDAODel.deleteCliente(cliente);
				break;
			case 4: //listar clientes
				ClienteDAO clienteDAOList = new ClienteDAO();
				for(Cliente cliente1 : clienteDAOList.getAll()) {
					JOptionPane.showMessageDialog(null, "Cliente: "+cliente1.getNome());
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida! Tente novamente.");
				break;
			}
			break;
		case 2:
			switch (operacao) {
			case 1: //cadastrar produto
				String nome = JOptionPane.showInputDialog(null, "Informe o nome do Produto: ");
				String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do produto: ");
				String quantidade = JOptionPane.showInputDialog(null, "Informe a quantidade de produtos disponíveis: ");
				String valor = JOptionPane.showInputDialog(null, "Informe o valor do produto: ");
				if(nome == null || descricao == null || quantidade == null || valor == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				produto.setNomeProduto(valor);
				produto.setDescricao(descricao);
				produto.setQuantidade(Integer.parseInt(quantidade));
				produto.setValor(Float.parseFloat(valor));
				
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produtoDAO.cadastrarProduto(produto);
				break;
			case 2: //atualizar produto
				String idProduto = JOptionPane.showInputDialog(null, "Informe o código do produto que deseja atualizar: ");
				String nome = JOptionPane.showInputDialog(null, "Informe o novo nome do Produto: ");
				String descricao = JOptionPane.showInputDialog(null, "Informe a nova descrição do produto: ");
				String quantidade = JOptionPane.showInputDialog(null, "Informe a nova quantidade de produtos disponíveis: ");
				String valor = JOptionPane.showInputDialog(null, "Informe o novo valor do produto: ");
				if(idProduto == null || nome == null || descricao == null || quantidade == null || valor == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				produto.setIdProduto(Integer.parseInt(idProduto));
				produto.setNomeProduto(nome);
				produto.setDescricao(descricao);
				produto.setQuantidade(Integer.parseInt(quantidade));
				produto.setValor(Float.parseFloat(valor));
				ProdutoDAO produtoDAOAt = new ProdutoDAO();
				produtoDAOAt.atualizarProduto(produto);				
				break;
	                   
			case 3: //deletar produto
				produto.setIdProduto(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do produto que deseja deletar: ")));
				ProdutoDAO produtoDAODel = new ProdutoDAO();
				produtoDAODel.deleteProduto(produto);
				break;
			case 4: //listar produtos
				ProdutoDAO produtoDAOList = new ProdutoDAO();
				for(Produto produto1 : produtoDAOList.getAll()) {
					JOptionPane.showMessageDialog(null, "Produto: "+produto1.getNome_produto());
				}
				
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida! Tente novamente.");
				break;
			}
			break;  
		case 3:
			switch (operacao) {
			case 1: //cadastrar venda
				String codigoCliente = JOptionPane.showInputDialog(null, "Informe o código do cliente: ");
				String valorVenda = JOptionPane.showInputDialog(null, "Informe o valor da venda: ");
				String dataVenda = JOptionPane.showInputDialog(null, "Informe a data da venda: ");
				if(codigoCliente == null || valorVenda == null || dataVenda == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				venda.setIdCliente(Integer.parseInt(codigoCliente));
				venda.setValorVenda(Float.parseFloat(valorVenda));
				venda.setDataVenda(dataVenda);
				VendaDAO vendaDAO = new VendaDAO();
				vendaDAO.cadastrarVenda(venda);
				break;
			case 2: //atualizar venda
				String idVenda = JOptionPane.showInputDialog(null, "Informe o código da venda que deseja atualizar: ");
				String codigoCliente = JOptionPane.showInputDialog(null, "Informe o novo código do cliente: ");
				String valorVenda = JOptionPane.showInputDialog(null, "Informe o novo valor da venda: ");
				String dataVenda = JOptionPane.showInputDialog(null, "Informe a nova data da venda: ");
				if(idVenda == null || codigoCliente == null || valorVenda == null || dataVenda == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				venda.setId_venda(Integer.parseInt(idVenda));
				venda.setId_cliente(Integer.parseInt(codigoCliente));
				venda.setValor_venda(Float.parseFloat(valorVenda));
				venda.setData_venda(dataVenda);
				VendaDAO vendaDAOAt = new VendaDAO();
				vendaDAOAt.atualizarVenda(venda);
				break;
	                   
			case 3: //deldetar venda
				String idVenda = JOptionPane.showInputDialog(null, "Informe o código da venda que deseja deletar: ");
				if(idVenda == null) {
					JOptionPane.showMessageDialog(null, "valores informados incorretamente");
					break;
				}
				venda.setId_venda(Integer.parseInt(idVenda));
				VendaDAO vendaDAODel = new VendaDAO();
				vendaDAODel.deleteVenda(venda);
				break;
			case 4: //listar vendas
				VendaDAO vendaDAOList = new VendaDAO();
				for(Venda venda1 : vendaDAOList.getAll()) {
					JOptionPane.showMessageDialog(null, "Venda: "+venda1.getId_venda());
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida! Tente novamente.");
				break;
			}
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida! Tente novamente.");
			break;
		
	 } while (opcao != 0);
	}

}
