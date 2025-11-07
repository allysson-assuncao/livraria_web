package org.example.livraria_web.controller;

import org.example.livraria_web.model.Autor;
import org.example.livraria_web.model.Livro;
import org.example.livraria_web.repository.AutorRepository;
import org.example.livraria_web.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LivrariaController {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    @Autowired
    public LivrariaController(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage(Model model) {
        // Envia as listas para a página HTML
        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaLivros", livroRepository.findAll());

        // Envia objetos vazios para os formulários de cadastro
        model.addAttribute("autorForm", new Autor());
        model.addAttribute("livroForm", new Livro());

        // Retorna o nome do arquivo HTML
        return "index";
    }

    // --- CRUD AUTOR ---

    /**
     * Salva (cria ou atualiza) um autor.
     *
     * @ModelAttribute pega o objeto "autorForm" que veio do formulário.
     */
    @PostMapping("/autores/salvar")
    public String salvarAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/"; // Redireciona para a página principal
    }

    /**
     * Prepara a página para edição de um autor.
     * Carrega o autor pelo ID e o coloca no formulário.
     */
    @GetMapping("/autores/editar/{id}")
    public String editarAutor(@PathVariable Integer id, Model model) {
        // Busca o autor ou retorna erro
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID do Autor inválido:" + id));

        // Seta o autor no formulário
        model.addAttribute("autorForm", autor);

        // Re-envia as listas para a página
        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaLivros", livroRepository.findAll());

        // Envia um formulário de livro vazio
        model.addAttribute("livroForm", new Livro());

        return "index";
    }

    @GetMapping("/autores/excluir/{id}")
    public String excluirAutor(@PathVariable Integer id) {
        autorRepository.deleteById(id);
        return "redirect:/";
    }

    // --- CRUD LIVRO ---

    @PostMapping("/livros/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/";
    }

    @GetMapping("/livros/editar/{id}")
    public String editarLivro(@PathVariable Integer id, Model model) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID do Livro inválido:" + id));

        model.addAttribute("livroForm", livro);

        model.addAttribute("listaAutores", autorRepository.findAll());
        model.addAttribute("listaLivros", livroRepository.findAll());

        model.addAttribute("autorForm", new Autor());

        return "index";
    }

    @GetMapping("/livros/excluir/{id}")
    public String excluirLivro(@PathVariable Integer id) {
        livroRepository.deleteById(id);
        return "redirect:/";
    }

}
