import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {FormBuilder} from '@angular/forms';
import {ModalMode} from '../domain/modal-mode';

@Component({
  selector: 'app-book-modal',
  templateUrl: './book-modal.component.html',
  styleUrls: ['./book-modal.component.scss']
})
export class BookModalComponent implements OnInit {

  editMode: boolean;
  form = this.fb.group({
    id: '',
    name: '',
    author: '',
    releaseYear: '',
    numberOfPages: '',
    isAvailable: true,
    userId: 1
  });

  constructor(private dialogRef: MatDialogRef<BookModalComponent>,
              private fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
    console.log(this.data);
    console.log(ModalMode.UPDATE.valueOf());
    if (this.data.mode === ModalMode.UPDATE.valueOf()) {
      console.log(this.data.book);
      this.editMode = true;
      this.form = this.fb.group(this.data.book);
    }
  }

  save() {
    this.dialogRef.close(this.form.getRawValue());
  }

  close() {
    this.dialogRef.close();
  }

}
