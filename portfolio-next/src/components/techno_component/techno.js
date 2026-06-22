'use client';

import React from 'react';
import './techno.css'

export default function Techno(props) {

  console.log(props.size)

  return (
    <img className='techno_img' 
         src={"/techno/" + props.item + ".png"} 
         title={props.item} 
         style={{width: props.size + "vh", height: props.size + "vh"}}/>
  );
}