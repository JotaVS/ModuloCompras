package com.modulodecompras.modulo.Resources;


import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.PedidosService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidosResource {
    @Autowired
    private PedidosService service;
    @GetMapping
    public ResponseEntity<List<Pedido>>fidAll(){
        List<Pedido> list = service.fidAll();
        return  ResponseEntity.ok().body(list);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> finfById(@PathVariable Long id){
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok().body(pedido);

    }
    }

