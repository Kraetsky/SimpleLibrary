import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

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
    phone: ''
  });

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
  }

}
