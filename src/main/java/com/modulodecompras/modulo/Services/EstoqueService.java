package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Estoque;
import com.modulodecompras.modulo.Services.dao.EstoqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    EstoqueDao estDao;

        public Estoque saveP(Estoque estoque){return estDao.save(estoque);}

    public Estoque buscarEstoquePeloIdProd(int id) {
        return estDao.findByIdProduto(id);
    }

    public Estoque debitarProdutoPeloId(int id, int qntd) throws Exception {
            Estoque prod = buscarEstoquePeloIdProd(id);
            int qntdEstoque = prod.getQuantidade();
            if (qntdEstoque >= qntd) {
                prod.setQuantidade(qntdEstoque - qntd);
            } else {
                throw new Exception("Quantidade não existe no estoque!");
            }
            estDao.save(prod);
            return prod;

    }

    public Estoque adicionarProdutoPeloId(int id, int qntd) {
        Estoque prod = buscarEstoquePeloIdProd(id);
        int qntdEstoque = prod.getQuantidade();
        prod.setQuantidade(qntdEstoque + qntd);
        estDao.save(prod);
        return prod;
    }



}
