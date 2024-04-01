package vn.codegym.repository;

import java.util.List;

public interface IStudentRepository<E> {
    List<E> findAll();
    void save(E e);
    void update(int id,E e);
    void remove(int id, E e);
    E findById(int id);
}
