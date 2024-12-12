async function getUser(email) {
	try {
		const response = await fetch(
			`http://localhost:8080/api/user/v1/${email}`
		);
		if (!response.ok) {
			throw new Error(`Erro ao atualizar usuário: ${response.status}`);
		}
		const data = await response.json();
		console.log("Data:", data);
		return data;
	} catch (error) {
		console.error("Erro ao atualizar usuário:", error);
	}
}

async function updateUser(email, formData) {
	const response = await fetch(
		"http://localhost:8080/api/user/v1/update/" + email,
		{
			method: "PUT",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(formData),
		}
	);
	console.log("Response:", response);
	if (response.ok) {
		console.log("Response OK");
		alert(
			`User ${formData.firstName} ${formData.lastName} was updated with success!`
		);
	} else {
		const responseBody = await response.json(); // Parse the response body as JSON
		console.log("Response not OK. Body:", responseBody);
		alert(
			`An error occurred while updating this user. ${responseBody.message}`
		);
	}
}

let image = null;

function bytesToBase64(bytes) {
	const binary = atob(bytes);
	const binaryLength = binary.length;
	const byteArray = new Uint8Array(binaryLength);
	for (let i = 0; i < binaryLength; i++) {
		byteArray[i] = binary.charCodeAt(i);
	}
	return btoa(String.fromCharCode.apply(null, byteArray));
}

function updateImage(photo) {
	if (!photo) {
		return;
	}
    image = photo.photo;
	const imageItem = document.createElement("div");
	const img = document.createElement("img");
	const carousel = document.getElementById("imageCarousel");
	carousel.innerHTML = "";

	try {
		const photoBase64 = bytesToBase64(photo.photo);
		const photoUrl = `data:image/jpeg;base64,${photoBase64}`;

		imageItem.className = "image-item";

		img.src = photoUrl;
		img.alt = photo.name;
	} catch (error) {
		imageItem.className = "image-item";

		img.src = URL.createObjectURL(
			document.getElementById("imageFile").files[0]
		); // Cria uma URL temporária para o arquivo de imagem
		img.alt = document.getElementById("imageFile").name;
	}
	const removeBtn = document.createElement("button");
	removeBtn.className = "remove-btn";
	removeBtn.innerText = "X";
	removeBtn.onclick = removeImage;

	imageItem.appendChild(img);
	imageItem.appendChild(removeBtn);
	carousel.appendChild(imageItem);
}

function removeImage() {
	const carousel = document.getElementById("imageCarousel");
	carousel.innerHTML = "";
    image = null;
}

async function saveImage() {
	const imageFile = document.getElementById("imageFile").files[0];
	if (!imageFile) {
		return null;
	}

	// Verifica se o tamanho do arquivo é maior que 1MB (1MB = 1048576 bytes)
	const maxSize = 1048576; // 1MB em bytes
	if (imageFile.size > maxSize) {
		alert(
			"The selected file is too large. Please select a file smaller than 1MB."
		);
		return;
	}

	image = await fileToByteArray(imageFile);
	const photo = {
		photo: image,
		name: imageFile.name,
	};
	updateImage(photo);
	return photo;
}

// Função para transformar o arquivo em um vetor de bytes
function fileToByteArray(file) {
	return new Promise((resolve, reject) => {
		const reader = new FileReader();

		// Lê o arquivo como um ArrayBuffer
		reader.readAsArrayBuffer(file);

		// Quando a leitura estiver completa
		reader.onload = () => {
			const arrayBuffer = reader.result; // Resultado como ArrayBuffer
			const byteArray = Array.from(new Uint8Array(arrayBuffer)); // Converte para Uint8Array
			resolve(byteArray); // Retorna o vetor de bytes
		};

		// Em caso de erro na leitura
		reader.onerror = () => reject(reader.error);
	});
}

document.getElementById("userForm").addEventListener("submit", async (event) => {
    event.preventDefault();
});

document.addEventListener("DOMContentLoaded", () => {
	const updateButton = document.getElementById("updateButton");
	const emailInput = document.getElementById("email");
	const userFirstName = document.getElementById("firstName");
	const userLastName = document.getElementById("lastName");
	const userPassword = document.getElementById("password");
	const userRoleInput = document.getElementById("role");

	emailInput.addEventListener("focusout", async () => {
		const email = emailInput.value;
		const user = await getUser(email);
		if (user) {
			userFirstName.value = user.firstName;
			userLastName.value = user.lastName;
			userPassword.value = user.password;
			userRoleInput.value = user.userRole;

			// Mapeia a role do usuário para o valor correspondente no select
			const roleMapping = {
				Administrator: "1",
				"Sales Manager": "2",
				Editor: "3",
				"Shipping Manager": "4",
				Assistant: "5",
			};
			userRoleInput.value = roleMapping[user.userRole] || ""; // Define a opção selecionada do elemento select
			updateImage(user.photo);
		}
	});

	updateButton.addEventListener("click", async () => {
		const email = emailInput.value;
		const firstName = userFirstName.value;
		const lastName = userLastName.value;
		const password = userPassword.value;
		const userRole = userRoleInput.value;
		const photo = await saveImage();
		const formData = {
			email,
			firstName,
			lastName,
			password,
			userRole,
			photo,
		};
		await updateUser(email, formData);
	});
});
