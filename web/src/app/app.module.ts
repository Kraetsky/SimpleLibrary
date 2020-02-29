import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import { BookListComponent } from './book-list/book-list.component';
import {BooksService} from './service/books.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {
  MatAutocompleteModule, MatButtonModule, MatButtonToggleModule,
  MatCheckboxModule,
  MatDatepickerModule,
  MatDialogModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule, MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSortModule,
  MatTable,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule
} from '@angular/material';
import {CdkTableModule} from '@angular/cdk/table';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { BookModalComponent } from './book-modal/book-modal.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import {AuthInterceptor} from './security/interceptor';
import {AuthService} from './service/auth.service';
import {UsersService} from './service/users.service';
import { IndexComponent } from './index/index.component';
import { UserComponent } from './user/user.component';
import {AuthenticationGuard} from './security/authentication-guard';
import {FileService} from './service/file.service';


@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookModalComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CdkTableModule,
    MatAutocompleteModule,
    MatPaginatorModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    BooksService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    AuthService,
    UsersService,
    FileService,
    AuthenticationGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    BookModalComponent
  ]
})
export class AppModule { }
