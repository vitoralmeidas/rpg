package br.com.tills.dungeonsdragons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tills.dungeonsdragons.exceptions.GildaLessThanOneException;
import br.com.tills.dungeonsdragons.exceptions.IdNotFoundException;
import br.com.tills.dungeonsdragons.jdbc.Conexao;
import br.com.tills.dungeonsdragons.model.Atributo;

/***
 * Classe Dao para a Classe Atributo
 * 
 * @author Till's Tech
 * @version 1.1
 */

public class AtributoDao {

	// inclui atributo na lista de atributos

	/***
	 * Lista para armazenar os atributos do objeto Personagem do tipo Atributo
	 */
	List<Atributo> atributo = new ArrayList<>();
	Connection con;
	
	
	

	public AtributoDao(Connection con) {
		this.con = con;
	}

	/**
	 * Realiza a inclusão do objeto Atributo na lista
	 * 
	 * @param atributo Objeto Atributo que sera incluido
	 * @throws SQLException
	 */
	public void incluir(Atributo atributo) {

		this.atributo.add(atributo);

	}

	public void insert(int idPesonagem) throws SQLException {
		int i = 0;
		while (i < atributo.size()) {

			PreparedStatement stmt = con.prepareStatement(
					"insert into tb_atributo (cd_atributo,nm_atributo,vl_atributo,cd_personagem) values (SQ_atributo.nextval,?,?,?)");

			stmt.setString(1, atributo.get(i).getNome());
			stmt.setInt(2, atributo.get(i).getValor());
			stmt.setInt(3, idPesonagem);

			stmt.executeUpdate();
			i++;
		}
	}

	public List<Atributo> listar(int id) throws SQLException {
		// Criar o PreparedStatement com o comando SQL de select

		PreparedStatement stmt = con
				.prepareStatement("select nm_atributo,vl_atributo from TB_atributo where CD_Personagem = ?");
		
		stmt.setInt(1, id);

		// Executar o comando SQL e obter o ResultSet
		ResultSet result = stmt.executeQuery();

		List<Atributo> lista = new ArrayList<>();

		// Percorre todos os registros encontrados
		while (result.next()) {
			// Recupera os dados do filme no resultSet
			Atributo a = parse(result);
			lista.add(a);
		}

		// Retorna a lista de filme
		return lista;
	}

	private Atributo parse(ResultSet r) throws SQLException {

		String nome = r.getString("NM_atributo");
		int valor = r.getInt("vl_atributo");

		// Instancia o personagem com os dados

		return new Atributo(nome, valor);
	}

	
	public void deletar(int codigo) throws SQLException, IdNotFoundException, GildaLessThanOneException {
		// Criar o PreparedStatement com o comando SQL de delete
		
		PreparedStatement stmt = con.prepareStatement("delete from TB_atributo where CD_PERSONAGEM = ?");

		// Setar o valor para a query
		stmt.setInt(1, codigo);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Verificar se algum registro foi apagado do banco
		if (qtd == 0)
			throw new IdNotFoundException("Id n�o encontrado para remo��o");
	}

	
	/**
	 * remove todo o conteudo da lista atributo instanciando um novo obj do tipo
	 * Atributo
	 */
	public void zerar() {
		atributo = new ArrayList<Atributo>();

	}

	public List<Atributo> getAtributoDao() {
		return this.atributo;
	}

}
