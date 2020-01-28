package com.io.usos.config;

import com.io.usos.models.*;
import com.io.usos.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoriesInitializer {
    @Autowired
    AnkietaRepository ankietaRepository;
    @Autowired
    OcenaRepository ocenaRepository;
    @Autowired
    PracownikRepository pracownikRepository;
    @Autowired
    PrzedmiotRepository przedmiotRepository;
    @Autowired
    RokRepository rokRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StypendiumRepository stypendiumRepository;

    @Bean
    InitializingBean init(){
        return () -> {
            if(ankietaRepository.findAll().isEmpty()){
                Student student1 = new Student("Kacper", "Jedrzejewski", "97021411111");
                Student student2 = new Student("Piotr", "Jadczuk", "97072522222");
                Student student3 = new Student("Kinga", "Markowicz", "98112133333");

                studentRepository.save(student1);
                studentRepository.save(student2);
                studentRepository.save(student3);

                Przedmiot przedmiot1 = new Przedmiot("Inzynieria oprogramowania");
                Przedmiot przedmiot2 = new Przedmiot("Platformy programowania");

                przedmiotRepository.save(przedmiot1);
                przedmiotRepository.save(przedmiot2);

                Stypendium stypendium1 = new Stypendium(student1, 4.5f, "");
                Stypendium stypendium2 = new Stypendium(student2, 4.45f, "Dej piniondz");

                stypendiumRepository.save(stypendium1);
                stypendiumRepository.save(stypendium2);

                Rok rok1 = new Rok("Informatyka", Instant.now(), ZonedDateTime.now().plusYears(3).plusMonths(6).toInstant());
                Rok rok2 = new Rok("Matematyka", ZonedDateTime.now().minusYears(1).toInstant(), ZonedDateTime.now().plusYears(2).toInstant());

                rokRepository.save(rok1);
                rokRepository.save(rok2);

                Pracownik pracownik1 = new Pracownik("Jarosław", "Skaruz", false);
                Pracownik pracownik2 = new Pracownik("Grzegorz", "Terlikowski", false);
                Pracownik pracownik3 = new Pracownik("Beata", "Ciok", true);

                pracownikRepository.save(pracownik1);
                pracownikRepository.save(pracownik2);
                pracownikRepository.save(pracownik3);

                Ocena ocena1 = new Ocena(4.5f, student1, przedmiot2);
                Ocena ocena2 = new Ocena(5f, student1, przedmiot1);
                Ocena ocena3 = new Ocena(5f, student2, przedmiot1);
                Ocena ocena4 = new Ocena(5f, student3, przedmiot1);

                ocenaRepository.save(ocena1);
                ocenaRepository.save(ocena2);
                ocenaRepository.save(ocena3);
                ocenaRepository.save(ocena4);
            }
        };
    }
}