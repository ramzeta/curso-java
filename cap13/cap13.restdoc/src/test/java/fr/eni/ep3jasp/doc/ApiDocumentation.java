package fr.eni.ep3jasp.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.RequestDispatcher;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDocumentation {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

	@Autowired
	private CiudadRepository ciudadRepository;

	@Autowired
	private InformationRepository informationRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void errorExample() throws Exception {
		this.mockMvc
				.perform(get("/error")
						.requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400)
						.requestAttr(RequestDispatcher.ERROR_REQUEST_URI,
								"/ciudades")
						.requestAttr(RequestDispatcher.ERROR_MESSAGE,
								"La información 'http://localhost:8080/informations/123' no existe"))
				.andDo(print()).andExpect(status().isBadRequest())
				.andExpect(jsonPath("error", is("Bad Request")))
				.andExpect(jsonPath("timestamp", is(notNullValue())))
				.andExpect(jsonPath("status", is(400)))
				.andExpect(jsonPath("path", is(notNullValue())))
				.andDo(document("error-example",
						responseFields(
								fieldWithPath("error").description("El error HTTP que se produce, es decir, `Bad Request`"),
								fieldWithPath("message").description("Descripción de la causa del error"),
								fieldWithPath("path").description("El path con el que la consulta se ha hecho"),
								fieldWithPath("status").description("El código estador HTTP, es decir, `400`"),
								fieldWithPath("timestamp").description("El tiempo, en milisegundos que ha tardado en producirse el error"))));
	}

	@Test
	public void indexExample() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andDo(document("index-example",
					links(
							linkWithRel("ciudades").description("Las ciudades <<resources-ciudades,Ciudades resource>>"),
							linkWithRel("informations").description("las informaciones <<resources-informations,Informations resource>>"),
							linkWithRel("profile").description("El profile ALPS para el servicio")),
					responseFields(
							subsectionWithPath("_links").description("<<resources-index-links,Links>> hacia el resto de recursos"))));

	}

	@Test
	public void ciudadesListExample() throws Exception {
		this.ciudadRepository.deleteAll();

		createCiudad("París",
				"Capital de Francia");
		createCiudad("Madrid",
				"Capital de España");
		createCiudad("Barcelona", "Ciudad bañada por el mar Mediterraneo");

		this.mockMvc.perform(get("/ciudades"))
			.andExpect(status().isOk())
			.andDo(document("ciudades-list-example",
					links(
							linkWithRel("self").description("Enlace canónico para este recurso"),
							linkWithRel("profile").description("El profile ALPS para este recurso")),
					responseFields(
							subsectionWithPath("_embedded.ciudades").description("Una lista de <<resources-ciudad, Ciudad resources>>"),
							subsectionWithPath("_links").description("<<resources-informations-list-links, Links>> hacia otros recursos"))));
	}

	@Test
	public void ciudadesCreateExample() throws Exception {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "Grand");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> ciudad = new HashMap<String, Object>();
		ville.put("nombre", "París");
		ville.put("body", "Capital de Francia");
		ville.put("informations", Arrays.asList(informationLocation));

		this.mockMvc.perform(
				post("/ciudades").contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(ciudad))).andExpect(
				status().isCreated())
				.andDo(document("ciudades-create-example",
						requestFields(
									fieldWithPath("nombre").description("El nombre de la ciudad"),
									fieldWithPath("body").description("La descripción de la ciudad"),
									fieldWithPath("informations").description("Una lista de información"))));
	}

	@Test
	public void ciudadGetExample() throws Exception {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "París");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> ciudad = new HashMap<String, Object>();
		ville.put("nombre", "París");
		ville.put("body", "Capital de Francia");
		ville.put("informations", Arrays.asList(informationLocation));

		String ciudadLocation = this.mockMvc
				.perform(
						post("/ciudades").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(ciudad)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(ciudadLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("nombre", is(ciudad.get("nombre"))))
			.andExpect(jsonPath("body", is(ciudad.get("body"))))
			.andExpect(jsonPath("_links.self.href", is(ciudadLocation)))
			.andExpect(jsonPath("_links.informations", is(notNullValue())))
			.andDo(print())
			.andDo(document("ciudad-get-example",
					links(
							linkWithRel("self").description("Enlace canónico hacia este <<resources-ciudad,ciudad>>"),
							linkWithRel("ciudad").description("Esta <<resources-ciudad,ciudad>>"),
							linkWithRel("informations").description("La información de esta ciudad")),
					responseFields(
							fieldWithPath("nombre").description("El nombre de la ciudad"),
							fieldWithPath("body").description("La descripción de la ciudad"),
							subsectionWithPath("_links").description("<<resources-ciudad-links,Links>> hacia otros recursos"))));
	}

	@Test
	public void informationsListExample() throws Exception {
		this.ciudadRepository.deleteAll();
		this.informationRepository.deleteAll();

		createInformation("París");
		createInformation("Madrid");
		createInformation("Barcelona");

		this.mockMvc.perform(get("/informations"))
			.andExpect(status().isOk())
			.andDo(document("informations-list-example",
					links(
							linkWithRel("self").description("Enlace conónico para este recurso"),
							linkWithRel("profile").description("El profile ALPS de este recurso")),
					responseFields(
							subsectionWithPath("_embedded.informations").description("Una lista de <<resources-information,Information resources>>"),
							subsectionWithPath("_links").description("<<resources-informations-list-links, Links>> hacia otros recursos"))));
	}

	@Test
	public void informationsCreateExample() throws Exception {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "París");

		this.mockMvc.perform(
				post("/informations").contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated())
				.andDo(document("informations-create-example",
						requestFields(
								fieldWithPath("nombre").description("El nombre de la información"))));
	}

	@Test
	public void ciudadUpdateExample() throws Exception {
		Map<String, Object> ciudad = new HashMap<String, Object>();
		ville.put("nombre", "París");
		ville.put("body", "Capital de Francia");

		String ciudadLocation = this.mockMvc
				.perform(
						post("/ciudades").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(ciudad)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(ciudadLocation)).andExpect(status().isOk())
				.andExpect(jsonPath("nombre", is(ciudad.get("nombre"))))
				.andExpect(jsonPath("body", is(ciudad.get("body"))))
				.andExpect(jsonPath("_links.self.href", is(ciudadLocation)))
				.andExpect(jsonPath("_links.informations", is(notNullValue())));

		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "París");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> ciudadUpdate = new HashMap<String, Object>();
		ciudadUpdate.put("informations", Arrays.asList(informationLocation));

		this.mockMvc.perform(
				patch(ciudadLocation).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(ciudadUpdate)))
				.andExpect(status().isNoContent())
				.andDo(document("ciudad-update-example",
						requestFields(
								fieldWithPath("nombre").description("El nombre de la ciudad").type(JsonFieldType.STRING).optional(),
								fieldWithPath("body").description("La descripción de la ciudad").type(JsonFieldType.STRING).optional(),
								fieldWithPath("informations").description("Un lista de información").optional())));
	}

	@Test
	public void informationGetExample() throws Exception {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "París");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		this.mockMvc.perform(get(informationLocation))
			.andExpect(status().isOk())
			.andExpect(jsonPath("nombre", is(information.get("nombre"))))
			.andDo(document("information-get-example",
					links(
							linkWithRel("self").description("El nombre canónico de este  <<resources-information,information>>"),
							linkWithRel("information").description("Esta <<resources-information,information>>"),
							linkWithRel("ciudades").description("La <<resources-informationged-ciudades,ciudades>> que tiene esta información")),
					responseFields(
							fieldWithPath("nombre").description("El nombre de la información"),
							subsectionWithPath("_links").description("<<resources-information-links,Links>> hacia otros recursos"))));
	}

	@Test
	public void informationUpdateExample() throws Exception {
		Map<String, String> information = new HashMap<String, String>();
		information.put("nombre", "París");

		String informationLocation = this.mockMvc
				.perform(
						post("/informations").contentType(MediaTypes.HAL_JSON).content(
								this.objectMapper.writeValueAsString(information)))
				.andExpect(status().isCreated()).andReturn().getResponse()
				.getHeader("Location");

		Map<String, Object> informationUpdate = new HashMap<String, Object>();
		informationUpdate.put("nombre", "Madrid");

		this.mockMvc.perform(
				patch(informationLocation).contentType(MediaTypes.HAL_JSON).content(
						this.objectMapper.writeValueAsString(informationUpdate)))
				.andExpect(status().isNoContent())
				.andDo(document("information-update-example",
						requestFields(
								fieldWithPath("nombre").description("El nombre de la información"))));
	}

	private void createCiudad(String nom, String body) {
		Ciudad ciudad = new Ciudad();
		ville.setNombre(nombre);
		ville.setBody(body);

		this.ciudadRepository.save(ciudad);
	}

	private void createInformation(String nombre) {
		Information information = new Information();
		information.setNombre(nombre);
		this.informationRepository.save(information);
	}
}
