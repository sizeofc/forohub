package com.jonatan.forohub.domain.curso;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Data
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
//
//    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Topico> topicos; // Relación inversa (opcional)

    public Curso(DatosRegistroCurso datos){
        this.nombre=datos.nombre();
        this.categoria=datos.categoria();
    }

    public Curso(){}


    public Long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public Categoria getCategoria(){
        return categoria;
    }
}
