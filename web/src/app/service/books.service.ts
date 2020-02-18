import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Book} from '../domain/book';
import {map} from 'rxjs/operators';

@Injectable()
export class BooksService {

  constructor(private http: HttpClient) {
  }

  public getAll(): Observable<Book[]> {
    return this.http.get('api/books/all').pipe(map(res => res['data'] as Book[]));
  }

  public getById(id: number): Observable<Book> {
    return this.http.get(`api/books/${id}`).pipe(map(res => res['data'] as Book));
  }

  public createorUpdate(book: Book): Observable<Book> {
    if (book.id) {
      return this.http.put('api/books', book).pipe(map(res => res['data'] as Book));
    }
    return this.http.post('api/books', book).pipe(map(res => res['data'] as Book));
  }

  public delete(id: number) {
    return this.http.delete(`api/books/${id}`);
  }

}
