'use client';

import React from 'react';
import './detailed_item.css'

export default function Detailed_Item(props) {

  return (
    <div className='detailed_item_container'>
        <div className='picture_container'>
            <img src={props.img}/>
        </div>
        <div className='text_container'>
            <h3>{props.title}</h3>
            {props.nivel && <p>{props.nivel}</p>}
            <p>{props.text}</p>
        </div>
    </div>
  );
}