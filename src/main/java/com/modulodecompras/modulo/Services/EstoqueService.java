package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Services.dao.EstoqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    EstoqueDao estDao;

    public Estoque saveP(Estoque estoque){return estDao.save(estoque);}

    public Estoque buscarEstoquePeloIdProd(Long id) {
        return estDao.findByIdProduto(id);
    }

    public String debitarProdutoPeloId(Long id, int qntd) throws Exception {
            Estoque prod = buscarEstoquePeloIdProd(id);
            int qntdEstoque = prod.getQuantidade();
            if (qntdEstoque >= qntd) {
                prod.setQuantidade(qntdEstoque - qntd);
            } else {
                throw new Exception("Quantidade não existe no estoque!");
            }
            estDao.save(prod);
            return("Debitado "+qntd+" com sucesso do estoque, agora o estoque possui "+prod.getQuantidade()+" produtos.");

    }

    public String adicionarProdutoPeloId(Long id, int qntd) {
        Estoque prod = buscarEstoquePeloIdProd(id);
        int qntdEstoque = prod.getQuantidade();
        prod.setQuantidade(qntdEstoque + qntd);
        estDao.save(prod);
        return ("Adicionado "+qntd+" com sucesso do estoque, agora o estoque possui "+prod.getQuantidade()+" produtos.");
    }

    public boolean verificarEPIPorMatricula(String matricula, Long idEPI) {
        Estoque estoque = buscarEstoquePeloIdProd(idEPI);
        if (estoque != null && estoque.getQuantidade() > 0) {
            return true; // O EPI está disponível no estoque
        } else {
            return false; // O EPI não está disponível no estoque
        }
    }




}
