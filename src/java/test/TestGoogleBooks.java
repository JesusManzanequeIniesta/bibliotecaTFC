package test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import tools.GoogleBooks;

public class TestGoogleBooks {

	public static void main(String[] args) throws IOException {
		JSONObject json=GoogleBooks.buscaISBN("9788467261288");
		// Ahora saco la chicha
		System.out.println(json);
		System.out.println(" Titulo ="+json.getString("titulo"));
		System.out.println("Lista de Autores");
		JSONArray jsAutores=json.getJSONArray("autores");
		for (int i=0;i<jsAutores.length();i++) {
			System.out.println("Autor="+jsAutores.getString(i));
		}
	}

}
