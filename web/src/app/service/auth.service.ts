import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import * as moment from 'moment';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient,
              private router: Router) {
  }

  login(login: string, password: string) {
    return (this.http.post('/api/login', {login: login, password: password}) as Observable<any>).subscribe(res => {
      this.setSession(res);
      this.router.navigateByUrl('/');
    });
  }

  private setSession(authResult) {
    const expiresAt = moment().add(authResult.expiresIn, 'second');
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', JSON.stringify(expiresAt.valueOf()));
  }

  logout() {
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
  }

  public isLoggedIn(): boolean {
    return !!localStorage.getItem('id_token');
  }

  public isTokenExpired() {
    return moment().isBefore(this.getExpiration());
  }

  isLoggedOut() {
    return !this.isLoggedIn();
  }

  getExpiration() {
    const expiration = localStorage.getItem('expires_at');
    const expiresAt = JSON.parse(expiration);
    return moment(expiresAt);
  }
}
