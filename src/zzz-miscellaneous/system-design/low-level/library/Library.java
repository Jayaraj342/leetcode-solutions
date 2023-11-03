import java.time.LocalDate;
import java.util.*;

enum BookFormat {
    HARDCOVER,
    PAPERBACK,
    AUDIO_BOOK,
    EBOOK,
    NEWSPAPER,
    MAGAZINE,
    JOURNAL
}

enum BookStatus {
    AVAILABLE,
    RESERVED,
    LOANED,
    LOST
}

enum ReservationStatus{
    WAITING,
    PENDING,
    CANCELED,
    NONE
}

enum AccountStatus{
    ACTIVE,
    CLOSED,
    CANCELED,
    BLACKLISTED,
    NONE
}

class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
}

class Constants {
    public static final int MAX_BOOKS_ISSUED_TO_A_USER = 5;
    public static final int MAX_LENDING_DAYS = 10;
}

abstract class Account {
    private String id;
    private String password;
    private AccountStatus status;
    private Person person;

    abstract boolean resetPassword();
}

abstract class Librarian extends Account {
    abstract boolean addBookItem(BookItem bookItem);

    abstract boolean blockMember(Member member);

    abstract boolean unBlockMember(Member member);
}

abstract class Member extends Account {
    private Date dateOfMembership;
    private int totalBooksCheckedout;

    abstract int getTotalBooksCheckedout();

    abstract boolean reserveBookItem(BookItem bookItem);

    abstract void incrementTotalBooksCheckedout();

    public boolean checkoutBookItem(BookItem bookItem) {
        if (this.getTotalBooksCheckedOut() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
            ShowError("The user has already checked-out maximum number of books");
            return false;
        }
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
            // book item has a pending reservation from another user
            ShowError("This book is reserved by another member");
            return false;
        } else if (bookReservation != null) {
            // book item has a pending reservation from the give member, update it
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }

        if (!bookItem.checkout(this.getId())) {
            return false;
        }

        this.incrementTotalBooksCheckedout();
        return true;
    }

    private void checkForFine(String bookItemBarcode) {
        BookLending bookLending = BookLending.fetchLendingDetails(bookItemBarcode);
        Date dueDate = bookLending.getDueDate();
        Date today = new Date();
        // check if the book has been returned within the due date
        if (today.compareTo(dueDate) > 0) {
            long diff = todayDate.getTime() - dueDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Fine.collectFine(memberId, diffDays);
        }
    }

    public void returnBookItem(BookItem bookItem) {
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null) {
            // book item has a pending reservation
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
        }
        bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
    }

    public boolean renewBookItem(BookItem bookItem) {
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        // check if this book item has a pending reservation from another member
        if (bookReservation != null && bookReservation.getMemberId() != this.getMemberId()) {
            ShowError("This book is reserved by another member");
            member.decrementTotalBooksCheckedout();
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
            return false;
        } else if (bookReservation != null) {
            // book item has a pending reservation from this member
            bookReservation.updateStatus(ReservationStatus.COMPLETED);
        }
        BookLending.lendBook(bookItem.getBarCode(), this.getMemberId());
        bookItem.updateDueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS));
        return true;
    }
}

abstract class BookReservation {
    private Date creationDate;
    private ReservationStatus status;
    private String bookItemBarcode;
    private String memberId;

    abstract BookReservation fetchReservationDetails(String barcode);
}

abstract class BookLending {
    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private String bookItemBarcode;
    private String memberId;

    abstract boolean lendBook(String barcode, String memberId);
    abstract BookLending fetchLendingDetails(String barcode);
}

class Fine {
    private Date creationDate;
    private double bookItemBarcode;
    private String memberId;

    public static void collectFine(String memberId, long days) {}
}

public abstract class Book {
    private String ISBN;
    private String title;
    private String subject;
    private String publisher;
    private String language;
    private int numberOfPages;
    private List<Author> authors;
}

class BookItem extends Book {
    private String barcode;
    private boolean isReferenceOnly;
    private Date borrowed;
    private Date dueDate;
    private double price;
    private BookFormat format;
    private BookStatus status;
    private Date dateOfPurchase;
    private Date publicationDate;
    private Rack placedAt;

    public boolean checkout(String memberId) {
        if(bookItem.getIsReferenceOnly()) {
            ShowError("This book is Reference only and can't be issued");
            return false;
        }
        if(!BookLending.lendBook(this.getBarCode(), memberId)){
            return false;
        }
        this.updateBookItemStatus(BookStatus.LOANED);
        return true;
    }
}

class Rack {
    private int number;
    private String locationIdentifier;
}

interface Search {
    List<Book> searchByTitle(String title);
    List<Book> searchByAuthor(String author);
    List<Book> searchBySubject(String subject);
    List<Book> searchByPubDate(Date publishDate);
}

class Catalog implements Search {
    private HashMap<String, List<Book>> bookTitles;
    private HashMap<String, List<Book>> bookAuthors;
    private HashMap<String, List<Book>> bookSubjects;
    private HashMap<String, List<Book>> bookPublicationDates;

    public List<Book> searchByTitle(String query) {
        // return all books containing the string query in their title.
        return bookTitles.get(query);
    }

    public List<Book> searchByAuthor(String query) {
        // return all books containing the string query in their author's name.
        return bookAuthors.get(query);
    }

    @Override
    public List<Book> searchBySubject(String subject) {
        return null;
    }

    @Override
    public List<Book> searchByPubDate(Date publishDate) {
        return null;
    }
}