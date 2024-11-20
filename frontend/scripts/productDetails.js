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

		const url = `http://localhost:3000/api/product/v1/${params.get("id")}`;

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
	return product;
}

function renderImages(images) {
	return images
		.map((image) => {
			return `<img src="${image.url}" alt="Product image">`;
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
	productName.innerHTML = data.name;
	productShortDescription.innerHTML = data.shortDescription;
	productFullDescription.innerHTML = data.fullDescription;
	productCategory.innerHTML = data.category;
	productBrand.innerHTML = data.brand;
	productListPrice.innerHTML = data.listPrice;
	productCost.innerHTML = data.cost;
	productDiscountPercent.innerHTML = data.discountPercent;
	productLength.innerHTML = data.length;
	productWidth.innerHTML = data.width;
	productHeight.innerHTML = data.height;
	productWeight.innerHTML = data.weight;
	productActive.checked = data.active;
	productStockAmount.innerHTML = data.stockAmount;

	productImages.innerHTML = renderImages(data.images);
});
