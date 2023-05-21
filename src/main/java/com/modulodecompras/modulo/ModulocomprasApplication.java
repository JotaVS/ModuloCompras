package com.modulodecompras.modulo;

import com.modulodecompras.modulo.Model.Fornecedores;
import com.modulodecompras.modulo.Model.Produtos;
import com.modulodecompras.modulo.Services.dao.FornecedoresDao;
import com.modulodecompras.modulo.Services.dao.ProdutoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class ModulocomprasApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(ModulocomprasApplication.class, args);
	}

	@Autowired
	ProdutoDao prodDao;

	@Autowired
	FornecedoresDao fornDao;

	@Override
	public void run(String... args) throws Exception {


//		Fornecedores f = new Fornecedores();
//		f.setNome("zezin");
//
//		fornDao.save(f);
////		fornDao.save(f);
//
//		Produtos p = new Produtos();
//		p.setNome("generico");
//		p.setFornecedores(f);
//		prodDao.save(p);



	}
}