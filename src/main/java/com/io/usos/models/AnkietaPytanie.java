package com.io.usos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pytania")
public class AnkietaPytanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tresc;

    public AnkietaPytanie(String tresc){
        this.tresc = tresc;
    }

    @Override
    public String toString() {
        return tresc;
    }
}
