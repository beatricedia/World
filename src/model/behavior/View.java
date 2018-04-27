package model.behavior;

import java.util.Optional;
import java.util.Set;

import model.element.ElementType;
import view.*;

public interface View {

    public Optional<Direction> find(WorldView world, Vector position, ElementType element);


    public Optional<Direction> findRandom(WorldView world, Vector position, ElementType element);


    public Optional<Direction> find(WorldView world, Vector position, Set<ElementType> element);


    public Set<Direction> findAll(WorldView world, Vector position, ElementType element);


}