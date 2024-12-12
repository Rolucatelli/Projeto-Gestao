async function getUser(email) {
	try {
		const response = await fetch(`http://localhost:8080/api/user/v1/${email}`);
		if (!response.ok) {
			throw new Error(`Erro ao buscar usuário: ${response.status}`);
		}
		const data = await response.json();
		return data;
	} catch (error) {
		console.error("Erro ao buscar usuário:", error);
	}
}

async function removeUser(email) {
	const response = await fetch("http://localhost:8080/api/user/v1/delete/" + email, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    });
    console.log('Response:', response);
    if (response.ok) {
        console.log('Response OK');
    } else {
        const responseBody = await response.json(); // Parse the response body as JSON
        console.log('Response not OK. Body:', responseBody);
        alert(`An error occurred while deleting this user. ${responseBody.message}`);
    }
}

document.addEventListener("DOMContentLoaded", () => {
	// Selecionar elementos do DOM
	const removeButton = document.getElementById("removeButton");
	const confirmationModal = document.getElementById("confirmationModal");
	const confirmRemoveButton = document.getElementById("confirmRemoveButton");
	const cancelModalButton = document.getElementById("cancelModalButton");
    const emailInput = document.getElementById("email");
    const userFirstName = document.getElementById("firstName");
    const userLastName = document.getElementById("lastName");

	// Função para abrir o modal
	const openModal = () => {
        if (!emailInput.value) {
            alert("Email is required!");
            return;
        }
        console.log('Email:', emailInput.value);
		confirmationModal.style.display = "block";
	};

	// Função para fechar o modal
	const closeModal = () => {
		confirmationModal.style.display = "none";
	};

	// Evento para abrir o modal ao clicar no botão "Remove User"
	removeButton.addEventListener("click", openModal);

	// Evento para fechar o modal ao clicar no botão "Cancel" dentro do modal
	cancelModalButton.addEventListener("click", closeModal);

    // Evento para buscar o usuário a partir do email
    emailInput.addEventListener("focusout", async () => {
        try {
            let data = await getUser(emailInput.value);
            console.log('Data:', data);
            if (data) {
                userFirstName.value = data.firstName;
                userLastName.value = data.lastName;
            }
        } catch (error) {
            console.error('Erro ao buscar usuário:', error);
        }
    });

	// Evento para confirmar a remoção do usuário
	confirmRemoveButton.addEventListener("click", () => {

        removeUser(emailInput.value);

		console.log("User removal confirmed!");

		// Após a remoção, feche o modal
		closeModal();
	});

	// Fechar o modal ao clicar fora dele (opcional)
	window.addEventListener("click", (event) => {
		if (event.target === confirmationModal) {
			closeModal();
		}
	});

    fetchUsers();
});
