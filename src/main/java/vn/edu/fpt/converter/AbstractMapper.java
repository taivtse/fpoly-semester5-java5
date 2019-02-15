package vn.edu.fpt.converter;

public interface AbstractMapper<D, E> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
