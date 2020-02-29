import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {

    const jwt = localStorage.getItem('id_token');

    if (jwt) {
      const cloned = req.clone({
        headers: req.headers.set('Authentication', jwt)
      });
      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}
