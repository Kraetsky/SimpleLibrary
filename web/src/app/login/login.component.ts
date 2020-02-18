import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {MatDialog} from '@angular/material';
import {BookModalComponent} from '../book-modal/book-modal.component';
import {Router} from '@angular/router';
import {AuthService} from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    login: '',
    password: ''
  });

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthService) {
  }

  ngOnInit() {

  }

  login() {
    const val = this.loginForm.value;

    if (val.login && val.password) {
      this.authService.login(val.login, val.password)
        .subscribe(
          () => {
            console.log('User is logged in');
            this.router.navigateByUrl('/');
          }
        );
    }
  }

}
