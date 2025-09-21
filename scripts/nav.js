// -- Récupérer les éléments
const btnHamburger = document.getElementById("hamburger");
const accueil = document.getElementById("accueil");
const aProposPage = document.getElementById("aProposPage");
const parcoursPage = document.getElementById("parcoursPage");
const associationPage = document.getElementById("associationPage");
const projetPage = document.getElementById("projetPage");
const allPage = [accueil, aProposPage, parcoursPage, associationPage, projetPage];

btnHamburger.addEventListener('click', () => {
    if(btnHamburger.firstChild.src.includes("style/Images/hamburger-svgrepo-com.svg")) {
        btnHamburger.firstChild.src = "style/Images/return-svgrepo-com.svg";
        allPage.forEach(page => {
            page.style.display = 'flex';
        });
    } else {
        btnHamburger.firstChild.src = "style/Images/hamburger-svgrepo-com.svg";
        allPage.forEach(page => {
            page.style.display = 'none';
        });
    }
})