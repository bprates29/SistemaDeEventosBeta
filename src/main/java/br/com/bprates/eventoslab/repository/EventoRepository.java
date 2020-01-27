package br.com.bprates.eventoslab.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.bprates.eventoslab.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
	
	List<Evento> findByNome(String nome);

}
