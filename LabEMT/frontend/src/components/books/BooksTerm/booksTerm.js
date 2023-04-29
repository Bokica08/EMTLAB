import React from "react";
import {Link} from "react-router-dom";

const booksTerm = (props)=>{
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"d-flex justify-content-end"}>
                <Link to={`/books/edit/${props.term.id}`} className={"btn btn-success  m-1"} onClick={()=>props.onEditBook(props.term.id)}>Edit</Link>
                <button className={"btn btn-danger  m-1"} onClick={()=>props.onDeleteBook(props.term.id)}>Delete</button>
                <button className={"btn btn-warning  m-1"} onClick={()=>props.onMarkAsTaken(props.term.id)}>Mark As Taken</button>
            </td>
        </tr>
    )
}

export default booksTerm