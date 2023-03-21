package br.com.tills.dungeonsdragons.model;

/**
 * Classe que abstrai um Item
 * 
 * @author JaelsonSantos
 * @version 1.0
 */
public class Item {
	/**
	 * Codigo de identificação do Item
	 */
	private int id;
	/**
	 * Nome do item
	 */
	private String nome;
	/**
	 * quantidade do item
	 */
	private int quantidade;
	/**
	 * descrição do item
	 */
	private String descricao;

	public Item() {
	}

	public Item(int id, String nome, int quantidade, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
	}
	
	

	public Item(String nome, int quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String obj() {
		return "\"nome\":\"" + nome + "\",\"quantidade\":\"" + quantidade + "\"";
	}

	@Override
	public String toString() {

		return "Nome: " + nome + "\nQuantidade: " + quantidade;

	}

}
