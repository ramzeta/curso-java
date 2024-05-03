package fr.eni.ep3jasp.mongodb.ex2;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// tag::test1[]
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Ex2MongoDBTests {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private CapitalRepository capitalRepository;
  @Before
  public void deleteAllBeforeTests() throws Exception {
    capitalRepository.deleteAll();
  }

// end::test1[]

  // tag::creación[]
  @Test
  public void shouldCreateCapital() throws Exception {
    mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andExpect(
          header().string("Location", containsString("capitales/")));
  }
  // end::creación[]

  // tag::lectura[]
  @Test
  public void shouldRetrieveCapital() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andReturn();
    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
          jsonPath("$.pais").value("Haití")).andExpect(
          jsonPath("$.capital").value("Puerto Príncipe")).andExpect(
          jsonPath("$.continente").value("América"));
  }
  // end::lectura[]

  // tag::busqueda[]
  @Test
  public void shouldQueryCapital() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andReturn();
    mockMvc.perform(
          get("/capitales/search/findByContinente?continente={continente}", "América")).andExpect(
          status().isOk()).andDo(print()).andExpect(
          jsonPath("$._embedded.capital[0].pais").value(
                "Haití"));
  }
  // end::busqueda[]

  // tag::actualizacion[]
  @Test
  public void shouldUpdateCapital() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andReturn();
    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(put(location).content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isNoContent());
    mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
          jsonPath("$.pais").value("Haití")).andExpect(
          jsonPath("$.capital").value("Puerto Príncipe")).andExpect(
          jsonPath("$.continente").value("América"));
  }
  // end::actualizacion[]

  // tag::actualizacionparcial[]
  @Test
  public void shouldPartiallyUpdateCapital() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andReturn();
    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(
          patch(location).content("{\"pais\": \"HAITI\"}")).andExpect(
          status().isNoContent());
    mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
          jsonPath("$.pais").value("HAITI")).andExpect(
          jsonPath("$.capital").value("Puerto Príncipe")).andExpect(
          jsonPath("$.continente").value("América"));
  }
  // end::actualizacionparcial[]

  // tag::suppr[]
  @Test
  public void shouldDeleteCapital() throws Exception {
    MvcResult mvcResult = mockMvc.perform(post("/capitales").content(
          "{\"pais\": \"Haití\", \"capital\":\"Puerto Príncipe\", \"continente\":\"América\"}")).andExpect(
          status().isCreated()).andReturn();
    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(delete(location)).andExpect(status().isNoContent());
    mockMvc.perform(get(location)).andExpect(status().isNotFound());
  }
  // end::suppr[]
}