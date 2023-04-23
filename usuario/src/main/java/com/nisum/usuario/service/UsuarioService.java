
package com.nisum.usuario.service;

import com.nisum.usuario.model.Usuario;
import com.nisum.usuario.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class UsuarioService {
    
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    public Usuario findByName(String name) {
        return repository.findByName(name);
    }
    
    public Usuario get(String userId) {
        return repository.findById(userId);
    }

    public List<Usuario> list() {
        Iterable<Usuario> users = repository.findAll();
        List<Usuario> list = new ArrayList<Usuario>();
        users.forEach(list::add);
        return list;
    }

    public Usuario create(Usuario user) {
        return repository.save(user);
    }
}
