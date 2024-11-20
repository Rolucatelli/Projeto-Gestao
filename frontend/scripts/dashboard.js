let productDisplay = null;
// Function to dynamically build the table rows
function populateTable(products) {
    const tableBody = document.getElementById('product-table-body');
    tableBody.innerHTML = '';

    productDisplay = products;

    products.forEach(product => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = product.id;
        row.appendChild(idCell);

        const imageCell = document.createElement('td');
        const imgElement = document.createElement('img');
        imgElement.src = product.imageUrl;
        imgElement.alt = product.name;
        imgElement.classList.add('product-image');
        imageCell.appendChild(imgElement);
        imageCell.classList.add('product-image-cell');
        row.appendChild(imageCell);

        const nameCell = document.createElement('td');
        nameCell.textContent = product.name;
        row.appendChild(nameCell);

        const descriptionCell = document.createElement('td');
        descriptionCell.textContent = product.description;
        row.appendChild(descriptionCell);

        const brandCell = document.createElement('td');
        brandCell.textContent = product.brand;
        row.appendChild(brandCell);

        const categoryCell = document.createElement('td');
        categoryCell.textContent = product.category;
        row.appendChild(categoryCell);

        const priceCell = document.createElement('td');
        priceCell.textContent = product.price;
        row.appendChild(priceCell);


        tableBody.appendChild(row);
    });
}

// Fetch products data from the API and populate the table
let page = 0;
let size = 5;

let lastPage = -1;

let sortBy = 'name';
let sortAsc = true;

let search = null;

async function fetchProducts() {
    try {
        // Prepare URL
        let url = `http://localhost:8080/api/product/v1/all?size=${size}&page=${page}`;
        
        url += `&sortBy=${sortBy}&sortDirection=${sortAsc ? 'asc' : 'desc'}`;

        if (search != null) {
            url += `&search=${encodeURIComponent(search)}`;
        }

        // Fetch data from API
        const response = await fetch(url);
        if (!response.ok) { throw new Error(`HTTP error! status: ${response.status}`); }

        const data = await response.json();
        console.log(data);
        console.log('Products', data.content);

        lastPage = data.page.totalPages - 1;

        populateTable(data.content);
        renderPagination(data.page.totalPages, page);
    } catch (err) {
        console.error('Error fetching products:', err);
    }
}

// Function to render pagination controls
function renderPagination(totalPages, currentPage) {
    const pagesContainer = document.getElementById('pagination-box');
    pagesContainer.innerHTML = '';

    // Create '<<' button (First page)
    const goFirstPageBtn = document.createElement('button');
    goFirstPageBtn.textContent = '<<';
    goFirstPageBtn.disabled = (currentPage === 0);
    goFirstPageBtn.addEventListener('click', () => {
        page = 0;
        fetchProducts();
    });
    goFirstPageBtn.classList.add('pagination-btn');
    pagesContainer.appendChild(goFirstPageBtn);

    // Create '<' button (Previous page)
    const goPreviusPage = document.createElement('button');
    goPreviusPage.textContent = '<';
    goPreviusPage.disabled = (currentPage === 0);
    goPreviusPage.addEventListener('click', () => {
        page -= 1;
        fetchProducts();
    });
    goPreviusPage.classList.add('pagination-btn');
    pagesContainer.appendChild(goPreviusPage);

    // Create page number button
    const stayCurrentPage = document.createElement('button');
    stayCurrentPage.textContent = `${currentPage + 1}`;
    stayCurrentPage.classList.add('pagination-btn');
    pagesContainer.appendChild(stayCurrentPage);

    // Create '>' button (Next page)
    const goNextPageBtn = document.createElement('button');
    goNextPageBtn.textContent = '>';
    goNextPageBtn.disabled = (currentPage === totalPages - 1);
    goNextPageBtn.addEventListener('click', () => {
        page += 1;
        fetchProducts();
    });
    goNextPageBtn.classList.add('pagination-btn');
    pagesContainer.appendChild(goNextPageBtn);

    // Create '>>' button (Last page)
    const goLastPageBtn = document.createElement('button');
    goLastPageBtn.textContent = '>>';
    goLastPageBtn.disabled = (currentPage === totalPages - 1);
    goLastPageBtn.addEventListener('click', () => {
        page = lastPage;
        fetchProducts();
    });
    goLastPageBtn.classList.add('pagination-btn');
    pagesContainer.appendChild(goLastPageBtn);
}

// Function to search product by string:
document.getElementById('search-btn').addEventListener('click', function() {
    const input = document.getElementById('search-inp').value.trim();
    if (input.length > 0) {
        search = input;
        fetchProducts();
    } else {
        if (search != null) {
            search = null;
            fetchProducts();
        }
    }
});

// Function to sort table data:
// Select all elements with the class 'column-sortable'
const sortableColumns = document.querySelectorAll('.column-sortable');

// Add a click event listener to each column
sortableColumns.forEach(column => {
    column.addEventListener('click', function() {
        sortAsc = !sortAsc;
        sortBy = column.id;
        console.log(`Sorting by [${sortBy}] in ${sortAsc ? 'asc' : 'desc'} order`)
        fetchProducts();
    });
});


// Delete Modal functions
function renderDeleteModalOptions() {
    const dropdown = document.getElementById('product-dropdown');
    dropdown.innerHTML = '';

    const defaultOption = document.createElement('option');
    defaultOption.text = 'Select a product';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    dropdown.appendChild(defaultOption);

    productDisplay.forEach(product => {
        const option = document.createElement('option');
        option.text = product.name;
        option.value = product.id;
        dropdown.appendChild(option);
    });
}
document.getElementById('delete-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting

    const productID = document.getElementById('product-dropdown').value;
    console.log('Product ID to deletion:', productID);

    // TODO: CALL DELETE ENDPOINT
});


// Call the function to fetch products and populate the table when the page loads
document.addEventListener('DOMContentLoaded', function () {
    console.log(fetchProducts());

    const rmvBtn = document.getElementById('rmv-btn');
    const modal = document.getElementById('delete-modal');
    const closeModal = document.getElementById('close-modal');
    const cancelModal = document.getElementById('cancel-remove');

    rmvBtn.addEventListener('click', () => {
        modal.style.display = 'block';
        renderDeleteModalOptions();
    });

    closeModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    cancelModal.addEventListener('click', () => {
        modal.style.display = 'none';
    });

});


