//
// Definitions for schema: http://service.server.order.nure.ua/
//  http://localhost:8080/ws/services/BookService?xsd=bookserviceimpl_schema1.xsd
//
//
// Constructor for XML Schema item {http://service.server.order.nure.ua/}getBookResponse
//
function getBookResponse() {
	this.typeMarker = 'getBookResponse';
	this._return = null;
}

//
// accessor is getBookResponse.prototype.getReturn
// element get for return
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for return
// setter function is is
// getBookResponse.prototype.setReturn
//
function getBookResponse_getReturn() {
	return this._return;
}

getBookResponse.prototype.getReturn = getBookResponse_getReturn;

function getBookResponse_setReturn(value) {
	this._return = value;
}

getBookResponse.prototype.setReturn = getBookResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}getBookResponse
//
function getBookResponse_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._return != null) {
			xml = xml + this._return.serialize(cxfjsutils, 'return', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

getBookResponse.prototype.serialize = getBookResponse_serialize;

function getBookResponse_deserialize(cxfjsutils,
		element) {
	var newobject = new getBookResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setReturn(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}addBookResponse
//
function addBookResponse() {
	this.typeMarker = 'addBookResponse';
	this._return = null;
}

//
// accessor is addBookResponse.prototype.getReturn
// element get for return
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for return
// setter function is is
// addBookResponse.prototype.setReturn
//
function addBookResponse_getReturn() {
	return this._return;
}

addBookResponse.prototype.getReturn = addBookResponse_getReturn;

function addBookResponse_setReturn(value) {
	this._return = value;
}

addBookResponse.prototype.setReturn = addBookResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}addBookResponse
//
function addBookResponse_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._return != null) {
			xml = xml + this._return.serialize(cxfjsutils, 'return', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

addBookResponse.prototype.serialize = addBookResponse_serialize;

function addBookResponse_deserialize(cxfjsutils,
		element) {
	var newobject = new addBookResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setReturn(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item {http://service.server.order.nure.ua/}getBook
//
function getBook() {
	this.typeMarker = 'getBook';
	this._id = 0;
}

//
// accessor is getBook.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for id
// setter function is is getBook.prototype.setId
//
function getBook_getId() {
	return this._id;
}

getBook.prototype.getId = getBook_getId;

function getBook_setId(value) {
	this._id = value;
}

getBook.prototype.setId = getBook_setId;
//
// Serialize {http://service.server.order.nure.ua/}getBook
//
function getBook_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		xml = xml + '<id>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._id);
		xml = xml + '</id>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

getBook.prototype.serialize = getBook_serialize;

function getBook_deserialize(cxfjsutils, element) {
	var newobject = new getBook();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing id');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseInt(value);
	}
	newobject.setId(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}listBooksResponse
//
function listBooksResponse() {
	this.typeMarker = 'listBooksResponse';
	this._return = [];
}

//
// accessor is
// listBooksResponse.prototype.getReturn
// element get for return
// - element type is {http://order.nure.ua/entity/book/}Book
// - required element
// - array
//
// element set for return
// setter function is is
// listBooksResponse.prototype.setReturn
//
function listBooksResponse_getReturn() {
	return this._return;
}

listBooksResponse.prototype.getReturn = listBooksResponse_getReturn;

function listBooksResponse_setReturn(value) {
	this._return = value;
}

listBooksResponse.prototype.setReturn = listBooksResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}listBooksResponse
//
function listBooksResponse_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._return != null) {
			for (var ax = 0; ax < this._return.length; ax++) {
				if (this._return[ax] == null) {
					xml = xml + '<return/>';
				} else {
					xml = xml
							+ this._return[ax].serialize(cxfjsutils, 'return',
									null);
				}
			}
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

listBooksResponse.prototype.serialize = listBooksResponse_serialize;

function listBooksResponse_deserialize(
		cxfjsutils, element) {
	var newobject = new listBooksResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
		item = [];
		do {
			var arrayItem = null;
			var value = null;
			if (!cxfjsutils.isElementNil(curElement)) {
				arrayItem = Book_deserialize(
						cxfjsutils, curElement);
			}
			item.push(arrayItem);
			curElement = cxfjsutils.getNextElementSibling(curElement);
		} while (curElement != null
				&& cxfjsutils.isNodeNamedNS(curElement, '', 'return'));
		newobject.setReturn(item);
		var item = null;
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}updateBookCount
//
function updateBookCount() {
	this.typeMarker = 'updateBookCount';
	this._id = 0;
	this._count = 0;
}

//
// accessor is updateBookCount.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for id
// setter function is is
// updateBookCount.prototype.setId
//
function updateBookCount_getId() {
	return this._id;
}

updateBookCount.prototype.getId = updateBookCount_getId;

function updateBookCount_setId(value) {
	this._id = value;
}

updateBookCount.prototype.setId = updateBookCount_setId;
//
// accessor is updateBookCount.prototype.getCount
// element get for count
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for count
// setter function is is
// updateBookCount.prototype.setCount
//
function updateBookCount_getCount() {
	return this._count;
}

updateBookCount.prototype.getCount = updateBookCount_getCount;

function updateBookCount_setCount(value) {
	this._count = value;
}

updateBookCount.prototype.setCount = updateBookCount_setCount;
//
// Serialize {http://service.server.order.nure.ua/}updateBookCount
//
function updateBookCount_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		xml = xml + '<id>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._id);
		xml = xml + '</id>';
	}
	// block for local variables
	{
		xml = xml + '<count>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._count);
		xml = xml + '</count>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

updateBookCount.prototype.serialize = updateBookCount_serialize;

function updateBookCount_deserialize(cxfjsutils,
		element) {
	var newobject = new updateBookCount();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing id');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseInt(value);
	}
	newobject.setId(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing count');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseInt(value);
	}
	newobject.setCount(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}updateBookCountResponse
//
function updateBookCountResponse() {
	this.typeMarker = 'updateBookCountResponse';
	this._return = '';
}

//
// accessor is
// updateBookCountResponse.prototype.getReturn
// element get for return
// - element type is {http://www.w3.org/2001/XMLSchema}boolean
// - required element
//
// element set for return
// setter function is is
// updateBookCountResponse.prototype.setReturn
//
function updateBookCountResponse_getReturn() {
	return this._return;
}

updateBookCountResponse.prototype.getReturn = updateBookCountResponse_getReturn;

function updateBookCountResponse_setReturn(value) {
	this._return = value;
}

updateBookCountResponse.prototype.setReturn = updateBookCountResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}updateBookCountResponse
//
function updateBookCountResponse_serialize(
		cxfjsutils, elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		xml = xml + '<return>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._return);
		xml = xml + '</return>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

updateBookCountResponse.prototype.serialize = updateBookCountResponse_serialize;

function updateBookCountResponse_deserialize(
		cxfjsutils, element) {
	var newobject = new updateBookCountResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = (value == 'true');
	}
	newobject.setReturn(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}updateBookResponse
//
function updateBookResponse() {
	this.typeMarker = 'updateBookResponse';
	this._return = null;
}

//
// accessor is
// updateBookResponse.prototype.getReturn
// element get for return
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for return
// setter function is is
// updateBookResponse.prototype.setReturn
//
function updateBookResponse_getReturn() {
	return this._return;
}

updateBookResponse.prototype.getReturn = updateBookResponse_getReturn;

function updateBookResponse_setReturn(value) {
	this._return = value;
}

updateBookResponse.prototype.setReturn = updateBookResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}updateBookResponse
//
function updateBookResponse_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._return != null) {
			xml = xml + this._return.serialize(cxfjsutils, 'return', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

updateBookResponse.prototype.serialize = updateBookResponse_serialize;

function updateBookResponse_deserialize(
		cxfjsutils, element) {
	var newobject = new updateBookResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setReturn(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}deleteBookResponse
//
function deleteBookResponse() {
	this.typeMarker = 'deleteBookResponse';
	this._return = null;
}

//
// accessor is
// deleteBookResponse.prototype.getReturn
// element get for return
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for return
// setter function is is
// deleteBookResponse.prototype.setReturn
//
function deleteBookResponse_getReturn() {
	return this._return;
}

deleteBookResponse.prototype.getReturn = deleteBookResponse_getReturn;

function deleteBookResponse_setReturn(value) {
	this._return = value;
}

deleteBookResponse.prototype.setReturn = deleteBookResponse_setReturn;
//
// Serialize {http://service.server.order.nure.ua/}deleteBookResponse
//
function deleteBookResponse_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._return != null) {
			xml = xml + this._return.serialize(cxfjsutils, 'return', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

deleteBookResponse.prototype.serialize = deleteBookResponse_serialize;

function deleteBookResponse_deserialize(
		cxfjsutils, element) {
	var newobject = new deleteBookResponse();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing return');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'return')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setReturn(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}updateBook
//
function updateBook() {
	this.typeMarker = 'updateBook';
	this._book = null;
}

//
// accessor is updateBook.prototype.getBook
// element get for book
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for book
// setter function is is
// updateBook.prototype.setBook
//
function updateBook_getBook() {
	return this._book;
}

updateBook.prototype.getBook = updateBook_getBook;

function updateBook_setBook(value) {
	this._book = value;
}

updateBook.prototype.setBook = updateBook_setBook;
//
// Serialize {http://service.server.order.nure.ua/}updateBook
//
function updateBook_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._book != null) {
			xml = xml + this._book.serialize(cxfjsutils, 'book', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

updateBook.prototype.serialize = updateBook_serialize;

function updateBook_deserialize(cxfjsutils,
		element) {
	var newobject = new updateBook();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing book');
	if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'book')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setBook(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item {http://service.server.order.nure.ua/}addBook
//
function addBook() {
	this.typeMarker = 'addBook';
	this._book = null;
}

//
// accessor is addBook.prototype.getBook
// element get for book
// - element type is {http://order.nure.ua/entity/book/}Book
// - optional element
//
// element set for book
// setter function is is addBook.prototype.setBook
//
function addBook_getBook() {
	return this._book;
}

addBook.prototype.getBook = addBook_getBook;

function addBook_setBook(value) {
	this._book = value;
}

addBook.prototype.setBook = addBook_setBook;
//
// Serialize {http://service.server.order.nure.ua/}addBook
//
function addBook_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._book != null) {
			xml = xml + this._book.serialize(cxfjsutils, 'book', null);
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

addBook.prototype.serialize = addBook_serialize;

function addBook_deserialize(cxfjsutils, element) {
	var newobject = new addBook();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing book');
	if (curElement != null && cxfjsutils.isNodeNamedNS(curElement, '', 'book')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			item = Book_deserialize(cxfjsutils,
					curElement);
		}
		newobject.setBook(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}listBooks
//
function listBooks() {
	this.typeMarker = 'listBooks';
	this._pattern = null;
}

//
// accessor is listBooks.prototype.getPattern
// element get for pattern
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for pattern
// setter function is is
// listBooks.prototype.setPattern
//
function listBooks_getPattern() {
	return this._pattern;
}

listBooks.prototype.getPattern = listBooks_getPattern;

function listBooks_setPattern(value) {
	this._pattern = value;
}

listBooks.prototype.setPattern = listBooks_setPattern;
//
// Serialize {http://service.server.order.nure.ua/}listBooks
//
function listBooks_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._pattern != null) {
			xml = xml + '<pattern>';
			xml = xml + cxfjsutils.escapeXmlEntities(this._pattern);
			xml = xml + '</pattern>';
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

listBooks.prototype.serialize = listBooks_serialize;

function listBooks_deserialize(cxfjsutils,
		element) {
	var newobject = new listBooks();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing pattern');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'pattern')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			value = cxfjsutils.getNodeText(curElement);
			item = value;
		}
		newobject.setPattern(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}deleteBook
//
function deleteBook() {
	this.typeMarker = 'deleteBook';
	this._id = 0;
}

//
// accessor is deleteBook.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for id
// setter function is is
// deleteBook.prototype.setId
//
function deleteBook_getId() {
	return this._id;
}

deleteBook.prototype.getId = deleteBook_getId;

function deleteBook_setId(value) {
	this._id = value;
}

deleteBook.prototype.setId = deleteBook_setId;
//
// Serialize {http://service.server.order.nure.ua/}deleteBook
//
function deleteBook_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		xml = xml + '<id>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._id);
		xml = xml + '</id>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

deleteBook.prototype.serialize = deleteBook_serialize;

function deleteBook_deserialize(cxfjsutils,
		element) {
	var newobject = new deleteBook();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing id');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseInt(value);
	}
	newobject.setId(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	return newobject;
}

//
// Constructor for XML Schema item
// {http://service.server.order.nure.ua/}DAOException
//
function DAOException() {
	this.typeMarker = 'DAOException';
	this._message = null;
}

//
// accessor is DAOException.prototype.getMessage
// element get for message
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for message
// setter function is is
// DAOException.prototype.setMessage
//
function DAOException_getMessage() {
	return this._message;
}

DAOException.prototype.getMessage = DAOException_getMessage;

function DAOException_setMessage(value) {
	this._message = value;
}

DAOException.prototype.setMessage = DAOException_setMessage;
//
// Serialize {http://service.server.order.nure.ua/}DAOException
//
function DAOException_serialize(cxfjsutils,
		elementName, extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		if (this._message != null) {
			xml = xml + '<message>';
			xml = xml + cxfjsutils.escapeXmlEntities(this._message);
			xml = xml + '</message>';
		}
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

DAOException.prototype.serialize = DAOException_serialize;

function DAOException_deserialize(cxfjsutils,
		element) {
	var newobject = new DAOException();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing message');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement, '', 'message')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			value = cxfjsutils.getNodeText(curElement);
			item = value;
		}
		newobject.setMessage(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	return newobject;
}

//
// Definitions for schema: null
// http://localhost:8080/ws/services/BookService?wsdl#types1
//
//
// Definitions for schema: http://order.nure.ua/entity/book/
// http://localhost:8080/ws/services/BookService?xsd=bookserviceimpl_schema3.xsd
//
//
// Constructor for XML Schema item {http://order.nure.ua/entity/book/}Book
//
function Book() {
	this.typeMarker = 'Book';
	this._title = '';
	this._author = [];
	this._isbn = null;
	this._price = 0.0;
	this._category = 'none';
	this._count = 0;
	this._id = null;
}

//
// accessor is Book.prototype.getTitle
// element get for title
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
//
// element set for title
// setter function is is Book.prototype.setTitle
//
function Book_getTitle() {
	return this._title;
}

Book.prototype.getTitle = Book_getTitle;

function Book_setTitle(value) {
	this._title = value;
}

Book.prototype.setTitle = Book_setTitle;
//
// accessor is Book.prototype.getAuthor
// element get for author
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - required element
// - array
//
// element set for author
// setter function is is Book.prototype.setAuthor
//
function Book_getAuthor() {
	return this._author;
}

Book.prototype.getAuthor = Book_getAuthor;

function Book_setAuthor(value) {
	this._author = value;
}

Book.prototype.setAuthor = Book_setAuthor;
//
// accessor is Book.prototype.getIsbn
// element get for isbn
// - element type is {http://www.w3.org/2001/XMLSchema}string
// - optional element
//
// element set for isbn
// setter function is is Book.prototype.setIsbn
//
function Book_getIsbn() {
	return this._isbn;
}

Book.prototype.getIsbn = Book_getIsbn;

function Book_setIsbn(value) {
	this._isbn = value;
}

Book.prototype.setIsbn = Book_setIsbn;
//
// accessor is Book.prototype.getPrice
// element get for price
// - element type is {http://www.w3.org/2001/XMLSchema}double
// - required element
//
// element set for price
// setter function is is Book.prototype.setPrice
//
function Book_getPrice() {
	return this._price;
}

Book.prototype.getPrice = Book_getPrice;

function Book_setPrice(value) {
	this._price = value;
}

Book.prototype.setPrice = Book_setPrice;
//
// accessor is Book.prototype.getCategory
// element get for category
// - element type is {http://order.nure.ua/entity/book/}Category
// - required element
//
// element set for category
// setter function is is Book.prototype.setCategory
//
function Book_getCategory() {
	return this._category;
}

Book.prototype.getCategory = Book_getCategory;

function Book_setCategory(value) {
	this._category = value;
}

Book.prototype.setCategory = Book_setCategory;
//
// accessor is Book.prototype.getCount
// element get for count
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - required element
//
// element set for count
// setter function is is Book.prototype.setCount
//
function Book_getCount() {
	return this._count;
}

Book.prototype.getCount = Book_getCount;

function Book_setCount(value) {
	this._count = value;
}

Book.prototype.setCount = Book_setCount;
//
// accessor is Book.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - optional element
//
// element set for id
// setter function is is Book.prototype.setId
//
function Book_getId() {
	return this._id;
}

Book.prototype.getId = Book_getId;

function Book_setId(value) {
	this._id = value;
}

Book.prototype.setId = Book_setId;
//
// Serialize {http://order.nure.ua/entity/book/}Book
//
function Book_serialize(cxfjsutils, elementName,
		extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		xml = xml + ' ';
		xml = xml
				+ 'xmlns:jns0=\'http://order.nure.ua/entity/book/\' xmlns:jns1=\'http://order.nure.ua/entity/\' ';
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	// block for local variables
	{
		xml = xml + '<jns0:title>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._title);
		xml = xml + '</jns0:title>';
	}
	// block for local variables
	{
		if (this._author != null) {
			for (var ax = 0; ax < this._author.length; ax++) {
				if (this._author[ax] == null) {
					xml = xml + '<jns0:author/>';
				} else {
					xml = xml + '<jns0:author>';
					xml = xml + cxfjsutils.escapeXmlEntities(this._author[ax]);
					xml = xml + '</jns0:author>';
				}
			}
		}
	}
	// block for local variables
	{
		if (this._isbn != null) {
			xml = xml + '<jns0:isbn>';
			xml = xml + cxfjsutils.escapeXmlEntities(this._isbn);
			xml = xml + '</jns0:isbn>';
		}
	}
	// block for local variables
	{
		xml = xml + '<jns0:price>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._price);
		xml = xml + '</jns0:price>';
	}
	// block for local variables
	{
		xml = xml + '<jns0:category>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._category);
		xml = xml + '</jns0:category>';
	}
	// block for local variables
	{
		xml = xml + '<jns0:count>';
		xml = xml + cxfjsutils.escapeXmlEntities(this._count);
		xml = xml + '</jns0:count>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

Book.prototype.serialize = Book_serialize;

function Book_deserialize(cxfjsutils, element) {
	var newobject = new Book();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing title');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = value;
	}
	newobject.setTitle(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing author');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement,
					'http://order.nure.ua/entity/book/', 'author')) {
		item = [];
		do {
			var arrayItem = null;
			var value = null;
			if (!cxfjsutils.isElementNil(curElement)) {
				value = cxfjsutils.getNodeText(curElement);
				arrayItem = value;
			}
			item.push(arrayItem);
			curElement = cxfjsutils.getNextElementSibling(curElement);
		} while (curElement != null
				&& cxfjsutils.isNodeNamedNS(curElement,
						'http://order.nure.ua/entity/book/', 'author'));
		newobject.setAuthor(item);
		var item = null;
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing isbn');
	if (curElement != null
			&& cxfjsutils.isNodeNamedNS(curElement,
					'http://order.nure.ua/entity/book/', 'isbn')) {
		var value = null;
		if (!cxfjsutils.isElementNil(curElement)) {
			value = cxfjsutils.getNodeText(curElement);
			item = value;
		}
		newobject.setIsbn(item);
		var item = null;
		if (curElement != null) {
			curElement = cxfjsutils.getNextElementSibling(curElement);
		}
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing price');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseFloat(value);
	}
	newobject.setPrice(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing category');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = value;
	}
	newobject.setCategory(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	cxfjsutils.trace('curElement: ' + cxfjsutils.traceElementName(curElement));
	cxfjsutils.trace('processing count');
	var value = null;
	if (!cxfjsutils.isElementNil(curElement)) {
		value = cxfjsutils.getNodeText(curElement);
		item = parseInt(value);
	}
	newobject.setCount(item);
	var item = null;
	if (curElement != null) {
		curElement = cxfjsutils.getNextElementSibling(curElement);
	}
	return newobject;
}

//
// Simple type (enumeration) {http://order.nure.ua/entity/book/}Category
//
// - Fantasy
// - Action
// - Love novel
// - none
//
// Definitions for schema: http://order.nure.ua/entity/
// http://localhost:8080/ws/services/BookService?xsd=bookserviceimpl_schema4.xsd
//
//
// Constructor for XML Schema item {http://order.nure.ua/entity/}Entity
//
function Entity() {
	this.typeMarker = 'Entity';
	this._id = null;
}

//
// accessor is Entity.prototype.getId
// element get for id
// - element type is {http://www.w3.org/2001/XMLSchema}int
// - optional element
//
// element set for id
// setter function is is Entity.prototype.setId
//
function Entity_getId() {
	return this._id;
}

Entity.prototype.getId = Entity_getId;

function Entity_setId(value) {
	this._id = value;
}

Entity.prototype.setId = Entity_setId;
//
// Serialize {http://order.nure.ua/entity/}Entity
//
function Entity_serialize(cxfjsutils, elementName,
		extraNamespaces) {
	var xml = '';
	if (elementName != null) {
		xml = xml + '<';
		xml = xml + elementName;
		xml = xml + ' ';
		xml = xml
				+ 'xmlns:jns0=\'http://order.nure.ua/entity/book/\' xmlns:jns1=\'http://order.nure.ua/entity/\' ';
		if (extraNamespaces) {
			xml = xml + ' ' + extraNamespaces;
		}
		xml = xml + '>';
	}
	if (elementName != null) {
		xml = xml + '</';
		xml = xml + elementName;
		xml = xml + '>';
	}
	return xml;
}

Entity.prototype.serialize = Entity_serialize;

function Entity_deserialize(cxfjsutils, element) {
	var newobject = new Entity();
	cxfjsutils.trace('element: ' + cxfjsutils.traceElementName(element));
	var curElement = cxfjsutils.getFirstElementChild(element);
	var item;
	return newobject;
}

//
// Definitions for service: {http://service.server.order.nure.ua/}BookService
//

// Javascript for {http://service.server.order.nure.ua/}BookService

function BookService() {
	this.jsutils = new CxfApacheOrgUtil();
	this.jsutils.interfaceObject = this;
	this.synchronous = false;
	this.url = null;
	this.client = null;
	this.response = null;
	this.globalElementSerializers = [];
	this.globalElementDeserializers = [];
	this.globalElementSerializers['{http://service.server.order.nure.ua/}getBookResponse'] = getBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}getBookResponse'] = getBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}addBookResponse'] = addBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}addBookResponse'] = addBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}listBooksResponse'] = listBooksResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}listBooksResponse'] = listBooksResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}getBook'] = getBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}getBook'] = getBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookCount'] = updateBookCount_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookCount'] = updateBookCount_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookCountResponse'] = updateBookCountResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookCountResponse'] = updateBookCountResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookResponse'] = updateBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookResponse'] = updateBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBook'] = updateBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBook'] = updateBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}deleteBookResponse'] = deleteBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}deleteBookResponse'] = deleteBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}addBook'] = addBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}addBook'] = addBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}listBooks'] = listBooks_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}listBooks'] = listBooks_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}deleteBook'] = deleteBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}deleteBook'] = deleteBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}DAOException'] = DAOException_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}DAOException'] = DAOException_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}getBookResponse'] = getBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}getBookResponse'] = getBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}addBookResponse'] = addBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}addBookResponse'] = addBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}getBook'] = getBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}getBook'] = getBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}listBooksResponse'] = listBooksResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}listBooksResponse'] = listBooksResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookCount'] = updateBookCount_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookCount'] = updateBookCount_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookCountResponse'] = updateBookCountResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookCountResponse'] = updateBookCountResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBookResponse'] = updateBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBookResponse'] = updateBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}deleteBookResponse'] = deleteBookResponse_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}deleteBookResponse'] = deleteBookResponse_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}updateBook'] = updateBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}updateBook'] = updateBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}addBook'] = addBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}addBook'] = addBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}listBooks'] = listBooks_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}listBooks'] = listBooks_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}deleteBook'] = deleteBook_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}deleteBook'] = deleteBook_deserialize;
	this.globalElementSerializers['{http://service.server.order.nure.ua/}DAOException'] = DAOException_serialize;
	this.globalElementDeserializers['{http://service.server.order.nure.ua/}DAOException'] = DAOException_deserialize;
	this.globalElementSerializers['{http://order.nure.ua/entity/book/}Book'] = Book_serialize;
	this.globalElementDeserializers['{http://order.nure.ua/entity/book/}Book'] = Book_deserialize;
	this.globalElementSerializers['{http://order.nure.ua/entity/}Entity'] = Entity_serialize;
	this.globalElementDeserializers['{http://order.nure.ua/entity/}Entity'] = Entity_deserialize;
}

