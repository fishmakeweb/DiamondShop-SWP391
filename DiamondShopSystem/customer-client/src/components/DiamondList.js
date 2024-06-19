import React, { useState } from 'react';
import "../style/DiamondList.css";

function DiamondList({ items }) {
    const [currentPage, setCurrentPage] = useState(0);
    const itemsPerPage = 8;

    const totalPages = Math.ceil(items.length / itemsPerPage);
    const startIndex = currentPage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const itemsToShow = items.slice(startIndex, endIndex);

    const changePage = (pageNumber) => setCurrentPage(pageNumber);
    const pageNumbers = Array.from({ length: totalPages }, (_, i) => i);

    return (
        <div className="diamond-list-container">
            <table className="diamond-table">
                <thead>
                    <tr>
                        <th>Shape</th>
                        <th>Millimetre (mm)</th>
                        <th>Carat</th>
                        <th>Color</th>
                        <th>Clarity</th>
                        <th>Cut</th>
                        <th>Price</th>
                        <th>GIA</th>
                        <th>Buy</th>
                    </tr>
                </thead>
                <tbody>
                    {itemsToShow.map(item => (
                        <tr key={item.diamondId}>
                            <td>{item.shape.shapeDescription}</td>
                            <td>{item.measurement.length} x {item.measurement.width} x {item.measurement.height}</td>
                            <td>{item.carat.carat}</td>
                            <td>{item.color.colorDescription}</td>
                            <td>{item.clarity.clarityDescription}</td>
                            <td>{item.cut.cutDescription}</td>
                            <td>{item.price}</td>
                            <td>{new Date(item.gia.issueDate).toLocaleDateString()}</td>
                            <td>
                                <button className="add-to-cart-button">
                                    Add to Cart
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="pagination">
                {pageNumbers.map(number => (
                    <button
                        key={number}
                        onClick={() => changePage(number)}
                        className={`page-button ${currentPage === number ? "active" : ""}`}
                    >
                        {number + 1}
                    </button>
                ))}
            </div>
        </div>
    );
}

export default DiamondList;
