package pe.edu.galaxy.training.java.ms.gestion.talleres.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.edu.galaxy.training.java.ms.gestion.talleres.controller.common.Response;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerDTO;
import pe.edu.galaxy.training.java.ms.gestion.talleres.dto.TallerSituacionDTO;

public class TallerControllerTest {

	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Autowired
	private ObjectMapper objectMapper;
	
	private TallerDTO defaultTallerDTO;

	@BeforeEach
    public void setup() {
		defaultTallerDTO = TallerDTO
				.builder()
				.nombre("Tx Demo Controller")
				.descripcion("Taller Test Descripción")
				.duracion(10)
				.idInstructor(1)
				.tallerSituacion(TallerSituacionDTO.builder().id(1L).build())
				.estado("1")
				.build();
    }
	
	//@Test
	public void testFindAll() throws Exception 
	{
	  this.mockMvc.perform(
	      get("/talleres/v1")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.codigo").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].id").isNotEmpty());
	}
	
	
	//@Test 
	  public void testShouldReturnCreatedWhenValidTaller() throws Exception{
	  this.mockMvc
	  		.perform(
			 post("/talleres/v1")
			  	.content(objectMapper.writeValueAsString(defaultTallerDTO))
			  	.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
	  			.andExpect(status().isCreated())
	  			.andExpect(MockMvcResultMatchers.jsonPath("$.data.nombre").value(defaultTallerDTO.getNombre())); 
	  }
	 
	
	
	  @SuppressWarnings("deprecation")
	@Test 
	  public void testShouldFindTallerWhenExists() throws Exception { 
		  Long id = 31L; 
		  defaultTallerDTO.setId(id);
		  defaultTallerDTO.getTallerSituacion().setNombre("PROGRAMADO");
		  
		  Response response= Response.builder()
				  			.codigo(1)
				  			.mensaje("Exito al consultar el taller con el "+id)
				  			.data(defaultTallerDTO)
				  			.build();
		  this.mockMvc
		  		.perform(get("/talleres/v1/" + id)
		  		.contentType(MediaType.APPLICATION_JSON)
		  		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		  		.andExpect(status().isOk())
		  		.andExpect(MockMvcResultMatchers.content().string(Matchers.is(objectMapper.writeValueAsString(response)))); 
	  }
}
