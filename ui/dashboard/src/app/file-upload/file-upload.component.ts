import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { FileUploadService } from './file-upload.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {

  action= 'upload';
  fileName = 'Choose file...';
  activefile :File;
  fileListMap={};
  selectedFileKey=null;
  retrievedImage= null;
  constructor(
    private fileUploadService:FileUploadService,
    private sanitizer:DomSanitizer
  ) { }

  ngOnInit() {
  }

  onFileChange(input){
    this.fileName = input.target.files[0].name;
    if (input.target.files && input.target.files[0]) {      
      const reader = new FileReader();
      reader.addEventListener('load', (event: any) => {
        (<HTMLImageElement>document.getElementById('preview')).src=event.target.result 

      });
      reader.readAsDataURL(input.target.files[0]);

     this.activefile= input.target.files[0];
    }else{
      this.activefile= null;
    }
  }

  setForm(action){
    this.action= action;
    this.fileListMap={} ;
    this.retrievedImage= null;
    this.selectedFileKey= null
    if(this.action=='check'){
      this.getImagesMap();
    }
  }

  submitFile(){
    let payload = new FormData();
    payload.append("file",this.activefile,this.activefile.name)
    console.log("payload ::::",payload);
    
    this.fileUploadService.submitImage(payload).subscribe((response: any) => {
      console.log("response ::::",response);
      
    });
  }
  getImagesMap(){
    this.fileListMap={} ;
    this.selectedFileKey= null;
    this.fileUploadService.getImagesMap().subscribe(data=>{
      this.fileListMap=data;
      console.log("this.fileListMap::,",this.fileListMap);
      
    })
  }
  selectFile( key){
    console.log("KEY----------------",this.selectedFileKey);
    
  }
  previewFile(){
    this.fileUploadService.getImage(this.selectedFileKey).subscribe(data=>{
      // var b64Response = btoa(data);
      // this.retrievedImage=data;
      // this.retrievedImage = 'data:image/jpeg;base64,' + b64Response;
      console.log("retrievedImage:::",data);
      let objectURL = URL.createObjectURL(data);       
      this.retrievedImage = this.sanitizer.bypassSecurityTrustUrl(objectURL);

      
    })
  
  }
  deleteFile(){
    this.retrievedImage= null;
    this.fileUploadService.deleteImage(this.selectedFileKey).subscribe(data=>{
    this.getImagesMap();
    })
  }
}

