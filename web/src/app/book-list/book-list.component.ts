import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {BooksService} from '../service/books.service';
import {Book} from '../domain/book';
import {MatPaginator, MatTableDataSource, MatSort, MatDialog} from '@angular/material';
import {BookModalComponent} from '../book-modal/book-modal.component';
import {ModalMode} from '../domain/modal-mode';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  displayedColumns = ['id', 'name', 'author', 'numberOfPages', 'year', 'isAvailable', 'actions'];
  datasource: MatTableDataSource<Book> = new MatTableDataSource<Book>();
  books: Book[];
  ModalMode = ModalMode;

  constructor(private booksService: BooksService,
              private matDialog: MatDialog) {
  }

  ngOnInit() {
    this.loadBooks();
  }

  ngAfterViewInit() {
  }

  loadBooks() {
    this.booksService.getAll().subscribe(data => {
      this.books = data;
      this.datasource = new MatTableDataSource<Book>(this.books);
      this.datasource.paginator = this.paginator;
      this.datasource.sort = this.sort;
    });
  }

  remove(book: Book) {
    this.booksService.delete(book.id).subscribe(() => this.loadBooks());
  }

  edit(book: Book) {
    this.booksService.createorUpdate(book).subscribe(() => this.loadBooks());
  }

  openDialog(book: Book, mode: ModalMode) {
    this.matDialog.open(BookModalComponent, {
      width: '400px',
      height: '350px',
      data: {book: book, mode: mode}
    }).afterClosed().subscribe(
      res => {
        if (res) {
          this.edit(res);
        }
      }
    );
  }
}
