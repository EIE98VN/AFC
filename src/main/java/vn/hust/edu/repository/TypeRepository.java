package vn.hust.edu.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import vn.hust.edu.model.Certificate;

@NoRepositoryBean
public interface TypeRepository<T extends Certificate> extends PagingAndSortingRepository<Certificate, Integer>, JpaSpecificationExecutor<Certificate> {

    @Query("select e from #{#entityName} as e where e.code = code")
    T findByCode(String code);

    @Query("select e from #{#entityName} as e where e.code = id")
    T findById(String id);

}
