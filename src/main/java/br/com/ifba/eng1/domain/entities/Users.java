package br.com.ifba.eng1.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "lastName", nullable = false )
    private String lastName;

    @Column( name = "email", nullable = false )
    private String email;

    @Column( name = "password", nullable = false )
    private String password;

    @Column( name = "birth", nullable = false )
    private LocalDate birthDate;

    @Column( name = "cellphoneNumber", nullable = false )
    private String cellphoneNumber;
    
    @Column(name = "passwordResetToken")
    private String passwordResetToken;
    
    @ManyToMany(mappedBy="members")
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "manager")
    private List<Project> managedProjects = new ArrayList<>();
}
