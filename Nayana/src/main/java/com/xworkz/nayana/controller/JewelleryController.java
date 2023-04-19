package com.xworkz.nayana.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.nayana.dto.JewelleryDTO;
import com.xworkz.nayana.service.JewelleryService;

@Controller
@RequestMapping("/")
public class JewelleryController {
	
	
	@Autowired
	private JewelleryService jewelleryService;

	private List<String> colors = Arrays.asList("silver", "Black", "White", "gold");

	public JewelleryController() {
		System.out.println("Created " + this.getClass().getSimpleName());
	}

	@GetMapping("jewellery")
	public String onJewellery(Model model) {
		System.out.println("Running onJewellery on get mapping");
		List<String> colors = Arrays.asList("silver", "Black", "White", "gold");
		model.addAttribute("colors", colors);
		return "jewellery";
	}

	@GetMapping("search")
	public String onSearch(@RequestParam int id,Model model) {
		System.out.println("Running on search for id "+id);
		JewelleryDTO dto=this.jewelleryService.findById(id);
		if(dto!=null) {
			model.addAttribute("dto",dto);
		}else {
			model.addAttribute("message","Data not found");
		}
		return "JewellerySearch";
	}

	@PostMapping("jewellery")
	public String onJewellery(Model model, JewelleryDTO dto) {
		System.out.println("Running onJewellery on post mapping" + dto);
		Set<ConstraintViolation<JewelleryDTO>> violations = this.jewelleryService.validateAndSave(dto);
		if (violations.isEmpty()) {
			System.out.println("No violations in controler go to success page");
			return "jewellery";
		}
		model.addAttribute("colors", colors);
		model.addAttribute("errors", violations);
		model.addAttribute("dto", dto);
		System.err.println("Violations in controller");
		return "jewellery";
	}
	
	@GetMapping("searchByName")
	public String onSearchByName(@RequestParam String name, Model model) {
		System.out.println("Running on search for name " + name);
		List<JewelleryDTO> dto = this.jewelleryService.findByName(name);
		if (dto != null) {
			model.addAttribute("dto", dto);
		} else {
			model.addAttribute("message", "Data not found");
		}
		return "JewelleryNameSearch";
	}
	
	@GetMapping("update")
	public String onUpdate(@RequestParam int id, Model model) {
		System.out.println("running onUpdate "+id);
		JewelleryDTO dto=this.jewelleryService.findById(id);
		model.addAttribute("dto", dto);
		model.addAttribute("colors", colors);
		
		return "JewelleryUpdate";
			
	}
	
	@PostMapping("update")
	public String onUpdate(Model model, JewelleryDTO dto) {
		System.out.println("running onUpdate "+dto);
		Set<ConstraintViolation<JewelleryDTO>> constraintviolations = this.jewelleryService.validateAndUpdate(dto);
		if(constraintviolations.size()>0) {
			model.addAttribute("errors",constraintviolations);
		}else {
			model.addAttribute("message", "Jewellery update Success");
		}
		return "JewelleryUpdate";
	}
	
	@GetMapping("delete")
	public String onDelete(@RequestParam int id,Model model) {
		System.out.println("Running onDelete");
		boolean delete=this.jewelleryService.validateAnddelete(id);
		if(delete=true) {
			System.out.println("deleted data of :"+id);
			model.addAttribute("delete", "Deleted successfully : ID : ");
			model.addAttribute("id",id);
		}else {
			model.addAttribute("notDeleted","Id not found");
		}
		return "JewelleryNameSearch";
	}
	
	@GetMapping("searchByNameAndPrice")
	public String onSearchByNameAndPrice(@RequestParam String name,int price, Model model) {
		System.out.println("Running on search for name and price " + name + price);
		List<JewelleryDTO> dto = this.jewelleryService. findByNameAndPrice(name,price);
		
		if (dto != null ) {
			model.addAttribute("dto", dto);
		} else {
			model.addAttribute("message", "Data not found");
		}
		return "JewelleryNameAndPriceSearch";
	}
	
	
	@GetMapping("/searchAll")
	public String onSearchAll(Model model) {
		System.out.println("Running on search for all ");
		List<JewelleryDTO> dto = this.jewelleryService.findAll();
		if (dto != null) {
			model.addAttribute("dto", dto);
		} else {
			model.addAttribute("message1", "Data not found");
		}
		return "SearchAll";
	}
}