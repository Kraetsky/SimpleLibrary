import {Component, OnInit} from '@angular/core';
import {UsersService} from '../service/users.service';
import {User} from '../domain/user';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.scss']
})
export class IndexComponent implements OnInit {

  user: User;

  constructor(private usersService: UsersService) {
  }

  ngOnInit() {
    this.usersService.getCurrentUser().subscribe(res => this.user = res);
  }

}
