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
        console.log(data);
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
        const photoBase64 = bytesToBase64(user.photo.photo);
        const photoUrl = `data:image/jpeg;base64,${photoBase64}`;
        row.innerHTML = `
            <td>${user.email}</td>
            <td><img src="${photoUrl}" alt="${user.photo.name}" width="50" height="50"></td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.userRole}</td>
            <td>${user.enabled ? "Active" : "Inactive"}</td>
        `;
        tableBody.appendChild(row);
    });
}

function bytesToBase64(bytes) {
    const binary = atob(bytes);
    const binaryLength = binary.length;
    const byteArray = new Uint8Array(binaryLength);
    for (let i = 0; i < binaryLength; i++) {
        byteArray[i] = binary.charCodeAt(i);
    }
    return btoa(String.fromCharCode.apply(null, byteArray));
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
