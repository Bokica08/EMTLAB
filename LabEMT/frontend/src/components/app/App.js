import './App.css';
import {Component} from "react";
import bookService from "../../repository/bookRepository";
import Header from "../header/header";
import {Redirect, Route, BrowserRouter as Router} from "react-router-dom";
import Books from "../books/BookList/books";
import AddBook from "../books/AddBook/addBook";
import EditBook from "../books/EditBook/editBook";
import Authors from "../authors/authors.js";
import Countries from "../countries/countries";
import Categories from "../categories/categories";
class App extends Component
{
  constructor(props) {
    super(props);
    this.state={
      books:[],
      authors:[],
      countries:[],
      categories:[],
      book:{},
      author:{},
      country:{}
    }
  }
  render()
  {

    return(
        <Router>
          <Header/>
          <main>
            <div className={"container mt-3"}>
              <Route path={"/books"} exact render={()=>
                  <Books books={this.state.books} onDeleteBook={this.deleteBook} onEditBook={this.getBook} onMarkAsTaken={this.markAsTakenBook}/>
              }></Route>
              <Route path={"/books/add"} exact render={()=>
                  <AddBook onAddBook={this.addBook} authors={this.state.authors} categories={this.state.categories}/>
              }></Route>
              <Route path={"/books/edit/:id"} exact render={()=>
                  <EditBook onEditBook={this.editBook} book={this.state.book} authors={this.state.authors} categories={this.state.categories}/>
              }></Route>
              <Route path={"/authors"} exact render={()=>
                  <Authors authors={this.state.authors}/>
              }></Route>
              <Route path={"/countries"} exact render={()=>
                  <Countries countries={this.state.countries}/>
              }></Route>
              <Route path={"/category"} exact render={()=>
                  <Categories categories={this.state.categories}/>
              }></Route>
              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>

    )
  }
  componentDidMount() {
    this.loadBooks();
    this.loadAuthors();
    this.loadCountries();
    this.loadCategories();
  }

  loadBooks=()=>{
    bookService.fetchBooks().then((data)=>{
      this.setState({
        books:data.data
      })
    })
  }

  loadAuthors=()=>{
    bookService.fetchAuthors().then((data)=>{
      this.setState({
        authors:data.data
      })
    })
  }

  loadCountries=()=>{
    bookService.fetchCountries().then((data)=>
    {
      this.setState({
        countries:data.data
      })
    })
  }
  loadCategories=()=>{
    bookService.fetchCategories().then((data)=>{
      this.setState({
        categories:data.data
      })
    })
  }
  addBook=(name, category, authorId, availableCopies)=>{
    bookService.addBook(name, category, authorId, availableCopies).then(()=>this.loadBooks())
  }
  editBook=(id, name, category, authorId, availableCopies)=>{
    bookService.editBook(id, name, category, authorId, availableCopies).then(()=>this.loadBooks())
  }
  deleteBook=(id)=>{
    bookService.deleteBook(id).then(()=>this.loadBooks())
    window.location.reload();
  }
  getBook=(id)=>{
    bookService.getBookById(id).then((data)=>{
      this.setState({
        book:data.data
      })
    })
  }

  markAsTakenBook=(id)=>{
    bookService.marksAsTaken(id).then(()=>this.loadBooks());
  }
}

export default App;
