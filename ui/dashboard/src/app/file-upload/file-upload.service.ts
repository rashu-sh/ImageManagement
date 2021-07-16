import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private http: HttpClient) { }
  submitImage(requestObj: any) {
    console.log("in submitImage :::",requestObj);
    
    return this.http.post<any>(environment.api+ '/setimages', requestObj,{ observe: 'response' });
  }

  getImagesMap() {    
    return this.http.get<any>(environment.api+ '/getImagesMap');
  }
  getHeaders() {
    const options = { headers: {}}
    options.headers['Content-Type'] = 'application/json';
    options.headers['Access-Control-Allow-Origin'] = "*";
    options.headers['Allow'] = "*";
    options.headers['Access-Control-Allow-Methods'] = ["*"];

    return options;
  }

  getImage(id) {    
    let headers = new Headers({'Content-Type': 'image/*', 'Accept': 'image/*'});

    return this.http.get<any>(environment.api+ '/getImage/'+id  ,{responseType: 'blob' as 'json'});
  }
  deleteImage(id) {    
    return this.http.delete<any>(environment.api+ '/getImage/'+id  );
  }

}
