package br.com.gft.casadeshow.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/imagens";
	
	public void saveEvent(Event event, MultipartFile file) {
		if (file.getSize() == 0) {
			event.setFoto("default.jpg");
			
		} else {
			String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
			this.UploadFile(file);
			event.setFoto(nameFile);
		}
		repository.save(event);
	}
	
	public void UploadFile(MultipartFile file) {

		try {

			Path filename = Paths.get(uploadDirectory, file.getOriginalFilename());
			Files.write(filename, file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
