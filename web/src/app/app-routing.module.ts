import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {BookListComponent} from './book-list/book-list.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {IndexComponent} from './index/index.component';
import {UserComponent} from './user/user.component';
import {AuthenticationGuard} from './security/authentication-guard';

const routes: Routes = [
  {
    path: '',
    component: IndexComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'books',
    component: BookListComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'user-details',
    component: UserComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
