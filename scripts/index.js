// -- Récupérer les bons éléments
const indexDiv = document.getElementById("indexDiv");
const aProposPage = document.getElementById("aProposPage");
const parcoursPage = document.getElementById("parcoursPage");
const associationPage = document.getElementById("associationPage");
const projetPage = document.getElementById("projetPage");
const allPage = [aProposPage, parcoursPage, associationPage, projetPage];

// -- Placement initial des éléments
// Centrage initial (calculé)
const centerX = (indexDiv.clientWidth - aProposPage.clientWidth) / 2;
const centerY = (indexDiv.clientHeight - aProposPage.clientHeight) / 2;

// Appliquer le centrage
allPage.forEach(page => {
    page.style.left = centerX + 'px';
    page.style.top = centerY + 'px';
});

// -- Déplacement des éléments
// A Propos - Déplacement après 1.5 seconde vers le haut
let start1 = Date.now();
let timer1 = setInterval(function() {
    let timePassed1 = Date.now() - start1;
    aProposPage.style.top = centerY - timePassed1 / 6 + 'px';
    if (timePassed1 > 1500) clearInterval(timer1);
}, 20);

// Parcours - Déplacement après 1.5 seconde vers la gauche
let start2 = Date.now();
let timer2 = setInterval(function() {
    let timePassed2 = Date.now() - start2;
    parcoursPage.style.left = centerX - timePassed2 / 3 + 'px';
    if (timePassed2 > 1500) clearInterval(timer2);
}, 20);

// Projet - Déplacement après 1.5 seconde vers le bas
let start3 = Date.now();
let timer3 = setInterval(function() {
    let timePassed3 = Date.now() - start3;
    projetPage.style.top = centerY + timePassed3 / 6 + 'px';
    if (timePassed3 > 1500) clearInterval(timer3);
}, 20);

// Association - Déplacement après 1.5 seconde vers la droite
let start4 = Date.now();
let timer4 = setInterval(function() {
    let timePassed4 = Date.now() - start4;
    associationPage.style.left = centerX + timePassed4 / 3 + 'px';
    if (timePassed4 > 1500) clearInterval(timer4);
}, 20);

// let start2 = Date.now();
// let timer2 = setInterval(function() {
//     let timePassed2 = Date.now() - start2;
//     parcoursPage.style.right = timePassed2 / 5 + 'px';
//     if (timePassed2 > 2000) clearInterval(timer2);
// }, 20);

// let start3 = Date.now();
// let timer3 = setInterval(function() {
//     let timePassed3 = Date.now() - start3;
//     associationPage.style.left = timePassed3 / 5 + 'px';
//     if (timePassed3 > 2000) clearInterval(timer3);
// }, 20);

// let start4 = Date.now();
// let timer4 = setInterval(function() {
//     let timePassed4 = Date.now() - start4;
//     projetPage.style.top = timePassed4 / 7 + 'px';
//     if (timePassed4 > 2000) clearInterval(timer4);
// }, 20);
    
