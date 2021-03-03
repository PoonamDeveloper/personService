package com.embl.people.repo;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.embl.people.repo.PersonRepository;


/**
 * Test Class to test PersonRepository CRUD methods
 * @author poonam
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class PersonRepositoryTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonRepository personRepository;

  @BeforeEach
  public void deleteAllBeforeTests() throws Exception {
      personRepository.deleteAll();
  }

  @Test
  public void shouldReturnRepositoryIndex() throws Exception {
      mockMvc.perform(get("/")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.people").exists());
  }

  @Test
  public void shouldCreateEntity() throws Exception {
      mockMvc.perform(post("/people")
        .content("{\"firstName\": \"Boris\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", containsString("people/")));
  }

  @Test
  public void shouldRetrieveEntity() throws Exception {
      MvcResult mvcResult = mockMvc.perform(post("/people")
        .content("{\"firstName\": \"Boris\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated()).andReturn();

      String location = mvcResult.getResponse().getHeader("Location");
      mockMvc.perform(get(location))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Boris"))
        .andExpect(jsonPath("$.lastName").value("Johnson"));
  }

  @Test
  public void searchByFirstName() throws Exception {
      mockMvc.perform(post("/people")
        .content("{ \"firstName\": \"Boris\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated());

      mockMvc.perform(get("/people/search/findByFirstName?firstName={lastName}", "Boris"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.people[0].lastName").value("Johnson"));
  }

  @Test
  public void searchByLastName() throws Exception {
      mockMvc.perform(post("/people")
        .content("{ \"firstName\": \"Boris\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated());

      mockMvc.perform(get("/people/search/findByLastName?lastName={lastName}", "Johnson"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.people[0].firstName").value("Boris"));
  }

  @Test
  public void searchByAge() throws Exception {
      mockMvc.perform(post("/people")
          .content("{\"firstName\": \"Boris\","
              + " \"lastName\":\"Johnson\","
              + " \"age\":\"60\","
              + " \"favColour\":\"Blue\""
              + "}"))
        .andExpect(status().isCreated());

      mockMvc.perform(get("/people/search/findByAge?age={age}", "60"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.people[0].firstName").value("Boris"));
  }
  
  @Test
  public void searchByFavColour() throws Exception {
      mockMvc.perform(post("/people")
          .content("{\"firstName\": \"Boris\","
              + " \"lastName\":\"Johnson\","
              + " \"age\":\"60\","
              + " \"favColour\":\"Blue\""
              + "}"))
        .andExpect(status().isCreated());

      mockMvc.perform(get("/people/search/findByFavColour?favColour={favColour}", "Blue"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.people[0].firstName").value("Boris"));
  }
  
  @Test
  public void shouldUpdateEntity() throws Exception {
      MvcResult mvcResult = mockMvc.perform(post("/people")
        .content("{\"firstName\": \"Boris\","
              + " \"lastName\":\"Johnson\","
              + " \"age\":\"60\","
              + " \"favColour\":\"Blue\""
              + "}"))
        .andExpect(status().isCreated()).andReturn();

      String location = mvcResult.getResponse().getHeader("Location");
      mockMvc.perform(put(location)
        .content("{\"firstName\": \"Bilbo\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isNoContent());

      mockMvc.perform(get(location))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.firstName").value("Bilbo"))
              .andExpect(jsonPath("$.lastName").value("Johnson"))
              .andExpect(jsonPath("$.age").value("0") /*Replaced with default values*/
             );
  }

  @Test
  public void shouldPartiallyUpdateEntity() throws Exception {
      MvcResult mvcResult = mockMvc.perform(post("/people")
        .content("{\"firstName\": \"Boris\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated()).andReturn();
      String location = mvcResult.getResponse().getHeader("Location");
      mockMvc.perform(patch(location)
        .content("{\"firstName\": \"Bilbo Jr.\"}"))
        .andExpect(status().isNoContent());

      mockMvc.perform(get(location)).andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Bilbo Jr."))
        .andExpect(jsonPath("$.lastName").value("Johnson"));
  }

  @Test
  public void shouldDeleteEntity() throws Exception {
      MvcResult mvcResult = mockMvc.perform(post("/people")
        .content("{ \"firstName\": \"Bilbo\", \"lastName\":\"Johnson\"}"))
        .andExpect(status().isCreated()).andReturn();

      String location = mvcResult.getResponse().getHeader("Location");
      mockMvc.perform(delete(location)).andExpect(status().isNoContent());

      mockMvc.perform(get(location)).andExpect(status().isNotFound());
  }

}
