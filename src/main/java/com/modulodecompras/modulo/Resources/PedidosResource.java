package com.modulodecompras.modulo.Resources;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.PedidosService;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidosResource {
    @Autowired
    private PedidosService pedidoService;


    @PostMapping
    public ResponseEntity<ResponseEntity<String>> salvarPedido(@RequestBody Pedido pedido) {
        ResponseEntity<String> pedidoSalvo = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(pedidoSalvo);
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }






    @GetMapping(value = "/statusPedido/{id}")
    public ResponseEntity pegarStatuspedido(@RequestBody PedidoDTO dto) throws Exception {

        URL url = new URL("ffs" + dto.getCodPedido() + "ddd");
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String status = "";
        StringBuilder jsonPedido = new StringBuilder();
        while ((status = br.readLine()) != null) {
            jsonPedido.append(status);
        }

        return null;
    }
}