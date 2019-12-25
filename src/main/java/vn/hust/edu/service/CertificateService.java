package vn.hust.edu.service;

public interface CertificateService<T>{

    T findById(String id);

    T findByCode(String code);

    T save(T t);
}
