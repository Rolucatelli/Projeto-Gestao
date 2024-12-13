async function getUser(email) {
	try {
		const response = await fetch(`http://localhost:8080/api/user/v1/${email}`);
		if (!response.ok) {
			throw new Error(`Erro ao atualizar usuário: ${response.status}`);
		}
		const data = await response.json();
		return data;
	} catch (error) {
		console.error("Erro ao atualizar usuário:", error);
	}
}

async function updateUser(email, formData) {
	const response = await fetch("http://localhost:8080/api/user/v1/update/" + email, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData),
    });
    console.log('Response:', response);
    if (response.ok) {
        console.log('Response OK');
        alert(`User ${formData.firstName} ${formData.lastName} was updated with success!`);
    } else {
        const responseBody = await response.json(); // Parse the response body as JSON
        console.log('Response not OK. Body:', responseBody);
        alert(`An error occurred while updating this user. ${responseBody.message}`);
    }
}

document.addEventListener("DOMContentLoaded", () => { 
    const updateButton = document.getElementById("updateButton");
    const emailInput = document.getElementById("email");
    const userFirstName = document.getElementById("firstName");
    const userLastName = document.getElementById("lastName");
    const userPassword = document.getElementById("password");

    emailInput.addEventListener("focusout", async () => {
        const email = emailInput.value;
        const user = await getUser(email);
        if (user) {
            userFirstName.value = user.firstName;
            userLastName.value = user.lastName;
            userPassword.value = user.password;
        }
    });

    updateButton.addEventListener("click", async () => {
        const email = emailInput.value;
        const firstName = userFirstName.value;
        const lastName = userLastName.value;
        const password = userPassword.value;
        const formData = {
            email,
            firstName,
            lastName,
            password
        };
        await updateUser(email, formData);
    });
});