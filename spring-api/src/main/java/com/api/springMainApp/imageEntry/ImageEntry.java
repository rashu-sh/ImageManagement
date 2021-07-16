package com.api.springMainApp.imageEntry;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Blob;

@Entity
@Table(name = "images")
public class ImageEntry {

	 	@Id
		@Column(name="id")
	    private String Id;
		@Column(name="name")
	 	private String Name;
		@Column(name="image")
	    private Blob Image;
	    
	    public ImageEntry(String id,String Name, Blob image) {
			super();
			this.Id = id;
			this.Name= Name;
			this.Image = image;
		}
	    
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			this.Id = id;
		}
		public Blob getImage() {
			return Image;
		}
		
		public void setImage(Blob image) {
			this.Image = image;
		}
		
		

		public ImageEntry() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "ImageEntry [id=" + Id + ", name=" + Name + ", image=" + Image.toString() + "]";
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			this.Name = name;
		}
	    
	    
	    

	   
}
