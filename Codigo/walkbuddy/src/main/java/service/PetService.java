package service;

import dao.PetDAO;
import model.Pet;
import spark.Request;
import spark.Response;

public class PetService {
	
	public static String inserir(Request request, Response response) {
		String resultado = "erro";
		boolean sucesso = false;
		response.status(400);
		Pet pet = new Pet();
		pet.nome = request.queryParams("nome");
		pet.idade = Integer.parseInt(request.queryParams("idade"));
		pet.raca = request.queryParams("raca");
		pet.perfilComportamental = request.queryParams("perfilComportamental");
		pet.foto = request.queryParams("foto");
		pet.idDono = Integer.parseInt(request.queryParams("idDono"));
		sucesso = PetDAO.inserir(pet);
		if(sucesso) {
			resultado = "Pet " + pet.nome + " adicionado";
            response.status(201);
		}
		return resultado;
	}
	
	public static String listar(Request request, Response response) {
		String resultado = "erro";
		resultado = PetDAO.listar();
        response.status(200);
		return resultado;
	}

}