function updateBook_op_onsuccess(client,
		responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling updateBookResponse_deserializeResponse');
		responseObject = updateBookResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.updateBook_onsuccess = updateBook_op_onsuccess;

function updateBook_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.updateBook_onerror = updateBook_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}updateBook
// Wrapped operation.
// parameter book
// - Object constructor is Book
//
function updateBook_op(successCallback,
		errorCallback, book) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(1);
	args[0] = book;
	xml = this.updateBook_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.updateBook_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.updateBook_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:UpdateBook';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.updateBook = updateBook_op;

function updateBook_serializeInput(cxfjsutils,
		args) {
	var wrapperObj = new updateBook();
	wrapperObj.setBook(args[0]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml + wrapperObj.serialize(cxfjsutils, 'jns2:updateBook', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.updateBook_serializeInput = updateBook_serializeInput;

function updateBookResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = updateBookResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function listBooks_op_onsuccess(client,
		responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling listBooksResponse_deserializeResponse');
		responseObject = listBooksResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.listBooks_onsuccess = listBooks_op_onsuccess;

function listBooks_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.listBooks_onerror = listBooks_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}listBooks
// Wrapped operation.
// parameter pattern
// - simple type {http://www.w3.org/2001/XMLSchema}string//
function listBooks_op(successCallback,
		errorCallback, pattern) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(1);
	args[0] = pattern;
	xml = this.listBooks_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.listBooks_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.listBooks_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:ListBooks';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.listBooks = listBooks_op;

function listBooks_serializeInput(cxfjsutils,
		args) {
	var wrapperObj = new listBooks();
	wrapperObj.setPattern(args[0]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml + wrapperObj.serialize(cxfjsutils, 'jns2:listBooks', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.listBooks_serializeInput = listBooks_serializeInput;

function listBooksResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = listBooksResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function addBook_op_onsuccess(client, responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling addBookResponse_deserializeResponse');
		responseObject = addBookResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.addBook_onsuccess = addBook_op_onsuccess;

function addBook_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.addBook_onerror = addBook_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}addBook
// Wrapped operation.
// parameter book
// - Object constructor is Book
//
function addBook_op(successCallback,
		errorCallback, book) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(1);
	args[0] = book;
	xml = this.addBook_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.addBook_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.addBook_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:AddBook';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.addBook = addBook_op;

function addBook_serializeInput(cxfjsutils, args) {
	var wrapperObj = new addBook();
	wrapperObj.setBook(args[0]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml + wrapperObj.serialize(cxfjsutils, 'jns2:addBook', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.addBook_serializeInput = addBook_serializeInput;

function addBookResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = addBookResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function updateBookCount_op_onsuccess(client,
		responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling updateBookCountResponse_deserializeResponse');
		responseObject = updateBookCountResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.updateBookCount_onsuccess = updateBookCount_op_onsuccess;

function updateBookCount_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.updateBookCount_onerror = updateBookCount_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}updateBookCount
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}int// parameter count
// - simple type {http://www.w3.org/2001/XMLSchema}int//
function updateBookCount_op(successCallback,
		errorCallback, id, count) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(2);
	args[0] = id;
	args[1] = count;
	xml = this.updateBookCount_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.updateBookCount_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.updateBookCount_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:UpdateBookCount';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.updateBookCount = updateBookCount_op;

function updateBookCount_serializeInput(
		cxfjsutils, args) {
	var wrapperObj = new updateBookCount();
	wrapperObj.setId(args[0]);
	wrapperObj.setCount(args[1]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml
				+ wrapperObj
						.serialize(cxfjsutils, 'jns2:updateBookCount', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.updateBookCount_serializeInput = updateBookCount_serializeInput;

function updateBookCountResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = updateBookCountResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function getBook_op_onsuccess(client, responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling getBookResponse_deserializeResponse');
		responseObject = getBookResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.getBook_onsuccess = getBook_op_onsuccess;

function getBook_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.getBook_onerror = getBook_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}getBook
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}int//
function getBook_op(successCallback,
		errorCallback, id) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(1);
	args[0] = id;
	xml = this.getBook_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.getBook_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.getBook_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:GetBook';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.getBook = getBook_op;

function getBook_serializeInput(cxfjsutils, args) {
	var wrapperObj = new getBook();
	wrapperObj.setId(args[0]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml + wrapperObj.serialize(cxfjsutils, 'jns2:getBook', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.getBook_serializeInput = getBook_serializeInput;

function getBookResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = getBookResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function deleteBook_op_onsuccess(client,
		responseXml) {
	if (client.user_onsuccess) {
		var responseObject = null;
		var element = responseXml.documentElement;
		this.jsutils.trace('responseXml: '
				+ this.jsutils.traceElementName(element));
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('first element child: '
				+ this.jsutils.traceElementName(element));
		while (!this.jsutils.isNodeNamedNS(element,
				'http://schemas.xmlsoap.org/soap/envelope/', 'Body')) {
			element = this.jsutils.getNextElementSibling(element);
			if (element == null) {
				throw 'No env:Body in message.'
			}
		}
		element = this.jsutils.getFirstElementChild(element);
		this.jsutils.trace('part element: '
				+ this.jsutils.traceElementName(element));
		this.jsutils
				.trace('calling deleteBookResponse_deserializeResponse');
		responseObject = deleteBookResponse_deserializeResponse(
				this.jsutils, element);
		client.user_onsuccess(responseObject);
	}
}

BookService.prototype.deleteBook_onsuccess = deleteBook_op_onsuccess;

function deleteBook_op_onerror(client) {
	if (client.user_onerror) {
		var httpStatus;
		var httpStatusText;
		try {
			httpStatus = client.req.status;
			httpStatusText = client.req.statusText;
		} catch (e) {
			httpStatus = -1;
			httpStatusText = 'Error opening connection to server';
		}
		client.user_onerror(httpStatus, httpStatusText);
	}
}

BookService.prototype.deleteBook_onerror = deleteBook_op_onerror;

//
// Operation {http://service.server.order.nure.ua/}deleteBook
// Wrapped operation.
// parameter id
// - simple type {http://www.w3.org/2001/XMLSchema}int//
function deleteBook_op(successCallback,
		errorCallback, id) {
	this.client = new CxfApacheOrgClient(this.jsutils);
	var xml = null;
	var args = new Array(1);
	args[0] = id;
	xml = this.deleteBook_serializeInput(this.jsutils, args);
	this.client.user_onsuccess = successCallback;
	this.client.user_onerror = errorCallback;
	var closureThis = this;
	this.client.onsuccess = function(client, responseXml) {
		closureThis.deleteBook_onsuccess(client, responseXml);
	};
	this.client.onerror = function(client) {
		closureThis.deleteBook_onerror(client);
	};
	var requestHeaders = [];
	requestHeaders['SOAPAction'] = 'urn:DeleteBook';
	this.jsutils.trace('synchronous = ' + this.synchronous);
	this.client.request(this.url, xml, null, this.synchronous, requestHeaders);
}

BookService.prototype.deleteBook = deleteBook_op;

function deleteBook_serializeInput(cxfjsutils,
		args) {
	var wrapperObj = new deleteBook();
	wrapperObj.setId(args[0]);
	var xml;
	xml = cxfjsutils
			.beginSoap11Message("xmlns:jns0='http://order.nure.ua/entity/book/' xmlns:jns1='http://order.nure.ua/entity/' xmlns:jns2='http://service.server.order.nure.ua/' ");
	// block for local variables
	{
		xml = xml + wrapperObj.serialize(cxfjsutils, 'jns2:deleteBook', null);
	}
	xml = xml + cxfjsutils.endSoap11Message();
	return xml;
}

BookService.prototype.deleteBook_serializeInput = deleteBook_serializeInput;

function deleteBookResponse_deserializeResponse(
		cxfjsutils, partElement) {
	var returnObject = deleteBookResponse_deserialize(
			cxfjsutils, partElement);

	return returnObject;
}
function BookService_BookServicePort() {
	this.url = 'http://localhost:8080/ws/services/BookService';
}
BookService_BookServicePort.prototype = new BookService;
