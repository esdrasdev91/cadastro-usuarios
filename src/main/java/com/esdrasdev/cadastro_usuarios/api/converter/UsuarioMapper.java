package com.esdrasdev.cadastro_usuarios.api.converter;

import com.esdrasdev.cadastro_usuarios.api.request.UsuarioRequestDTO;
import com.esdrasdev.cadastro_usuarios.api.response.UsuarioResponseDTO;
import com.esdrasdev.cadastro_usuarios.infrastructure.entities.UsuarioEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario);

    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioRequestDTO usuario);

    List<UsuarioResponseDTO> paraListaUsuarioResponseDTO(List<UsuarioEntity> usuarios);


}
