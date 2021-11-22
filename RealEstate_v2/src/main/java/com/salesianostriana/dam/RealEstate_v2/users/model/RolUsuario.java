package com.salesianostriana.dam.RealEstate_v2.users.model;

public enum RolUsuario {

    GESTOR("Gestor"), ADMIN("Administrador"), PROPIETARIO("Propietario");

    private final String texto;

    /**
     * Constructor para poder mostrar el texto de cada Enum en el formulario
     * de reserva.
     */

        private RolUsuario(String texto) {
        this.texto = texto;
    }

        public String getTexto() {
        return texto;
    }
}
