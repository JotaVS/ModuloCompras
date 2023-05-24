package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.DTO.PedidoDTO;
import com.modulodecompras.modulo.Model.ItemPedido;
import com.modulodecompras.modulo.Model.Pedido;
import com.modulodecompras.modulo.Repository.PedidosRepository;
import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import com.modulodecompras.modulo.Services.dao.PedidoDao2;
import com.modulodecompras.modulo.Services.dto.ProdutosPedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {
    @Autowired
    private PedidosRepository repository;

    @Autowired
    private PedidoDao2 pedDao;

    @Autowired
    private ProdutoService prodServ;

    @Autowired ItemPedidoService ipServ;


    public void salvarListaItemPedido(List<ProdutosPedidoDTO> listaProdutos){
        Pedido idPedido = findMaiorValorID();

        for (ProdutosPedidoDTO produto: listaProdutos){

            ItemPedido ip = ItemPedido.builder()
                    .pedido(idPedido)
                    .produtosPed(prodServ.buscaProdutoPeloId(produto.getIdProduto()))
                    .quantidade(produto.getQuantidadeProduto())
                    .build();

            ipServ.saveIP(ip);

        }

    }

    public Pedido findMaiorValorID() {
        Optional<Pedido> optionalPedido = pedDao.findMaiorValorID();
        return optionalPedido.orElse(null);
    }

    public float calcularValor(List<ProdutosPedidoDTO> listaProdutos){
        float valorTotal = 0.0f;

        for (ProdutosPedidoDTO produto: listaProdutos){
            float valorCompra = produto.getValorCompra();
            int quantidade = produto.getQuantidadeProduto();

            float subtotal = valorCompra * quantidade;

            valorTotal += subtotal;

        }

        return (valorTotal);
    }

//        public float calcularValorTotal(List<ProdutosPedidoDTO> produtosPedidoDTO) {
//        float valorTotal = 0.0f;
//
//        for (ProdutosPedidoDTO produto : produtosPedidoDTO) {
//            float valorCompra = produto.getValorCompra();
//            int quantidade = produto.getQuantProduto();
//
//            float subtotal = valorCompra * quantidade;
//
//            valorTotal += subtotal;
//        }
//
//        return valorTotal;
//    }


    @Transactional(readOnly = true)
    public List<PedidoDTO> fidAll(){
        List<Pedido> list = repository.findAll();
        return  list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());


    }

    @Transactional(readOnly = true)
    public PedidoDTO findById(Long id){
        Optional<Pedido> obj= repository.findById(id);
        Pedido entity = obj.orElseThrow(()-> new EntityNotFoundException("entity not found"));
        return new PedidoDTO(entity);
    }
    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido entity = new Pedido();
//        entity.setCodPedido(dto.getCodPedido());
//        entity.setProdutos(dto.getProdutos());
        entity = repository.save(entity);
        return new PedidoDTO(entity);
    }
}
