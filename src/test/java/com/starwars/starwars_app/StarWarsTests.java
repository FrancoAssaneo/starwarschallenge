package com.starwars.starwars_app;

import com.starwars.app.StarwarsAppApplication;
import com.starwars.app.util.JwtUtil;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StarwarsAppApplication.class)
@AutoConfigureMockMvc
public class StarWarsTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    public void setUp() {
        token = jwtUtil.generateToken("franco_test");
    }

    @Test
    public void testGetPeople() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/people/all")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.totalRecords").value(0))
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].uid").value("1"))
                .andExpect(jsonPath("$.results[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.results[0].url").value("https://www.swapi.tech/api/people/1"));
    }

    @Test
    public void testGetPeopleById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/people/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.result.properties.name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.result.properties.height").value("172"))
                .andExpect(jsonPath("$.result.properties.mass").value("77"))
                .andExpect(jsonPath("$.result.properties.gender").value("male"))
                .andExpect(jsonPath("$.result.properties.created").value("2025-01-20T06:45:32.872Z"))
                .andExpect(jsonPath("$.result.properties.edited").value("2025-01-20T06:45:32.872Z"))
                .andExpect(jsonPath("$.result.properties.homeworld").value("https://www.swapi.tech/api/planets/1"))
                .andExpect(jsonPath("$.result.properties.url").value("https://www.swapi.tech/api/people/1"))
                .andExpect(jsonPath("$.result.properties.hair_color").value("blond"))
                .andExpect(jsonPath("$.result.properties.skin_color").value("fair"))
                .andExpect(jsonPath("$.result.properties.eye_color").value("blue"))
                .andExpect(jsonPath("$.result.properties.birth_year").value("19BBY"))
                .andExpect(jsonPath("$.result.description").value("A person within the Star Wars universe"))
                .andExpect(jsonPath("$.result._id").value("5f63a36eee9fd7000499be42"))
                .andExpect(jsonPath("$.result.uid").value("1"))
                .andExpect(jsonPath("$.result.__v").value(0));
    }

    @Test
    public void testGetFilms() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/films/all")
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result[0].description").value("A Star Wars Film"))
                .andExpect(jsonPath("$.result[0]._id").value("5f63a117cf50d100047f9762"))
                .andExpect(jsonPath("$.result[0].uid").value("1"))
                .andExpect(jsonPath("$.result[0].properties.title").value("A New Hope"))
                .andExpect(jsonPath("$.result[0].properties.episode_id").value(4))
                .andExpect(jsonPath("$.result[0].properties.director").value("George Lucas"))
                .andExpect(jsonPath("$.result[0].properties.producer").value("Gary Kurtz, Rick McCallum"))
                .andExpect(jsonPath("$.result[0].properties.release_date").value("1977-05-25"))
                .andExpect(jsonPath("$.result[0].properties.species").isArray())
                .andExpect(jsonPath("$.result[0].properties.species[0]").value("https://www.swapi.tech/api/species/1"))
                .andExpect(jsonPath("$.result[0].properties.starships").isArray())
                .andExpect(jsonPath("$.result[0].properties.starships[0]").value("https://www.swapi.tech/api/starships/2"))
                .andExpect(jsonPath("$.result[0].properties.vehicles").isArray())
                .andExpect(jsonPath("$.result[0].properties.vehicles[0]").value("https://www.swapi.tech/api/vehicles/4"))
                .andExpect(jsonPath("$.result[0].properties.characters").isArray())
                .andExpect(jsonPath("$.result[0].properties.characters[0]").value("https://www.swapi.tech/api/people/1"))
                .andExpect(jsonPath("$.result[0].properties.planets").isArray())
                .andExpect(jsonPath("$.result[0].properties.planets[0]").value("https://www.swapi.tech/api/planets/1"))
                .andExpect(jsonPath("$.result[0].properties.url").value("https://www.swapi.tech/api/films/1"))
                .andExpect(jsonPath("$.result[0].properties.created").value("2025-01-20T06:45:32.863Z"))
                .andExpect(jsonPath("$.result[0].properties.edited").value("2025-01-20T06:45:32.863Z"));
    }

    @Test
    public void testGetFilmById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/films/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.result").isMap())  // Aseguramos que result es un objeto
                .andExpect(jsonPath("$.result._id").value("5f63a117cf50d100047f9762"))
                .andExpect(jsonPath("$.result.uid").value("1"))
                .andExpect(jsonPath("$.result.description").value("A Star Wars Film"))
                .andExpect(jsonPath("$.result.properties.title").value("A New Hope"))
                .andExpect(jsonPath("$.result.properties.episode_id").value(4))
                .andExpect(jsonPath("$.result.properties.opening_crawl").value("It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy...."))
                .andExpect(jsonPath("$.result.properties.director").value("George Lucas"))
                .andExpect(jsonPath("$.result.properties.producer").value("Gary Kurtz, Rick McCallum"))
                .andExpect(jsonPath("$.result.properties.release_date").value("1977-05-25"))
                .andExpect(jsonPath("$.result.properties.species").isArray())
                .andExpect(jsonPath("$.result.properties.species[0]").value("https://www.swapi.tech/api/species/1"))
                .andExpect(jsonPath("$.result.properties.species[1]").value("https://www.swapi.tech/api/species/2"))
                .andExpect(jsonPath("$.result.properties.starships").isArray())
                .andExpect(jsonPath("$.result.properties.starships[0]").value("https://www.swapi.tech/api/starships/2"))
                .andExpect(jsonPath("$.result.properties.starships[1]").value("https://www.swapi.tech/api/starships/3"))
                .andExpect(jsonPath("$.result.properties.vehicles").isArray())
                .andExpect(jsonPath("$.result.properties.vehicles[0]").value("https://www.swapi.tech/api/vehicles/4"))
                .andExpect(jsonPath("$.result.properties.vehicles[1]").value("https://www.swapi.tech/api/vehicles/6"))
                .andExpect(jsonPath("$.result.properties.characters").isArray())
                .andExpect(jsonPath("$.result.properties.characters[0]").value("https://www.swapi.tech/api/people/1"))
                .andExpect(jsonPath("$.result.properties.characters[1]").value("https://www.swapi.tech/api/people/2"))
                .andExpect(jsonPath("$.result.properties.planets").isArray())
                .andExpect(jsonPath("$.result.properties.planets[0]").value("https://www.swapi.tech/api/planets/1"))
                .andExpect(jsonPath("$.result.properties.planets[1]").value("https://www.swapi.tech/api/planets/2"))
                .andExpect(jsonPath("$.result.properties.url").value("https://www.swapi.tech/api/films/1"))
                .andExpect(jsonPath("$.result.properties.created").value("2025-01-20T06:45:32.863Z"))
                .andExpect(jsonPath("$.result.properties.edited").value("2025-01-20T06:45:32.863Z"));
    }

    @Test
    public void testGetVehicles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/all")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.totalRecord").value(0))
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.previous").value(nullValue()))
                .andExpect(jsonPath("$.next").value("https://www.swapi.tech/api/vehicles?page=2&limit=10"))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].uid").value("4"))
                .andExpect(jsonPath("$.results[0].name").value("Sand Crawler"))
                .andExpect(jsonPath("$.results[0].url").value("https://www.swapi.tech/api/vehicles/4"));
    }

    @Test
    public void testGetVehicleById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/4")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.result").isMap())
                .andExpect(jsonPath("$.result._id").value("5f63a160cf50d100047f97fc"))
                .andExpect(jsonPath("$.result.uid").value("4"))
                .andExpect(jsonPath("$.result.description").value("A vehicle"))
                .andExpect(jsonPath("$.result.properties.name").value("Sand Crawler"))
                .andExpect(jsonPath("$.result.properties.model").value("Digger Crawler"))
                .andExpect(jsonPath("$.result.properties.vehicle_class").value("wheeled"))
                .andExpect(jsonPath("$.result.properties.manufacturer").value("Corellia Mining Corporation"))
                .andExpect(jsonPath("$.result.properties.cost_in_credits").value("150000"))
                .andExpect(jsonPath("$.result.properties.lenght").value(nullValue()))
                .andExpect(jsonPath("$.result.properties.crew").value("46"))
                .andExpect(jsonPath("$.result.properties.passengers").value("30"))
                .andExpect(jsonPath("$.result.properties.max_atmosphering_speed").value("30"))
                .andExpect(jsonPath("$.result.properties.cargo_capacity").value("50000"))
                .andExpect(jsonPath("$.result.properties.consumables").value("2 months"))
                .andExpect(jsonPath("$.result.properties.films").isEmpty())
                .andExpect(jsonPath("$.result.properties.pilots").isEmpty())
                .andExpect(jsonPath("$.result.properties.url").value("https://www.swapi.tech/api/vehicles/4"))
                .andExpect(jsonPath("$.result.properties.created").value("2020-09-17T17:46:31.415Z"))
                .andExpect(jsonPath("$.result.properties.edited").value("2020-09-17T17:46:31.415Z"));

    }

    @Test
    public void testGetStarships() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/starships/all")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.totalRecords").value(0))
                .andExpect(jsonPath("$.totalPages").value(0))
                .andExpect(jsonPath("$.previous").value(nullValue()))
                .andExpect(jsonPath("$.next").value("https://www.swapi.tech/api/starships?page=2&limit=10"))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results[0].uid").value("2"))
                .andExpect(jsonPath("$.results[0].name").value("CR90 corvette"))
                .andExpect(jsonPath("$.results[0].url").value("https://www.swapi.tech/api/starships/2"));
    }

    @Test
    public void testGetStarshipById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/starships/2")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("ok"))
                .andExpect(jsonPath("$.result").isMap())
                .andExpect(jsonPath("$.result._id").value("5f63a34fee9fd7000499be1e"))
                .andExpect(jsonPath("$.result.uid").value("2"))
                .andExpect(jsonPath("$.result.description").value("A Starship"))
                .andExpect(jsonPath("$.result.properties.model").value("CR90 corvette"))
                .andExpect(jsonPath("$.result.properties.starship_class").value("corvette"))
                .andExpect(jsonPath("$.result.properties.manufacturer").value("Corellian Engineering Corporation"))
                .andExpect(jsonPath("$.result.properties.cost_in_credits").value("3500000"))
                .andExpect(jsonPath("$.result.properties.lenght").value(nullValue()))
                .andExpect(jsonPath("$.result.properties.crew").value("30-165"))
                .andExpect(jsonPath("$.result.properties.passengers").value("600"))
                .andExpect(jsonPath("$.result.properties.max_atmosphering_speed").value("950"))
                .andExpect(jsonPath("$.result.properties.hyperdrive_rating").value("2.0"))
                .andExpect(jsonPath("$.result.properties.mglt").value(nullValue()))
                .andExpect(jsonPath("$.result.properties.cargo_capacity").value("3000000"))
                .andExpect(jsonPath("$.result.properties.consumables").value("1 year"))
                .andExpect(jsonPath("$.result.properties.pilots").isEmpty());

    }
}
