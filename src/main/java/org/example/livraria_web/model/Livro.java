package org.example.livraria_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "livro", schema = "allysson_livraria")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(unique = true)
    private String isbn;

    private Integer anoPublicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    public String getNomeAutor() {
        return (autor != null) ? autor.getNome() : "Sem Autor";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id != null && id.equals(livro.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
