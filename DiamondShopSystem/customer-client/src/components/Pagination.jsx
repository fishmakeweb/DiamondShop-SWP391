import React from 'react';

import { Link } from 'react-router-dom';

const Pagination = ({ totalPages, currentPage, onPageChange }) => {

  const paginationItems = [];
  let startPage = Math.max(1, currentPage - 1);
  let endPage = Math.min(totalPages, currentPage + 2);

  if (currentPage > 1) {
    paginationItems.push(
      <Link to={`/jewelry/page/${currentPage - 1}`}>
      <button key="prev" className="px-3 py-1 mx-1 rounded bg-gray-200">
        {'<'}
      </button>
      </Link>
    );
  }

  // Always add the first page if not already included
  if (startPage > 1) {
    paginationItems.push(
      <Link to={`/jewelry/page/${1}`}>
      <button key={1} className="px-3 py-1 mx-1 rounded bg-gray-200">
        1
      </button>
      </Link>
    );
    if (startPage > 2) {
      paginationItems.push(<span key="firstEllipsis" className="px-3 py-1 mx-1">...</span>);
    }
  }

  for (let page = startPage; page <= endPage; page++) {
    paginationItems.push(
      <Link to={`/jewelry/page/${page}`}>
      <button key={page}
        className={`px-3 py-1 mx-1 rounded ${currentPage === page ? 'bg-black text-white' : 'bg-gray-200'}`}>
        {page}
      </button>
      </Link>
    );
  }

  // If the end page is less than the total number of pages, show ellipsis and last page
  if (endPage < totalPages) {
    if (endPage < totalPages - 1) {
      paginationItems.push(<span key="lastEllipsis" className="px-3 py-1 mx-1">...</span>);
    }
    paginationItems.push(
      <Link to={`/jewelry/page/${totalPages}`}>
      <button key={totalPages} className="px-3 py-1 mx-1 rounded bg-gray-200">
        {totalPages}
      </button>
      </Link>
    );
  }

  if (currentPage < totalPages) {
    paginationItems.push(
      <Link to={`/jewelry/page/${currentPage + 1}`}>
      <button key="next" className="px-3 py-1 mx-1 rounded bg-gray-200">
        {'>'}
      </button>
      </Link>
    );
  }

  return (
    <div className="flex justify-center my-4">
      {paginationItems}
    </div>
  );
};

export default Pagination;
