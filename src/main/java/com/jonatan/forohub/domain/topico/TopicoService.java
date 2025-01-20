package com.jonatan.forohub.domain.topico;

import com.jonatan.forohub.domain.PropietarioService;
import com.jonatan.forohub.infra.errores.UnauthorizedException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class TopicoService {

    @Autowired
    private final TopicoRepository topicRepository;
    @Autowired
    PropietarioService propietarioService;

    public TopicoService(TopicoRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topico updateTopic(Long topicId, DatosModificacionTopico datos) {
        // Obtener el tópico y verificar permisos
        Topico topic = getAuthorizedTopico(topicId);

        // Actualizar el tópico
        topic.modificar(datos);
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long topicId) {
        // Obtener el tópico y verificar permisos
        Topico topic = getAuthorizedTopico(topicId);
        // Eliminar el tópico
        topicRepository.delete(topic);
    }

    private Topico getAuthorizedTopico(Long topicId) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        // Buscar el tópico por ID
        Topico topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        // Verificar si el usuario es el autor o tiene el rol de administrador
        boolean isAuthor = topic.getAutor().equals(currentUsername);
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAuthor && !isAdmin) {
            throw new UnauthorizedException("Acceso denegado");
        }

        return topic;
    }
}