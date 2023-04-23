package com.nisum.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.usuario.controller.JwtAuthenticationController;
import com.nisum.usuario.model.Telefono;
import com.nisum.usuario.model.Usuario;
import com.nisum.usuario.repository.UsuarioRepository;
import com.nisum.usuario.service.JwtUsuarioDetailsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class UsuarioApplicationTests {


  @Autowired
  private JwtAuthenticationController lookupController;
    
  @Autowired
  UsuarioRepository repository;
  
  @Autowired
  JwtUsuarioDetailsService  jwtUsuarioService;

  @DisplayName("se obtiene token con usuario y password correctos")
    @Test
   public  void caso1() throws Exception{     
        Telefono phone = new Telefono();
        phone.setCitycode("1");
        phone.setNumber("9876512454");
        phone.setContrycode("59");
        List<Telefono> phones = new ArrayList<Telefono>();
        phones.add(phone);

        Usuario user = new Usuario();
        user.setName("Mauricio Vigil");
        user.setPassword("12345678");
        user.setEmail("mau@dominio.cl");

        user.setCreated(new Date());
                            user.setToken("gfhjgfhGSDGYQTRYQTR");
                            user.setLast_login(new Date());
                            user.setIsactive(true);
        user.setPhones(phones);
        Usuario newUser = repository.save(user);
        assertEquals(newUser.getName(), user.getName());
    }
  
   @DisplayName("se obtiene token con usuario y password correctos y email invalido")
    @Test
   public  void caso2() throws Exception{     
        Telefono phone = new Telefono();
        phone.setCitycode("1");
        phone.setNumber("9876512454");
        phone.setContrycode("59");
        List<Telefono> phones = new ArrayList<>();
        phones.add(phone);

        Usuario user = new Usuario();
        user.setName("Mauricio Vigil 2");
        user.setPassword("12345678");
        user.setEmail("abc@dominiox.cl");

        user.setCreated(new Date());
                            user.setToken("gfhjgfhGSDGYQTRYQTR");
                            user.setLast_login(new Date());
                            user.setIsactive(true);
        user.setPhones(phones);
        Usuario newUser ;
        try {
           
           newUser = repository.save(user);
       } catch (Exception e) {
           newUser = new Usuario();
           newUser.setEmail("");
           // System.out.println(e.getMessage());
       }
        
         //assertEquals(user.getEmail(),newUser.getEmail());
        assertEquals(newUser.getEmail() , "" );
    }
   
   @DisplayName("se obtiene token con usuario y password correctos y password invalido")
    @Test
   public  void caso3() throws Exception{     
        Telefono phone = new Telefono();
        phone.setCitycode("1");
        phone.setNumber("9876512454");
        phone.setContrycode("59");
        List<Telefono> phones = new ArrayList<>();
        phones.add(phone);

        Usuario user = new Usuario();
        user.setName("Mauricio Vigil 3");
        user.setPassword("1234");
        user.setEmail("xyz@dominio.cl");

        user.setCreated(new Date());
                            user.setToken("gfhjgfhGSDGYQTRYQTR");
                            user.setLast_login(new Date());
                            user.setIsactive(true);
        user.setPhones(phones);
        Usuario newUser ;
        try {
           newUser = repository.save(user);
           System.out.println(newUser.getEmail());
       } catch (Exception e) {
            newUser = new Usuario();
           newUser.setEmail("");
       }
         //assertEquals(user.getEmail(),newUser.getEmail());
        assertEquals(newUser.getEmail(), "");
    }
    
    private static String asJsonString(final Object obj) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
