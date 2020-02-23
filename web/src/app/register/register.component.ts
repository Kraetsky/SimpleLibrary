import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UsersService} from '../service/users.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm = this.fb.group({
    name: '',
    login: '',
    password: '',
    email: (''),
    phoneNumber: '',
    isActive: true
  });

  constructor(private fb: FormBuilder,
              private usersService: UsersService,
              private router: Router) {
  }

  ngOnInit() {
  }

  register() {
    this.usersService.create(this.registerForm.getRawValue())
      .subscribe(() => this.router.navigate(['login'], { queryParams: { register: 'true' } }));
  }

}
