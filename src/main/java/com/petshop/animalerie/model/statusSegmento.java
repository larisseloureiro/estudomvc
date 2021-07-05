package com.petshop.animalerie.model;

public enum statusSegmento {
	CACHORRO("Cachorro"), GATO("Gato"), PASSARO("Pássaro"), PEIXE("Peixe");

	private String nome;

	statusSegmento(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
