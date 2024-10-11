package com.esdrasdev.cadastro_usuarios.business;

import com.esdrasdev.cadastro_usuarios.api.request.UsuarioRequestDTO;
import com.esdrasdev.cadastro_usuarios.api.response.UsuarioResponseDTO;
import com.esdrasdev.cadastro_usuarios.infrastructure.entities.UsuarioEntity;
import com.esdrasdev.cadastro_usuarios.infrastructure.exceptions.BusinessException;
import com.esdrasdev.cadastro_usuarios.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioUpdateMapper usuarioUpdateMapper;
    private final UsuarioMapper usuarioMapper;


    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.saveAndFlush(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    public UsuarioResponseDTO atualizaCadastro(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuario = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
            usuarioUpdateMapper.updateUsuarioFromDTO(usuarioRequestDTO, usuario);
            return usuarioMapper.paraUsuarioResponseDTO(salvaUsuario(usuario));
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
        UsuarioEntity entity = usuarioRepository.selectFromEmail(email);

        return entity != null ? usuarioMapper.paraUsuarioResponseDTO(entity) : null;
    }

    public List<UsuarioResponseDTO> buscaPorIniciais(String iniciais){
        List<UsuarioEntity> entity = usuarioRepository.findByNomeOrEmailStartingWith(iniciais);

        return entity.isEmpty() ? Collections.emptyList() : usuarioMapper.paraListaUsuarioResponseDTO(entity);
    }

    public void fazUpdateDeEmail(String email, String documento) {
        usuarioRepository.updateEmail(documento, email);
    }

    public void deletaDadosUsuario(String email) {

        usuarioRepository.deleteByEmail(email);

    }

}
