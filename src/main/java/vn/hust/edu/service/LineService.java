package vn.hust.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hust.edu.model.Line;
import vn.hust.edu.repository.LineRepository;

import java.util.List;

@Service
public class LineService {

    @Autowired
    LineRepository lineRepository;

    /**
     * Find line by id
     *
     * @param id id of line
     * @return instance of Line, null if not found
     */
    public Line findById(int id){
        return lineRepository.findById(id);
    }

    /**
     * Find line by name
     *
     * @param name name of line
     * @return instance of Line, null if not found
     */
    public Line findByName(String name){
        return lineRepository.findByName(name);
    }

    /**
     * Save Line object into database
     *
     * @param line
     * @return instance of saved Line
     */
    public Line save(Line line){
        return lineRepository.save(line);
    }

    public List<Line> findAll(){
        return lineRepository.findAll();
    }
}
