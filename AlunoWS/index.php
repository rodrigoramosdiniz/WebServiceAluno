<?php 
  require_once("AlunoService.php");
  session_start();
  $serviceAluno = new AlunoService();
  
  $novoAluno = $serviceAluno->listar();
 
 
?>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <title>Client Soap Aluno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  </head>

  
  <body>

  <div class="jumbotron">
    
  <div class="panel panel-default">
    <div class="panel-heading">
        <h3>Cadastro de Alunos</h3>
    </div>
    </div>
    <form action="inserirAluno.php" method="post">
    <div class="form-group">
                <label for="nome">Nome</label>
                <input class="form-control" type="text" id="nome" name="nome" required>
            </div>
            
            <div class="form-group">
                <label for="media">Media</label>
                <input class="form-control" type="text" id="media" name="media" required>
            </div>

            <div class="form-group">
                <label for="quantidade">Faltas</label>
                <input class="form-control" type="text" id="faltas" name="faltas" required>
            </div>
            <button type="submit" class="btn btn-primary">Salvar Aluno</button>
        </form>
         
    

    <table class="table table-bordered">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Nome</th>
                  <th>Media</th>
                  <th>Faltas</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <?php foreach($novoAluno as $aluno): ?>
                  <tr>
                    <td><?= $aluno->id; ?></td>
                    <td><?= $aluno->nome; ?></td>
                    <td><?= $aluno->media; ?></td>
                    <td><?= $aluno->faltas; ?></td>
                    <td>
                     
                      <a href="edit.php?id=<?= $aluno->id; ?>" class="btn btn-primary btn-sm">Editar</a>
                      <a href="remover.php?id=<?= $aluno->id; ?>" class="btn btn-danger btn-sm">Excluir</a>
                        
                    </td>
                  </tr>
                <?php endforeach; ?>
              </tbody>
            </table>
    <!-- Fim Home -->
    
  </body>
   
</html>