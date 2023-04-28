package br.com.base.Compras.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class teste {

    @GetMapping()
    public String home(){
        return "Tá rodando";
    }
}
