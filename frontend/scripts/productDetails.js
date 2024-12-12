class Carousel {
	constructor(carouselSelector, prevSelector, nextSelector) {
		this.carousel = document.querySelector(carouselSelector);
		this.carouselImages = this.carousel.querySelector(".carousel-images");
		this.prevButton = this.carousel.querySelector(prevSelector);
		this.nextButton = this.carousel.querySelector(nextSelector);
		this.currentIndex = 0;
		this.init();
	}

	init() {
		this.prevButton.addEventListener("click", () => this.showPrevImage());
		this.nextButton.addEventListener("click", () => this.showNextImage());
	}

	showPrevImage() {
		const totalImages = this.carouselImages.children.length;
		this.currentIndex = (this.currentIndex - 1 + totalImages) % totalImages;
		this.updateCarousel();
	}

	showNextImage() {
		const totalImages = this.carouselImages.children.length;
		this.currentIndex = (this.currentIndex + 1) % totalImages;
		this.updateCarousel();
	}

	updateCarousel() {
		const offset = -this.currentIndex * 100;
		this.carouselImages.style.transform = `translateX(${offset}%)`;
	}
}

// Onde começa a joça
document.addEventListener("DOMContentLoaded", () => {
	new Carousel(".carousel", ".prev", ".next");
});

async function getProducts() {
	try {
		const params = new URLSearchParams(window.location.search);

		const url = `http://localhost:8080/api/product/v1/${params.get("id")}`;

		console.log(url);
		// Fetch data from API
		const response = await fetch(url);
		if (!response.ok) {
			throw new Error(`HTTP error! status: ${response.status}`);
		}

		const data = await response.json();
		console.log(data);
		return data;
	} catch (err) {
		console.error("Error fetching products:", err);
	}
}

function renderImages(images) {
	return images
		.map((image) => {
			return `<img src="${image.url}" alt="Product image">`;
		})
		.join("");
}

function renderDetails(details) {
	return details
		.map((detail) => {
			return `
			<div class="detail">
				<h3>Detail ${details.indexOf(detail) + 1}:</h3>
				<div class="row">
					<span id="detailName">${detail.name}:</span>
					<span id="detailValue">${detail.value}</span>
				</div>
			</div>
		`;
		})
		.join("");
}

getProducts().then((data) => {
	const productName = document.getElementById("product-name");
	const productShortDescription = document.getElementById(
		"product-short-description"
	);
	const productImages = document.getElementsByClassName("carousel-images")[0];
	const productFullDescription = document.getElementById(
		"product-full-description"
	);
	const productCategory = document.getElementById("product-category");
	const productBrand = document.getElementById("product-brand");
	const productListPrice = document.getElementById("product-list-price");
	const productCost = document.getElementById("product-cost");
	const productDiscountPercent = document.getElementById(
		"product-discount-percent"
	);
	const productLength = document.getElementById("product-length");
	const productWidth = document.getElementById("product-width");
	const productHeight = document.getElementById("product-height");
	const productWeight = document.getElementById("product-weight");
	const productActive = document.getElementById("product-active");
	const productStockAmount = document.getElementById("product-stock-amount");
	const detailsList = document.getElementsByClassName(".details-section")[0];
	productName.innerHTML = data.name;
	productShortDescription.innerHTML = data.shortDescription;
	productFullDescription.innerHTML = data.fullDescription;
	productCategory.innerHTML = data.category;
	productBrand.innerHTML = data.brand;
	productListPrice.innerHTML = data.price;
	productCost.innerHTML = data.cost;
	productDiscountPercent.innerHTML = data.discount;
	productLength.innerHTML = data.dimensions.length;
	productWidth.innerHTML = data.dimensions.width;
	productHeight.innerHTML = data.dimensions.height;
	productWeight.innerHTML = data.dimensions.weight;
	productActive.checked = data.isActive;
	productStockAmount.innerHTML = data.stockAmount;
	detailsList.innerHTML = renderDetails(data.details);

	productImages.innerHTML = renderImages(data.images);
});
