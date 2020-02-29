import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class FileService {

  constructor(private http: HttpClient) {
  }

  public uploadFile(data: FormData) {
    this.http.post('api/file/upload', data);
  }

}
