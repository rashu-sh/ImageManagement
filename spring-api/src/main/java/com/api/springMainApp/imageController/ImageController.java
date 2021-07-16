package com.api.springMainApp.imageController;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.springMainApp.ImageService.ImageService;
import com.api.springMainApp.imageEntry.ImageEntry;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.ws.rs.FormParam;

@RestController
public class ImageController {

	@Autowired
	public ImageService imageservice ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/getImage/{id}")
	public ResponseEntity<byte[]> getImageById(@PathVariable String id) {
		System.out.println("\n\n id :::: "+id);
		Optional<ImageEntry> userImage = this.imageservice.getImageById(id);
        byte[] imageBytes = null;
        if (userImage.isPresent()) {
            try {
				imageBytes = userImage.get().getImage().getBytes(1, (int) userImage.get().getImage().length());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

	
	@RequestMapping("/getImagesMap")
	public HashMap<String, String> getImagesMap() {
		List<ImageEntry> userImage = this.imageservice.getAllImages();
        HashMap<String,String> map=new HashMap<String,String>();	
        for (int i = 0; i <userImage.size(); i++) {
			map.put(userImage.get(i).getId(), userImage.get(i).getName());
		} 
        return map;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/setimages")
	public void uploadFile( @FormParam("file") MultipartFile file)  {
		System.out.println("file:::"+file);
		System.out.println("file.getOriginalFilename():::"+file.getOriginalFilename());
		String filename= StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("\n\n\n filename::: "+filename);
		Blob blob= null;
		try {
			blob = new SerialBlob(file.getBytes() );

		} catch (SerialException e1) {
			System.out.println( "exception 1");
			e1.printStackTrace();
		} catch (SQLException e1) {
			System.out.println( "exception 2");

			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println( "exception 3");

			e1.printStackTrace();
		}
		ImageEntry e = new ImageEntry(""+System.currentTimeMillis(),filename,blob);

		System.out.println("blob:: \n\n"+e.toString());
		this.imageservice.SetImage(e);
		
//		return e.getId();
		
	}
	
	@DeleteMapping("/getImage/{id}")
	public void getImagesMap(@PathVariable String id) {
		this.imageservice.deleteImage(id);

	}
	
	
}
