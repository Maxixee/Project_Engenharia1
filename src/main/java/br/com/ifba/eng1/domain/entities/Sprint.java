package br.com.ifba.eng1.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sprint")
public class Sprint {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "id_project", nullable = false)
        private Project idProject;

        @NotBlank(message = "Name is mandatory")
        @Size(max = 255, message = "Name should not exceed 255 characters")
        private String name;

        @NotBlank(message = "Start Date is mandatory")
        private String startDate;

        @NotBlank(message = "End Date is mandatory")
        private String endDate;

        private boolean status;

    }