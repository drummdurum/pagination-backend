package dat3.paginationdemo.services;

import dat3.paginationdemo.entity.Book;
import dat3.paginationdemo.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

  public Page<Book> getFilteredBooks(Pageable pageable, String column, String value) {
    if (column.equals("title")) {
      return bookRepository.findByTitleContainingIgnoreCase(value, pageable);
    } else if (column.equals("author")) {
      return bookRepository.findByAuthorContainingIgnoreCase(value, pageable);
    } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid filter column");
  }

  public Page<Book> getFilteredBooksV2(Pageable pageable, String titleFilter, String authorFilter) {
    Page<Book> books = bookRepository.findByTitleAndAuthorContainingIgnoreCase(titleFilter, authorFilter, pageable);
    return books;
  }
}
