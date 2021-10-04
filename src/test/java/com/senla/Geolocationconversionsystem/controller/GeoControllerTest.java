package com.senla.Geolocationconversionsystem.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class GeoControllerTest {

  @Autowired
  private MockMvc mockMvc;


  @Test
  void getPosition() throws Exception {
    this.mockMvc.perform(get("/position/Москва, ул. Льва Толстого, 16")).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().string(containsString("Longitude: 37.58829; Latitude: 55.73398")));
  }

  @Test
  void getAddress() throws Exception {
    this.mockMvc.perform(get("/address/53.89079,27.525773")).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().string(containsString("проспект Дзержинского 5, 220036 Мінск, Беларусь")));
  }

  @Test
  void checkIncorrectDataForPosition() throws Exception{
    this.mockMvc.perform(get("/position/adgdt45 56 ghjfy")).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().string(containsString("Неверные данные. Проверьте правильность введенного адреса.")));
  }

  @Test
  void checkIncorrectDataForAddress() throws Exception{
    this.mockMvc.perform(get("/address/999.3456,563.9987")).
            andDo(print()).
            andExpect(status().isOk()).
            andExpect(content().string(containsString("Неверные данные. Проверьте правильность введенных координат.")));
  }
}