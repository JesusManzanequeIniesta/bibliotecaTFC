package test;

import tools.Tools;

public class TestEnvioEmail {
	
	public static void main(String[] args) throws Exception {
		String email="alumno.86075@ies-azarquiel.es";
		String asunto="Validación en correo registro aplicación biblioteca";
		String token=Tools.generaToken();
		String cuerpo=Tools.creaCuerpoCorreo(token);
		Tools.enviarConGMail(email,asunto,cuerpo);
		System.out.println(" Terminado ");
	}
	
}
