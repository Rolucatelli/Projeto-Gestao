const baseUrl = "http://localhost:8080/api/user/v1";

function showAddUserForm() {
    const addUserForm = document.getElementById("addUserForm");
    addUserForm.style.display = "block";
}

function closeForm() {
    const addUserForm = document.getElementById("addUserForm");
    addUserForm.style.display = "none";
}

async function addUser(event) {
    event.preventDefault();

    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const role = document.getElementById("role").value;
    const status = document.getElementById("status").checked;

    const user = {
        firstName,
        lastName,
        role,
        status,
    };

    try {
        const response = await fetch(`${baseUrl}/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });

        if (!response.ok) {
            throw new Error(`Erro ao adicionar usuário: ${response.status}`);
        }

        alert("Usuário adicionado com sucesso!");
        closeForm();
        fetchUsers(); 
    } catch (error) {
        console.error("Erro ao adicionar usuário:", error);
        alert("Erro ao adicionar usuário. Verifique o console para mais detalhes.");
    }
}

async function fetchUsers() {
    try {
        const response = await fetch(`${baseUrl}/all`);
        if (!response.ok) {
            throw new Error(`Erro ao buscar usuários: ${response.status}`);
        }
        const data = await response.json();
        populateTable(data.content); 
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
            <td>${user.status ? "Ativo" : "Inativo"}</td>
        `;
        tableBody.appendChild(row);
    });
}

function searchUser() {
    const searchInput = document.getElementById("searchInput").value.trim();

 
    if (!searchInput || isNaN(searchInput)) {
        alert("Digite um ID válido (somente números) para buscar.");
        return;
    }

    fetch(`${baseUrl}/find/${searchInput}`)
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
