package com.modulodecompras.modulo.Services;

import com.modulodecompras.modulo.Model.Fornecedores;

import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.FornecedoresDao;
import com.modulodecompras.modulo.Services.dto.FornecedorProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedoresService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FornecedoresDao fDao;
    @Autowired
    ProdutoService pServ;

    public Fornecedores saveF(Fornecedores fornecedores){return fDao.save(fornecedores);}

    public Fornecedores buscaFornecedoresPeloId(int id) {
        Optional<Fornecedores> op = fDao.findById(id);

        if (op.isPresent()){
            return op.get();
        }else{
            return null;
        }
    }

    public Fornecedores buscaFornecedoresPeloNome(String nome){
        return fDao.findByNome(nome);
    }

    public List<Fornecedores> buscaAllFornecedores() {
        return fDao.findAll();
    }

    public String apagarFornecedores(int id) throws Exception {
        Fornecedores f = buscaFornecedoresPeloId(id);
        if (f == null){
            throw new Exception("Fornecedor não encontrado!!");
        }
        fDao.delete(f);
        return "Fornecedor "+id+" apagado com Sucesso!!";
    }

    public Object updateFornecedores(int id, Fornecedores fornecedores) throws Exception{
        Fornecedores f = buscaFornecedoresPeloId(id);
        if (f == null){
            throw new Exception("Fornecedor não encontrado!!");
        }
        f.setNome(fornecedores.getNome());
        f.setCnpj(fornecedores.getCnpj());
        f.setTelefone(fornecedores.getTelefone());
        f.setPrazoPagamento(fornecedores.getPrazoPagamento());
        f.setDescontoVolume(fornecedores.getDescontoVolume());

        return fDao.save(f);
    }

    public List<FornecedorProdutoDTO> getFornecedorPorPedidoCliente (int pedidoId) throws Exception {
        restTemplate = new RestTemplate();
        String url = "http://backend-vendas-production.up.railway.app/pedido/buscar/" + pedidoId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String json = response.getBody();

        JsonReader jsonReader = Json.createReader(new StringReader(json));

        JsonObject pedido = jsonReader.readObject();
        JsonArray jsonArray = pedido.getJsonArray("itensPedido");

        List<FornecedorProdutoDTO> fornProdutoDTOs = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.getJsonObject(i);
            JsonObject itemPedido = jsonObject.getJsonObject("itemPedido");
            int idProduto = itemPedido.getInt("idProduto");
            Produtos p = pServ.buscaProdutoPeloId(Long.valueOf(idProduto));
            Fornecedores f = p.getFornecedores();

            FornecedorProdutoDTO fornPedido = FornecedorProdutoDTO.builder()
                    .nomeFornecedor(f.getNome())
                    .nomeProduto(p.getNome())
                    .build();
            fornProdutoDTOs.add(fornPedido);
        }
        return fornProdutoDTOs;
    }
}
