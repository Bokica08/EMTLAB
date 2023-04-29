import React from 'react';
import ReactPaginate from 'react-paginate'
import BooksTerm from "../BooksTerm/booksTerm";
import {Link} from 'react-router-dom';

class Books extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            page:0,
            size:5
        }
    }

    getBooks=(offset, nextPageOffset)=>
    {
        return (this.props.books.map((d, index)=>
        {
            return(<BooksTerm term={d} onDeleteBook={this.props.onDeleteBook} onEditBook={this.props.onEditBook} onMarkAsTaken={this.props.onMarkAsTaken}></BooksTerm>)
        }).filter((d, index)=>{
            return (index>=offset && index<nextPageOffset)
        }))
    }

    handlePageClick=(e) =>
    {
        let selected = e.selected
        this.setState({
            page: selected
        })
    }

    render()
    {
        const offset = this.state.page * this.state.size
        const nextPageOffset = this.state.size + this.state.page*this.state.size
        const pages = Math.ceil(this.props.books.length / this.state.size)
        const books = this.getBooks(offset, nextPageOffset)
        return(
            <div className={"container"}>
                <h2 className={"text-center"}>Books</h2>
                <table className={"table"}>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Author</th>
                        <th>AvailableCopies</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        books
                    }
                    </tbody>
                </table>

                <div className={"row"}>
                    <div className={"col-md-12"}>
                        <ReactPaginate  previousLabel={"Previous"}
                                        nextLabel={"Next"}
                                        breakLabel={<a href="/#">...</a>}
                                        pageCount={pages}
                                        marginPagesDisplayed={2}
                                        pageRangeDisplayed={5}
                                        onPageChange={this.handlePageClick}
                                        className='pagination m-4 justify-content-center'
                                        breakClassName={'page-item'}
                                        breakLinkClassName={'page-link'}
                                        containerClassName={'pagination'}
                                        pageClassName={'page-item'}
                                        pageLinkClassName={'page-link'}
                                        previousClassName={'page-item'}
                                        previousLinkClassName={'page-link'}
                                        nextClassName={'page-item'}
                                        nextLinkClassName={'page-link'}
                                        activeClassName={'active'}  />
                    </div>
                </div>
                <div className={"row"}>
                    <div className={"col-md-12"}>
                        <Link to={"/books/add"} className={"btn btn-dark w-100"}>Add new book</Link>
                    </div>
                </div>
            </div>


        )
    }
}

export default Books;
