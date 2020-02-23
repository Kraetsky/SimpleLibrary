import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../domain/user';
import {Observable} from 'rxjs/Observable';
import {map} from 'rxjs/operators';

@Injectable()
export class UsersService {

  constructor(private http: HttpClient) {
  }

  public checkLoginExists() {

  }

  public getCurrentUser(): Observable<User> {
    return this.http.get('api/users/current').pipe(map(res => res['data'] as User));
  }

  public create(user): Observable<User> {
    return this.http.post('api/users', user).pipe(map(res => res['data'] as User));
  }

  public getById(id: number): Observable<User> {
    return this.http.get(`api/users/${id}`).pipe(map(res => res['data'] as User));
  }

}
