package br.com.dcc193t3.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.dcc193t3.dao.AnotacaoRepository;
import br.com.dcc193t3.dao.EtiquetaRepository;
import br.com.dcc193t3.dao.ItemRepository;
import br.com.dcc193t3.dao.VinculoRepository;
import br.com.dcc193t3.model.Vinculo;

/**
 * VinculoAdminController
 */
@Controller
@RequestMapping("admin/vinculo")
public class VinculoAdminController {


    @Autowired
    VinculoRepository vinculoRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    AnotacaoRepository anotacaoRepository;

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/")
    public String homeAdminVinculo(Model model){
        model.addAttribute("listaVinculo",vinculoRepository.findAll());
        return "ADMIN/vinculo/home-vinculo";
    }

    @RequestMapping("/criar")
    public String criarVinculo(Model model){
        model.addAttribute("vinculo",new Vinculo());
        model.addAttribute("listaOrigem",itemRepository.findAll());
        model.addAttribute("listaDestino",itemRepository.findAll());
        model.addAttribute("listaEtiqueta",etiquetaRepository.findAll());
        return "ADMIN/vinculo/criar-vinculo";
    }
    @RequestMapping("/deletar/{id}")
    public String deletarAdminVinculo(@PathVariable Long id){
        vinculoRepository.deleteById(id);
        return "redirect:/admin/vinculo/";
    }

    @RequestMapping("/editar/{id}")
    public String editarAdminVinculo(@PathVariable Long id, Model model){
        model.addAttribute("listaOrigem",itemRepository.findAll());
        model.addAttribute("listaDestino",itemRepository.findAll());
        model.addAttribute("listaEtiqueta",etiquetaRepository.findAll());
        model.addAttribute("vinculo",vinculoRepository.findById(id).get());
        return "ADMIN/vinculo/editar-vinculo";
    }

    @RequestMapping("/editar/salvar")
    public String editarsalvarAdminVinculo(Vinculo vinculo){
        vinculoRepository.save(vinculo);
        return "redirect:/admin/vinculo/";
    }

    @RequestMapping("/salvar")
    public String salvarTrabalho(Vinculo vinculo){
        vinculoRepository.save(vinculo);
        return "redirect:/admin/vinculo/";
    }
    
}