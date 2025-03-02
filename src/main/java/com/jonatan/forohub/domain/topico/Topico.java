package com.jonatan.forohub.domain.topico;

import com.jonatan.forohub.domain.curso.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;


import java.time.LocalDateTime;

@Data
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
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false) // Clave foránea hacia Curso
    private Curso curso;

    public Topico(DatosRegistrotopico datos,Curso curso,String username){
        this.titulo= datos.titulo();
        this.mensaje= datos.mensaje();
        this.fechaCreacion= LocalDateTime.now();
        this.autor= username;
        this.curso= curso;
        this.status=true;

    }

    public Topico(){}

    public Topico(Topico newtopico) {
//        this.id=null;
        this.titulo= newtopico.getTitulo();
        this.mensaje= newtopico.getMensaje();
        this.fechaCreacion= LocalDateTime.now();
        this.autor= newtopico.getAutor();
        this.curso= newtopico.getCurso();
        System.out.println("curso ingresado de newTopico " + newtopico.getCurso().getNombre());
        this.status=true;
    }

    public Long getId(){
        return this.id;
    }

    public void setCurso(Curso curso){
        this.curso=curso;
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

    public Curso getCurso(){
        return this.curso;
    }

    public void setAutor(String username){
        this.autor= username;
    }

    public LocalDateTime getFechaCreacion(){
        return this.fechaCreacion;
    }

    public void modificar(@Valid DatosModificacionTopico datos) {
        if(datos.titulo()!=null&&!datos.titulo().isEmpty()){
            this.titulo= datos.titulo();
        }
        if(datos.mensaje()!=null&&!datos.mensaje().isEmpty()){
            this.mensaje= datos.mensaje();
        }

    }
}
