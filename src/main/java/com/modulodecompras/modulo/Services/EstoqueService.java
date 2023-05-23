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

    public Estoque buscarEstoquePeloIdProd(int id){

        return estDao.findByIdProduto(id);
    }

}
