package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.Line;

import java.util.List;

public interface LineRepository extends PagingAndSortingRepository<Line, Integer>, JpaSpecificationExecutor<Line> {

    Line findById(int id);

    Line findByName(String name);

    Line save(Line line);

    List<Line> findAll();
}
