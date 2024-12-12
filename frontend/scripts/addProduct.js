async function fetchCategories() {
	try {
		const url = `http://localhost:8080/api/category/v1/all`;

		const response = await fetch(url);
		if (!response.ok) {
			throw new Error(`http error! Status:${response.status}`);
		}
		const data = await response.json();
		console.log(data.categories);
		renderCategoryOptions(data.categories);
	} catch (err) {
		console.error(`Error fetching cateogries!`, err);
	}
}

function renderCategoryOptions(categories) {
	const select = document.getElementById("category");
	categories.forEach((category) => {
		const option = document.createElement("option");
		option.text = category;
		option.value = category;
		select.appendChild(option);
	});
}

function addDetail() {
	const detailsList = document.getElementById("detailsList");
	const detail = document.createElement("div");
	detail.className = "detail";
	detail.innerHTML = `
        <div class="attribute-field">
            <label for="detail-name">Detail Name:</label>
            <input type="text" id="detail-name" class="styled-input">
        </div>
        <div class="attribute-field">
            <label for="detail-value">Detail Value:</label>
            <input type="text" id="detail-value" class="styled-input">
        </div>
        <button class="remove-detail-btn" onclick="removeDetail(this)">X</button>
    `;
	detailsList.appendChild(detail);
}

function removeDetail(button) {
	button.parentElement.remove();
}

let details = [];

function saveDetails() {
    const detailNodes = document.querySelectorAll(".detail");
    details = Array.from(detailNodes).map((detail) => {
        const name = detail.querySelector("#detail-name").value;
        const value = detail.querySelector("#detail-value").value;
        return { name, value };
    });
    console.log("details=", details);
}

document.addEventListener("DOMContentLoaded", function () {
	fetchCategories();
});

let images = [];

function saveImage() {
	const imageUrl = document.getElementById("imageUrl").value;
	console.log("url=", imageUrl);

	if (imageUrl) {
		const image = {
			url: imageUrl,
			description: "",
			isPrincipal: false,
		};

		images.push(image);

		document.getElementById("imageUrl").value = "";

		updateCarousel();
	} else {
		alert("Por favor, insira uma URL válida.");
	}
}

function updateCarousel() {
	const carousel = document.getElementById("imageCarousel");
	carousel.innerHTML = "";

	images.forEach((image, index) => {
		const imageItem = document.createElement("div");
		imageItem.className = "image-item";

		const img = document.createElement("img");
		img.src = image.url;

		const removeBtn = document.createElement("button");
		removeBtn.className = "remove-btn";
		removeBtn.innerText = "X";
		removeBtn.onclick = () => removeImage(index);

		imageItem.appendChild(img);
		imageItem.appendChild(removeBtn);
		carousel.appendChild(imageItem);
	});
}

function removeImage(index) {
	images.splice(index, 1);
	updateCarousel();
}

// Função para enviar os dados do produto
function enviarProduto(event) {
	event.preventDefault(); //Impedir atualização da página.

	const produto = {
		name: document.getElementById("name").value,
		shortDescription: document.getElementById("shortDescription").value,
		fullDescription: document.getElementById("fullDescription").value,
		category: document.getElementById("category").value,
		brand: document.getElementById("brand").value,
		length: parseFloat(document.getElementById("length").value),
		width: parseFloat(document.getElementById("width").value),
		height: parseFloat(document.getElementById("height").value),
		weight: parseFloat(document.getElementById("weight").value),
		price: parseFloat(document.getElementById("listPrice").value),
		cost: parseFloat(document.getElementById("cost").value),
		discount: parseFloat(document.getElementById("discountPercent").value),
		active: document.getElementById("active").checked,
		stock: parseInt(document.getElementById("stockAmount").value),
	};

	if (images.length === 0) {
		alert("Por favor, adicione pelo menos uma imagem.");
		return;
	}

	images[0].isPrincipal = true;

    // Adicionando os detalhes ao produto
    saveDetails();

	const produtoComImagens = {
		...produto, // Desestruturação para adicionar as propriedades do produto
		images: images,
        details: details,
	};

	fetch("http://localhost:8080/api/product/v1/create", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(produtoComImagens),
	})
		.then((response) => {
			if (!response.ok) {
				throw new Error("Erro na criação do produto");
			}
			return response.json();
		})
		.then((data) => {
			console.log("Produto criado com sucesso:", data);
			window.location.href = "../html/adminControlPanel.html";
		})
		.catch((error) => {
			console.error("Erro ao criar produto:", error);
		});
}
