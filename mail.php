<?php
    $subject = $_POST['subject'];
    $message = $_POST['message'];
    $from = $_POST['email'];
    $retour = mail('emilie.dubief@etu.univ-grenoble-alpes.fr', $subject, $message, 'From: '.$from);
    header('Location: ./apropos.html');
    exit();
?>