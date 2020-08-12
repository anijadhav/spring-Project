package com.start.springProject.bootStrap;

import com.start.springProject.model.Author;
import com.start.springProject.model.Book;
import com.start.springProject.model.Publisher;
import com.start.springProject.repo.AuthorRepo;
import com.start.springProject.repo.BookRepo;
import com.start.springProject.repo.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootStrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("BootStrap Started");

        Publisher publisher=new Publisher();
        publisher.setName("Jacoco publisher");
        publisher.setAddressLine1("Wall street");
        publisher.setState("Las Vegas");
        publisherRepo.save(publisher);

        System.out.println("Publisher Count:"+publisherRepo.count());

        Author aniket=new Author("Aniket","Jadhav");
        Book book=new Book("Java","123456");
        aniket.getBooks().add(book);
        book.getAuthors().add(aniket);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepo.save(aniket);
        bookRepo.save(book);
        publisherRepo.save(publisher);

        Author rod=new Author("Rod","Johnson");
        Book noEJB=new Book("J2EE Development without EJB","4587453245");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        book.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepo.save(rod);
        bookRepo.save(noEJB);
        publisherRepo.save(publisher);

        System.out.println("Number of books:"+bookRepo.count());
        System.out.println("Number of Publisher book"+publisher.getBooks().size());
    }
}
