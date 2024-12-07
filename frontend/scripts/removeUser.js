document.addEventListener("DOMContentLoaded", () => {
    // Selecionar elementos do DOM
    const removeButton = document.getElementById("removeButton");
    const confirmationModal = document.getElementById("confirmationModal");
    const confirmRemoveButton = document.getElementById("confirmRemoveButton");
    const cancelModalButton = document.getElementById("cancelModalButton");

    // Função para abrir o modal
    const openModal = () => {
        confirmationModal.style.display = "block";
    };

    // Função para fechar o modal
    const closeModal = () => {
        confirmationModal.style.display = "none";
    };

    // Evento para abrir o modal ao clicar no botão "Remove User"
    removeButton.addEventListener("click", openModal);

    // Evento para fechar o modal ao clicar no botão "Cancel" dentro do modal
    cancelModalButton.addEventListener("click", closeModal);

    // Evento para confirmar a remoção do usuário
    confirmRemoveButton.addEventListener("click", () => {
        // Aqui você pode adicionar a lógica para remover o usuário
        console.log("User removal confirmed!");

        // Após a remoção, feche o modal
        closeModal();
    });

    // Fechar o modal ao clicar fora dele (opcional)
    window.addEventListener("click", (event) => {
        if (event.target === confirmationModal) {
            closeModal();
        }
    });
});
