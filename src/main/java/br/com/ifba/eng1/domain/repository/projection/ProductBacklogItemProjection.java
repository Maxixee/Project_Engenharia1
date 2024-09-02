package br.com.ifba.eng1.domain.repository.projection;

import java.util.Date;

public interface ProductBacklogItemProjection {

    Long getId();
    String getDescription();
    Date getStartDate();
    Date getEndDate();

}
