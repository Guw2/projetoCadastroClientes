package com.lorian.projetocadastrocliente.services;

import com.lorian.projetocadastrocliente.DTOs.ClienteDTO;
import com.lorian.projetocadastrocliente.entities.Cliente;
import com.lorian.projetocadastrocliente.repositories.ClienteRepository;
import com.lorian.projetocadastrocliente.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
        if (!repository.findById(id).isPresent()){
            throw new ResourceNotFoundException("Resource Not Found Error.");
        }else{
            Optional<Cliente> cliente = repository.findById(id);
            return cliente.map(c -> new ClienteDTO(c)).orElse(null);
        }
    }

    public ClienteDTO insert(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        DtoToEntity(clienteDTO, cliente);
        repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO){
        try{
            Cliente entity = repository.getReferenceById(id);
            DtoToEntity(clienteDTO, entity);
            repository.save(entity);
            return new ClienteDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Resource Not Found Error.");
        }
    }

    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resource Not Found Error.");
        }else {
            repository.deleteById(id);
        }
    }

    private void DtoToEntity(ClienteDTO dto, Cliente entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setChildren(dto.getChildren());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
    }

}
