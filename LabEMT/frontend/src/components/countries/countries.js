import React from "react";

const countries = (props)=>{
    return(
        <div className={"container"}>
            <h2 className={"text-center"}>Countries</h2>
            <table className={"table"}>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Continent</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {
                    props.countries.map((c)=>{
                        return(
                            <tr>
                                <td>{c.name}</td>
                                <td>{c.continent}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        </div>
    )
}

export default countries