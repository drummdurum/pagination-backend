package dat3.paginationdemo.services;

import dat3.paginationdemo.entity.Book;
import dat3.paginationdemo.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Page<Book> getAllBooks(Pageable pageable) {
    Page<Book> books = bookRepository.findAll(pageable);
    // Observe the amount of extra information in the JSON response.
    // We probably only need totalPages, so you could create a new DTO class with book info and totalPages.
    // books.getTotalPages();
    // books.getTotalElements();
    return books;
  }
}
