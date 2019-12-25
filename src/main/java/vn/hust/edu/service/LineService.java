package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.Line;
import vn.hust.edu.repository.LineRepository;

@Service
public class LineService {

    @Autowired
    LineRepository lineRepository;

    Line findById(int id){
        return lineRepository.findById(id);
    }

    Line findByName(String name){
        return lineRepository.findByName(name);
    }

    Line save(Line line){
        return lineRepository.save(line);
    }
}
