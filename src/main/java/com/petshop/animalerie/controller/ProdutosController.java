package com.petshop.animalerie.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.petshop.animalerie.model.Produto;
import com.petshop.animalerie.model.statusSegmento;
import com.petshop.animalerie.repository.Produtos;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	private static final String CADASTRO_VIEW = "CadProdutos";

	@Autowired
	private Produtos produtos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Produto());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		produtos.save(produto);
		attributes.addFlashAttribute("mensagem", "Produto salvo com sucesso");
		return "redirect:/produtos/novo";
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Produto> todosProdutos = produtos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaEstoque");
		mv.addObject("produtos", todosProdutos);
		return mv;
	}

	@ModelAttribute("todosSegmentos")
	public List<statusSegmento> todosSegmentos() {
		return Arrays.asList(statusSegmento.values());
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Produto produto) {

		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(produto);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		produtos.deleteById(codigo);
		attributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
		return "redirect:/produtos";

	}

}
