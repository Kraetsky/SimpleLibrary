import {Component} from '@angular/core';
import {AuthService} from './service/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Simple Library';

  constructor(private authService: AuthService,
              private router: Router) {

  }

  logout() {
    this.authService.logout();
    this.router.navigate(['login']);
  }

  isLoggedOut() {
    return this.authService.isLoggedOut();
  }
}
