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
import br.com.tills.dungeonsdragons.model.Item;

/***
 * Classe Dao para a Classe Item
 * 
 * @author Till's Tech
 * @version 1.1
 */

public class ItemDao {
	/***
	 * Lista para armazenar os atributos do objeto Personagem do tipo Item
	 */
	List<Item> item = new ArrayList<>();
	Connection con;
	
	
	
	
	

	public ItemDao(Connection con) {
		this.con = con;
	}

	/**
	 * Realiza a inclusão do objeto Item na lista
	 * 
	 * @param item Objeto item que sera incluido
	 */

	public void incluir(Item item) {

		this.item.add(item);

	}

	public void insert(int idPesonagem) throws SQLException {
		con = Conexao.conecta();
		int i = 0;
		while (i < item.size()) {

			PreparedStatement stmt = con.prepareStatement(
					"insert into tb_item (cd_item,"
										+ "cd_personagem,"
										+ "nm_item,"
										+ "vl_item,"
										+ "ds_item) values(SQ_item.nextval,?,?,?,?)");

			stmt.setInt(1, idPesonagem);
			stmt.setString(2, item.get(i).getNome());
			stmt.setInt(3, item.get(i).getQuantidade());
			stmt.setString(4, item.get(i).getDescricao());
		
			stmt.executeUpdate();
			i++;
		}
	}
	
	
	private Item parse(ResultSet r) throws SQLException {

		String nome = r.getString("nm_item");
		int quantidade = r.getInt("vl_item");

		// Instancia o personagem com os dados

		return new Item(nome, quantidade);
	}


	public List<Item> listar(int id) throws SQLException {
		// Criar o PreparedStatement com o comando SQL de select

		con = Conexao.conecta();
		PreparedStatement stmt = con
				.prepareStatement("select nm_item,vl_item from tb_item where cd_personagem = ?");
		
		stmt.setInt(1, id);

		// Executar o comando SQL e obter o ResultSet
		ResultSet result = stmt.executeQuery();

		List<Item> lista = new ArrayList<>();

		// Percorre todos os registros encontrados
		while (result.next()) {
			// Recupera os dados do filme no resultSet
			Item i = parse(result);
			lista.add(i);
		}

		// Retorna a lista de filme
		return lista;
	}

	public void deletar(int codigo) throws SQLException, IdNotFoundException, GildaLessThanOneException {
		// Criar o PreparedStatement com o comando SQL de delete
		con = Conexao.conecta();
		PreparedStatement stmt = con.prepareStatement("delete from TB_item where CD_PERSONAGEM = ?");

		// Setar o valor para a query
		stmt.setInt(1, codigo);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Verificar se algum registro foi apagado do banco
		if (qtd == 0)
			throw new IdNotFoundException("Id n�o encontrado para remo��o");
	}

	
	/**
	 * remove todo o conteudo da lista item instanciando um novo obj do tipo Item
	 */

	public void zerar() {
		item = new ArrayList<Item>();
	}

	public List<Item> getItem() {
		return item;
	}

}
