package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository repository;
    @Transactional(readOnly = true)
    public List<Pedido> fidAll(){
        return repository.findAll();
    }
}
