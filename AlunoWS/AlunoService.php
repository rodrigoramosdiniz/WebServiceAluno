
<?php

class AlunoService{



   private $client;

    public function __construct()
    {
       $this->client = new SoapClient("http://localhost:8000/aluns?WSDL");
    }

    public function inserir(StdClass $aluno): void
    {
        $parametros = [
            "aluno" => [
                "id" => 0,
                "nome" => $aluno->setNome,
                "media" => $aluno->SetMedia,
                "faltas" => $aluno->setFaltas
            ]
        ];
        
        try {
             $this->client->__soapCall("inserir", [$parametros]);
        } catch(Exception $exception) {
            echo $exception->getMessage();
            exit;
        }
    }

    public function listar(): array
    {
        try {
            $result = $this->client->__soapCall("listar", []);
        } catch(Exception $ex) {
            echo $ex->getMessage();exit;
        }
        return $result->return;
    }

  public function remover(int $id):void{
      $parametros = [
        "id" => $id
      ];
  }
}
    ?>

