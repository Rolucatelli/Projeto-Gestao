document.getElementById("userForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const password = document.getElementById("password").value.trim();

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
        photo: image ? image.url : null
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
    }
}

let image = null;

function updateImage() {
    const carousel = document.getElementById('imageCarousel');
    carousel.innerHTML = '';

    if (image) {
        const imageItem = document.createElement('div');
        imageItem.className = 'image-item';

        const img = document.createElement('img');
        img.src = image.url;
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

function saveImage() {
    const imageUrl = document.getElementById('imageUrl').value.trim();

    if (!imageUrl) {
        alert('Please enter a valid image URL.');
        return;
    }

    image = { url: imageUrl };
    updateImage();
}

function removeImage() {
    image = null;
    updateImage();
}

document.getElementById("cancelButton").addEventListener("click", function () {
    window.location.href = "/users";
});
