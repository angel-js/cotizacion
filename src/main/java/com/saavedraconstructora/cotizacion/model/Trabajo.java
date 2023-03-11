package com.saavedraconstructora.cotizacion.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trabajo")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "trabajo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @Column(name = "fecha_trabajo", columnDefinition = "DATETIME NOT NULL")
    private LocalDateTime fecha_trabajo;

    @PrePersist
    protected void onCreate() {
        fecha_trabajo = LocalDateTime.now();
    }
    @ManyToOne
    private Status status;

    public Trabajo() {
    }

    public Trabajo(Departamento departamento, Supervisor supervisor, Usuario usuario, List<Item> items, LocalDateTime fecha_trabajo, Status status) {
        this.departamento = departamento;
        this.supervisor = supervisor;
        this.usuario = usuario;
        this.items = items;
        this.fecha_trabajo = fecha_trabajo;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Métodos de conveniencia para manejar la relación bidireccional con Item
    public void addItem(Item item) {
        items.add(item);
        item.setTrabajo(this);
    }
    public void removeItem(Item item) {
        items.remove(item);
        item.setTrabajo(null);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getFecha_trabajo() {
        return fecha_trabajo;
    }

    public void setFecha_trabajo(LocalDateTime fecha_trabajo) {
        this.fecha_trabajo = fecha_trabajo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    /**
    @Override
    public String toString() {
        return "Trabajo{" +
                "id=" + id +
                ", supervisor=" + supervisor +
                ", usuario=" + usuario +
                ", fecha_trabajo=" + fecha_trabajo +
                ", status=" + status +
                '}';
    }*/
}
