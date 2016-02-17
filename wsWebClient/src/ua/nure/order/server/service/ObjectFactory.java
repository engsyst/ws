
package ua.nure.order.server.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ua.nure.order.server.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetBookResponse_QNAME = new QName("http://service.server.order.nure.ua/", "getBookResponse");
    private final static QName _GetBook_QNAME = new QName("http://service.server.order.nure.ua/", "getBook");
    private final static QName _ListBooks_QNAME = new QName("http://service.server.order.nure.ua/", "listBooks");
    private final static QName _UpdateBookCount_QNAME = new QName("http://service.server.order.nure.ua/", "updateBookCount");
    private final static QName _AddBookResponse_QNAME = new QName("http://service.server.order.nure.ua/", "addBookResponse");
    private final static QName _UpdateBookCountResponse_QNAME = new QName("http://service.server.order.nure.ua/", "updateBookCountResponse");
    private final static QName _UpdateBook_QNAME = new QName("http://service.server.order.nure.ua/", "updateBook");
    private final static QName _ListBooksResponse_QNAME = new QName("http://service.server.order.nure.ua/", "listBooksResponse");
    private final static QName _AddBook_QNAME = new QName("http://service.server.order.nure.ua/", "addBook");
    private final static QName _DAOException_QNAME = new QName("http://service.server.order.nure.ua/", "DAOException");
    private final static QName _DeleteBook_QNAME = new QName("http://service.server.order.nure.ua/", "deleteBook");
    private final static QName _DeleteBookResponse_QNAME = new QName("http://service.server.order.nure.ua/", "deleteBookResponse");
    private final static QName _UpdateBookResponse_QNAME = new QName("http://service.server.order.nure.ua/", "updateBookResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ua.nure.order.server.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link GetBook }
     * 
     */
    public GetBook createGetBook() {
        return new GetBook();
    }

    /**
     * Create an instance of {@link ListBooks }
     * 
     */
    public ListBooks createListBooks() {
        return new ListBooks();
    }

    /**
     * Create an instance of {@link UpdateBookCount }
     * 
     */
    public UpdateBookCount createUpdateBookCount() {
        return new UpdateBookCount();
    }

    /**
     * Create an instance of {@link AddBookResponse }
     * 
     */
    public AddBookResponse createAddBookResponse() {
        return new AddBookResponse();
    }

    /**
     * Create an instance of {@link UpdateBookCountResponse }
     * 
     */
    public UpdateBookCountResponse createUpdateBookCountResponse() {
        return new UpdateBookCountResponse();
    }

    /**
     * Create an instance of {@link UpdateBook }
     * 
     */
    public UpdateBook createUpdateBook() {
        return new UpdateBook();
    }

    /**
     * Create an instance of {@link ListBooksResponse }
     * 
     */
    public ListBooksResponse createListBooksResponse() {
        return new ListBooksResponse();
    }

    /**
     * Create an instance of {@link AddBook }
     * 
     */
    public AddBook createAddBook() {
        return new AddBook();
    }

    /**
     * Create an instance of {@link DAOException }
     * 
     */
    public DAOException createDAOException() {
        return new DAOException();
    }

    /**
     * Create an instance of {@link DeleteBook }
     * 
     */
    public DeleteBook createDeleteBook() {
        return new DeleteBook();
    }

    /**
     * Create an instance of {@link DeleteBookResponse }
     * 
     */
    public DeleteBookResponse createDeleteBookResponse() {
        return new DeleteBookResponse();
    }

    /**
     * Create an instance of {@link UpdateBookResponse }
     * 
     */
    public UpdateBookResponse createUpdateBookResponse() {
        return new UpdateBookResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "getBookResponse")
    public JAXBElement<GetBookResponse> createGetBookResponse(GetBookResponse value) {
        return new JAXBElement<GetBookResponse>(_GetBookResponse_QNAME, GetBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "getBook")
    public JAXBElement<GetBook> createGetBook(GetBook value) {
        return new JAXBElement<GetBook>(_GetBook_QNAME, GetBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "listBooks")
    public JAXBElement<ListBooks> createListBooks(ListBooks value) {
        return new JAXBElement<ListBooks>(_ListBooks_QNAME, ListBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "updateBookCount")
    public JAXBElement<UpdateBookCount> createUpdateBookCount(UpdateBookCount value) {
        return new JAXBElement<UpdateBookCount>(_UpdateBookCount_QNAME, UpdateBookCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "addBookResponse")
    public JAXBElement<AddBookResponse> createAddBookResponse(AddBookResponse value) {
        return new JAXBElement<AddBookResponse>(_AddBookResponse_QNAME, AddBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "updateBookCountResponse")
    public JAXBElement<UpdateBookCountResponse> createUpdateBookCountResponse(UpdateBookCountResponse value) {
        return new JAXBElement<UpdateBookCountResponse>(_UpdateBookCountResponse_QNAME, UpdateBookCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "updateBook")
    public JAXBElement<UpdateBook> createUpdateBook(UpdateBook value) {
        return new JAXBElement<UpdateBook>(_UpdateBook_QNAME, UpdateBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "listBooksResponse")
    public JAXBElement<ListBooksResponse> createListBooksResponse(ListBooksResponse value) {
        return new JAXBElement<ListBooksResponse>(_ListBooksResponse_QNAME, ListBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "addBook")
    public JAXBElement<AddBook> createAddBook(AddBook value) {
        return new JAXBElement<AddBook>(_AddBook_QNAME, AddBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DAOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "DAOException")
    public JAXBElement<DAOException> createDAOException(DAOException value) {
        return new JAXBElement<DAOException>(_DAOException_QNAME, DAOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "deleteBook")
    public JAXBElement<DeleteBook> createDeleteBook(DeleteBook value) {
        return new JAXBElement<DeleteBook>(_DeleteBook_QNAME, DeleteBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "deleteBookResponse")
    public JAXBElement<DeleteBookResponse> createDeleteBookResponse(DeleteBookResponse value) {
        return new JAXBElement<DeleteBookResponse>(_DeleteBookResponse_QNAME, DeleteBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.order.nure.ua/", name = "updateBookResponse")
    public JAXBElement<UpdateBookResponse> createUpdateBookResponse(UpdateBookResponse value) {
        return new JAXBElement<UpdateBookResponse>(_UpdateBookResponse_QNAME, UpdateBookResponse.class, null, value);
    }

}
