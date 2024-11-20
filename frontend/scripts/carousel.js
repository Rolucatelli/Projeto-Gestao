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
