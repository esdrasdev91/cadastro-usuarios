package com.esdrasdev.cadastro_usuarios.api.converter;

import com.esdrasdev.cadastro_usuarios.api.request.UsuarioRequestDTO;
import com.esdrasdev.cadastro_usuarios.infrastructure.entities.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsuarioUpdateMapper {

    UsuarioUpdateMapper INSTANCE = Mappers.getMapper(UsuarioUpdateMapper.class);

    void updateUsuarioFromDTO(UsuarioRequestDTO usuarioDTO, @MappingTarget UsuarioEntity usuarioEntity);
}
