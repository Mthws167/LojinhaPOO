package models;

import java.util.Date;

public class Venda {
	private int idVenda;
	private float valorVenda;
	private String dataVenda; 
	private int idCliente;
	
	
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int id_venda) {
		this.id_venda = id_venda;
	}
	public float getValorVenda() {
		return valor_venda;
	}
	public void setValorVenda(float valor_venda) {
		this.valor_venda = valor_venda;
	}
	public String getDataVenda() {
		return data_venda;
	}
	public void setDataVenda(String string) {
		this.data_venda = string;
	}
	public void add(Venda venda) {
		// TODO Auto-generated method stub
		
	}
	public int getIdCliente() {
		return id_cliente;
	}
	public void setIdCliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
}
