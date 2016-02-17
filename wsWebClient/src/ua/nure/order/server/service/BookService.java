package ua.nure.order.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2016-02-17T09:48:13.813+02:00
 * Generated source version: 3.0.2
 * 
 */
@WebService(targetNamespace = "http://service.server.order.nure.ua/", name = "BookService")
@XmlSeeAlso({ObjectFactory.class, ua.nure.order.entity.ObjectFactory.class, ua.nure.order.entity.book.ObjectFactory.class})
public interface BookService {

    @WebMethod(action = "urn:DeleteBook")
    @RequestWrapper(localName = "deleteBook", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.DeleteBook")
    @ResponseWrapper(localName = "deleteBookResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.DeleteBookResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ua.nure.order.entity.book.Book deleteBook(
        @WebParam(name = "id", targetNamespace = "")
        int id
    ) throws DAOException_Exception;

    @WebMethod(action = "urn:GetBook")
    @RequestWrapper(localName = "getBook", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.GetBook")
    @ResponseWrapper(localName = "getBookResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.GetBookResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ua.nure.order.entity.book.Book getBook(
        @WebParam(name = "id", targetNamespace = "")
        int id
    ) throws DAOException_Exception;

    @WebMethod(action = "urn:UpdateBookCount")
    @RequestWrapper(localName = "updateBookCount", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.UpdateBookCount")
    @ResponseWrapper(localName = "updateBookCountResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.UpdateBookCountResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean updateBookCount(
        @WebParam(name = "id", targetNamespace = "")
        int id,
        @WebParam(name = "count", targetNamespace = "")
        int count
    ) throws DAOException_Exception;

    @WebMethod(action = "urn:ListBooks")
    @RequestWrapper(localName = "listBooks", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.ListBooks")
    @ResponseWrapper(localName = "listBooksResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.ListBooksResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ua.nure.order.entity.book.Book> listBooks(
        @WebParam(name = "pattern", targetNamespace = "")
        java.lang.String pattern
    );

    @WebMethod(action = "urn:UpdateBook")
    @RequestWrapper(localName = "updateBook", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.UpdateBook")
    @ResponseWrapper(localName = "updateBookResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.UpdateBookResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ua.nure.order.entity.book.Book updateBook(
        @WebParam(name = "book", targetNamespace = "")
        ua.nure.order.entity.book.Book book
    ) throws DAOException_Exception;

    @WebMethod(action = "urn:AddBook")
    @RequestWrapper(localName = "addBook", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.AddBook")
    @ResponseWrapper(localName = "addBookResponse", targetNamespace = "http://service.server.order.nure.ua/", className = "ua.nure.order.server.service.AddBookResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ua.nure.order.entity.book.Book addBook(
        @WebParam(name = "book", targetNamespace = "")
        ua.nure.order.entity.book.Book book
    ) throws DAOException_Exception;
}
