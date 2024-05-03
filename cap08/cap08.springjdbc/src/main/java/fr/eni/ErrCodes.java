package fr.eni;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Personalización de códigos de error
 *
 * @author $author$
 * @version $Revision$
  */
@Slf4j
public class ErrCodes {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext appContext;
		appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// of course, an ApplicationContext is just a BeanFactory
		BeanFactory factory = (BeanFactory) appContext;

		//dataAccessResourceFailureCodes
		
		SQLErrorCodes h2 = (SQLErrorCodes) factory
				.getBean("H2");

		String[] lista = h2.getDataAccessResourceFailureCodes();
		String codes=Arrays.stream(lista).map(x->x.toString()).collect(Collectors.joining(",","[","]"));;
		
		
		log.info("Códigos para getDataAccessResourceFailureCodes:"+codes);
		
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test2");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		try {
			jdbcTemplate.execute("CALL ABS(1, 2)");
		} catch (UncategorizedSQLException e) {
			log.info("UncategorizedSQLException:"+e.getMessage());
		} catch (DataAccessResourceFailureException e)  {
			log.info("DataAccessException:"+e.getMessage());
		}
 
		

		dataSource.destroy();
	}

}
