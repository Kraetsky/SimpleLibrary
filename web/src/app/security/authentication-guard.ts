import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../service/auth.service';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';

@Injectable()
export class AuthenticationGuard implements CanActivate, CanActivateChild {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['login']);
      return of(false);
    }
    return of(true);
  }

  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['login']);
      return of(false);
    }
    return of(true);
  }
}
