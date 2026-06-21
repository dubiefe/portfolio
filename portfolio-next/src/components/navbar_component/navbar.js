'use client';

import React from 'react';
import './navbar.css'
import Link from 'next/link'

export default function Navbar() {

  return (
    <div className='navbar_container'>
        <Link href="/" className='button'>
            <img src='/icons/home.svg'/>
        </Link>
        <Link href="/about" className='button'>
            A propos
        </Link>
        <Link href="/studies" className='button'>
            Parcours
        </Link>
        <Link href="/commitment" className='button'>
            Engagements
        </Link>
        <Link href="/projects" className='button'>
            Projets
        </Link>
        <Link href="/contact" className='button'>
            Me contacter
        </Link>
    </div>
  );
}