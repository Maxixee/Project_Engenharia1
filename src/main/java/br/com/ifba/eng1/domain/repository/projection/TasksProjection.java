package br.com.ifba.eng1.domain.repository.projection;

import br.com.ifba.eng1.domain.entities.Users;


public interface TasksProjection {

    Long getId();
    String getDescription();
    String getPriority();
    Users getInCharge();
}
