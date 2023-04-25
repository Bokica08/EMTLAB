import React from "react";

const authors = (props) =>
{
    return (
        <div className={"container"}>
            <h2 className={"text-center"}>Authors</h2>
            <table className={"table"}>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Country</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {
                    props.authors.map((a)=>{
                        return(
                            <tr>
                                <td>{a.name}</td>
                                <td>{a.surname}</td>
                                <td>{a.country.name}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}

export default authors