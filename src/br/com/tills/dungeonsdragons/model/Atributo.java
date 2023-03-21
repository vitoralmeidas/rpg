package br.com.tills.dungeonsdragons.model;

/***
 * Classe que abstrai um Atributo
 * 
 * @author Till's Tech
 * @version 1.0
 */

public class Atributo {
	/***
	 * Codigo do Atributo
	 */
	private int id;
	/***
	 * Nome do Atributo
	 */
	private String nome;
	/**
	 * 
	 */
	private int valor;

	public Atributo() {
	}

	public Atributo(int id, String nome, int valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
	}
	

	public Atributo(String nome, int valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	/***
	 * Incrementa mais 1 no atributo valor
	 */
	public void add() {
		this.valor++;
	}

	public String obj() {
		return "\"" + nome + "\":" + valor;
	}

	@Override
	public String toString() {

		return nome + ":" + valor;
	}

}
