/**
 * 
 */
package excepciones;

@SuppressWarnings("serial")
public class BibliotecaException extends Exception {

	int codigoError;
	public BibliotecaException() {
		
	}
	
	public BibliotecaException(String message,int codigoError) {
		super(message);
		this.codigoError=codigoError;
	}
	public int getErrorCode() {
		return codigoError;
	}
}
