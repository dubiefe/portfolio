"use client"

import "./page.css";
import { useState } from "react";

import Link from 'next/link'
import Detailed_Item from "@/components/detailed_item_component/detailed_item";

export default function Home() {

  const [languages, setLanguages] = useState([
    {img:"/images/flag_france.jpg", title:"Français", nivel:"Niveau: C2", text:"Je suis née en France, je parle donc français depuis ma naissance."},
    {img:"/images/flag_england.png", title:"Anglais", nivel:"Niveau: C1", text:"J'apprends l'anglais scolairement mais aussi à travers différents médias (cinéma, littérature, etc.). J'ai également étudié en anglais pendant 1 an en Espagne."},
    {img:"/images/flag_germany.png", title:"Allemand", nivel:"Niveau: B1", text:"J'ai appris l'allemand au collège et au lycée. J'ai passé le Deutsches Sprachdiplom I en 2021 qui valide mon niveau B1."},
    {img:"/images/flag_spain.png", title:"Espagnol", nivel:"Niveau: A2", text:"J'ai passé un an en Espagne lors d'un ERASMUS, les cours étant en anglais, je m'en suis servi dans la vie de tous les jours."},
    {img:"/images/flag_china.png", title:"Mandarin", nivel:"Niveau: Notions", text:"J'ai appris des bases de mandarin au collège, que j'ai retravaillé avec l'application Duolingo."},
  ])

  const [interests, setInterests] = useState([
    {img:"/images/interests_piano.png", title:"Piano", text:"Je pratique le piano depuis 2014. J'ai d'ailleurs eu l'occasion de jouer dans des groupes de musique au conservatoire et pendant un an au lycée. Cela m'a aidé à développer mon écoute des autres et ma coordination."},
    {img:"/images/interests_craft.png", title:"Loisirs créatifs", text:"Je suis particulièrement passionnée par les loisirs créatifs, comme les origamis, la broderie ou le découpage. Je mets beaucoup d'applications dans tous ces projets créatifs qui me prennent souvent beaucoup de temps, mais qui sont très gratifiants."},
    {img:"/images/interests_dance.png", title:"Danse", text:"J'ai fait de la danse pendant 13 ans et cela m'a beaucoup apporté en matière d'écoute des autres et de coordination. C'est aussi une activité qui m'aide à m'exprimer et à me canaliser."},
    {img:"/images/interests_reading.png", title:"Lecture", text:"Je passe beaucoup de temps à lire tout type d'ouvrage, que ce soit des romans, des bandes dessinées, des mangas ou des livres de contes. J'ai même eu l'occasion d'écrire des histoires et des poèmes dans le cadre scolaire, mais aussi sur mon temps libre."},
  ])

  return (
    <main className="about_container">
      <div className="main_info_container">
        <img src="/images/picture.jpg" alt="self_picture"/>
        <div>
            <h1>Émilie Dubief - Étudiante en Informatique</h1>
            <div>
                <p>Je suis une étudiante en informatique depuis trois ans à l'IUT2 de Grenoble, actuellement en ERASMUS à l'U-Tad près de 
                    Madrid. </p>
                <p>J'aimerais devenir développeuse puisque je m'épanouis dans mes études, notamment dans les cours de programmation. 
                    C'est un métier qui m'intéresse aussi pour sa polyvalence. En effet, il offre la possibilité de travailler avec des 
                    clients tous plus différents les uns que les autres, cela me permettrait de découvrir d'autres secteurs d'activité et 
                    leur fonctionnement.</p>
            </div>
            <div className="button_container">
                <a className="button" href="https://www.linkedin.com/in/emilie-dubief-746b81343/" title="LinkedIn" target="_blank">
                    <img src="/icons/linkedin.svg" alt="linkedin_logo"/>
                </a>
                <Link className='button' href="/contact" title="Me contacter">
                    <img src="/icons/mail.svg" alt="mail_logo"/>
                </Link>
            </div>
        </div>
      </div>
      <hr/>
      <div className="languages_container">
        <h1>Langues Parlées</h1>
        <div>
            {languages && languages.map(item => {
                return <Detailed_Item key={item.title} img={item.img} title={item.title} nivel={item.nivel} text={item.text}/>
            })}
        </div>
      </div>
      <hr/>
      <div className="interests_container">
        <h1>Mes centres d'intérêts</h1>
        <div>
            {interests && interests.map(item => {
                return <Detailed_Item key={item.title} img={item.img} title={item.title} text={item.text}/>
            })}
        </div>
      </div>
    </main>
  );
}
