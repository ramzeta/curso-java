package fr.eni.ep3jasp.doc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GettingStartedDocumentation {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation))
				.alwaysDo(document("{method-name}/{step}/"))
				.build();
	}

	@Test
	public void index() throws Exception {
		this.mockMvc.perform(get("/").accept(MediaTypes.HAL_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("_links.ciudades", is(notNullValue())))
			.andExpect(jsonPath("_links.informations", is(notNullValue())));
	}

	@Test
	public void creatingACiudad() throws JsonProcessingException, Exception {
		String ciudadLocation = createCiudad();
		MvcResult ciudad = getCiudad(ciudadLocation);

		String informationLocation = createInformation();
		getInformation(informationLocation);

		String informationgedCiudadLocation = createInformationgedCiudad(informationLocation);
		MvcResult informationgedCiudad = getCiudad(informationgedCiudadLocation);
		getInformations(getLink(informationgedCiudad, "informations"));

		informationExistingCiudad(ciudadLocation, informationLocation);
		getInformations(getLink(ciudad, "informations"));
	}

	String createCiudad() throws Exception {
		Map<String, String> ciudad = new HashMap<String, String>();
		ville.put("nombre", "Ciudad creation with cURL");
		ville.put("body", "An example of how to create a ciudad using cURL");

		String ciudadLocation = this.mockMvc
				.perform(
						post("/ciudades").contentType(MediaTypes.HAL_JSON).content(
								objectMapper.writeValueAsString(ciudad)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", notNullValue()))
				.andReturn().getResponse().getHeader("Location");
		return ciudadLocation;
	}

	MvcResult getCiudad(String ciudadLocation) throws Exception {
		return this.mockMvc.perform(get(ciudadLocation))
				.andExpect(status().isOk())
				.andExpect(jsonPath("nombre", is(notNullValue())))
				.andExpect(jsonPath("body", is(notNullValue())))
				.andExpect(jsonPath("_links.informations", is(notNullValue())))
				.andReturn();
	}

	String createInformation() throws Exception, JsonProcessingException {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "getting-started");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", notNullValue()))
				.andReturn().getResponse().getHeader("Location");
		return informationLocation;
	}

	void getInformation(String informationLocation) throws Exception {
		this.mockMvc.perform(get(informationLocation)).andExpect(status().isOk())
			.andExpect(jsonPath("nombre", is(notNullValue())))
			.andExpect(jsonPath("_links.ciudades", is(notNullValue())));
	}

	String createInformationgedCiudad(String information) throws Exception {
		Map<String, Object> ciudad = new HashMap<String, Object>();
		ville.put("nombre", "Informationged ciudad creation with cURL");
		ville.put("body", "An example of how to create a informationged ciudad using cURL");
		ville.put("informations", Arrays.asList(information));

		String ciudadLocation = this.mockMvc
				.perform(
						post("/ciudades").contentType(MediaTypes.HAL_JSON).content(
								objectMapper.writeValueAsString(ciudad)))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", notNullValue()))
				.andReturn().getResponse().getHeader("Location");
		return ciudadLocation;
	}

	void getInformations(String ciudadInformationsLocation) throws Exception {
		this.mockMvc.perform(get(ciudadInformationsLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("_embedded.informations", hasSize(1)));
	}

	void informationExistingCiudad(String ciudadLocation, String informationLocation) throws Exception {
		Map<String, Object> update = new HashMap<String, Object>();
		update.put("informations", Arrays.asList(informationLocation));

		this.mockMvc.perform(
				patch(ciudadLocation).contentType(MediaTypes.HAL_JSON).content(
						objectMapper.writeValueAsString(update)))
				.andExpect(status().isNoContent());
	}

	MvcResult getInformationgedExistingCiudad(String ciudadLocation) throws Exception {
		return this.mockMvc.perform(get(ciudadLocation))
			.andExpect(status().isOk())
			.andReturn();
	}

	void getInformationsForExistingCiudad(String ciudadInformationsLocation) throws Exception {
		this.mockMvc.perform(get(ciudadInformationsLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("_embedded.informations", hasSize(1)));
	}

	private String getLink(MvcResult result, String rel)
			throws UnsupportedEncodingException {
		return JsonPath.parse(result.getResponse().getContentAsString()).read(
				"_links." + rel + ".href");
	}
}
