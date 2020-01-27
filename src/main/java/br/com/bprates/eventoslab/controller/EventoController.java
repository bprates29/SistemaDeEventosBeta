package br.com.bprates.eventoslab.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bprates.eventoslab.model.Evento;
import br.com.bprates.eventoslab.repository.EventoRepository;

@Controller
public class EventoController {
	@Autowired
    private EventoRepository eventoRepository;
	
	@GetMapping("/signup")
    public String showSignUpForm(Evento evento) {
        return "add-evento";
    }
	
	@PostMapping("/addevento")
    public String create(@Valid Evento evento, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "index";
        }
         
        eventoRepository.save(evento);
        model.addAttribute("eventos", eventoRepository.findAll());
        return "index";
    }
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Evento evento = eventoRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid evento Id:" + id));
	     
	    model.addAttribute("evento", evento);
	    return "update-evento";
	}
	
	@PostMapping("/update/{id}")
	public String updateEvento(@PathVariable("id") long id, @Valid Evento evento, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        evento.setId(id);
	        return "update-evento";
	    }
	         
	    eventoRepository.save(evento);
	    model.addAttribute("eventos", eventoRepository.findAll());
	    return "index";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteEvento(@PathVariable("id") long id, Model model) {
	    Evento evento = eventoRepository.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    eventoRepository.delete(evento);
	    model.addAttribute("eventos", eventoRepository.findAll());
	    return "index";
	}
}
