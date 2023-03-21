package br.com.tills.dungeonsdragons.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tills.dungeonsdragons.exceptions.ClasseNotFoundException;
import br.com.tills.dungeonsdragons.exceptions.GildaLessThanOneException;
import br.com.tills.dungeonsdragons.exceptions.IdNotFoundException;
import br.com.tills.dungeonsdragons.jdbc.Conexao;
import br.com.tills.dungeonsdragons.model.Personagem;

/***
 * Classe Dao para a Classe Personagem
 * 
 * @author Till's Tech
 * @version 1.1
 */
public class PersonagemDao {
	/***
	 * Lista para armazenar os atributos do objeto Personagem do tipo Personagem
	 */
	List<Personagem> personagem = new ArrayList<>();
	Connection con ;
	
	

	public PersonagemDao(Connection con) {
		
		this.con = con;
	}


	/**
	 * Realiza a inclusão do objeto Personagem na lista
	 * 
	 * @param personagem Objeto personagem que sera incluido
	 * @throws SQLException
	 */

	public void incluir(Personagem personagem) throws SQLException {

	
		PreparedStatement stmt = con.prepareStatement(
				"INSERT INTO TB_PERSONAGEM" + "(CD_PERSONAGEM,NM_PERSONAGEM,NM_RACA,NM_CLASSE,NM_GUILDA,"
						+ "VL_EXPERIENCIA,VL_LEVEL) VALUES (?, ?, ?, ?,	?,?,?)");

		stmt.setInt(1, personagem.getId());
		stmt.setString(2, personagem.getNome());
		stmt.setString(3, personagem.getRaca());
		stmt.setString(4, personagem.getClasse());
		stmt.setString(5, personagem.getGuilda());
		stmt.setInt(6, personagem.getExperiencia());
		stmt.setInt(7, personagem.getLevel());

		stmt.executeUpdate();

	}

	
	/**
	 * Realiza a exclusão do objeto Personagem
	 * 
	 * @param id Codigo de idenficicação do personagem
	 * @throws GildaLessThanOneException
	 *  @throws IdIdNotFoundException
	 *  @throws SQLException
	 */

	public void deletar(int codigo) throws SQLException, IdNotFoundException, GildaLessThanOneException {

		PreparedStatement stmt = con.prepareStatement("delete from TB_PERSONAGEM where CD_PERSONAGEM = ?");

		stmt.setInt(1, codigo);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("Id n�o encontrado para remo��o");
	}

	
	private Personagem parse(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("CD_PERSONAGEM");
		String nome = resultSet.getString("NM_PERSONAGEM");
		String raca = resultSet.getString("NM_RACA");
		String classe = resultSet.getString("NM_CLASSE");
		String guilda = resultSet.getString("NM_GUILDA");
		int experiencia = resultSet.getInt("VL_EXPERIENCIA");
		int level = resultSet.getInt("VL_LEVEL");


		Personagem personagem = new Personagem(id, nome, raca, classe, guilda, experiencia, level);

		return personagem;

	}

	/**
	 * Realiza a busca do objeto Personagem na lista
	 * 
	 * @param id Codigo de idenficicação do personagem
	 * @return Personagem com base no id
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */

	public Personagem buscarPorId(int codigo) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from TB_PERSONAGEM where CD_PERSONAGEM = ?");

		stmt.setInt(1, codigo);

		ResultSet result = stmt.executeQuery();

		if (!result.next()) {
			throw new IdNotFoundException("Personagem não encontrado");
		}

		Personagem p = parse(result);
		return p;
	}

	/**
	 * gera uma lista dos personagens
	 * 
	 * @throws ClasseNotFoundException
	 * @throws SQLException
	 */
	public List<Personagem> listar() throws SQLException, ClasseNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from TB_PERSONAGEM");

		ResultSet result = stmt.executeQuery();

		List<Personagem> lista = new ArrayList<Personagem>();

		while (result.next()) {

			Personagem persona = parse(result);
			lista.add(persona);
		}

		return lista;

	}

	/**
	 * Altera o nome do personagem
	 * 
	 * @param id   Codigo de identificação
	 * @param nome Nome que será incluido
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void alteraNome(int id, String nome) throws SQLException, IdNotFoundException {
		
		PreparedStatement stmt = con
				.prepareStatement("update TB_PERSONAGEM set NM_PERSONAGEM = ? where CD_PERSONAGEM = ?");

		stmt.setString(1, nome);
		stmt.setInt(2, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("Personagem not found");

	}

	/**
	 * Lista o personagem com base na classe
	 * 
	 * @param raca Raça que será utilizada como base na busca
	 * @throws SQLExeption
	 * @throws ClasseNotFoundException
	 */
	public List<Personagem> buscarPorClasse(String classe) throws SQLException, ClasseNotFoundException {
		
		PreparedStatement stmt = con.prepareStatement("select * from TB_PERSONAGEM where NM_CLASSE = ?");

		stmt.setString(1, classe);

		ResultSet result = stmt.executeQuery();

		List<Personagem> lista = new ArrayList<>();

		while (result.next()) {
			Personagem p = parse(result);
			lista.add(p);
		}

		return lista;
	}

	/**
	 * 
	 * @param id
	 * @return retorna a lista de atributos com base no id
	 * @throws ClasseNotFoundException
	 * @throws SQLException
	 */

	public String json() throws SQLException, ClasseNotFoundException {

		List<Personagem> list = listar();
		int i = 0;
		String json = "[";

		while (i < list.size()) {
			if (i == list.size() - 1) {
				json += "{" + list.get(i).obj() + ",\"Itens\":[";

				int j = 0;
				while (j < list.get(i).getInventario().size()) {
					if (j == list.get(i).getInventario().size() - 1) {
						json += "{" + list.get(i).getInventario().get(j).obj() + "}],\"Atributos\": [{";
					} else {
						json += "{" + list.get(i).getInventario().get(j).obj() + "},";
					}
					j++;
				}
				j = 0;
				while (j < list.get(i).getListaAtributo().size()) {
					if (j == list.get(i).getListaAtributo().size() - 1) {
						json += list.get(i).getListaAtributo().get(j).obj() + "}]}]";
					} else {
						json += list.get(i).getListaAtributo().get(j).obj() + ",";
					}
					j++;
				}
			} // if
			else {
				int j = 0;
				json += "{" + list.get(i).obj() + ",\"Itens\":[";
				while (j < list.get(i).getInventario().size()) {
					if (j == list.get(i).getInventario().size() - 1) {
						json += "{" + list.get(i).getInventario().get(j).obj() + "}],\"Atributos\": [{";
					} else {
						json += "{" + list.get(i).getInventario().get(j).obj() + "},";
					}
					j++;
				}
				j = 0;
				while (j < list.get(i).getListaAtributo().size()) {
					if (j == list.get(i).getListaAtributo().size() - 1) {
						json += list.get(i).getListaAtributo().get(j).obj() + "}]},";
					} else {
						json += list.get(i).getListaAtributo().get(j).obj() + ",";
					}
					j++;

				}

			}
			i++;
		}

		return json;

	}

	public void gravar(String json, String nomeArquivo) throws IOException {

		PrintWriter write = new PrintWriter(new FileWriter(nomeArquivo + ".json"));

		write.println(json);

		write.close();
	}

}// class
