package vn.hust.edu.service;

public interface CertificateService<T>{

    /**
     * Find certificate by id
     *
     * @param id id of certificate
     * @return instance of Certificate, null if not found
     */
    T findById(String id);

    /**
     * Find certificate by code
     *
     * @param code code of certificate
     * @return instance of Certificate, null if not found
     */
    T findByCode(String code);

    /**
     * Save a Certificate object into database
     *
     * @param t Certificate object
     * @return saved Certificate object
     */
    T save(T t);
}
