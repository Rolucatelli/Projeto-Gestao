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
        photo: image ? image.url : null,
        enabled: document.querySelector('input[name="enabled"]:checked').value,
    };

    try {
        const response = await fetch("/api/users", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
        });

        const message = await response.text();
        const messageElement = document.getElementById("message");
        messageElement.textContent = message;

        if (response.ok) {
            messageElement.style.color = "green";
            setTimeout(() => {
                window.location.href = "/users";
            }, 2000);
        } else {
            messageElement.style.color = "red";
        }
    } catch (error) {
        const messageElement = document.getElementById("message");
        messageElement.textContent = "Error: " + error.message;
        messageElement.style.color = "red";
    }
});

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
