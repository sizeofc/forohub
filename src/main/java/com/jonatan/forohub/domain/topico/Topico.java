package com.jonatan.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(DatosRegistrotopico datos){
        this.titulo= datos.titulo();
        this.mensaje= datos.mensaje();
        this.fechaCreacion= LocalDateTime.now();
        this.autor= datos.autor();
        this.curso= datos.curso();
        this.status=true;

    }

    public Topico(){}

    public Long getId(){
        return this.id;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getMensaje(){
        return this.mensaje;
    }

    public String getAutor(){
        return this.autor;
    }

    public String getCurso(){
        return this.curso;
    }

    public LocalDateTime getFechaCreacion(){
        return this.fechaCreacion;
    }
}
