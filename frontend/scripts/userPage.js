const baseUrl = "http://localhost:8080/api/user/v1";

function showAddUserForm() {
    const addUserForm = document.getElementById("addUserForm");
    addUserForm.style.display = "block";
}

function closeForm() {
    const addUserForm = document.getElementById("addUserForm");
    addUserForm.style.display = "none";
}

async function fetchUsers() {
    try {
        const response = await fetch(`${baseUrl}/all`);
        if (!response.ok) {
            throw new Error(`Erro ao buscar usuários: ${response.status}`);
        }
        const data = await response.json();
        populateTable(data.content); 
        console.log(data);
    } catch (error) {
        console.error("Erro ao buscar usuários:", error);
    }
}

function populateTable(users) {
    const tableBody = document.getElementById("userTableBody");
    tableBody.innerHTML = ""; 
    users.forEach(user => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userRole}</td>
            <td>${user.enabled ? "Active" : "Inactive"}</td>
        `;
        tableBody.appendChild(row);
    });
}

function searchUser() {
    const searchInput = document.getElementById("searchInput").value.trim();

 
    if (!searchInput) {
        fetchUsers();
        return;
    }

    fetch(`${baseUrl}/${searchInput}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Usuário não encontrado. Status: ${response.status}`);
            }
            return response.json();
        })
        .then(user => {
            populateTable([user]); 
        })
        .catch(error => {
            console.error("Erro ao buscar usuário:", error);
            alert("Usuário não encontrado.");
        });
}

fetchUsers();
