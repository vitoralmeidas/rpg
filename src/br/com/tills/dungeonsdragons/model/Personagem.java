package br.com.tills.dungeonsdragons.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que abstrai um Personagem
 * 
 * @author JaelsonSantos
 * @version
 */
public class Personagem {
	/**
	 * Codigo de identificação
	 */
	private int id;
	/**
	 * Nome do Personagem
	 */
	private String nome;
	/**
	 * Raça do Personagem
	 */
	private String raca;
	/**
	 * Classe do Personagem
	 */
	private String classe;
	/**
	 * Gilda do Personagem
	 */
	private String guilda;
	/**
	 * Experientcia do Personagem
	 */
	private int experiencia;
	/**
	 * Level do Personagem
	 */
	private int level;
	/**
	 * Lista do tipo Atributo do Personagem
	 */
	private List<Atributo> listaAtributo = new ArrayList<>();
	/**
	 * Lista do tipo Item do Personagem (inventário)
	 */
	private List<Item> inventario = new ArrayList<>();

	// costrutor padrao
	public Personagem() {

	}

	public Personagem(int id, String nome, String raca, String classe, String guilda, int experiencia, int level,
			List<Atributo> listaAtributo, List<Item> inventario) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.classe = classe;
		this.guilda = guilda;
		this.experiencia = experiencia;
		this.level = level;
		this.listaAtributo = listaAtributo;
		this.inventario = inventario;
	}
	
	
	

	public Personagem(int id, String nome, String raca, String classe, String guilda, int experiencia, int level) {
		super();
		this.id = id;
		this.nome = nome;
		this.raca = raca;
		this.classe = classe;
		this.guilda = guilda;
		this.experiencia = experiencia;
		this.level = level;
	}

	// getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Atributo> getListaAtributo() {
		return listaAtributo;
	}

	public void setListaAtributo(List<Atributo> listaAtributo) {
		this.listaAtributo = listaAtributo;
	}

	public List<Item> getInventario() {
		return inventario;
	}

	public void setInventario(List<Item> inventario) {
		this.inventario = inventario;
	}

	public String getGuilda() {
		return guilda;
	}

	public void setGuilda(String guilda) {
		this.guilda = guilda;
	}

	public String obj() {
		return "\"id\":" + id + ",\"nome\":\"" + nome + "\",\"raça\":\"" + raca + "\",\"classe\":\"" + classe
				+ "\",\"gilda\":\"" + guilda + "\",\"experiencia\":\"" + experiencia + "\",\"level\":\"" + level + "\"";

	}

	@Override
	public String toString() {
		return "ID: " + id + "\n" + "Nome: " + nome + "\n" + "Raça: " + raca + "\n" + "Classe: " + classe + "\n"
				+ "Guilda: " + guilda + "\n" + "Experiencia: " + experiencia + "\n" + "Level: " + level + "\n\r";
	}

}
