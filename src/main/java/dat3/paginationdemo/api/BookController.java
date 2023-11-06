package dat3.paginationdemo.api;


import dat3.paginationdemo.entity.Book;
import dat3.paginationdemo.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public Page<Book> getAllBooks(Pageable pageable) {
    return bookService.getAllBooks(pageable);
  }

  // You can skip the previous method and use this one instead if filtering is needed.
  @GetMapping("/filtered")
  public Page<Book> getAllBooksFiltered(Pageable pageable,
                                        @RequestParam(name = "author", required = false) String author,
                                        @RequestParam(name = "title", required = false) String title
  )
  {
    return bookService.getFilteredBooksV2(pageable, title, author);
  }

}

