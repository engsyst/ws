CREATE VIEW `books` AS SELECT book.id, book.title, concatauthors(book.id) as `authors`, book.isbn, book.price, book.count
  from book;
