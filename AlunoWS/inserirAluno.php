<?php
require_once("AlunoService.php");

$nome = $_POST["nome"];
$media = $_POST["media"];
$faltas = $_POST["faltas"];

    $alunoNovo = new StdClass();

        $alunoNovo->setNome = $nome;
        $alunoNovo->setMedia = $media;
        $alunoNovo->setFaltas = $faltas;

    $AlunoService = new AlunoService();

$AlunoService->inserir($alunoNovo);

header("Location: index.php");

?>