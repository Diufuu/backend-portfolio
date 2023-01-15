package com.app.myportfolio.service;

import com.app.myportfolio.entity.Usuario;
import com.app.myportfolio.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping ( path = "/buscar" )
    public List <Usuario> GetAllUsuario(){
        return usuarioRepository.findAll();
    }
    
    @PostMapping ( path = "/guardar" )
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    @DeleteMapping ( path = "/eliminar/{usuarioid}" )
    public void deleteUsuario(@PathVariable ("usuarioid") Integer usuarioid) {
        Optional <Usuario> usuario;
        usuario = usuarioRepository.findById(usuarioid);
        if(usuario.isPresent()){
            usuarioRepository.delete(usuario.get());
        }
    }
    
    @PostMapping ( path = "/login")
    public Usuario login(@RequestBody Usuario usuario){
        
        List<Usuario> usuarios = usuarioRepository.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
        
        if(!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        
        return null;
    }
}