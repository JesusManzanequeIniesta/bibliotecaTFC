package test;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import tools.Tools;

public class TestCreaPdf {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		Tools.creaFicheroPDF("./","Ejemplar","000001S");

	}

}
