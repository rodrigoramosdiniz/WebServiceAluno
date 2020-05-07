/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import localhost._8000.aluns.Aluno;

/**
 *
 * @author Rodrigo
 */
public class ControllerAluno {

    private final AlunoView view;
    private Aluno alunoAtual;
    private static final String EDITANDO = "EDITANDO";
    private static final String INICIAL = "INICIAL";
    private static final String OBJETO_SELECIONADO = "OBJETO_SELECIONADO";

    private String estado;
  
    public ControllerAluno(AlunoView view) {
        this.view = view;
        alunoAtual = null;
        this.estado = INICIAL;
        InicializacaoBotoes();
        aplicarEstado();
        inicializarTabela();
    }

    public void aplicarEstado() {
        if (estado.equals(ControllerAluno.INICIAL)) {

            view.getCampoNome().setEnabled(false);
            view.getCampoMedia().setEnabled(false);
            view.getCampoFalta().setEnabled(false);

            view.getBotaoAlterar().setEnabled(false);
            view.getBotaoRemover().setEnabled(false);
            view.getBotaoSalvar().setEnabled(false);
            view.getBotaoNovo().setEnabled(true);
        }

        if (estado.equals(ControllerAluno.EDITANDO)) {

            view.getCampoNome().setEnabled(true);
            view.getCampoMedia().setEnabled(true);
             view.getCampoFalta().setEnabled(true);

            view.getBotaoSalvar().setEnabled(true);
            view.getBotaoAlterar().setEnabled(false);
            view.getBotaoRemover().setEnabled(false);
            view.getBotaoNovo().setEnabled(false);
        }

        if (estado.equals(ControllerAluno.OBJETO_SELECIONADO)) {

            view.getCampoNome().setEnabled(false);
            view.getCampoMedia().setEnabled(false);
            view.getCampoFalta().setEnabled(true);
            
            view.getBotaoAlterar().setEnabled(true);
            view.getBotaoSalvar().setEnabled(false);
            view.getBotaoRemover().setEnabled(true);
            view.getBotaoNovo().setEnabled(true);
        }

    }

    
    
    private void inicializarTabela() {
        String[] colunas = {"ID", "Nome", "Media","Faltas"};

        List<Aluno> listaAlunos = ControllerAluno.listar();
        Object[][] data = {};
        DefaultTableModel tb = new DefaultTableModel(data, colunas);
        for (Aluno aluno : listaAlunos) {
            
            Object[] dadosRecebidos = {aluno.getId(), aluno.getNome(), aluno.getMedia(), aluno.getFaltas()};
            tb.addRow(dadosRecebidos);
        }
        
        
        view.getTabela().setModel(tb);

        this.estado = ControllerAluno.OBJETO_SELECIONADO;
        aplicarEstado();
    }

    private void InicializacaoBotoes() {

        view.getBotaoNovo().addActionListener((ActionEvent e) -> {

            alunoAtual = null;

            this.estado = ControllerAluno.EDITANDO;

            aplicarEstado();

        });

        view.getBotaoAlterar().addActionListener((ActionEvent e) -> {

            List<Aluno> listaAlunos = ControllerAluno.listar();

            int linhaSelecionada = view.getTabela().getSelectedRow();

            if (linhaSelecionada >= 0) {
                alunoAtual = listaAlunos.get(linhaSelecionada);

                view.getCampoNome().setText(alunoAtual.getNome());
                view.getCampoMedia().setText(String.valueOf(alunoAtual.getMedia()));
                view.getCampoFalta().setText(String.valueOf(alunoAtual.getFaltas()));

                this.estado = ControllerAluno.EDITANDO;
                aplicarEstado();
            }

        });

        view.getBotaoSalvar().addActionListener((ActionEvent e) -> {

            if (alunoAtual == null) {

                Aluno aluno = new Aluno();

                aluno.setNome(view.getCampoNome().getText());

                aluno.setMedia(Double.parseDouble(view.getCampoMedia().getText()));
                
                aluno.setFaltas(Integer.parseInt(view.getCampoFalta().getText()));

                ControllerAluno.inserir(aluno);
                this.estado = ControllerAluno.OBJETO_SELECIONADO;
                inicializarTabela();
                LimparCampos();

            } else {
                alunoAtual.setNome(view.getCampoNome().getText());
                
                alunoAtual.setMedia(Double.parseDouble(view.getCampoMedia().getText()));
                
                alunoAtual.setFaltas(Integer.parseInt(view.getCampoFalta().getText()));

                ControllerAluno.alterar(alunoAtual);
                this.estado = ControllerAluno.INICIAL;
                aplicarEstado();
                inicializarTabela();
                LimparCampos();
            }

        });

        
        
        view.getBotaoRemover().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = view.getTabela().getSelectedRow();
                if (linhaSelecionada >=0) {
                   
                    
                    ControllerAluno.remover(linhaSelecionada);
                    
                    inicializarTabela();
                }   ControllerAluno.this.estado = ControllerAluno.OBJETO_SELECIONADO;
                aplicarEstado();
            }
        });

    }

    public void LimparCampos() {

        view.getCampoNome().setText("");
        view.getCampoMedia().setText("");
        view.getCampoFalta().setText("");
    }

    private static void alterar(localhost._8000.aluns.Aluno aluno) {
        localhost._8000.aluns.ServiceDaoAlunoWsService service = new localhost._8000.aluns.ServiceDaoAlunoWsService();
        localhost._8000.aluns.ServiceDaoAlunoWs port = service.getServiceDaoAlunoWsPort();
        port.alterar(aluno);
    }

    private static void inserir(localhost._8000.aluns.Aluno aluno) {
        localhost._8000.aluns.ServiceDaoAlunoWsService service = new localhost._8000.aluns.ServiceDaoAlunoWsService();
        localhost._8000.aluns.ServiceDaoAlunoWs port = service.getServiceDaoAlunoWsPort();
        port.inserir(aluno);
    }

    private static java.util.List<localhost._8000.aluns.Aluno> listar() {
        localhost._8000.aluns.ServiceDaoAlunoWsService service = new localhost._8000.aluns.ServiceDaoAlunoWsService();
        localhost._8000.aluns.ServiceDaoAlunoWs port = service.getServiceDaoAlunoWsPort();
        return port.listar();
    }

    private static void remover(int id) {
        localhost._8000.aluns.ServiceDaoAlunoWsService service = new localhost._8000.aluns.ServiceDaoAlunoWsService();
        localhost._8000.aluns.ServiceDaoAlunoWs port = service.getServiceDaoAlunoWsPort();
        port.remover(id);
    }

    
    

}
