// Affichage des projets IUT
async function getProjet () {
    let resObj = await fetch("/data/titre_projets.json");
    if (resObj.ok) {
        // If the result is OK (Status HTTP between 200 and 299)
        const resJSON = await resObj.json();
        const projets = resJSON["IUT"];
        console.log(projets);
        afficheProjet(projets);
    }  else {
        // Else we display the error
        console.error(resObj);
    }
}

//<div name="btn_projets" projet="test"></div>
async function afficheProjet (projets) {
    // Récupérer les éléments html
    const projets_IUT = document.getElementById("projetsIUT")
    projets.forEach(projet => {
        // Titre
        const h3 = document.createElement("h3");
        h3.textContent = projet.titre;
        // Résumé
        const p = document.createElement("p");
        p.textContent = projet.resume;
        // division
        const div = document.createElement("div");
        div.setAttribute("name","btn_projets");
        div.setAttribute("projet",projet.code);
        div.append(h3);
        div.append(p);
        projets_IUT.append(div);
        projets_IUT.append(document.createElement("hr"));
    });
    // Récupération des éléments
    const btn_projets = document.getElementsByName("btn_projets");

    // Redirection lors du clique
    btn_projets.forEach(btn_projet => {
        btn_projet.addEventListener('click', () => {
            window.location.href = '/page_projet.html?projet=' + btn_projet.getAttribute('projet');
        });
    });
}

getProjet();

