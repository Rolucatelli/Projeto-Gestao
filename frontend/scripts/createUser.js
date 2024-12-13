document.getElementById("userForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const password = document.getElementById("password").value.trim();
    const role = document.getElementById("role").value;
    let roles = [role];

    if (!email || email.length > 128) {
        showMessage("Email must be between 1 and 128 characters.", "error");
        return;
    }
    if (firstName.length < 2 || firstName.length > 60) {
        showMessage("First Name must be between 2 and 60 characters.", "error");
        return;
    }
    if (lastName.length < 2 || lastName.length > 60) {
        showMessage("Last Name must be between 2 and 60 characters.", "error");
        return;
    }
    if (!password || password.length > 64) {
        showMessage("Password must be between 1 and 64 characters.", "error");
        return;
    }

    const formData = {
        email,
        firstName,
        lastName,
        password,
        roles,
        photo //: image ? image.file : null
    };

    try {

        console.log('Create user data:', formData);

        const response = await fetch("http://localhost:8080/api/user/v1/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
        });

        console.log('Response:', response);
        if (response.ok) {
            console.log('Response OK');
            alert(`User ${formData.firstName} ${formData.lastName} was created with success!`);
            cleanForm();
        } else {
            const responseBody = await response.json(); // Parse the response body as JSON
            console.log('Response not OK. Body:', responseBody);
            alert(`An error occurred while creating this user. ${responseBody.message}`);
            cleanForm(responseBody.message);
        }
    } catch (error) {
        console.log('ERROR', error);
        const messageElement = document.getElementById("message");
        messageElement.textContent = "Error: " + error.message;
        messageElement.style.color = "red";
    }
});

function cleanForm(message) {

    const messageContainer = document.getElementById('email-message');

    document.getElementById("email").value = "";

    if (message) {
        messageContainer.textContent = message;
        messageContainer.style.display = "block";
    } else {
        messageContainer.style.display = "none";

        document.getElementById("firstName").value = "";
        document.getElementById("lastName").value = "";
        document.getElementById("password").value = "";
        removeImage();
    }
}

let photo = null;

let image = null;
function updateImage() {
    const carousel = document.getElementById('imageCarousel');
    carousel.innerHTML = '';

    if (image) {
        const imageItem = document.createElement('div');
        imageItem.className = 'image-item';

        const img = document.createElement('img');
        img.src = URL.createObjectURL(document.getElementById('imageFile').files[0]); // Cria uma URL temporária para o arquivo de imagem
        img.alt = 'User Photo';

        const removeBtn = document.createElement('button');
        removeBtn.className = 'remove-btn';
        removeBtn.innerText = 'X';
        removeBtn.onclick = removeImage;

        imageItem.appendChild(img);
        imageItem.appendChild(removeBtn);
        carousel.appendChild(imageItem);
    }
}

async function saveImage() {
    const imageFile = document.getElementById('imageFile').files[0];

    if (!imageFile) {
        alert('Please select a valid image file.');
        return;
    }

    // Verifica se o tamanho do arquivo é maior que 1MB (1MB = 1048576 bytes)
    const maxSize = 1048576; // 1MB em bytes
    if (imageFile.size > maxSize) {
        alert('The selected file is too large. Please select a file smaller than 1MB.');
        return;
    }
    image = await fileToByteArray(imageFile);

    photo = {
        photo: image,
        name: imageFile.name,
    };
    updateImage();
}

function removeImage() {
    image = null;
    updateImage();
}

document.getElementById("cancelButton").addEventListener("click", function () {
    window.location.href = "/users";
});

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
