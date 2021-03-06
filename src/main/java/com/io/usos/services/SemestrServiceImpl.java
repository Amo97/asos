package com.io.usos.services;

import com.io.usos.exceptions.ObjectNotFoundException;
import com.io.usos.models.*;
import com.io.usos.repositories.OcenaRepository;
import com.io.usos.repositories.PrzedmiotRepository;
import com.io.usos.repositories.RokRepository;
import com.io.usos.repositories.SemestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class SemestrServiceImpl implements SemestrService {

    @Autowired
    OcenaRepository ocenaRepository;

    @Autowired
    PrzedmiotRepository przedmiotRepository;

    @Autowired
    RokRepository rokRepository;

    @Autowired
    SemestRepository semestRepository;

    @Override
    public Ocena getOcena(int id) {
        Optional<Ocena> optionalOcena = ocenaRepository.findById(id);
        Ocena ocena = optionalOcena.orElseThrow(() -> new ObjectNotFoundException("ocena",id));
        return ocena;
    }

    @Override
    public List<Ocena> getAllOcena() {
        return ocenaRepository.findAll();
    }

    @Override
    public void deleteOcena(int id) {
        if (ocenaRepository.existsById(id) == true) {
            ocenaRepository.deleteById(id);
        }
    }

    @Override
    public void saveOcena(Ocena ocena) {
        ocena.setDataWstawienia(Instant.now());
        ocenaRepository.saveAndFlush(ocena);
    }

    @Override
    public float getSredniaOcen(int id) {
        List<Ocena> o = ocenaRepository.findAllByStudent_Id(id);
        float tmp = 0;
        for(int i=0;i<o.size();i++){
            tmp+=o.get(i).getOcena();
        }
        return tmp/o.size();
    }

    @Override
    public void saveSemestr(Semestr semestr) {
        semestRepository.save(semestr);
    }

    @Override
    public Przedmiot getPrzedmiot(int id) {
        Optional<Przedmiot> optionalPrzedmiot = przedmiotRepository.findById(id);
        Przedmiot przedmiot = optionalPrzedmiot.orElseThrow(() -> new ObjectNotFoundException("przedmiot",id));
        return przedmiot;
    }

    @Override
    public List<Przedmiot> getAllPrzedmiot() {
        return przedmiotRepository.findAll();
    }

    @Override
    public List<Student> getStudenciPrzedmiotu(int idPrzedmiotu) {
        Przedmiot przedmiot = przedmiotRepository.findById(idPrzedmiotu).orElse(new Przedmiot());
        return przedmiot.getStudenci();
    }

    @Override
    public List<Ocena> getOcenyStudentówPrzedmiotu(int idPrzedmiotu) {
        return ocenaRepository.findAllByPrzedmiotIdOrderByStudentId(idPrzedmiotu);
    }

    @Override
    public void deletePrzedmiot(int id) {
        if (przedmiotRepository.existsById(id) == true) {
            przedmiotRepository.deleteById(id);
        }
    }

    @Override
    public void savePrzedmiot(Przedmiot przedmiot) {
        przedmiotRepository.save(przedmiot);
    }

    @Override
    public Rok getRok(int id) {
        Optional<Rok> optionalRok = rokRepository.findById(id);
        Rok rok = optionalRok.orElseThrow(() -> new ObjectNotFoundException("rok",id));
        return rok;
    }

    @Override
    public Rok getRokByNazwaKierunku(String nazwaKierunku) {
        return rokRepository.findAllByNazwaKierunku(nazwaKierunku);
    }

    @Override
    public List<Rok> getAllRok() {
        return rokRepository.findAll();
    }

    @Override
    public void deleteRok(int id) {
        if (rokRepository.existsById(id) == true) {
            rokRepository.deleteById(id);
        }
    }

    @Override
    public void saveRok(Rok rok) {
        rokRepository.save(rok);
    }
}
