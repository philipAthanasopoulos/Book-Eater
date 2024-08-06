package objectArmy.bookEater.service;

import objectArmy.bookEater.entity.book.Author;
import objectArmy.bookEater.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    public Optional<Author> getAuthorByName(String authorName) {
        return Optional.ofNullable(authorRepository.findByName(authorName));
    }

    public Author getAuthorById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorOrElseCreate(String authorName) {
        return getAuthorByName(authorName).orElseGet(() -> {
            Author author = new Author();
            author.setName(authorName);
            return addAuthor(author);
        });
    }

    public void saveAuthor(Author author) {
        if (authorRepository.findByName(author.getName()) == null) authorRepository.save(author);
    }
}



