// Affichage des projets IUT

let dataFilePath;

if (window.location.hostname !== 'dubiefe.github.io') {
    // En local, on utilise le chemin relatif
    dataFilePath = 'data/titre_projets.json';
} else {
    // Sur GitHub Pages, le projet est dans le dossier portfolio
    dataFilePath = '/portfolio/data/titre_projets.json';
}

async function getProjet () {
    let resObj = await fetch(dataFilePath);
    if (resObj.ok) {
        // If the result is OK (Status HTTP between 200 and 299)
        const resJSON = await resObj.json();
        const projets_IUT = resJSON["IUT"];
        const projets_Perso = resJSON["Perso"];
        afficheProjet(projets_IUT, "IUT");
        afficheProjet(projets_Perso, "Perso");
    }  else {
        // Else we display the error
        console.error(resObj);
    }
}

//<div name="btn_projets" projet="test"></div>
async function afficheProjet (projets, type) {
    // Récupérer les éléments html
    let div_projets;
    if(type === "IUT") {
        div_projets = document.getElementById("projetsIUT")
    } else {
        div_projets = document.getElementById("projetsPerso")
    }
    projets.forEach(projet => {
        // Titre
        const h3 = document.createElement("h3");
        h3.textContent = projet.titre;
        // Résumé
        const p = document.createElement("p");
        p.textContent = projet.resume;
        // division Text
        const divText = document.createElement("div");
        divText.append(h3);
        divText.append(p);
        // Lien vers la page
        const btn_projet = document.createElement("a");
        btn_projet.setAttribute("name","btn_projets");
        btn_projet.setAttribute("projet",projet.code);
        btn_projet.setAttribute("class","button");
        const imageLoupe = document.createElement("img");
        imageLoupe.src = "style/Images/magnifier-search-zoom-svgrepo-com.svg";
        imageLoupe.alt = "plus d'information";
        btn_projet.append(imageLoupe);
        // Division globale
        const divGlobale = document.createElement("div");
        divGlobale.setAttribute("class","divProjet");
        divGlobale.append(divText);
        divGlobale.append(btn_projet);
        // Ajout à la page
        div_projets.append(divGlobale);
        div_projets.append(document.createElement("hr"));
    });
    // Récupération des éléments
    const btn_projets = document.getElementsByName("btn_projets");

    // Redirection lors du clique

    let redirectionFilePath;

    if (window.location.hostname !== 'dubiefe.github.io') {
        // En local, on utilise le chemin relatif
        redirectionFilePath = '/page_projet.html?projet=';
    } else {
        // Sur GitHub Pages, le projet est dans le dossier portfolio
        redirectionFilePath = '/portfolio/page_projet.html?projet=';
}

    btn_projets.forEach(btn_projet => {
        btn_projet.addEventListener('click', () => {
            window.location.href = redirectionFilePath + btn_projet.getAttribute('projet');
        });
    });
}

getProjet();

