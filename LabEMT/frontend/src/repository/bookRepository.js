import axios from '../custom-axios/axios';

const BookService = {

    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchCountries:()=>{
        return axios.get("/countries")
    },
    fetchCategories: () => {
        return axios.get("/books/category");
    },
    fetchAuthors: () => {
        return axios.get("/authors")
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, authorId, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies,


        });
    },

    editBook: (id, name, category, authorId, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "authorId": authorId,
            "availableCopies": availableCopies,

        })
    },
    getBookById:(id)=>{
        return axios.get(`/books/${id}`);
    },
    marksAsTaken:(id)=>
    {
        return axios.put(`/books/markAsTaken/${id}`)
    }



}
export default BookService;