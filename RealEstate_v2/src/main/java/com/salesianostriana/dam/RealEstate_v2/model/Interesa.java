package com.salesianostriana.dam.RealEstate_v2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)

public class Interesa {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @JsonIgnore
    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name="vivienda_id", foreignKey = @ForeignKey(name = "FK_INTERESA_VIVIENDA"))
    private Vivienda vivienda;

    @JsonIgnore
    @ManyToOne
    @MapsId("interesado_id")
    @JoinColumn(name="interesado_id", foreignKey = @ForeignKey(name = "FK_INTERESA_INTERESADO"))
    private Usuario interesado;


    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();
    private String mensaje;

}
