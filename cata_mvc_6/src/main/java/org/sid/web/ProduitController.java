package org.sid.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.dao.ProduitRepository;
import org.sid.entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	//@RequestMapping(value="/index", method=RequestMethod.GET) // or you can use GetMaping instead
	@GetMapping("/index")
	public String getProds(Model model, @RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="motCle", defaultValue="") String mc){
		//Page<Produit> pageProduits = produitRepository.findAll(PageRequest.of(page, 5));
		Page<Produit> pageProduits = produitRepository.findByDesignationContains(mc, PageRequest.of(page, 5));
		model.addAttribute("listProduits", pageProduits.getContent());
		model.addAttribute("pages", new int[pageProduits.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle", mc);
		return "produits";
	}

	@GetMapping("/delete")
	public String deleteItem(@RequestParam(name="id") Long id){
		produitRepository.deleteById(id);
		return "redirect:/index";
	}
	
	@GetMapping("/formProduit")
	public String form(Model model){
		model.addAttribute("produit", new Produit());
		return "formProduit";
	}
	
	@GetMapping("/edit")
	public String editForm(Model model, @RequestParam(name="id") Long id){
		Produit produit = produitRepository.findById(id).get();
		model.addAttribute("produit", produit);
		return "editProduit";
	}
	
	@PostMapping("/save")
	public String save(Model model, @Valid Produit produit, BindingResult br){
		if (br.hasErrors()) return "formProduit";
		produitRepository.save(produit);
		
		return "redirect:/index";
	}
	
	@GetMapping("/")
	public String welcome(){
		return "redirect:/index";
	}
	
	@GetMapping("/403")
	public String notAllowed(){
		return "403";
	}
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	
	
	
	
	
	
}
