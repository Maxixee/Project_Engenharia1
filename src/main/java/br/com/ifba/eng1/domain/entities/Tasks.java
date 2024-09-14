package br.com.ifba.eng1.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "priority", nullable = false)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "inCharge_id", referencedColumnName = "Id", nullable = false)
    private Users inCharge;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
