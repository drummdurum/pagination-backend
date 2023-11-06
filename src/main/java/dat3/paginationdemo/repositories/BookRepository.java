package dat3.paginationdemo.repositories;

import dat3.paginationdemo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
  Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
  Page<Book> findByAuthorContainingIgnoreCase(String title,Pageable pageable);

  // This version will work also if one or both of the parameters is null
  @Query("SELECT b FROM Book b WHERE " +
          "(LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) OR :title IS NULL) AND " +
          "(LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')) OR :author IS NULL)")
  //@Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) AND LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
  Page<Book> findByTitleAndAuthorContainingIgnoreCase(@Param("title") String title, @Param("author") String author, Pageable pageable);

}
