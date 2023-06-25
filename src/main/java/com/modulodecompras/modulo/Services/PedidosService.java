package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.ItemPedido;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Repository.PedidoRepository;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import com.modulodecompras.modulo.Repository.ProdutoRepository;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.dao.PedidoDao2;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository pedidoRepository;

    @Autowired
    private PedidoDao2 pedDao;

    @Autowired
    private ProdutoService prodServ;

    @Autowired ItemPedidoService ipServ;
    @Autowired
    private ProdutoRepository produtoRepository;




    public Pedido findMaiorValorID() {
        Optional<Pedido> optionalPedido = pedDao.findMaiorValorID();
        return optionalPedido.orElse(null);
    }

    @Value("http://localhost:9000/api/pedido/aprovar")
    private String outraApiUrl;

    public ResponseEntity<String> salvarPedido(Pedido pedido) {
        RestTemplate restTemplate = new RestTemplate();
        double valorTotal = 0.0;
        String status = "";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Pedido> requestEntity = new HttpEntity<>(pedido, headers);
        ResponseEntity<Pedido> response = restTemplate.exchange(outraApiUrl, HttpMethod.POST, requestEntity, Pedido.class);

        // Verificar cada item do pedido
        if (response.getStatusCode().is2xxSuccessful()) {
            Pedido pedidoAprovado = response.getBody();
            if (response.getBody().isAprovado()){
                pedidoAprovado.setAprovado(true);
                for (ItemPedido item : pedidoAprovado.getItems()) {
                    Produtos produto = item.getProduto();

                    // Verificar se o produto já existe
                    if (produto.getId() == null) {
                        // O produto não existe, então salve-o antes de atribuí-lo ao item
                        produtoRepository.save(produto);
                    }

                    // Agora você pode atribuir o produto ao item
                    item.setProduto(produto);
                }

                // Calcular o valor total do pedido
                for (ItemPedido item : pedidoAprovado.getItems()) {
                    double valorItem = item.getProduto().getPreco();
                    int quantidadeItem = item.getQuantidade();
                    double valorItemTotal = valorItem * quantidadeItem;
                    valorTotal += valorItemTotal;
                }

                // Atribuir o valor total ao pedido
                pedidoAprovado.setValorTotal(valorTotal);

                // Agora você pode salvar o pedido
                pedidoRepository.save(pedidoAprovado);
                return ResponseEntity.ok("Aprovado");


            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("pagamento Negado");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("erro ao processar");
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }










}
