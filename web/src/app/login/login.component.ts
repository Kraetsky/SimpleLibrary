import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
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
  registerNote: boolean;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthService,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      if (Object.keys(params).includes('register')) {
        this.registerNote = params.get['register'];
      }
    });
  }

  ngOnInit() {
  }

  login() {
    const val = this.loginForm.value;
    if (val.login && val.password) {
      this.authService.login(val.login, val.password);
    }
  }

}
