package com.api.springMainApp.ImageService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.springMainApp.imageEntry.ImageEntry;
import com.api.springMainApp.imagesRepository.ImagesRepository;


@Service
public class ImageService {
	
	@Autowired
	public ImagesRepository repository;
	

	public List<ImageEntry> getAllImages(){
		 return (List<ImageEntry>) repository.findAll();
	}
	
	public Optional<ImageEntry> getImageById(String Id){	
//			return  repository.findById(Id).isPresent() ?repository.findById(Id).get():new Images();
		return  repository.findById(Id);
	}
	
	
	public ImageEntry SetImage(ImageEntry e) {
		System.out.println("\n\n e.toString() :::"+e.toString());
	 return repository.save(e);
	}
	
	public void deleteImage(String id) {
		  repository.deleteById(id);
	}

}
