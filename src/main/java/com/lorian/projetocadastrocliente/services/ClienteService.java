package com.lorian.projetocadastrocliente.services;

import com.lorian.projetocadastrocliente.DTOs.ClienteDTO;
import com.lorian.projetocadastrocliente.entities.Cliente;
import com.lorian.projetocadastrocliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public Page<ClienteDTO> findAllPage(PageRequest pageRequest){
        Page<Cliente> list = repository.findAll(pageRequest);
        return list.map(x -> new ClienteDTO(x));
    }

    public ClienteDTO findById(Long id){
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.map(c -> new ClienteDTO(c)).orElse(null);
    }

}
