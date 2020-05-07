<?php


require_once("AlunoService.php");

$id = intval($_GET["id"]);

$service = new AlunoService();
$service->remover($id);


header("Location: index.php");

?>