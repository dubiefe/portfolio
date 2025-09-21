// --- Récupérer le bon projet
const urlParams = new URLSearchParams(window.location.search);
const code_projet = urlParams.get('projet');

async function getProjet () {
    let resObj = await fetch("../data/details_projets.json");
    if (resObj.ok) {
        // If the result is OK (Status HTTP between 200 and 299)
        const resJSON = await resObj.json();
        const projet = resJSON[code_projet];
        console.log(projet);
        afficheProjet(projet);
    }  else {
        // Else we display the error
        console.error(resObj);
    }
}

async function afficheProjet (projet) {
    // Récupérer les éléments html
    const titre_projet = document.getElementById("titre_projet");
    const comp_tech = document.getElementById("details_comp_tech");
    const comp_pn = document.getElementById("details_comp_PN");
    const info_comp = document.getElementById("info_comp");

    // Titre
    titre_projet.textContent = projet.titre;
    // Compétences techniques
    projet.comp_tech.forEach(comp => {
        const img = document.createElement("img");
        img.src = "informations projets/Images/" + comp + ".png";
        img.alt = comp;
        const p = document.createElement("p");
        p.textContent = comp;
        const div = document.createElement("div");
        div.append(img);
        div.append(p);
        comp_tech.append(div);
    });
    // Compétence PN
    projet.comp_PN.forEach(comp => {
        const p = document.createElement("p");
        p.textContent = comp;
        comp_pn.append(p);
    });
    // Information complémentaires
    projet.info_comp.forEach(info => {
        info_comp.innerHTML += info;
    });
}

getProjet();


