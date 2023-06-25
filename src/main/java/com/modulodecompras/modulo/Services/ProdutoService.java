package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.EstoqueDao;
import com.modulodecompras.modulo.Services.dao.ProdutoDao;
import com.modulodecompras.modulo.Services.dto.ProdutoDTO;
import com.modulodecompras.modulo.Services.dto.ProdutoEstoqueDTO;
import com.modulodecompras.modulo.Services.dto.VerificarProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDao pDao;

    @Autowired
    EstoqueDao eDao;

    @Autowired
    EstoqueService eServ;

    @Autowired
    FornecedoresService fServ;

    public ProdutoDTO saveP(ProdutoDTO produtos){
        Produtos pnew = new Produtos();
        pnew.setNome(produtos.getNomeProduto());
        pnew.setValorUnidade(produtos.getPrecoUnit());
        pnew.setFornecedores(fServ.buscaFornecedoresPeloId(produtos.getIdFornecedor()));
        pnew.setDescricao(produtos.getDescricao());
        pDao.save(pnew);

        Estoque enew = new Estoque();
        enew.setQuantidade(produtos.getQtdEstoque());
        enew.setProdutos(pnew);
        eDao.save(enew);

        produtos.setIdProduto(Math.toIntExact(pnew.getId()));

        return (produtos);


    }

    public Produtos buscaProdutoPeloId(int id){
        Optional<Produtos> op = pDao.findById(id);

        if (op.isPresent()){
            return op.get();
        } else{
            return null;
        }
    }

    public VerificarProdutoDTO verificarProduto(int id){
        Optional<Produtos> op = pDao.findById(id);

        if (op.isPresent()){
            Estoque est = eServ.buscarEstoquePeloIdProd(id);

            VerificarProdutoDTO verProd = VerificarProdutoDTO.builder()
                    .qtdEstoque(est.getQuantidade())
                    .produtoExistente(true)
                    .build();

            return verProd;

        } else{

            VerificarProdutoDTO verProd = VerificarProdutoDTO.builder()
                    .qtdEstoque(0)
                    .produtoExistente(false)
                    .build();
            return verProd;

        }


    }


    public ProdutoEstoqueDTO buscaProdutoPeloIdDto(int id) throws Exception{
        Optional<Produtos> op = pDao.findById(id);

        if (op.isPresent()){

            Estoque est = eServ.buscarEstoquePeloIdProd(id);

            ProdutoEstoqueDTO prodEst = ProdutoEstoqueDTO.builder()
                    .idProduto(id)
                    .qtdEstoque(est.getQuantidade())
                    .nomeProduto(op.get().getNome())
                    .precoUnit(op.get().getValorUnidade())
                    .build();

            return prodEst;
        } else{
            throw new Exception("Produto não encontrado!!");
        }

    }

    public Produtos buscaProdutoPeloNome(String nome){
        return pDao.findByNome(nome);
    }

    public List<Produtos> buscaAllProduto() {
        return pDao.findAll();
    }
    public String apagarProduto(int id) throws Exception{
        Produtos p = buscaProdutoPeloId(id);
        if (p == null){
            throw new Exception("Produto não encontrado!!");
        }
        pDao.delete(p);
        return "Produto"+id+"apagado com Sucesso!!";

    }

    public Object updateProdutos(int id, Produtos produtos) throws Exception{
        Produtos p = buscaProdutoPeloId(id);
        if (p == null){
            throw new Exception("Produto não encontrado!!");
        }
        p.setNome(produtos.getNome());
        p.setDescricao(produtos.getDescricao());

        return pDao.save(p);
    }

}
