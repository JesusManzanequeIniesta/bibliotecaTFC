package test;

import java.io.IOException;

import excepciones.BibliotecaException;
import tools.Tools;

public class TestEnvioSMS {

	public static void main(String[] args) throws IOException, BibliotecaException {
		Tools.enviarSMS("647628264","No se asuste, lo envíe por error");
	}

}
