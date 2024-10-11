package com.esdrasdev.cadastro_usuarios.api.response;

public record UsuarioResponseDTO(
        Long id,

        String nome,

        String email,

        String documento,

        EnderecoResponseDTO endereco) {

}
