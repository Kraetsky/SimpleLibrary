import {Component, OnInit} from '@angular/core';
import {UsersService} from '../service/users.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {FileService} from '../service/file.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  selectedFile: File;
  imageUrl = 'https://www.civhc.org/wp-content/uploads/2018/10/question-mark.png';

  userForm = this.fb.group({
    name: '',
    login: '',
    password: '',
    email: '',
    phoneNumber: '',
    isActive: true
  });

  constructor(private usersService: UsersService,
              private fb: FormBuilder,
              private fileService: FileService) {
  }

  ngOnInit() {
    this.usersService.getCurrentUser().subscribe(res => this.userForm = this.fb.group(res));
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    const uploadData = new FormData();
    uploadData.append('profile_image', this.selectedFile, this.selectedFile.name);
    this.fileService.uploadFile(uploadData);
  }

  save() {
    this.usersService.create(this.userForm.getRawValue())
      .subscribe(() => this.onUpload());
  }

}
