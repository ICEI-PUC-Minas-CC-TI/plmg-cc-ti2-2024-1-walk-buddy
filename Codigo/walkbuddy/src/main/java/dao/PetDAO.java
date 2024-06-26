package dao;

import model.Pet;
import java.sql.*;

public class PetDAO {
	
	public static boolean inserir(Pet pet) {
		boolean status = false;
		try {
			Connection conexao = DAO.conectar();
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO pet (nome, dataNascimento, raca, perfilcomportamental, foto, id_dono) "
		               + "VALUES ('" + pet.nome + "', '" + pet.dataNascimento + "', '" + pet.raca
		               + "', '" + pet.perfilComportamental + "', '" + pet.foto + "', " + pet.idDono + ");";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (Exception e) {
			System.out.println("erro inserir");
		}
		return status;
	}
	
	public static String listar(int id) {
		String lista = "erro";
		try {
			Connection conexao = DAO.conectar();
			Statement st = conexao.createStatement();
			String sql = "SELECT * FROM pet WHERE id_dono = " + id + ";";
			ResultSet rs = st.executeQuery(sql);
			lista = "[";
			while(rs.next()) {	            	
	        	Pet pet = new Pet();
	        	pet.id = rs.getInt("id");
	        	pet.nome = rs.getString("nome");
	        	pet.dataNascimento = rs.getString("dataNascimento");
	        	pet.raca = rs.getString("raca");
	        	pet.perfilComportamental = rs.getString("perfilcomportamental");
	        	pet.foto = rs.getString("foto");
	        	pet.idDono = rs.getInt("id_dono");
	        	lista += pet.toString() + ",";
	        }
			if(lista.charAt(lista.length()-1)==',')
				lista = lista.substring(0, lista.length()-1);
			lista += "]";
			st.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

}
