package fr.eni.ep3jasp.cap05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// tag::code[]
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! Fin del job! Resultado para comprobación:");

      List<Departamento> results = jdbcTemplate.query("SELECT code, nombre FROM departamento", new RowMapper<Departamento>() {
        @Override
        public Departamento mapRow(ResultSet rs, int row) throws SQLException {
          return new Departamento(rs.getString(1), rs.getString(2));
        }
      });

      for (Departamento departamento : results) {
        log.info("[" + departamento + "] en base.");
      }

    }
  }
}
// end::code[]