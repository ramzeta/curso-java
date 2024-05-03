package fr.eni.ep3jasp.cap05;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

// tag::code[]
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  public DataSource dataSource;

  // tag::readerwriterprocessor[]
  @Bean
  public FlatFileItemReader<Departamento> reader() {
    FlatFileItemReader<Departamento> reader = new FlatFileItemReader<Departamento>();
    reader.setResource(new ClassPathResource("departamentos.csv"));
    reader.setLineMapper(new DefaultLineMapper<Departamento>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"code", "nombre"});
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Departamento>() {{
        setTargetType(Departamento.class);
      }});
    }});
    return reader;
  }

  @Bean
  public DepartamentoItemProcessor processor() {
    return new DepartamentoItemProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<Departamento> writer() {
    JdbcBatchItemWriter<Departamento> writer = new JdbcBatchItemWriter<Departamento>();
    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Departamento>());    writer.setSql("INSERT INTO departamento (code, nom) VALUES (:code, :nom)");
    writer.setDataSource(dataSource);
    return writer;
  }
  // end::readerwriterprocessor[]

  // tag::jobstep[]
  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener) {
    return jobBuilderFactory.get("importUserJob")
          .incrementer(new RunIdIncrementer())
          .listener(listener)
          .flow(step1())
          .end()
          .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
          .<Departamento, Departamento>chunk(10)
          .reader(reader())
          .processor(processor())
          .writer(writer())
          .build();
  }
  // end::jobstep[]
}
// end::code[]