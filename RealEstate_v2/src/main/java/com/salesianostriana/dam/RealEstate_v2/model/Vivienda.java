package com.salesianostriana.dam.RealEstate_v2.model;

import com.salesianostriana.dam.RealEstate_v2.users.model.Usuario;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo, descripcion, avatar, latlng;
    private String direccion, poblacion, provincia;

    @Column(name = "codigoPostal")
    private String codigoPostal;

    @Column(name = "numHabitaciones")
    private int numHabitaciones;

    @Column(name = "numBanios")
    private int numBanios;
    private double precio;
    private double metrosCuadrados;

    @Column(name = "tipoVivienda")
    @Enumerated(EnumType.STRING)
    private Tipo tipoVivienda;

    @Column(name = "tienePiscina")
    private boolean tienePiscina;

    @Column(name = "tieneAscensor")
    private boolean tieneAscensor;

    @Column(name = "tieneGaraje")
    private boolean tieneGaraje;

    @ManyToOne
    @JoinColumn(name = "inmobiliaria_id", foreignKey = @ForeignKey(name = "FK_VIVIENDA_INMOBILIARIA"), nullable = true)
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    @JoinColumn(name = "propietario_id", foreignKey = @ForeignKey(name = "FK_VIVIENDA_PROPIETARIO"))
    private Usuario propietario;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
    private List<Interesa> interesas = new ArrayList<>();


    public Vivienda(String titulo, String avatar, String tipo, double precio, String ubicacion, double metrosCuadrados, int numBanios, int numHabitaciones, boolean tieneAscensor, boolean tieneGaraje, boolean tienePiscina, String propietario, String inmobiliaria) {
    }

    public Vivienda(String titulo, String descripcion, String avatar, double precio, int interesas) {
    }

    public Vivienda(String titulo, String avatar, String tipo, double precio, String ubicacion, double metrosCuadrados, int numBanios, int numHabitaciones, boolean tieneAscensor, boolean tieneGaraje, boolean tienePiscina, UUID propietario, String inmobiliaria) {
    }


    public Vivienda(String titulo, String avatar, String tipo, double precio, String ubicacion, double metrosCuadrados, int numBanios, int numHabitaciones, boolean tieneAscensor, boolean tieneGaraje, boolean tienePiscina) {
    }




    ///// HELPERS /////

    public void addToPropietario(Usuario p) {
        this.propietario = p;
        p.getViviendas().add(this);
    }

    public void removeFromPropietario(Usuario p) {
        p.getViviendas().remove(this);
        this.propietario = null;
    }

    public void addToInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getViviendas().add(this);
    }

    public void removeFromInmobiliaria(Inmobiliaria i) {
        i.getViviendas().remove(this);
        this.inmobiliaria = null;
    }

}

