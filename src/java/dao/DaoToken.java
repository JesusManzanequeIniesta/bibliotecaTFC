/**
 * 
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import entidades.Token;
import excepciones.BibliotecaException;
import java.util.List;

/**
 * @author Ortiz modificado por Julio León
 *
 */
public class DaoToken {
	Dao dao;
	public DaoToken(Dao dao) {
		this.dao=dao;
	}
	
	public Token findByValue(String value) throws SQLException, Exception {
            Token token;
		try {
                        List<Token> listaToken = dao.getEm().createNamedQuery("Token.findByValue", Token.class)
                                                            .setParameter("value", value)
                                                            .getResultList();
                        if(listaToken != null){
                            return listaToken.get(0);
                        }
                        else{
                            return null;
                        }
                
		} catch (Exception e) {
			throw e;
		}

	}

	public void inserta(Token token) throws Exception {
            try{
                dao.getEm().persist(token);
                dao.getEm().flush();
            } catch (Exception ex){
                throw ex;
            }
        }
	
	public void borra(Token token) throws SQLException, BibliotecaException, Exception {
            dao.getEm().remove(token);
            dao.getEm().flush();
        }

}
