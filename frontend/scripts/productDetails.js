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

async function getProducts(url) {
    try {
        // Fetch data from API
        const response = await fetch(url);
        if (!response.ok) { throw new Error(`HTTP error! status: ${response.status}`); }

        const data = await response.json();
        console.log(data);
		return data;

    } catch (err) {
        console.error('Error fetching products:', err);
    }
}

const productName = document.getElementById("product-name");
const productDescription = document.getElementById("product-description");
const productCategory = document.getElementById("product-category");
const productBrand = document.getElementById("product-brand");
const productListPrice = document.getElementById("product-list-price");
const productCost = document.getElementById("product-cost");
const productDiscountPercent = document.getElementById("product-discount-percent");
const productLength = document.getElementById("product-length");
const productWidth = document.getElementById("product-width");
const productHeight = document.getElementById("product-height");
const productWeight = document.getElementById("product-weight");
const productActive = document.getElementById("product-active");
const productStockAmount = document.getElementById("product-stock-amount");

const url = "http://localhost:3000/products/1";

getProducts(url).then(data => {
	productName.innerHTML = data.name;
	productDescription.innerHTML = data.description;
	productCategory.innerHTML = data.category;
	productBrand.innerHTML = data.brand;
	productListPrice.innerHTML = data.listPrice;
	productCost.innerHTML = data.cost;
	productDiscountPercent.innerHTML = data.discountPercent;
	productLength.innerHTML = data.length;
	productWidth.innerHTML = data.width;
	productHeight.innerHTML = data.height;
	productWeight.innerHTML = data.weight;
	productActive.innerHTML = data.active;
	productStockAmount.innerHTML = data.stockAmount;
});