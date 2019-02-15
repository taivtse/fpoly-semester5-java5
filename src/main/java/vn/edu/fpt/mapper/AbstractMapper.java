package vn.edu.fpt.mapper;

public interface AbstractMapper<D, E> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
