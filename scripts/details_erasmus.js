// -- Récupérer les éléments
const btnOpenDetailsErasmus = document.getElementById("open_details_erasmus");
const divDetailsErasmus = document.getElementById("details_erasmus");

btnOpenDetailsErasmus.addEventListener('click', () => {
    if(btnOpenDetailsErasmus.src.includes("style/Images/arrow_down.svg")) {
        btnOpenDetailsErasmus.src = "style/Images/arrow_right.svg";
        divDetailsErasmus.style.display = 'none';
    } else {
        btnOpenDetailsErasmus.src = "style/Images/arrow_down.svg";
        divDetailsErasmus.style.display = 'block';
    }
})