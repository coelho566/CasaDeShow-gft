package br.com.gft.casadeshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.Historico;
import br.com.gft.casadeshow.repository.HistoricoRepository;

@Service
public class HistoricoService {
	
	@Autowired
	private HistoricoRepository repository;
	
	public void saveHistorico(Historico historico) {
	
		repository.save(historico);
	}

	public List<Historico> listHistorico() {
		
		return repository.findAll();
	}
}
