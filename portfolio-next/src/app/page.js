"use client"

import "./page.css";

import { useState } from "react";
import Techno from "@/components/techno_component/techno";

export default function Home() {

  const [techno, setTechno] = useState(["CloudFare", "CSS", "FXML", "HTML", "Java", "JavaScript", "Kaggle", "MongoDB", "Neo4J", 
                                        "NextJS", "PHP", "Postman", "PowerApps", "PowerAutomate", "Python", "R", "React", "Redis", 
                                        "SharePoint", "Spotify API", "SQL", "Talend", "TMDB API", "UML", "Whimsical"])

  const first_percent = (38 / 100) * techno.length
  const second_percent = (76 / 100) * techno.length
  const third_percent = (88 / 100) * techno.length

  return (
    <main className="home_container">
      <div className="div_left">
          {techno && techno.slice(0, first_percent).map(item => {
            return <Techno key={item} item={item} size={Math.floor(Math.random() * 6) + 8}/>
          })}
      </div>
      <div className="div_center">
        <div className="div_top">
          {techno && techno.slice(second_percent, third_percent).map(item => {
            return <Techno key={item} item={item} size={Math.floor(Math.random() * 6) + 8}/>
          })}
        </div>
        <div className="div_middle">
            <h1>Émilie Dubief</h1>
            <h2>Étudiante en Informatique</h2>
        </div>
        <div className="div_bottom">
          {techno && techno.slice(third_percent, techno.length).map(item => {
            return <Techno key={item} item={item} size={Math.floor(Math.random() * 6) + 8}/>
          })}
        </div>
      </div>
      <div className="div_right">
          {techno && techno.slice(first_percent, second_percent).map(item => {
            return <Techno key={item} item={item} size={Math.floor(Math.random() * 6) + 8}/>
          })}
      </div>
    </main>
  );
}
