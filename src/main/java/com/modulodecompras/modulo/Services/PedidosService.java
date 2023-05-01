package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository repository;
    @Transactional(readOnly = true)
    public List<Pedido> fidAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Pedido findById(Long id){
        Optional<Pedido> obj= repository.findById(id);
        Pedido entity = obj.orElseThrow(()-> new EntityNotFoundException("entity not found"));
        return entity;
    }
}
